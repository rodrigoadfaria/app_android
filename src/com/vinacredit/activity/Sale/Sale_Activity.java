package com.vinacredit.activity.Sale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vinacredit.Resource.MACROS;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.Account_Activity;
import com.vinacredit.activity.Account.ListItem;
import com.vinacredit.activity.Sale.Charge.Charge_Activity;
import com.vinacredit.activity.Sale.Identify.Identify_Activity;



public class Sale_Activity extends Activity{
	
	private static final int CAMERA_REQUEST = 1888;
	
    private Button 		btn_gotoAccount;
    private Button 		btn_gotoCharge;
    private TextView	txtTitleBar;
    private Button	btnItem, btnClear;
    private ImageView	imgUsername;
    private TextView	txtPriceItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_sale);
    	initialize();
    }
    private void initialize() {
    	// TODO Auto-generated method stub
    	btn_gotoAccount = (Button)findViewById(R.id._btnAccount);
    	btn_gotoCharge  = (Button)findViewById(R.id._btnSaleCharge);
    	txtTitleBar		= (TextView)findViewById(R.id.txtTitleBar);
    	btnItem			= (Button)findViewById(R.id.btnItem);
    	btnClear		= (Button)findViewById(R.id.btnClear);
    	imgUsername		= (ImageView)findViewById(R.id.imgUsername);
    	txtPriceItem	= (TextView)findViewById(R.id.txtPriceItem);
    	
    	/* tranlate language */
    	translate();

    }
    
    private void translate() {
	// TODO Auto-generated method stub
    	txtTitleBar.setText(MACROS.SALE_LBL);
    }
    
    /**
     * button account
     * @param view
     */
    public void gotoAccount(View view){
    	Intent i = new Intent(getApplicationContext(),Account_Activity.class);
		startActivity(i);
    }
    
    /**
     * button charge
     * @param view
     */
    public void gotoCharge(View view){
    	Intent i = new Intent(getApplicationContext(),Charge_Activity.class);
		startActivity(i);
    }
    
    /**
     * clear bill
     * @param view
     */
    public void clear(View view){
    	
    }
    
    /**
     * take a photo item
     * @param view
     */
    public void takeItem(View view){
    	
    	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    	startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    
    /**
     * button number pad
     * @param view
     */
    public void numClick(View view){
    	
    }
    
    /**
     * button clear number pad
     * @param view
     */
    public void clearClick(View view){
    	
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
            imgUsername.setImageBitmap(photo);
		}
	}
    
}
