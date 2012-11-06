package com.vinacredit.activity.Sale.Identify;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.activity.Sale.Signature.Signature_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Identify_Activity extends Activity{

	private TextView 	txtTitleBar;
	private ImageView	imgIdentify;
	private Button		btnBack, btnSignature, btnTakePhoto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		imgIdentify		= (ImageView)findViewById(R.id.imgIdentify);
		btnBack			= (Button)findViewById(R.id.btnBack);
		btnSignature	= (Button)findViewById(R.id.btnSignature);
		btnTakePhoto	= (Button)findViewById(R.id.btnTakePhoto);
		
		
		//action btnBack
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),Sale_Activity.class);
				startActivity(intent);
			}
		});
		
		//action btnSignature
		btnSignature.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),Signature_Activity.class);
				startActivity(intent);
			}
		});
		
		//action btnTakePhoto
		btnTakePhoto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Take Photo", Toast.LENGTH_LONG).show();
			}
		});
	}

}
