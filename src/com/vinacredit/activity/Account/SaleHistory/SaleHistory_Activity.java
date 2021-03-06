package com.vinacredit.activity.Account.SaleHistory;

import java.util.ArrayList;
import java.util.List;

import com.vinacredit.Resource.Library;
import com.vinacredit.Resource.MACROS;
import com.vinacredit.Resource.MySQLiteHelper;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.SaleHistory.DetailSale.DetailSale_Activity;

import con.vinacredit.DTO.Account;
import con.vinacredit.DTO.SumBill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class SaleHistory_Activity extends Activity{

	private ImageView			imgUsername;
	private ListView			listView1;
	private List<SumBill>		ListSumBill;
	private SaleHistoryAdapter	saleHistoryAdapter;
	private MySQLiteHelper		mDb;
	private Account				account;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sales_history);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		imgUsername		= (ImageView)findViewById(R.id.imgUsername);
		listView1		= (ListView)findViewById(R.id.listView1);

		
		mDb 	= new MySQLiteHelper(this);
		account	= new Account();
		Bundle extras = getIntent().getExtras();
		
		
		ListSumBill = new ArrayList<SumBill>();
		if(MACROS.TEST_DATABASE)
		{
			account = mDb.getAccount(extras.getString("EMAIL"));
			imgUsername.setImageBitmap(Library.getBitmapFromByte(account.getImageAcc()));
			ListSumBill = mDb.getSumBill(extras.getString("EMAIL"));
			

			saleHistoryAdapter = new SaleHistoryAdapter(this, ListSumBill);
			listView1.setAdapter(saleHistoryAdapter);
		}		
		
		listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),DetailSale_Activity.class);
				Bundle myBundle = new Bundle();
				myBundle.putString("EMAIL", ListSumBill.get(position).getEmail());
				myBundle.putString("DATE", ListSumBill.get(position).getDateSale());
				myBundle.putString("SUMBILL", ListSumBill.get(position).getSumBill());
				i.putExtras(myBundle);
				startActivity(i);
				overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
			}
			
		});
		
	}

	public void btnAccount(View view){
//		Intent intent = new Intent(getApplicationContext(),Account_Activity.class);
//		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
	@Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
}
