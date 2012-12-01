package com.vinacredit.activity.Done;

import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Done_Activity extends Activity{

	private ImageView 	imgUsername;
	private TextView 	txtSumPrice;
	private TextView 	txtPaidThank;
	private Button		btnDone;
	static String email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_done);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		imgUsername		= (ImageView)findViewById(R.id.imgUsername);
		txtSumPrice		= (TextView)findViewById(R.id.txtSumPrice);
		txtPaidThank	= (TextView)findViewById(R.id.txtPaidThank);
		btnDone			= (Button)findViewById(R.id.btnDone);
		
		translate();	

	}
	private void translate() {
		// TODO Auto-generated method stub
		txtPaidThank.setText(MACROS.DONE_PAID_LBL);
		btnDone.setText(MACROS.DONE_BTN);
	}
	
	public void btnDone(View view){
		Intent intent = new Intent(getApplicationContext(),Sale_Activity.class);
		SharedPreferences share = this.getSharedPreferences("EMAIL", MODE_WORLD_READABLE);
		intent.putExtra("EMAIL",share.getString("EMAIL", "NOTHING"));
		startActivity(intent);
		finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
}
