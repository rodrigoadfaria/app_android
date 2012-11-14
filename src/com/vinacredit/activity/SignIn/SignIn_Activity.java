package com.vinacredit.activity.SignIn;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vinacredit.Resource.*;
import com.vinacredit.activity.R;
import com.vinacredit.activity.InformationAccount.InformationAccount_Activity;
import com.vinacredit.activity.SignIn.WrongPass.WrongPass_Activity;

import con.vinacredit.DTO.Account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn_Activity extends Activity{

	// url to make request
	private static String url = "https://sites.google.com/site/vinacreditdemo/home/sqlite.json";
	
	// JSON Node names
	private static final String TAG_ACCOUNT 	= "sqlite";
	private static final String TAG_EMAIL 		= "mail";
	private static final String TAG_PASS 		= "pass";
	private static final String TAG_FIRSTNAME 	= "firstname";
	private static final String TAG_LASTNAME	= "lastname";
	private static final String TAG_FIRSTLOGIN	= "firstlogin";
	
	
	// contacts JSONArray
	private JSONArray contacts = null;
	private JSONParser jParser;
	private JSONObject json;
	
	
	private EditText 	edtUsername, edtPassword;
	private Button 		btnSignIn, btnWrongPass;
	private TextView	txtTitleBar;
	private MySQLiteHelper dbaccount;
	private Account account;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		edtUsername 	= (EditText)findViewById(R.id.edtUsername);
		edtPassword 	= (EditText)findViewById(R.id.edtPassword);
		btnSignIn 		= (Button)findViewById(R.id.btnSignIn);
		btnWrongPass 	= (Button)findViewById(R.id.btnWrongPass);
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		translate();
		
		
		dbaccount 	= new MySQLiteHelper(this);
		account		= new Account();
		// Creating JSON Parser instance
		jParser = new JSONParser();
		if(MACROS.TEST_SIGNIN_BL){
		    // getting JSON string from URL
		    json = jParser.getJSONFromUrl(url);    
		}
		
		
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(MACROS.TEST_SIGNIN_BL){
				    String email = edtUsername.getText().toString();
					String pass  = edtPassword.getText().toString();
					try {
						// Getting Array of Contacts
						contacts = json.getJSONArray(TAG_ACCOUNT);
						
						// looping through All Contacts
						for(int i = 0; i < contacts.length(); i++)
							{
								JSONObject c = contacts.getJSONObject(i);
							
								// Storing each json item in variable
								account.setEmail(c.getString(TAG_EMAIL));
								account.setPass(c.getString(TAG_PASS));
								account.setFirstName(c.getString(TAG_FIRSTNAME));
								account.setLastName(c.getString(TAG_LASTNAME));
								
								if(c.getString(TAG_EMAIL).equals(email) 
										&& (c.getString(TAG_PASS).equals(pass)))
									{
										Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();
										Intent intent = new Intent(getApplicationContext(),InformationAccount_Activity.class);
										intent.putExtra("EMAIL", email);
										startActivity(intent);
										return;
									}
							}
								Toast.makeText(getApplicationContext(), "Login do not successful", Toast.LENGTH_LONG).show();
							
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				else {
				    Intent intent = new Intent(getApplicationContext(),InformationAccount_Activity.class);
				    startActivity(intent);
				}
				

			}
		});
		
		btnWrongPass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),WrongPass_Activity.class);
				startActivity(i);
			}
		});
	}
	private void translate() {
		// TODO Auto-generated method stub
		edtUsername.setHint(MACROS.SIGNIN_EMAIL_TXT);
		edtPassword.setHint(MACROS.SIGNIN_PASS_TXT);
		btnSignIn.setText(MACROS.SIGNIN_BTN);
		btnWrongPass.setText(MACROS.SIGNIN_FORGOT_BTN);
		txtTitleBar.setText(MACROS.SIGNIN_LBL);
	}

}
