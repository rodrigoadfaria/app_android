package com.vinacredit.activity.SignIn;


import com.vinacredit.activity.R;
import com.vinacredit.activity.InformationAccount.InformationAccount_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignIn_Activity extends Activity{

	private EditText edtUsername, edtPassword;
	private Button btnSignIn, btnWrongPass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		edtUsername = (EditText)findViewById(R.id.edtUsername);
		edtPassword = (EditText)findViewById(R.id.edtPassword);
		btnSignIn = (Button)findViewById(R.id.btnSignIn);
		btnWrongPass = (Button)findViewById(R.id.btnWrongPass);
		
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
				
			}
		});
	}

}
