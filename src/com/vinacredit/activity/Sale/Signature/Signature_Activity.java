package com.vinacredit.activity.Sale.Signature;

import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Charge.Charge_Activity;
import com.vinacredit.activity.Sale.Identify.Identify_Activity;
import com.vinacredit.activity.Sale.Sending.Sending_Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Signature_Activity extends Activity{

	private TextView	txtTitleBar, txtPrice, txtName;
	private Button		btnIdentify, btnSending, btnClear;
	private DrawView	drawView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signature);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		drawView		= (DrawView)findViewById(R.id.drawing_screen_drawview);
		drawView.requestFocus();
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		btnIdentify		= (Button)findViewById(R.id._btnIdentify);
		btnSending		= (Button)findViewById(R.id._btnSending);
		btnClear		= (Button)findViewById(R.id.btnClear);
		txtPrice		= (TextView)findViewById(R.id.txtPrice);
		txtName			= (TextView)findViewById(R.id.txtName);
		
		SharedPreferences s = this.getSharedPreferences("EMAIL", MODE_WORLD_READABLE);
		txtPrice.setText(s.getString("SUMPRICE", "0")+ " VND.");
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
	
	public void Clear(View v){
		
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
