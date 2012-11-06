package com.vinacredit.activity.Sale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.Account_Activity;
import com.vinacredit.activity.Account.ListItem;
import com.vinacredit.activity.Sale.Charge.Charge_Activity;

public class Sale_Activity extends Activity{
    Button btn_gotoAccount;
    Button btn_gotoCharge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_sale);
	initialize();
    }
    private void initialize() {
	// TODO Auto-generated method stub
	btn_gotoAccount = (Button)findViewById(R.id._btnAccount);
	btn_gotoCharge  = (Button)findViewById(R.id._btnSaleCharge);
	
	//action button account
	btn_gotoAccount.setOnClickListener(new View.OnClickListener() {		
	    @Override
	    public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i = new Intent(getApplicationContext(),Account_Activity.class);
		startActivity(i);
	    }
	});
	//action button charge
	btn_gotoCharge.setOnClickListener(new View.OnClickListener() {		
	    @Override
	    public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i = new Intent(getApplicationContext(),Charge_Activity.class);
		startActivity(i);
	    }
	});

    }
    
}
