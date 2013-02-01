package com.vinacredit.activity.Sale.Charge;

import com.vinacredit.Resource.Library;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Receipt.Receipt_Activity;


import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class Charge_Activity extends Activity{

    private TextView 	txtChange, txtSumPrice;
    private EditText	txtPay;
    
    private String		_str_tmp 	= "";
    private String		_str_Change = "";
    private String		_str_Sum	= "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);
        initialize();
    }
    private void initialize() {
    	// TODO Auto-generated method stub
    	txtPay			= (EditText)findViewById(R.id.txtPay);
    	txtChange		= (TextView)findViewById(R.id.txtChange);
    	txtSumPrice		= (TextView)findViewById(R.id.txtSumPrice);
    	
    	Bundle bundle = getIntent().getExtras();
    	txtSumPrice.setText(bundle.getString("PRICEITEM"));
    	
    	_str_Sum = txtSumPrice.getText().toString().replace(",","");


    	txtPay.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				return true;
			}
		});
    }
    
    public void btnBack(View view){
//    	Intent i = new Intent(getApplicationContext(),Sale_Activity.class);
//		startActivity(i);    	
    	finish();
    	overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
    
    public void btnTenderCash(View view){
    	Intent i = new Intent(getApplicationContext(),Receipt_Activity.class);
		startActivity(i);		
		finish();
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
    
    public void numClick(View view){
    	String _str_number_click = "";
    	Log.i("Debug charge","view.getId :" + view.getId());
	
    	switch (view.getId()) {
		case R.id.btn10:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "1";
			break;
    
		case R.id.btn11:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "2";
			break;
    
		case R.id.btn12:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "3";
			break;

		case R.id.btn7:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "4";
			break;
    
		case R.id.btn8:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "5";
			break;
    
		case R.id.btn9:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "6";
			break;
    
		case R.id.btn4:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "7";
			break;
    
		case R.id.btn5:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "8";
			break;
    
		case R.id.btn6:
			if(_str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "9";
			break;
    
		case R.id.btn2:
			if(txtPay.getText().toString().equals("0") || _str_tmp.length() >= 9)
				return;
			else
				_str_number_click = "0";
		    break;
		case R.id.btn1:
			if(txtPay.getText().toString().equals("0") || _str_tmp.length() >= 6)
				return;
			else
				_str_number_click = "000";
		    break;
		default:
			break;
	}
    	if(_str_tmp.length() > 9)// 9,000,000,000 length is 13.
    		return; 
    	else
    		_str_tmp = _str_tmp + _str_number_click;
	if(_str_tmp.length() > 3){
		txtPay.setText(Library.addDotNumber(_str_tmp));
	} else 
		txtPay.setText(_str_tmp);
	Log.i("Debug Charge","_str_tmp :" + _str_tmp);
		 
	Double dou = Double.parseDouble(_str_tmp) - Double.parseDouble(_str_Sum);
	Integer i = dou.intValue();
	_str_Change = Library.addDotNumber(String.valueOf(i));
	String as = _str_Change.replaceAll("-,", "-");
	txtChange.setText(as);
}
    public void clearClick(View view){
	if(view.getId() == R.id.btn3){
		if(_str_tmp.length() < 1){
			txtPay.setText("0");
		} else 
			{
				_str_tmp = _str_tmp.substring(0,_str_tmp.length()-1);
				if(_str_tmp.length() > 3)
					txtPay.setText(Library.addDotNumber(_str_tmp));
				else
					txtPay.setText(_str_tmp);
				if(_str_tmp.length() < 1){
					txtPay.setText("0");
	    		}
			}   
		}
		String strPay = txtPay.getText().toString().replaceAll(",", "");
		Double dou = Double.parseDouble(strPay) - Double.parseDouble(_str_Sum);
		Integer i = dou.intValue();
		_str_Change = Library.addDotNumber(String.valueOf(i));
		String as = _str_Change.replaceAll("-,", "-");
		txtChange.setText(as);
    }
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
//    	super.onBackPressed();
    }
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
}
