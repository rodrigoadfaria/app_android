package com.vinacredit.activity.Account.SaleHistory.DetailSale;


import java.util.ArrayList;
import java.util.List;


import com.vinacredit.Resource.Library;
import com.vinacredit.Resource.MACROS;
import com.vinacredit.Resource.MySQLiteHelper;
import com.vinacredit.activity.R;

import con.vinacredit.DTO.Account;
import con.vinacredit.DTO.Bill;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DetailSale_Activity extends Activity{

	private ImageView		imgUsername;
	private ListView		listview1;
	private TextView		txtPriceTotal;
	private List<Bill>		listBill;
	private DetailSaleAdapter	detailAdapter;
	private MySQLiteHelper	mDb;
	private Account			account;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_sale);
		
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		imgUsername		= (ImageView)findViewById(R.id.imgUsername);
		listview1		= (ListView)findViewById(R.id.listView1);
		txtPriceTotal	= (TextView)findViewById(R.id.txtPriceTotal);

		/* initialize variable */
		account = new Account();
		mDb = new MySQLiteHelper(this);
		listBill	= new ArrayList<Bill>();
		Bundle extras = getIntent().getExtras();
		if(MACROS.TEST_DATABASE){
			account = mDb.getAccount(extras.getString("EMAIL"));
			imgUsername.setImageBitmap(Library.getBitmapFromByte(account.getImageAcc()));
			
			listBill = mDb.getBill(extras.getString("EMAIL"), extras.getString("DATE"));
			

			txtPriceTotal.setText(Library.addDotNumber(extras.getString("SUMBILL")));
			detailAdapter	= new DetailSaleAdapter(this, listBill);
			listview1.setAdapter(detailAdapter);
		}
		
				
	}
	
	public void btnSaleHistory(View v){		
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
