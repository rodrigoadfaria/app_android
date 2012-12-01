package com.vinacredit.activity.Account;

import java.util.ArrayList;
import java.util.List;

import com.vinacredit.Resource.*;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.SaleHistory.SaleHistory_Activity;
import com.vinacredit.activity.Account.Support.Support_Activity;
import com.vinacredit.activity.Account.Tax.Tax_Activity;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.activity.Sale.Charge.Charge_Activity;
import com.vinacredit.activity.Welcome.Welcome_Activity;

import con.vinacredit.DTO.Account;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Account_Activity extends Activity{

	private static final int IPC_ID = 1122;
	
	private ImageView imgUsername;
	private TextView txtUsername;
	private TextView txtEmail;
	private TextView txtTitleBar;
	private Button btnSignOut, btnBack;
	private ListView listView1;
	private String strSaleHistory, strTax, strSupport, strTaxNumber;
	private boolean bl_status_tax;
	
	private MySQLiteHelper 	dbSqlite;
	private Account			account;
    private AccountAdapter	accountAdapter;
    private List<ListItem> 	listItem;
    private ListItem		item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		initialize();          
	}
	private void initialize() {
		// TODO Auto-generated method stub			
		imgUsername		= (ImageView)findViewById(R.id.imgUsername);
		txtUsername 	= (TextView)findViewById(R.id.txtUsername);
		txtEmail		= (TextView)findViewById(R.id.txtEmail);
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		btnSignOut		= (Button)findViewById(R.id.btnSignOut);
		btnBack			= (Button)findViewById(R.id.btnBack);
		listView1		= (ListView)findViewById(R.id.listView1);
		
		translate();
		/* initialize variable */
		dbSqlite = new MySQLiteHelper(this);
		account  = new Account();
		item	= new ListItem();
		listItem = new ArrayList<ListItem>();
		listItem.add(new ListItem(BitmapFactory.decodeResource(getResources(), R.drawable.sales_history), "Sales History", item.getSubtitle(),
				BitmapFactory.decodeResource(getResources(), R.drawable.arrow)));
		listItem.add(new ListItem(BitmapFactory.decodeResource(getResources(), R.drawable.tax), "Tax", item.getSubtitle(),
				BitmapFactory.decodeResource(getResources(), R.drawable.arrow)));
		listItem.add(new ListItem(BitmapFactory.decodeResource(getResources(), R.drawable.help_support), "Support", item.getSubtitle(),
				BitmapFactory.decodeResource(getResources(), R.drawable.arrow)));
		accountAdapter = new AccountAdapter(this, listItem);
		listView1.setAdapter(accountAdapter);
		
		Bundle extras = getIntent().getExtras();
		if(MACROS.TEST_DATABASE) {
		account = dbSqlite.getAccount(extras.getString("EMAIL"));
	    imgUsername.setImageBitmap(Library.getBitmapFromByte(account.getImageAcc()));
	    
	    txtUsername.setText(account.getLastName() + " "+account.getFirstName());
	    txtEmail.setText(account.getEmail());
		}
        
        
        listView1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent i;
				switch (position) {
				case 0:
					i = new Intent(getApplicationContext(),SaleHistory_Activity.class);
					i.putExtra("EMAIL", account.getEmail());
					startActivity(i);
					break;
				case 1:
					i = new Intent(Account_Activity.this,Tax_Activity.class);
					Bundle myBundle = new Bundle();
					myBundle.putBoolean("STATUSTAX1", bl_status_tax);
					if(listItem.get(1).getSubtitle() == "")
						listItem.get(1).setSubtitle("0%");
					myBundle.putString("TAX1", listItem.get(1).getSubtitle());
					i.putExtras(myBundle);
					startActivityForResult(i, IPC_ID);
					break;
				case 2:
					i = new Intent(getApplicationContext(),Support_Activity.class);
					startActivity(i);
					break;
				}
			}
        	
		});
		
	}
	private void translate() {
		// TODO Auto-generated method stub
		txtTitleBar.setText(MACROS.ACCOUNT_LBL);
		btnSignOut.setText(MACROS.ACCOUNT_RIGHT_BTN);
		strSaleHistory 	= MACROS.ACCOUNT_SALEHISTORY_LBL;
		strTax			= MACROS.ACCOUNT_TAX_LBL;
		strSupport		= MACROS.ACCOUNT_SUPPORT_LBL;
	}
	
	public void btnBack(View view){
//		Intent i = new Intent(getApplicationContext(),Sale_Activity.class);
//		startActivity(i);
		finish();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
	
	public void btnSignOut(View view){
		Intent i = new Intent(getApplicationContext(),Welcome_Activity.class);
		startActivity(i);
		finish();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		try {
			switch (requestCode) {
			case IPC_ID: {
				if (resultCode == Activity.RESULT_OK) {
					Bundle extras = data.getExtras();
					if(extras != null){
						bl_status_tax = extras.getBoolean("STATUSTAX");
						if(bl_status_tax) {
							listItem.get(1).setSubtitle(extras.getString("TAX"));
							accountAdapter.notifyDataSetChanged();
						} else{
							listItem.get(1).setSubtitle("");
							accountAdapter.notifyDataSetChanged();
						}
							
							Toast.makeText(getBaseContext(), extras.getString("TAX"), Toast.LENGTH_SHORT).show();
					}
				}
				break;
			}
			}
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	
}
