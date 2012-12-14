package com.vinacredit.activity.Account.Tax;

import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.Account_Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Tax_Activity extends Activity{

	private Button 		btnAccount;
	private TextView	txtTitleBar;
	private TextView	txtAddSaleTax, txtTaxRate, txtTax, txtTaxName;
	private ToggleButton	tgbtnstatusTax;
	String _str_tax = "";
	boolean i_first_dot    = true;
	private boolean bl_status = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	    Log.i("debug","Tax_Activity");
	    setContentView(R.layout.activity_tax);
	    super.onCreate(savedInstanceState);
	    initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		btnAccount			= (Button)findViewById(R.id.btnAccount);
		txtTitleBar			= (TextView)findViewById(R.id.txtTitleBar);
		txtAddSaleTax		= (TextView)findViewById(R.id.txtAddSaleTax);
		txtTaxRate			= (TextView)findViewById(R.id.txtTaxRate);
		txtTax				= (TextView)findViewById(R.id.txtTax);
		txtTaxName			= (TextView)findViewById(R.id.txtTaxName);
		tgbtnstatusTax		= (ToggleButton)findViewById(R.id.tgbAddSaleTax);
		
		translate();		
		
		Intent myLocalIntent = getIntent();
        Bundle myBundle = myLocalIntent.getExtras();
        
        SharedPreferences share = this.getSharedPreferences("STAX",MODE_WORLD_READABLE );
    	txtTax.setText(share.getString("TAX", "0%"));        
        if(share.getBoolean("STATUSTAX", false)) {

        	tgbtnstatusTax.setChecked(true);
        	bl_status = true;
        }        
        else {
        	tgbtnstatusTax.setChecked(false);
        	bl_status = false;
        }

	}
	
	public void clickChange(View view){
		if(tgbtnstatusTax.isChecked()){
			bl_status = true;
		} else
			bl_status = false;
		
	}
	
	private void translate() {
		// TODO Auto-generated method stub
		txtTitleBar.setText(MACROS.TAX_LBL);
		txtAddSaleTax.setText(MACROS.TAX_SALETAX_LBL);
		txtTaxRate.setText(MACROS.TAX_TAXRATE_LBL);
		txtTaxName.setText(MACROS.TAX_TEXT_LBL);
	}

	public void btnAccount(View view){
		Intent intent = new Intent();
		Bundle myBundle = new Bundle();
		myBundle.putBoolean("STATUSTAX", bl_status);
		myBundle.putString("TAX", txtTax.getText().toString());
		intent.putExtras(myBundle);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}
	
	public void numClick(View view){
	    	String _str_number_click = "";	 
	    	boolean bl_pressed_dot = false;
	    	boolean bl_pressed_zero= false;
	    	Log.i("Debug tax","view.getId :" + view.getId());
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
			    bl_pressed_zero   = true;
				break;
			case R.id.btn1:
			    _str_number_click = ".";
			    bl_pressed_dot    = true;
				break;
			default:
				break;
		}
		String _str_tax_tmp = _str_tax + _str_number_click;
		int _test_exits_dot = test_dot(_str_tax_tmp);
		if(txtTax.getText().equals("0 %")){ // number is zero and user pressed zero
		    if(bl_pressed_zero){			
			bl_pressed_zero = false;
			return;
		    }
		    if(bl_pressed_dot){
			bl_pressed_dot = false;			
			return;
		    }
		}
		if(_test_exits_dot != -1 && bl_pressed_dot){// number had dot and user pressed dot again.
		    bl_pressed_dot = false;
		    if(i_first_dot)
			i_first_dot = false;
		    else
			return;
		}
		if(_str_tax_tmp.equals("100."))return;
		
		if(_test_exits_dot > 0){
		    if(_str_tax.length() - _test_exits_dot > 2)
			return;
		}
		_str_tax = _str_tax + _str_number_click;
		Log.i("Debug Tax", "_str_tax :" + _str_tax );
		
		if(_test_exits_dot != -1){
		    Log.i("Debug tax","number had dot _str_tax :" + _str_tax);
		    //float _number = Float.valueOf(_str_tax);
		    //if(_number > 100.0){
		//	_str_tax = "100";			
		    //}
		}
		else {
		    int _number = Integer.valueOf(_str_tax);
		    if(_number > 100){
			_str_tax = "100";			
		    }
		}
		txtTax.setText(_str_tax + "%");
		MACROS.tax_percent_str = txtTax.getText().toString();
		Log.i("Debug Tax", "number tax :" + txtTax.getText() );
		Log.i("Debug Tax", "MACROS.tax_percent_str :" + MACROS.tax_percent_str );
	}
	public int test_dot(String _str_test){
	    int _index = _str_test.indexOf(".");	
	    Log.i("Debug Tax","index dot :" + _index);
	    return _index;
	}
	public boolean test_number_tax(float number){
	   if(number > 100)return false; 
	   return true;
	}
	public void clearClick(View view){
		if(view.getId() == R.id.btn3){
		       txtTax.setText("0 %");
		       _str_tax = "";
		       i_first_dot = true;
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
	
	@Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	translate();
    }
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreferences share = this.getSharedPreferences("STAX",MODE_WORLD_READABLE);
	    SharedPreferences.Editor editor = share.edit();
	    editor.putBoolean("STATUSTAX",bl_status);
	    editor.putString("TAX", txtTax.getText().toString());
	    editor.commit();
	}
	
	
}
