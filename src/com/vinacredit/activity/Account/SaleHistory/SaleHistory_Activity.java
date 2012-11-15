package com.vinacredit.activity.Account.SaleHistory;

import com.vinacredit.Resource.MACROS;
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

	private TextView	txtTitleBar;
	private Button		btnAccount;
	private ImageView	imgUsername;
	private ListView	listView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sales_history);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		btnAccount		= (Button)findViewById(R.id.btnAccount);
		imgUsername		= (ImageView)findViewById(R.id.imgUsername);
		listView1		= (ListView)findViewById(R.id.listView1);
		
		translate();
	}
	private void translate() {
		// TODO Auto-generated method stub
		txtTitleBar.setText(MACROS.SALEHISTORY_LBL);
	}

	public void btnAccount(View view){
		Intent intent = new Intent(getApplicationContext(),Account_Activity.class);
		startActivity(intent);
	}
}
