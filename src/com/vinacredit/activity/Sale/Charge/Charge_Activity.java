package com.vinacredit.activity.Sale.Charge;

import com.vinacredit.Resource.Library;
import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.activity.Sale.Receipt.Receipt_Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class Charge_Activity extends Activity{
	
    private Button btnBack;
    private Button btnCash;
    private TextView txtTitlebar;
    private TextView txtPay;
    
    String _str_tmp = "";
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
    	txtPay			= (TextView)findViewById(R.id.txtPay);
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
    public void numClick(View view){
    	String _str_number_click = "";
    	Log.i("Debug charge","view.getId :" + view.getId());
	
    	switch (view.getId()) {
		case R.id.btn10:
		    _str_number_click = "1";
			break;
    
		case R.id.btn11:
		    _str_number_click = "2";
			break;
    
		case R.id.btn12:
		    _str_number_click = "3";
			break;

		case R.id.btn7:
		    _str_number_click = "4";
			break;
    
		case R.id.btn8:
		    _str_number_click = "5";
			break;
    
		case R.id.btn9:
		    _str_number_click = "6";
			break;
    
		case R.id.btn4:
		    _str_number_click = "7";
			break;
    
		case R.id.btn5:
		    _str_number_click = "8";
			break;
    
		case R.id.btn6:
		    _str_number_click = "9";
			break;
    
		case R.id.btn2:
		    _str_number_click = "0";
		    break;
		case R.id.btn1:
		    _str_number_click = "000";
		    break;
		default:
			break;
	}
    	if(_str_tmp.length() > 13)return; // 9,000,000,000 length is 13.
	_str_tmp = _str_tmp + _str_number_click;
	if(_str_tmp.length() > 3)
	    _str_tmp = Library.addDotNumber(_str_tmp);
	Log.i("Debug Charge","_str_tmp :" + _str_tmp);
	txtPay.setText(_str_tmp);	 
}
    public void clearClick(View view){
	if(view.getId() == R.id.btn3){
	    txtPay.setText("0");
	    _str_tmp = "";
	}
}
    private void translate() {
	// TODO Auto-generated method stub
    	btnCash.setText(MACROS.CHARGE_TENDER_BTN);
    	txtTitlebar.setText(MACROS.CHARGE_LBL);
    }
    
}
