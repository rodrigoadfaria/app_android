package com.vinacredit.activity.Sale.Charge;

import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.activity.Sale.Receipt.Receipt_Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class Charge_Activity extends Activity{
	
    private Button btnBack;
    private Button btnCash;
    private TextView txtTitlebar;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);
        initialize();
    }
    private void initialize() {
    	// TODO Auto-generated method stub
    	btnBack  		= (Button)findViewById(R.id.btnBack);
    	btnCash  		= (Button)findViewById(R.id.btnTenderCash);
    	txtTitlebar		= (TextView)findViewById(R.id.txtTitleBar);
    	
    	translate();
   	
    	//action button account
    	btnBack.setOnClickListener(new View.OnClickListener() {		
    		@Override
   	    	public void onClick(View arg0) {
    			// TODO Auto-generated method stub
    			Intent i = new Intent(getApplicationContext(),Sale_Activity.class);
    			startActivity(i);
    		}
    	});
    	
    	//action button charge
    	btnCash.setOnClickListener(new View.OnClickListener() {		
    		@Override
    		public void onClick(View arg0) {
    			// TODO Auto-generated method stub
    			Intent i = new Intent(getApplicationContext(),Receipt_Activity.class);
    			startActivity(i);
    		}
    	});
    }
	private void translate() {
		// TODO Auto-generated method stub
    	btnCash.setText(MACROS.CHARGE_TENDER_BTN);
    	txtTitlebar.setText(MACROS.CHARGE_LBL);
	}
}
