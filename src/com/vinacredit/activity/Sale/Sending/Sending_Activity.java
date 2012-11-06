package com.vinacredit.activity.Sale.Sending;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Receipt.Receipt_Activity;
import com.vinacredit.activity.Sale.Signature.Signature_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sending_Activity extends Activity{

	private Button		btnBack, btnReceipt;
	private TextView	txtTitlebar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sending);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		btnBack		= (Button)findViewById(R.id.btnBack);
		btnReceipt	= (Button)findViewById(R.id.btnReceipt);
		txtTitlebar	= (Button)findViewById(R.id.txtTitleBar);
		
		//action btnBack
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),Signature_Activity.class);
				startActivity(intent);
			}
		});
		
		//action btnReceipt
		btnReceipt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),Receipt_Activity.class);
				startActivity(intent);
			}
		});
	}

}
