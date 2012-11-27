package com.vinacredit.activity.Sale;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
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
    
    private MySQLiteHelper 	dbSqlite;
	private Account			account;
    private Library			library;
    private SaleAdapter		saleAdapter;
    private List<DataItem>	ListdataItem;
    private DataItem		dataItem;
    private Bitmap			bpPhoto;
    private int count = 1;
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
		library = new Library();
		if(MACROS.TEST_SIGNIN_BL) {
		Bundle extras = getIntent().getExtras();
		account = dbSqlite.getAccount(extras.getString("EMAIL"));
	    imgUsername.setImageBitmap(library.getBitmapFromByte(account.getImageAcc()));
		}
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
    	i.putExtra("EMAIL", account.getEmail());
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
    	ListdataItem.add(new DataItem(dataItem.getImgItem(), dataItem.getStrItem(), dataItem.getQuantityItem(), dataItem.getPriceItem()));
    	saleAdapter.notifyDataSetChanged();
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
    	count++;
    	Toast.makeText(getApplicationContext(), "demo plus item " + i, Toast.LENGTH_SHORT).show();
    	dataItem = ListdataItem.get(i);
    	dataItem.setQuantityItem(count + "x");
    	saleAdapter.notifyDataSetChanged();
    }
    
    /**
     * minus price item
     * @param v
     */
    public void MinusItem(View v){
    	int ix = listSale.getPositionForView(v);
    	Toast.makeText(getApplicationContext(), "demo minus item" + ix, Toast.LENGTH_SHORT).show();
    	count--;
    	dataItem = ListdataItem.get(ix);
    	dataItem.setQuantityItem(count + "x");
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
	if(_str_tmp.length() > 3)
	    _str_tmp = Library.addDotNumber(_str_tmp);
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
    		_str_tmp = _str_tmp.replaceAll(",","");
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
