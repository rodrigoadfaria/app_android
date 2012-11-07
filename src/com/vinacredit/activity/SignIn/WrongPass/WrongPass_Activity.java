package com.vinacredit.activity.SignIn.WrongPass;

import com.vinacredit.Resource.*;
import com.vinacredit.activity.R;
import com.vinacredit.activity.SignIn.SignIn_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WrongPass_Activity extends Activity{

	private Button 		btnCancel, btnSend;
	private EditText 	edtEmail;
	private TextView 	txtTitleBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wrongpass);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		edtEmail	= (EditText)findViewById(R.id.edtEmail);
		btnCancel	= (Button)findViewById(R.id.btnCancel);
		btnSend		= (Button)findViewById(R.id.btnSend);
		txtTitleBar = (TextView)findViewById(R.id.txtTitleBar);
		translate();
		
		//action btnCancel
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),SignIn_Activity.class);
				startActivity(intent);
			}
		});
		
		//action btnSend
		btnSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Sending email", Toast.LENGTH_LONG).show();
			}
		});
	}
	private void translate() {
		// TODO Auto-generated method stub
		edtEmail.setHint(MACROS.WRONGPASS_EMAIL_TXT);
		btnCancel.setText(MACROS.WRONGPASS_CANCEL_BTN);
		btnSend.setText(MACROS.WRONGPASS_SEND_BTN);
		txtTitleBar.setText(MACROS.WRONGPASS_LBL);
	}

}
