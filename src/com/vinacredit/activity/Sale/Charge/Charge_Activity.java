package com.vinacredit.activity.Sale.Charge;

import com.vinacredit.activity.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import com.vinacredit.Resource.*;

public class Charge_Activity extends Activity{
    
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_000;
    private Button btn_clear;
    
    private TextView txt_Sum_Price;
    private TextView txt_Pay_Price;
    
    String tmp_price;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge);
        initialize();
    }
    private void initialize() {
	// TODO Auto-generated method stub	
	btn_0 		= (Button)findViewById(R.id.btn_number_0);
	btn_1 		= (Button)findViewById(R.id.btn_number_1);
	btn_2 		= (Button)findViewById(R.id.btn_number_2);
	btn_3 		= (Button)findViewById(R.id.btn_number_3);
	btn_4 		= (Button)findViewById(R.id.btn_number_4);
	btn_5 		= (Button)findViewById(R.id.btn_number_5);
	btn_6 		= (Button)findViewById(R.id.btn_number_6);
	btn_7 		= (Button)findViewById(R.id.btn_number_7);
	btn_8		= (Button)findViewById(R.id.btn_number_8);
	btn_9		= (Button)findViewById(R.id.btn_number_9);
	btn_000 	= (Button)findViewById(R.id.btn_number_000);
	btn_clear 	= (Button)findViewById(R.id.btn_number_clear);	
	
	txt_Sum_Price	= (TextView)findViewById(R.id.txtSumPrice);
	txt_Pay_Price	= (TextView)findViewById(R.id.txtPay);
	
	
	
	btn_0.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "0";		
	    }
	});
	btn_1.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "1";		
	    }
	});
	btn_2.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "2";		
	    }
	});
	btn_3.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "3";		
	    }
	});
	btn_4.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "4";		
	    }
	});
	btn_5.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "5";		
	    }
	});
	btn_6.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "6";		
	    }
	});
	btn_7.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "7";		
	    }
	});
	btn_8.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "8";		
	    }
	});
	btn_9.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "9";		
	    }
	});
	btn_000.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "000";		
	    }
	});
	btn_clear.setOnClickListener(new View.OnClickListener() {
	    
	    @Override
	    public void onClick(View v) {
		// TODO Auto-generated method stub
		tmp_price = "0";		
	    }
	});
    }
   
}
