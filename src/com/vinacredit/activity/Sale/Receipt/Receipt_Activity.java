package com.vinacredit.activity.Sale.Receipt;

import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Done.Done_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Receipt_Activity extends Activity{

	private ImageView	imgUsername;
	private EditText	edtEmail;
	private Button		btnSkip;
	private Button		btnSend;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receipt);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		imgUsername		= (ImageView)findViewById(R.id.imgUsername);
		edtEmail		= (EditText)findViewById(R.id.edtEmail);
		btnSkip			= (Button)findViewById(R.id.btnSkip);
		btnSend			= (Button)findViewById(R.id.btnSend);
		
		translate();
		
		//action btnSkip
		btnSkip.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Skip send email", Toast.LENGTH_LONG ).show();
				Intent intent = new Intent(getApplicationContext(),Done_Activity.class);
				startActivity(intent);
			}
		});
		
		//action btnSend
		btnSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),Done_Activity.class);
				startActivity(intent);
			}
		});
		
	}
	private void translate() {
		// TODO Auto-generated method stub
		edtEmail.setHint(MACROS.RECEIPT_EMAIL_TXT);
		btnSkip.setText(MACROS.RECEIPT_SKIP_BTN);
		btnSend.setText(MACROS.RECEIPT_SEND_BTN);
	}

}
