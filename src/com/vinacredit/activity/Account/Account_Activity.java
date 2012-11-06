package com.vinacredit.activity.Account;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.SaleHistory.SaleHistory_Activity;
import com.vinacredit.activity.Account.Support.Support_Activity;
import com.vinacredit.activity.Account.Tax.Tax_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Account_Activity extends Activity{

	private ImageView imgUser;
	private TextView txtUsername;
	private TextView txtEmail;
	private Button btnSignOut;
	private ListView listView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		initialize();
        ListItem listitem_data[] = new ListItem[] {
        		new ListItem(R.drawable.sales_history, "Sale History", null),
                new ListItem(R.drawable.tax, "Tax", null),
                new ListItem(R.drawable.help_support, "Support", null)
        };
        
        ListItemAdapter adapter = new ListItemAdapter(this,R.layout.listview_row_account,listitem_data);
        
        listView1 = (ListView)findViewById(R.id.listView1);
        listView1.setAdapter(adapter);
        
        
        listView1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent i;
				switch (position) {
				case 1:
					 i = new Intent(getApplicationContext(), SaleHistory_Activity.class);
		        	  // sending data to new activity
		        	  startActivity(i);
		        	  break;
				case 2:
					 i = new Intent(getApplicationContext(), Tax_Activity.class);
		        	  // sending data to new activity
		        	  startActivity(i);
		        	  break;
				case 3:
		        	 i = new Intent(getApplicationContext(), Support_Activity.class);
		        	  // sending data to new activity
		        	  startActivity(i);
		        	  break;
				}
			}
        	
        });
	}
	private void initialize() {
		// TODO Auto-generated method stub
		imgUser			= (ImageView)findViewById(R.id.imgUsername);
		txtUsername 	= (TextView)findViewById(R.id.txtUsername);
		txtEmail		= (TextView)findViewById(R.id.txtEmail);
		btnSignOut		= (Button)findViewById(R.id.btnSignOut);
		listView1		= (ListView)findViewById(R.id.listView1);
	}

}
