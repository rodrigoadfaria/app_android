package com.vinacredit.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONDAO {
		
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
	
	public JSONDAO(){}
	
	public boolean login(String url, String email, String pass){
		jParser = new JSONParser();
	    // getting JSON string from URL
	    json = jParser.getJSONFromUrl(url);
		// Getting Array of Contacts
		try {
			contacts = json.getJSONArray(TAG_ACCOUNT);
		
		
		// looping through All Contacts
		for(int i = 0; i < contacts.length(); i++)
			{
				JSONObject c = contacts.getJSONObject(i);
			
				// Storing each json item in variable
				if(c.getString(TAG_EMAIL).equals(email) && c.getString(TAG_PASS).equals(pass))
					return true;
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
