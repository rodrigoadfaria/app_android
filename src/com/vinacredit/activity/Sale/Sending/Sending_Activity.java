package com.vinacredit.activity.Sale.Sending;

import com.vinacredit.activity.R;

import android.app.Activity;
import android.os.Bundle;
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
		
	}

}
