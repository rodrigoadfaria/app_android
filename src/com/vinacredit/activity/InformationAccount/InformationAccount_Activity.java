package com.vinacredit.activity.InformationAccount;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.Resource.*;

import con.vinacredit.DTO.Account;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;

public class InformationAccount_Activity extends Activity {

	private static final int CAMERA_REQUEST = 8888;
	
	private EditText 	edtFirstname, edtLastname, edtCompany, edtAddress, edtEmail
						, edtOldPass, edtNewpass, edtConfirmPass;
	private ImageButton imgUsername;
	ProgressDialog dialog = null;
	
	private MySQLiteHelper dbSqlite;
	private Account		account;
	
	private Bitmap 		photo;
	String oldPass = "";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_account);
        initialize();
    }
	private void initialize() {
		// TODO Auto-generated method stub
		edtFirstname   	= (EditText)findViewById(R.id.edtFirstname);
		edtLastname    	= (EditText)findViewById(R.id.edtLastname);
		edtCompany     	= (EditText)findViewById(R.id.edtCompany);
		edtAddress		= (EditText)findViewById(R.id.edtAddress);
		edtEmail 	   	= (EditText)findViewById(R.id.edtEmail);
		edtOldPass     	= (EditText)findViewById(R.id.edtOldPass);
		edtNewpass     	= (EditText)findViewById(R.id.edtNewPass);
		edtConfirmPass 	= (EditText)findViewById(R.id.edtConfirmPass);
		imgUsername		= (ImageButton)findViewById(R.id.imgUsername);

		dbSqlite = new MySQLiteHelper(this);
		account  = new Account();
		
		    Bundle extras = getIntent().getExtras();
		    edtEmail.setText(extras.getString("EMAIL"));
		    oldPass = extras.getString("PASS");
	}

	public void btnContinue(View view){
		dialog = ProgressDialog.show(InformationAccount_Activity.this, "", "Checking...", true);
		new Thread(new Runnable() {			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				checking();
			}
		}).start();
	}
	
	public void checking(){
		
		Intent i = new Intent(getApplicationContext(),Sale_Activity.class);		
		i.putExtra("EMAIL", edtEmail.getText().toString());
	    if(MACROS.TEST_DATABASE) {
	    	if(isCheck()) {
	    		account.setEmail(edtEmail.getText().toString());
				account.setPass(edtConfirmPass.getText().toString());
				account.setFirstName(edtFirstname.getText().toString());
				account.setLastName(edtLastname.getText().toString());
				account.setCompanyName(edtCompany.getText().toString());
				account.setAddress(edtAddress.getText().toString());
				
				/*convert bitemap to byte[] */	    
			    account.setImageAcc(Library.getBytesFromBitmap(photo));
			    
			    dbSqlite.AddAccount(account);
			    dialog.dismiss();
			    startActivity(i);			    
	    		finish();
	    	} else {
	    		runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						dialog.dismiss();
			    		Toast.makeText(getApplicationContext(), "Enter full infor,Please", Toast.LENGTH_SHORT).show();
					}
				});
	    		
	    	}	    			    	
	    } else {
		    dialog.dismiss();
		    startActivity(i);	    
			finish();
	    }
	}
	
	public void takePhoto(View view){
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAMERA_REQUEST);
	}
	
	public boolean isCheck(){
		if(edtFirstname.getText().toString() != null && edtLastname.getText().toString() != null &&
				edtCompany.getText().toString() != null && edtAddress.getText().toString() != null &&
				edtNewpass.getText().toString().length() >= 8 && 
				edtConfirmPass.getText().toString().equals(edtNewpass.getText().toString()) &&
				edtOldPass.getText().toString().equals(oldPass) &
				imgUsername != null)
			return true;
		return false;
	}
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_REQUEST) {
            photo = (Bitmap) data.getExtras().get("data");
            imgUsername.setImageBitmap(photo);
		}
	}
	
	@Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
