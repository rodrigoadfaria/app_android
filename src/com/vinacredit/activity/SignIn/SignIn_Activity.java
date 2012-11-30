package com.vinacredit.activity.SignIn;


import com.vinacredit.Resource.*;
import com.vinacredit.activity.R;
import com.vinacredit.activity.InformationAccount.InformationAccount_Activity;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.activity.SignIn.WrongPass.WrongPass_Activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn_Activity extends Activity{

	// url to make request
	private static String url = "https://sites.google.com/site/vinacreditdemo/home/sqlite.json";
	
	private JSONDAO jsonDao;
		
	private EditText 	edtUsername, edtPassword;
	private Button 		btnSignIn, btnWrongPass;
	private TextView	txtTitleBar;
	private MySQLiteHelper dbaccount;
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
		btnSignIn 		= (Button)findViewById(R.id.btnSignIn);
		btnWrongPass 	= (Button)findViewById(R.id.btnWrongPass);
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		translate();		
		
		dbaccount 	= new MySQLiteHelper(this);
		jsonDao		= new JSONDAO();
	}
	private void translate() {
		// TODO Auto-generated method stub
		edtUsername.setHint(MACROS.SIGNIN_EMAIL_TXT);
		edtPassword.setHint(MACROS.SIGNIN_PASS_TXT);
		btnSignIn.setText(MACROS.SIGNIN_BTN);
		btnWrongPass.setText(MACROS.SIGNIN_FORGOT_BTN);
		txtTitleBar.setText(MACROS.SIGNIN_LBL);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void btnSignIn(View view){
		String email = edtUsername.getText().toString();
		String pass  = edtPassword.getText().toString();
		/*----set flag -----*/
		if(MACROS.TEST_SIGNIN_BL){
		    
			if(jsonDao.login(url, email, pass)) {
				if(dbaccount.getAccountCount(email)) {
					Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
					Intent intent_Sale = new Intent(getApplicationContext(),Sale_Activity.class);
					intent_Sale.putExtra("EMAIL", email);
					startActivity(intent_Sale);
					return;
				} else {
					Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(getApplicationContext(),InformationAccount_Activity.class);
					intent.putExtra("EMAIL", email);
					intent.putExtra("PASS", pass);
					startActivity(intent);
					return;
				}
			} else
				Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_LONG).show();
		}
		else {
		    Intent intent = new Intent(getApplicationContext(),InformationAccount_Activity.class);
		    intent.putExtra("EMAIL", email);
		    intent.putExtra("PASS", pass);
		    startActivity(intent);
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

}
