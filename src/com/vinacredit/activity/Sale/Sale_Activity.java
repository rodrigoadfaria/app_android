package com.vinacredit.activity.Sale;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vinacredit.Resource.Library;
import com.vinacredit.Resource.MACROS;
import com.vinacredit.Resource.MySQLiteHelper;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Account.Account_Activity;
import com.vinacredit.activity.Sale.Charge.Charge_Activity;
import com.vinacredit.activity.Sale.Identify.Identify_Activity;

import con.vinacredit.DTO.Account;



public class Sale_Activity extends Activity{
	
	private static final int CAMERA_REQUEST = 1888;
	
    private Button 		btn_gotoAccount, btn_gotoCharge;
    private TextView	txtTitleBar;
    private Button		btnTakeItem, btnClear;
    private ImageView	imgUsername, imgItem;
    private TextView	txtPriceItem, txtItem;
    private ListView	listSale;
    private EditText	edtItem;
    
    private String		_str_tmp = "";
    private String 		_str_number_click = "";
    private String		_str_total_price = "0";
    private MySQLiteHelper 	dbSqlite;
	private Account			account;
    private SaleAdapter		saleAdapter;
    private List<DataItem>	ListdataItem;
    private DataItem		dataItem;
    private Bitmap			bpPhoto;
    
    private Bundle extras;
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
    	btnTakeItem		= (Button)findViewById(R.id.btnTakeItem);
    	btnClear		= (Button)findViewById(R.id.btnClear);
    	imgUsername		= (ImageView)findViewById(R.id.imgUsername);
    	txtPriceItem	= (TextView)findViewById(R.id.txtPriceItem);
    	listSale		= (ListView)findViewById(R.id.listSale);
    	txtItem			= (TextView)findViewById(R.id.txtItem);
    	edtItem			= (EditText)findViewById(R.id.edtItem);
    	imgItem			= (ImageView)findViewById(R.id.imgItem);
    	/* tranlate language */
    	translate();
    	
    	dataItem	 = new DataItem();
    	ListdataItem = new ArrayList<DataItem>();
    	saleAdapter  = new SaleAdapter(this, ListdataItem);
    	listSale.setAdapter(saleAdapter);
    	
    	/* database instance */
    	dbSqlite = new MySQLiteHelper(this);
    	
    	/* account instance */
		account  = new Account();		
		
		extras = getIntent().getExtras();
		if(MACROS.TEST_DATABASE) {
		
		account = dbSqlite.getAccount(extras.getString("EMAIL"));
	    imgUsername.setImageBitmap(Library.getBitmapFromByte(account.getImageAcc()));
		}
	    
    }
    
    private void translate() {
	// TODO Auto-generated method stub
    	txtTitleBar.setText(MACROS.SALE_LBL);
    	btn_gotoAccount.setText(MACROS.SALE_ACCOUNT);
    	btn_gotoCharge.setText(MACROS.SALE_CHARGE);
    	
    }
    
    /**
     * button account
     * @param view
     */
    public void gotoAccount(View view){
    	Intent i = new Intent(getApplicationContext(),Account_Activity.class);
    	i.putExtra("EMAIL", account.getEmail());
		startActivity(i);
    }
    
    /**
     * button charge
     * @param view
     */
    public void gotoCharge(View view){
    	
    	Intent i = new Intent(getApplicationContext(),Charge_Activity.class);
    	Bundle myBundle = new Bundle();
    	myBundle.putString("EMAIL", account.getEmail());
    	myBundle.putString("PRICEITEM", txtPriceItem.getText().toString());
    	i.putExtras(myBundle);
		startActivity(i);
    }
    
    /**
     * clear bill
     * @param view
     */
    public void clear(View view){
    	ListdataItem.clear();
    	_str_tmp = "";
    	_str_total_price = "0";
    	_str_number_click = "";
    	txtPriceItem.setText(Library.addDotNumber(_str_total_price));
    	saleAdapter.notifyDataSetChanged();
    }
    
    /**
     * take a photo item
     * @param view
     */
    public void takeItem(View view){
    	
    	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    	startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    
    public void btnIdentify(View view){
    	Intent i = new Intent(getApplicationContext(),Identify_Activity.class);
		startActivity(i);
    }
    
    /**
     * add item
     * @param view
     */
    public void AddItem(View view){
//    	Toast.makeText(getApplicationContext(), "demo add item", Toast.LENGTH_LONG).show();
    	dataItem.setImgItem(bpPhoto);
    	dataItem.setStrItem(edtItem.getText().toString());
    	dataItem.setQuantityItem("1x");
    	dataItem.setPriceItem(txtItem.getText().toString());
    	if(_str_tmp.length() > 0){
    		ListdataItem.add(0,new DataItem(dataItem.getImgItem(), dataItem.getStrItem(), dataItem.getQuantityItem(), dataItem.getPriceItem()));
        	saleAdapter.notifyDataSetChanged();
        	
        	/*------ assign price item into total price ------*/
        	_str_total_price = String.valueOf(Integer.parseInt(_str_total_price) + Integer.parseInt(_str_tmp));
        	txtPriceItem.setText(Library.addDotNumber(_str_total_price));
    	} else {
			Toast.makeText(getApplicationContext(), "Enter price item,Please!", Toast.LENGTH_SHORT).show();
		}    	
	    
    	/*------ reset variable ------*/
    	imgItem.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.chomsao));
    	bpPhoto = null;
    	_str_tmp="";
    	txtItem.setText("0");
    	edtItem.setText("");
    }
    
    /**
     * Plus price item
     * @param v
     */
    public void PlusItem(View v){
    	int i = listSale.getPositionForView(v);
    	Toast.makeText(getApplicationContext(), ListdataItem.get(i).getStrItem(), Toast.LENGTH_SHORT).show();
    	int count = ListdataItem.get(i).getQtyItem();
    	count++;
    	ListdataItem.get(i).setQtyItem(count);
    	ListdataItem.get(i).setQuantityItem(count + "x");
    	String _str_p = ListdataItem.get(i).getPriceItem().replaceAll(",", "");
    	/*------ assign price item into total price ------*/
    	_str_total_price = String.valueOf(Integer.parseInt(_str_total_price) + Integer.parseInt(_str_p));
    	txtPriceItem.setText(Library.addDotNumber(_str_total_price));
    	
    	saleAdapter.notifyDataSetChanged();
    }
    
    /**
     * minus price item
     * @param v
     */
    public void MinusItem(View v){
    	int ix = listSale.getPositionForView(v);
    	Toast.makeText(getApplicationContext(), ListdataItem.get(ix).getStrItem(), Toast.LENGTH_SHORT).show();
    	int count = ListdataItem.get(ix).getQtyItem();
    	String _str_p = ListdataItem.get(ix).getPriceItem().replaceAll(",", "");
    	count--;
    	/*------ assign price item into total price ------*/
    	_str_total_price = String.valueOf(Integer.parseInt(_str_total_price) - Integer.parseInt(_str_p));
    	txtPriceItem.setText(Library.addDotNumber(_str_total_price));
    	
    	if(count < 1) 
    		{
    			ListdataItem.remove(ix);
    			saleAdapter.notifyDataSetChanged();
    			return;
    		}
    	ListdataItem.get(ix).setQtyItem(count);
    	ListdataItem.get(ix).setQuantityItem(count + "x");    	
    	
    	saleAdapter.notifyDataSetChanged();
    }
    
    /**
     * button number pad
     * @param view
     */
    public void numClick(View view){
    	
    	Log.i("Debug charge","view.getId :" + view.getId());
	
    	switch (view.getId()) {
		case R.id.btn10:
		    _str_number_click = "1";
			break;
    
		case R.id.btn11:
		    _str_number_click = "2";
			break;
    
		case R.id.btn12:
		    _str_number_click = "3";
			break;

		case R.id.btn7:
		    _str_number_click = "4";
			break;
    
		case R.id.btn8:
		    _str_number_click = "5";
			break;
    
		case R.id.btn9:
		    _str_number_click = "6";
			break;
    
		case R.id.btn4:
		    _str_number_click = "7";
			break;
    
		case R.id.btn5:
		    _str_number_click = "8";
			break;
    
		case R.id.btn6:
		    _str_number_click = "9";
			break;
    
		case R.id.btn2:
		    _str_number_click = "0";
		    break;
		case R.id.btn1:
		    _str_number_click = "000";
		    break;
		default:
			break;
	}
    	if(_str_tmp.length() > 13) return; // 9,000,000,000 length is 13.
	_str_tmp = _str_tmp + _str_number_click;
	if(_str_tmp.length() > 3) {
		txtItem.setText(Library.addDotNumber(_str_tmp));
		return;
	}		
	Log.i("Debug Charge","_str_tmp :" + _str_tmp);
	txtItem.setText(_str_tmp);
    }
    
    /**
     * button clear number pad
     * @param view
     */
    public void clearClick(View view){
    	if(view.getId() == R.id.btn3){
//    	    String _str = txtItem.getText().toString();
    		if(_str_tmp.length() < 1){
    			txtItem.setText("0");
    		} else {
    			_str_tmp = _str_tmp.substring(0,_str_tmp.length()-1);
    			if(_str_tmp.length() > 3)
    				txtItem.setText(Library.addDotNumber(_str_tmp));
    			else
    				txtItem.setText(_str_tmp);
    			if(_str_tmp.length() < 1){
        			txtItem.setText("0");
        		}
    		}    		
    	}
    }
    
    @Override
    public void onBackPressed() {
    	// TODO Auto-generated method stub
//    	super.onBackPressed();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	translate();
    }
    
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreferences share = this.getSharedPreferences("EMAIL",MODE_WORLD_READABLE );
	    SharedPreferences.Editor editor = share.edit();
	    editor.putString("EMAIL",extras.getString("EMAIL"));
	    editor.putString("SUMPRICE", txtPriceItem.getText().toString());
	    editor.commit();
	}
	/* (non-Javadoc)
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAMERA_REQUEST) {
            bpPhoto = (Bitmap) data.getExtras().get("data");
            imgItem.setImageBitmap(bpPhoto);
		}
	}
    
}
