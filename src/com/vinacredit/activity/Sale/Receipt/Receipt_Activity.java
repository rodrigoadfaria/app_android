package com.vinacredit.activity.Sale.Receipt;


import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Done.Done_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Receipt_Activity extends Activity{

	private ImageView	imgUsername;
	private EditText	edtEmail;
	private Button		btnSkip;
	private Button		btnSend;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receipt);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		imgUsername		= (ImageView)findViewById(R.id.imgUsername);
		edtEmail		= (EditText)findViewById(R.id.edtEmail);
		btnSkip			= (Button)findViewById(R.id.btnSkip);
		btnSend			= (Button)findViewById(R.id.btnSend);
		
		translate();

	}
	public void sendMail(View view) {

		Intent i = new Intent(getApplicationContext(),Done_Activity.class);
		Mail m = new Mail("vinacredit1@gmail.com", "vinacredit1"); 
		 
	      String[] toArr = {edtEmail.getText().toString()}; 
	      m.setTo(toArr); 
	      m.setFrom("vinacredit1@gmail.com"); 
	      m.setSubject("Receipt"); 
	      m.setBody("Thank for paid!"); 
	 
	      try { 
//	        m.addAttachment("/sdcard/filelocation"); 
	 
	        if(m.send()) { 
	          Toast.makeText(Receipt_Activity.this, "Email was sent successfully.", Toast.LENGTH_LONG).show(); 
	          startActivity(i);
	        } else { 
	          Toast.makeText(Receipt_Activity.this, "Email was not sent.", Toast.LENGTH_LONG).show(); 
	        } 
	      } catch(Exception e) { 
	        //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
	        Log.e("MailApp", "Could not send email", e); 
	      } 
		
	    }
	
	public void btnSkip(View view){
		Toast.makeText(getApplicationContext(), "Skip send email", Toast.LENGTH_LONG ).show();
		Intent intent = new Intent(getApplicationContext(),Done_Activity.class);
		startActivity(intent);
		finish();
	}
	
	private void translate() {
		// TODO Auto-generated method stub
		edtEmail.setHint(MACROS.RECEIPT_EMAIL_TXT);
		btnSkip.setText(MACROS.RECEIPT_SKIP_BTN);
		btnSend.setText(MACROS.RECEIPT_SEND_BTN);
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
}
