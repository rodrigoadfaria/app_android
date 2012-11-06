package com.vinacredit.activity.Account.SaleHistory;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.Account_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SaleHistory_Activity extends Activity{

	private Button		btnAccount;
	private TextView	txtTitlteBar;
	private ImageView	imgUsername;
	private ListView	listView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		btnAccount		= (Button)findViewById(R.id.btnAccount);
		txtTitlteBar	= (TextView)findViewById(R.id.txtTitleBar);
		imgUsername		= (ImageView)findViewById(R.id.imgUsername);
		listView1		= (ListView)findViewById(R.id.listView1);
		
		//action btnAccount
		btnAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),Account_Activity.class);
				startActivity(intent);
			}
		});
	}

}
