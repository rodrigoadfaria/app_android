package com.vinacredit.activity.Sale.Receipt;


import com.vinacredit.activity.R;
import com.vinacredit.activity.Done.Done_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class Receipt_Activity extends Activity{

	private EditText	edtEmail;
	private Button		btnSend;
	private MailTLS		m;
	private Intent		intent;
	private static final int	EMAIL_NULL = 1;
	private static final int	EMAIL_INCORRECT = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receipt);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		edtEmail		= (EditText)findViewById(R.id.edtEmail);
		btnSend			= (Button)findViewById(R.id.btnSend);
		btnSend.setEnabled(false);
		
		m = new MailTLS("vinacredit1@gmail.com", "vinacredit1"); 
		m.setFrom("vinacredit1@gmail.com"); 
	    m.setSubject("Receipt"); 
	    m.setBody("Thank for paid!"); 
	    intent = new Intent(getApplicationContext(),Done_Activity.class);
	    
		edtEmail.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				if(actionId == EditorInfo.IME_ACTION_SEND){
				      if(checkEmail() == EMAIL_NULL) {
				    	  Toast.makeText(Receipt_Activity.this, "Enter email,Please!", Toast.LENGTH_SHORT).show(); 
				    	  return true;
				      } else if(checkEmail() == EMAIL_INCORRECT) {
				    	  Toast.makeText(Receipt_Activity.this, "Email incorrect,enter again.", Toast.LENGTH_SHORT).show(); 
				    	  return true;
				      } else if(checkEmail() == 0){
				    	  String[] toArr1 = {edtEmail.getText().toString()};
				    	  m.setTo(toArr1);
				    	  try{
				    		  if(m.send()){
				    			  Toast.makeText(Receipt_Activity.this, "Email was sent successfully.", Toast.LENGTH_SHORT).show(); 
				    	          startActivity(intent);
				    	          return true;
				    		  } else {				    			  
				    	          Toast.makeText(Receipt_Activity.this, "Email was not sent.", Toast.LENGTH_SHORT).show(); 				    			  
				    	          return true;
				  	        }
				    	  } catch(Exception e) { 
				  	        //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
				    		  startActivity(intent);
				  	        Log.e("MailApp", "Could not send email", e); 
				  	      }
				      }
				      
				}
				return false;
			}
		});
		
		
		edtEmail.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(checkEmail() == EMAIL_NULL || checkEmail() == EMAIL_INCORRECT){
					btnSend.setEnabled(false);
					return;
				} else if(checkEmail() == 0){
					btnSend.setEnabled(true);
					return;
				}
			}
		});

	}
	public void sendMail(View view) {	
		 
	      if(checkEmail() == EMAIL_NULL) {
	    	  Toast.makeText(Receipt_Activity.this, "Enter email,Please!", Toast.LENGTH_SHORT).show(); 
	    	  return;
	      } else if(checkEmail() == EMAIL_INCORRECT) {
	    	  Toast.makeText(Receipt_Activity.this, "Email incorrect,enter again.", Toast.LENGTH_SHORT).show(); 
	    	  return;
	      }
	      
	      String[] toArr = {edtEmail.getText().toString()}; 
	      m.setTo(toArr);       

	      
	      try { 
//	        m.addAttachment("/sdcard/filelocation"); 
	 
	        if(m.send()) { 
	          Toast.makeText(Receipt_Activity.this, "Email was sent successfully.", Toast.LENGTH_SHORT).show(); 
	          startActivity(intent);
	        } else { 
	          Toast.makeText(Receipt_Activity.this, "Email was not sent.", Toast.LENGTH_SHORT).show();

	        } 
	      } catch(Exception e) { 
	        //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show(); 
	        	startActivity(intent);
	        Log.e("MailApp", "Could not send email", e); 
	      } 
		
	    }
	
	public void btnSkip(View view){
		Toast.makeText(getApplicationContext(), "Skip send email", Toast.LENGTH_SHORT ).show();
		Intent intent = new Intent(getApplicationContext(),Done_Activity.class);
		startActivity(intent);
		finish();
	}

	public int checkEmail(){
	      if(edtEmail.getText().toString().equals("")) {
//	    	  Toast.makeText(Receipt_Activity.this, "Enter email,Please!", Toast.LENGTH_SHORT).show(); 
	    	  return EMAIL_NULL;
	      } else if(!edtEmail.getText().toString().contains("@") || !edtEmail.getText().toString().contains(".com")) {
//	    	  Toast.makeText(Receipt_Activity.this, "Email incorrect,enter again.", Toast.LENGTH_SHORT).show(); 
	    	  return EMAIL_INCORRECT;
	      }	      
	      return 0;
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
