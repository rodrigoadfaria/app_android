package com.vinacredit.activity.SignIn;


import com.vinacredit.Resource.*;
import com.vinacredit.activity.R;
import com.vinacredit.activity.InformationAccount.InformationAccount_Activity;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.activity.SignIn.WrongPass.WrongPass_Activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn_Activity extends Activity{

	// url to make request
	private static String url = "https://sites.google.com/site/vinacreditdemo/home/sqlite.json";
	
	private JSONDAO jsonDao;
		
	private EditText 	edtUsername, edtPassword;
	private MySQLiteHelper dbaccount;
	ProgressDialog dialog = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		edtUsername 	= (EditText)findViewById(R.id.edtUsername);
		edtPassword 	= (EditText)findViewById(R.id.edtPassword);	
		
		dbaccount 	= new MySQLiteHelper(this);
		jsonDao		= new JSONDAO();
	}
	
	/**
	 * 
	 * @param view
	 */
	public void btnSignIn(View view){
		dialog = ProgressDialog.show(SignIn_Activity.this, "", "Log in...", true);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				login();
			}
		}).start();
	}
	
	public void login(){
		String email = edtUsername.getText().toString();
		String pass  = edtPassword.getText().toString();

			
		/*----set flag -----*/
		if(MACROS.TEST_SIGNIN_BL){		
			if(email.equals("")){
				dialog.dismiss();
				Toast.makeText(getApplicationContext(), "Enter email,please!", Toast.LENGTH_SHORT).show();				
				return;
			} else if(pass.equals("")){
				dialog.dismiss();
				Toast.makeText(getApplicationContext(), "Enter pass,please!", Toast.LENGTH_SHORT).show();				
				return;
			}
			if(jsonDao.login(url, email, pass)) {
				if(dbaccount.getAccountCount(email)) {
//					Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
					Intent intent_Sale = new Intent(getApplicationContext(),Sale_Activity.class);
					intent_Sale.putExtra("EMAIL", email);					
					startActivity(intent_Sale);
					dialog.dismiss();
					finish();
				} else {
//					Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(getApplicationContext(),InformationAccount_Activity.class);
					intent.putExtra("EMAIL", email);
					intent.putExtra("PASS", pass);					
					startActivity(intent);
					dialog.dismiss();
					finish();
				}
			} else {
				dialog.dismiss();
				Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_LONG).show();				
			}
		}
		else {
		    Intent intent = new Intent(getApplicationContext(),InformationAccount_Activity.class);
		    intent.putExtra("EMAIL", email);
		    intent.putExtra("PASS", pass);
		    dialog.dismiss();
		    startActivity(intent);
		    finish();
		}
	}
	
	/**
	 * 
	 * @param view
	 */
	public void btnWrongPass(View view){
		Intent i = new Intent(getApplicationContext(),WrongPass_Activity.class);
		startActivity(i);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
	
	
}
