package com.vinacredit.activity.Sale.Charge;

import com.vinacredit.Resource.Library;
import com.vinacredit.Resource.MACROS;
import com.vinacredit.Resource.MySQLiteHelper;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.activity.Sale.Receipt.Receipt_Activity;

import con.vinacredit.DTO.Bill;
import con.vinacredit.DTO.SumBill;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;

public class Charge_Activity extends Activity{
	
    private Button btnBack;
    private Button btnCash;
    private TextView txtTitlebar;
    private TextView txtPay, txtChange, txtSumPrice;
    
    private MySQLiteHelper mDb;
    private SumBill			sumBill;
    private Bill			bill;
    
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
    	btnBack  		= (Button)findViewById(R.id.btnBack);
    	btnCash  		= (Button)findViewById(R.id.btnTenderCash);
    	txtTitlebar		= (TextView)findViewById(R.id.txtTitleBar);
    	txtPay			= (TextView)findViewById(R.id.txtPay);
    	txtChange		= (TextView)findViewById(R.id.txtChange);
    	txtSumPrice		= (TextView)findViewById(R.id.txtSumPrice);
    	
    	
    	mDb = new MySQLiteHelper(this);
    	sumBill = new SumBill();
    	bill = new Bill();
    	
    	Bundle bundle = getIntent().getExtras();
    	txtSumPrice.setText(bundle.getString("PRICEITEM"));
    	
    	_str_Sum = txtSumPrice.getText().toString().replace(",","");
    	
    	translate();

    }
    
    public void btnBack(View view){
//    	Intent i = new Intent(getApplicationContext(),Sale_Activity.class);
//		startActivity(i);
    	finish();
    }
    
    public void btnTenderCash(View view){
    	Intent i = new Intent(getApplicationContext(),Receipt_Activity.class);
    	if (MACROS.TEST_DATABASE) {
			Bundle myBundle = getIntent().getExtras();
			sumBill.setEmail(myBundle.getString("EMAIL"));
			sumBill.setDateSale(Library.getDate());
			sumBill.setSumBill(_str_Sum);
			
			bill.setEmail(myBundle.getString("EMAIL"));
			bill.setDateSale(Library.getDate());
			bill.setSumItem(_str_Sum);
			bill.setTimeSale(Library.getTime());
			try {
				mDb.insertBill(sumBill, bill);
			} catch (SQLException e) {
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}
		startActivity(i);
		finish();
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
		 
	
	_str_Change = String.valueOf(Integer.parseInt(_str_tmp) - Integer.parseInt(_str_Sum));
	txtChange.setText(Library.addDotNumber(_str_Change));
}
    public void clearClick(View view){
	if(view.getId() == R.id.btn3){
		if(_str_tmp.length() < 1){
			txtPay.setText("0");
		} else {
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
}
    private void translate() {
	// TODO Auto-generated method stub
    	btnCash.setText(MACROS.CHARGE_TENDER_BTN);
    	txtTitlebar.setText(MACROS.CHARGE_LBL);
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
    	translate();
    }
}
