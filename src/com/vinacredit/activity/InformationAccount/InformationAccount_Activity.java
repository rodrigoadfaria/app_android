package com.vinacredit.activity.InformationAccount;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.Account_Activity;
import com.vinacredit.activity.Sale.Sale_Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.app.Activity;
import android.content.Intent;

public class InformationAccount_Activity extends Activity {

	private EditText edtFirstname, edtLastname, edtCompany, edtAddressl, edtEmail
			, edtOldPass, edtNewpass, edtConfirmPass;
	private Button btnContinue;
	private ImageButton imgUsername;
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
		edtEmail 	   	= (EditText)findViewById(R.id.edtEmail);
		edtOldPass     	= (EditText)findViewById(R.id.edtOldPass);
		edtNewpass     	= (EditText)findViewById(R.id.edtNewPass);
		edtConfirmPass 	= (EditText)findViewById(R.id.edtConfirmPass);
		btnContinue    	= (Button)findViewById(R.id.btnContinue);
		imgUsername		= (ImageButton)findViewById(R.id.imgUsername);
		
		//action button continue
		btnContinue.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),Sale_Activity.class);
				startActivity(i);
			}
		});
	}

}
