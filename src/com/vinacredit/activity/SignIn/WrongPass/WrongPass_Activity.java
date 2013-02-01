package com.vinacredit.activity.SignIn.WrongPass;

import com.vinacredit.activity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WrongPass_Activity extends Activity{

	private EditText 	edtEmail;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wrongpass);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		edtEmail	= (EditText)findViewById(R.id.edtEmail);

	}
	/**
	 * @param view
	 */
	public void btnCancel(View view){
//		Intent intent = new Intent(getApplicationContext(),SignIn_Activity.class);
//		startActivity(intent);		
		finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	
	/**
	 * @param view
	 */
	public void btnSend(View view){
	      if(edtEmail.getText().toString().equals("")) {
	    	  Toast.makeText(WrongPass_Activity.this, R.string.str_email, Toast.LENGTH_SHORT).show(); 
	    	  return;
	      } else if(!edtEmail.getText().toString().contains("@") || !edtEmail.getText().toString().contains(".com")) {
	    	  Toast.makeText(WrongPass_Activity.this, R.string.str_inemail, Toast.LENGTH_SHORT).show(); 
	    	  return;
	      }
		Toast.makeText(getApplicationContext(), R.string.str_send, Toast.LENGTH_LONG).show();
	}
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
}
