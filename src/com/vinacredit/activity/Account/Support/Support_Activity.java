package com.vinacredit.activity.Account.Support;

import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.Account_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Support_Activity extends Activity{

	private Button btnAccount, btnVinacredit, btnHotline, btnInstruction;
	private TextView txtTitleBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_support);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		btnAccount		= (Button)findViewById(R.id.btnAccount);
		btnVinacredit	= (Button)findViewById(R.id.btnVinacredit);
		btnHotline		= (Button)findViewById(R.id.btnHotline);
		btnInstruction	= (Button)findViewById(R.id.btnInstruction);
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		
//		translate();
	}
	private void translate() {
		// TODO Auto-generated method stub
		btnVinacredit.setText(MACROS.SUPPORT_VINA_LBL);
		btnHotline.setText(MACROS.SUPPORT_HOTLINE_LBL);
		btnInstruction.setText(MACROS.SUPPORT_INSTRU_LBL);
		txtTitleBar.setText(MACROS.SUPPORT_LBL);
	}
	
	public void btnAccount(View view){
//		Intent intent = new Intent(getApplicationContext(),Account_Activity.class);
//		startActivity(intent);
		finish();
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
//    	translate();
    }

}
