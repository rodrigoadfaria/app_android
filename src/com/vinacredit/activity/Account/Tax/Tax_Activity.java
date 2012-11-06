package com.vinacredit.activity.Account.Tax;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.Account_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tax_Activity extends Activity{

	private Button 		btnAccount;
	private TextView	txtTitleBar;
	private TextView	txtAddSaleTax, txtTaxRate, txtTax, txtTaxName;
	
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
		btnAccount		= (Button)findViewById(R.id.btnAccount);
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		txtAddSaleTax		= (TextView)findViewById(R.id.txtAddSaleTax);
		txtTaxRate		= (TextView)findViewById(R.id.txtTaxRate);
		txtTax			= (TextView)findViewById(R.id.txtTax);
		txtTaxName		= (TextView)findViewById(R.id.txtTaxName);
		
		btnAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),Account_Activity.class);
				startActivity(intent);
			}
		});		
	}
	public void numClick(View view){
		switch (view.getId()) {
			case R.id.btn10:
				txtTax.setText(txtTax.getText() + "1");
				break;
            
			case R.id.btn11:
				txtTax.setText(txtTax.getText() + "2");
				break;
            
			case R.id.btn12:
				txtTax.setText(txtTax.getText() + "3");
				break;

			case R.id.btn7:
				txtTax.setText(txtTax.getText() + "4");
				break;
            
			case R.id.btn8:
				txtTax.setText(txtTax.getText() + "5");
				break;
            
			case R.id.btn9:
				txtTax.setText(txtTax.getText() + "6");
				break;
            
			case R.id.btn4:
				txtTax.setText(txtTax.getText() + "7");
				break;
            
			case R.id.btn5:
				txtTax.setText(txtTax.getText() + "8");
				break;
            
			case R.id.btn6:
				txtTax.setText(txtTax.getText() + "9");
				break;
            
			case R.id.btn2:
				txtTax.setText(txtTax.getText() + "0");
				break;
			case R.id.btn1:
				txtTax.setText(txtTax.getText() + "000");
				break;
		}
	}
	
	public void clearClick(View view){
		if(view.getId() == R.id.btn3){
			txtTax.setText("0");
		}
	}
}
