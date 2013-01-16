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
	}
	
	/**
	 * @param view
	 */
	public void btnSend(View view){
	      if(edtEmail.getText().toString().equals("")) {
	    	  Toast.makeText(WrongPass_Activity.this, "Enter email,Please!", Toast.LENGTH_SHORT).show(); 
	    	  return;
	      } else if(!edtEmail.getText().toString().contains("@") || !edtEmail.getText().toString().contains(".com")) {
	    	  Toast.makeText(WrongPass_Activity.this, "Email incorrect,enter again.", Toast.LENGTH_SHORT).show(); 
	    	  return;
	      }
		Toast.makeText(getApplicationContext(), "Sending email", Toast.LENGTH_LONG).show();
	}
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
}
