package com.vinacredit.activity.SignIn;

import com.vinacredit.Resource.*;
import com.vinacredit.activity.R;
import com.vinacredit.activity.InformationAccount.InformationAccount_Activity;
import com.vinacredit.activity.SignIn.WrongPass.WrongPass_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignIn_Activity extends Activity{

	private EditText 	edtUsername, edtPassword;
	private Button 		btnSignIn, btnWrongPass;
	private TextView	txtTitleBar;
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
		
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),InformationAccount_Activity.class);
				startActivity(intent);
			}
		});
		
		btnWrongPass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),WrongPass_Activity.class);
				startActivity(i);
			}
		});
	}
	private void translate() {
		// TODO Auto-generated method stub
		edtUsername.setHint(MACROS.SIGNIN_EMAIL_TXT);
		edtPassword.setHint(MACROS.SIGNIN_PASS_TXT);
		btnSignIn.setText(MACROS.SIGNIN_BTN);
		btnWrongPass.setText(MACROS.SIGNIN_FORGOT_BTN);
		txtTitleBar.setText(MACROS.SIGNIN_LBL);
	}

}
