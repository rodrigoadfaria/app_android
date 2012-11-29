package com.vinacredit.activity.Account;

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
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Account_Activity extends Activity{

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
    private Library			library;
    
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
		txtUsername 		= (TextView)findViewById(R.id.txtUsername);
		txtEmail		= (TextView)findViewById(R.id.txtEmail);
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		btnSignOut		= (Button)findViewById(R.id.btnSignOut);
		btnBack			= (Button)findViewById(R.id.btnBack);
		listView1		= (ListView)findViewById(R.id.listView1);
		
		translate();
		dbSqlite = new MySQLiteHelper(this);
		account  = new Account();
		library = new Library();
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			bl_status_tax = extras.getBoolean("STATUSTAX");
			if(bl_status_tax)
				strTaxNumber = extras.getString("TAX");
		}
		if(MACROS.TEST_SIGNIN_BL) {
		account = dbSqlite.getAccount(extras.getString("EMAIL"));
	    imgUsername.setImageBitmap(library.getBitmapFromByte(account.getImageAcc()));
	    
	    txtUsername.setText(account.getLastName() + " "+account.getFirstName());
	    txtEmail.setText(account.getEmail());
		}
		
		/* set item in listview    */
		ListItem listitem_data[] = new ListItem[] {
        		new ListItem(R.drawable.sales_history, 	strSaleHistory, null, 			R.drawable.arrow),
                new ListItem(R.drawable.tax, 			strTax, 		strTaxNumber, 	R.drawable.arrow),
                new ListItem(R.drawable.help_support, 	strSupport, 	null, 			R.drawable.arrow)
        };
        
        ListItemAdapter adapter = new ListItemAdapter(this,R.layout.listview_row_account,listitem_data);
        
        listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        
        
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
					i = new Intent(getApplicationContext(),Tax_Activity.class);
					startActivity(i);
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
		Intent i = new Intent(getApplicationContext(),Sale_Activity.class);
		startActivity(i);
	}
	
	public void btnSignOut(View view){
		Intent i = new Intent(getApplicationContext(),Welcome_Activity.class);
		startActivity(i);
	}

}
