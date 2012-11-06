package com.vinacredit.activity.Done;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;

import android.app.Activity;
import android.content.Intent;
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
		
		//action btnDone
		btnDone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),Sale_Activity.class);
				startActivity(intent);
			}
		});
	}

}
