package com.vinacredit.activity.Sale.Signature;

import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Charge.Charge_Activity;
import com.vinacredit.activity.Sale.Identify.Identify_Activity;
import com.vinacredit.activity.Sale.Sending.Sending_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Signature_Activity extends Activity{

	private TextView	txtTitleBar;
	private Button		btnIdentify, btnSending;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signature);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		btnIdentify		= (Button)findViewById(R.id._btnIdentify);
		btnSending		= (Button)findViewById(R.id._btnSending);
		
		translate();
	}
	//action btnIdentify
	public void gotoIdentify(View view){
//	    Intent intent = new Intent(getApplicationContext(),Identify_Activity.class);
//	    startActivity(intent);
		finish();
	}
	// action btn Sending 
	public void gotoSending(View view){
	    Intent intent = new Intent(getApplicationContext(),Sending_Activity.class);
	    startActivity(intent);
	}
	private void translate() {
		// TODO Auto-generated method stub
		txtTitleBar.setText(MACROS.SIGNATURE_LBL);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}

}
