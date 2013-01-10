package com.vinacredit.activity.InformationAccount;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.Resource.*;

import con.vinacredit.DTO.Account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

public class InformationAccount_Activity extends Activity {

	private static final int CAMERA_REQUEST = 8888;
	
	private EditText 	edtFirstname, edtLastname, edtCompany, edtAddress, edtEmail
						, edtOldPass, edtNewpass, edtConfirmPass;
	private Button 		btnContinue;
	private ImageButton imgUsername;
	private TextView	txtTitleBar, txtEmail, txtOldPass, txtNewPass, txtConfirmPass;
	
	private MySQLiteHelper dbSqlite;
	private Account		account;
	
	private Bitmap 		photo;
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
		btnContinue    	= (Button)findViewById(R.id.btnContinue);
		imgUsername		= (ImageButton)findViewById(R.id.imgUsername);
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		txtEmail		= (TextView)findViewById(R.id.txtEmail);
		txtOldPass		= (TextView)findViewById(R.id.txtOldPass);
		txtNewPass		= (TextView)findViewById(R.id.txtNewPass);
		txtConfirmPass	= (TextView)findViewById(R.id.txtConfirmPass);
		
//		translate();
		dbSqlite = new MySQLiteHelper(this);
		account  = new Account();
		
		    Bundle extras = getIntent().getExtras();
		    edtEmail.setText(extras.getString("EMAIL"));
	}
	private void translate() {
		// TODO Auto-generated method stub
		edtFirstname.setHint(MACROS.INFOR_FIRSTNAME_TXT);
		edtLastname.setHint(MACROS.INFOR_LASTNAME_TXT);
		edtCompany.setHint(MACROS.INFOR_COMPANY_TXT);
		edtAddress.setHint(MACROS.INFOR_ADDRESS_TXT);
		edtOldPass.setHint(MACROS.INFOR_OLDPASS_TXT);
		edtNewpass.setHint(MACROS.INFOR_NEWPASS_TXT);
		edtConfirmPass.setHint(MACROS.INFOR_CONFIRMPASS_TXT);
		btnContinue.setText(MACROS.INFOR_CONTINUE_BTN);
		
		txtEmail.setText(MACROS.INFOR_EMAIL_LBL);
		txtOldPass.setText(MACROS.INFOR_OLDPASS_LBL);
		txtNewPass.setText(MACROS.INFOR_NEWPASS_LBL);
		txtConfirmPass.setText(MACROS.INFOR_CONFIRMPASS_LBL);
		txtTitleBar.setText(MACROS.INFOR_LBL);
	}

	public void btnContinue(View view){
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
			    startActivity(i);
	    		finish();
	    	} else
	    		Toast.makeText(getApplicationContext(), "Enter full infor,Please", Toast.LENGTH_SHORT).show();	    	
	    }    	
	    startActivity(i);
		finish();
	}
	
	public void takePhoto(View view){
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAMERA_REQUEST);
	}
	
	public boolean isCheck(){
		if(edtFirstname.getText().toString() != null && edtLastname.getText().toString() != null &&
				edtCompany.getText().toString() != null && edtAddress.getText().toString() != null &&
				edtNewpass.getText().toString().length() >= 8 && edtConfirmPass.getText().toString().equals(edtNewpass.getText().toString()))
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
//    	translate();
    }
}
