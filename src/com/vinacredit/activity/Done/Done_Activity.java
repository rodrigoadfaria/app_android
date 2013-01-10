package com.vinacredit.activity.Done;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Done_Activity extends Activity{

	private TextView 	txtSumPrice;
	static String email;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_done);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		txtSumPrice		= (TextView)findViewById(R.id.txtSumPrice);

		SharedPreferences s = this.getSharedPreferences("EMAIL", MODE_PRIVATE);
		txtSumPrice.setText(s.getString("SUMPRICE", "0")+ " VND.");
//		Editor edit = s.edit();
//		edit.remove("SUMPRICE");
//		edit.commit();
	}
	
	public void btnDone(View view){
		Intent intent = new Intent(getApplicationContext(),Sale_Activity.class);
		SharedPreferences share = this.getSharedPreferences("EMAIL", MODE_PRIVATE);
		intent.putExtra("EMAIL",share.getString("EMAIL", "NOTHING"));
		startActivity(intent);
		finish();
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
    }
}
