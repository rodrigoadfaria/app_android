package com.vinacredit.activity.Account;

import java.util.ArrayList;
import java.util.List;

import com.vinacredit.Resource.*;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.SaleHistory.SaleHistory_Activity;
import com.vinacredit.activity.Account.Support.Support_Activity;
import com.vinacredit.activity.Account.Tax.Tax_Activity;
import com.vinacredit.activity.Welcome.Welcome_Activity;

import con.vinacredit.DTO.Account;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Account_Activity extends Activity{


	
	private ImageView imgUsername;
	private TextView txtUsername;
	private TextView txtEmail;
	private ListView listView1;
	
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
		listView1		= (ListView)findViewById(R.id.listView1);

		/* initialize variable */
		dbSqlite = new MySQLiteHelper(this);
		account  = new Account();
		item	= new ListItem();
		listItem = new ArrayList<ListItem>();
		listItem.add(new ListItem(BitmapFactory.decodeResource(getResources(), R.drawable.sales_history), getResources().getString(R.string.txtTitleBarSalesHistory), item.getSubtitle(),
				BitmapFactory.decodeResource(getResources(), R.drawable.arrow)));
		listItem.add(new ListItem(BitmapFactory.decodeResource(getResources(), R.drawable.tax), getResources().getString(R.string.txtTitleBarTax), item.getSubtitle(),
				BitmapFactory.decodeResource(getResources(), R.drawable.arrow)));
		listItem.add(new ListItem(BitmapFactory.decodeResource(getResources(), R.drawable.help_support), getResources().getString(R.string.txtTitleBarSupport), item.getSubtitle(),
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
    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    	
//    	alertDialogBuilder.setTitle("Confirm Clear Bill");
    	
    	alertDialogBuilder.setMessage("Confirm Sign out!")
    						.setCancelable(false)
    						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Intent i = new Intent(getApplicationContext(),Welcome_Activity.class);
									startActivity(i);
									finish();
								}
							})
							.setNegativeButton("No", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									dialog.cancel();
								}
							});
    	AlertDialog alertDialog = alertDialogBuilder.create();
    	
    	alertDialog.show();
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	
    	SharedPreferences share = this.getSharedPreferences("STAX",MODE_PRIVATE );
    	if(share.getBoolean("STATUSTAX", false)) {
    		listItem.get(1).setSubtitle(share.getString("TAX", "0%"));
    		accountAdapter.notifyDataSetChanged();
    	} else {
    		listItem.get(1).setSubtitle("");
    		accountAdapter.notifyDataSetChanged();
    	}
    }
	
}
