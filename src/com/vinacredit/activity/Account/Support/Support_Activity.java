package com.vinacredit.activity.Account.Support;

import com.vinacredit.activity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Support_Activity extends Activity{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_support);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub

	}

	
	public void btnAccount(View view){
//		Intent intent = new Intent(getApplicationContext(),Account_Activity.class);
//		startActivity(intent);		
		finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
	
	public void btnVinacredit(View view){
		Toast.makeText(getApplicationContext(), "Vinacredit", Toast.LENGTH_LONG).show();
	}
	
	public void btnHotline(View view){
		Toast.makeText(getApplicationContext(), "Hotline", Toast.LENGTH_LONG).show();
	}
	
	public void btnInstruction(View view){
		Toast.makeText(getApplicationContext(), "Instruction", Toast.LENGTH_LONG).show();
	}
	
	@Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }

}
