package com.vinacredit.activity.Account.SaleHistory.DetailSale;


import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.vinacredit.Resource.Library;
import com.vinacredit.Resource.MACROS;
import com.vinacredit.Resource.MySQLiteHelper;
import com.vinacredit.activity.R;

import con.vinacredit.DTO.Account;
import con.vinacredit.DTO.Bill;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DetailSale_Activity extends Activity{

	private TextView		txtTitleBar;
	private Button			btnSaleHistory;
	private ImageView		imgUsername;
	private ListView		listview1;
	private TextView		txtTotal, txtPriceTotal;
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
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		btnSaleHistory	= (Button)findViewById(R.id.btnSaleHistory);
		imgUsername		= (ImageView)findViewById(R.id.imgUsername);
		listview1		= (ListView)findViewById(R.id.listView1);
		txtTotal		= (TextView)findViewById(R.id.txtTotal);
		txtPriceTotal	= (TextView)findViewById(R.id.txtPriceTotal);
		
		/*translate language */
		translate();
		
		/* initialize variable */
		account = new Account();
		mDb = new MySQLiteHelper(this);
		listBill	= new ArrayList<Bill>();
		Bundle extras = getIntent().getExtras();
		if(MACROS.TEST_DATABASE){
			account = mDb.getAccount(extras.getString("EMAIL"));
			imgUsername.setImageBitmap(Library.getBitmapFromByte(account.getImageAcc()));
			
			listBill = mDb.getBill(extras.getString("EMAIL"), extras.getString("DATE"));
			
//			for (int i = 0; i < listBill.size(); i++) {
//				listBill.add(new Bill("Bill "+ i,listBill.get(i).getTimeSale(),listBill.get(i).getSumItem()));
//			}
			txtPriceTotal.setText(Library.addDotNumber(extras.getString("SUMBILL")));
			detailAdapter	= new DetailSaleAdapter(this, listBill);
			listview1.setAdapter(detailAdapter);
		}
		
				
	}

	private void translate() {
		// TODO Auto-generated method stub
		txtTitleBar.setText(MACROS.DETAILSALE_LBL);
		txtTotal.setText(MACROS.DETAILSALE_TOTAL_LBL);
	}
	
	public void btnSaleHistory(View v){
		finish();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
}
