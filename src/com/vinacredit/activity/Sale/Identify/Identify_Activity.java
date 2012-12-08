package com.vinacredit.activity.Sale.Identify;

import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;
import com.vinacredit.activity.Sale.Signature.Signature_Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Identify_Activity extends Activity{

	
	private static final int CAMERA_REQUEST = 9999;
	
	private Button		btnBack, btnSignature;
	private TextView	txtTitleBar;
	private ImageView	imgIdentify;
	private Button		btnTakePhoto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_identify);
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		btnBack			= (Button)findViewById(R.id.btnBack);
		btnSignature	= (Button)findViewById(R.id.btnSignature);
		btnTakePhoto	= (Button)findViewById(R.id.btnTakePhoto);
		txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
		imgIdentify		= (ImageView)findViewById(R.id.imgIdentify);
		translate();		

	}

	private void translate() {
		// TODO Auto-generated method stub
		btnSignature.setText(MACROS.IDENTIFY_RIGHT_BTN);
		btnTakePhoto.setText(MACROS.IDENTIFY_TAKEPHOTO_BTN);
		txtTitleBar.setText(MACROS.IDENTIFY_LBL);
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
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgIdentify.setImageBitmap(photo);
		} else return;
	}
	
	@Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	translate();
    }
	
}
