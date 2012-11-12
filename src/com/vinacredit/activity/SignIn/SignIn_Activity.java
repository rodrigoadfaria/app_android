package com.vinacredit.activity.SignIn;


import java.io.IOException;
import java.sql.SQLException;

import com.vinacredit.Resource.*;
import com.vinacredit.activity.R;
import com.vinacredit.activity.InformationAccount.InformationAccount_Activity;
import com.vinacredit.activity.SignIn.WrongPass.WrongPass_Activity;
import com.vinacredit.database.MySQLiteHelper;

import con.vinacredit.DTO.Account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn_Activity extends Activity{

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
		dbaccount = new MySQLiteHelper(this);
		final Account account = new Account();
		account.setEmail("hung");
		account.setPass("123");
		dbaccount.AddAccount(account);
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
				if(dbaccount.Login(edtUsername.getText().toString(), edtPassword.getText().toString())){
				Intent intent = new Intent(getApplicationContext(),InformationAccount_Activity.class);
				startActivity(intent);
					Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(), "Login do not successful", Toast.LENGTH_LONG).show();
				}
				
				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(SignIn_Activity.this,e.getMessage(), Toast.LENGTH_LONG).show();
				}
				dbaccount.close();
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
