package com.vinacredit.activity.Sale.Receipt;

import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Done.Done_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	    
	    try {
	        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

	        String[] recipients = new String[]{"nguyen@gmail.com"};
	        String[] ccList = { "vinacredit@gmail.com"};

	        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, recipients);
	        emailIntent.putExtra(android.content.Intent.EXTRA_CC, ccList);

	        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Receipt demo.");
	        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hi you, \n detail... ");

	        emailIntent.setType("text/plain");

	        startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
//	        finish();
	        
	        
	        
	    } catch (Exception e) {
		//Common.showMessage(getBaseContext(), e.toString());
	    }

	    //startActivity(i_inviteFriend );
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
