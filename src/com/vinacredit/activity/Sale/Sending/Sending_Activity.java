package com.vinacredit.activity.Sale.Sending;


import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.vinacredit.Resource.Library;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Receipt.Receipt_Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Sending_Activity extends Activity{

	private ImageView	imgSignature;
	private String 		decryption_data;
	private byte[]		b;
	
	int serverResponseCode = 0;
    ProgressDialog dialog = null;
    String ba;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sending);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		imgSignature	= (ImageView)findViewById(R.id.imgSignature);

		
		Bundle extras = getIntent().getExtras();
		b = extras.getByteArray("SIGNATURE");
		imgSignature.setImageBitmap(Library.getBitmapFromByte(b));
		
		Bitmap bit = ((BitmapDrawable)imgSignature.getDrawable()).getBitmap();

		ba = Base64.encodeBytes(Library.getBytesFromBitmap(bit));
		
		SharedPreferences share = this.getSharedPreferences("EMAIL", MODE_PRIVATE);
	    decryption_data = share.getString("DECRYPTION", "");
	    
//        Toast.makeText(getApplicationContext(), decryption_data, Toast.LENGTH_LONG).show();

	}

	public void btnBack(View view){
//		Intent intent = new Intent(getApplicationContext(),Signature_Activity.class);
//		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
	}
	
	public void btnReceipt(View view){
		Intent intent = new Intent(getApplicationContext(),Receipt_Activity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	
	public void send_server(View v){
		
        dialog = ProgressDialog.show(Sending_Activity.this, "", "Sending...", true);
        new Thread(new Runnable() {
               public void run() {                    
            	   uploadFile();                    
               }
             }).start(); 
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
	
	//Send file on server
	public int uploadFile() {
        
        HttpURLConnection conn = null;

            try { 
           
            
              //if you are using https, make sure to import java.net.HttpsURLConnection
          	  URL url=new URL("http://satrungkim.com/demo_iphone.php");

          	  //you need to encode ONLY the values of the parameters
          	  String param="string_1=" + URLEncoder.encode("vinacredit_android","UTF-8")+
          	  "&string_2="+URLEncoder.encode(decryption_data,"UTF-8")+
          	  "&string_3="+URLEncoder.encode("demo","UTF-8") + "&image="+URLEncoder.encode(ba,"UTF-8");
          	  

          	  conn=(HttpURLConnection)url.openConnection();
          	  //set the output to true, indicating you are outputting(uploading) POST data
          	  conn.setDoOutput(true);
          	  //once you set the output to true, you don't really need to set the request method to post, but I'm doing it anyway
          	  conn.setRequestMethod("POST");

          	  //Android documentation suggested that you set the length of the data you are sending to the server, BUT
          	  // do NOT specify this length in the header by using conn.setRequestProperty("Content-Length", length);
          	  //use this instead.
          	  conn.setFixedLengthStreamingMode(param.getBytes().length);
          	  conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
          	  //send the POST out
          	  PrintWriter out = new PrintWriter(conn.getOutputStream());
          	  out.print(param);
          	  out.close();
          	  
              // Responses from the server (code and message)
              serverResponseCode = conn.getResponseCode();
              final String serverResponseMessage = conn.getResponseMessage();
               
              Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);
              if(serverResponseCode == 200){
                  runOnUiThread(new Runnable() {
                       public void run() {
//                           tv.setText("File Upload Completed.");
                           Toast.makeText(Sending_Activity.this, serverResponseMessage + "\n" + "http://www.satrungkim.com/upload/iphone/vinacredit_android.txt" + "\n" 
                        		   			+ "http://www.satrungkim.com/upload/iphone/vinacredit_android.jpg", Toast.LENGTH_SHORT).show();
                       }
                   });  
              }
              
        } catch (MalformedURLException ex) {  
            dialog.dismiss();  
            ex.printStackTrace();
            Toast.makeText(Sending_Activity.this, "MalformedURLException", Toast.LENGTH_SHORT).show();
            Log.e("Upload file to server", "error: " + ex.getMessage(), ex);  
        } catch (Exception e) {
            dialog.dismiss();  
            e.printStackTrace();
            Toast.makeText(Sending_Activity.this, "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("Upload file to server Exception", "Exception : " + e.getMessage(), e);  
        }
        dialog.dismiss();       
        return serverResponseCode;  
       } 
}
