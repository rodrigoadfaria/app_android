package com.vinacredit.activity.Sale.Identify;


import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Signature.Signature_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Identify_Activity extends Activity{

	
	private static final int CAMERA_REQUEST = 9999;

	private ImageView	imgIdentify;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_identify);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		imgIdentify		= (ImageView)findViewById(R.id.imgIdentify);


	}
	
	public void btnBack(View view){
//		Intent intent = new Intent(getApplicationContext(),Sale_Activity.class);
//		startActivity(intent);
		finish();
	}

	public void btnSignature(View view){
		Intent intent = new Intent(getApplicationContext(),Signature_Activity.class);
		startActivity(intent);
	}
	
	public void btnTakePhoto(View view){
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAMERA_REQUEST);
	}
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_REQUEST) {
			if(resultCode == RESULT_OK){
				if(data != null){
					Bitmap photo = (Bitmap) data.getExtras().get("data");
		            imgIdentify.setImageBitmap(photo);
				} else
					Toast.makeText(getApplicationContext(), "No image.", Toast.LENGTH_SHORT).show();
			}
            if(resultCode == RESULT_CANCELED){
            	Toast.makeText(getApplicationContext(), "Picture could not be taken.", Toast.LENGTH_SHORT).show();
            }
		} 
	}
	
	@Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
	
}
