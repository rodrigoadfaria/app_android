package com.vinacredit.activity.InformationAccount;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.Account_Activity;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.Resource.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class InformationAccount_Activity extends Activity {

	private EditText 	edtFirstname, edtLastname, edtCompany, edtAddress, edtEmail
						, edtOldPass, edtNewpass, edtConfirmPass;
	private Button 		btnContinue;
	private ImageButton imgUsername;
	private TextView	txtTitleBar, txtEmail, txtOldPass, txtNewPass, txtConfirmPass;
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
		
		translate();
		
		Bundle extras = getIntent().getExtras();
		edtEmail.setText(extras.getString("EMAIL"));
		
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

}
