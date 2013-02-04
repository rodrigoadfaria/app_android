package com.vinacredit.activity.Done;

import com.vinacredit.Resource.Library;
import com.vinacredit.Resource.MACROS;
import com.vinacredit.Resource.MySQLiteHelper;
import com.vinacredit.activity.R;
import com.vinacredit.activity.Sale.Sale_Activity;

import con.vinacredit.DTO.Bill;
import con.vinacredit.DTO.SumBill;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Done_Activity extends Activity{

	private ImageView	imgUsername;
	private TextView 	txtSumPrice;
	static String email;
	private MySQLiteHelper mDb;
	private SumBill			sumBill;
    private Bill			bill;
    private con.vinacredit.DTO.Account account;
	private SharedPreferences s,sCard;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_done);
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		txtSumPrice		= (TextView)findViewById(R.id.txtSumPrice);
		imgUsername		= (ImageView)findViewById(R.id.imgUsername);
		
		
		s = this.getSharedPreferences("EMAIL", MODE_PRIVATE);
		sCard = this.getSharedPreferences("CHECKCARD", MODE_PRIVATE);
		txtSumPrice.setText(s.getString("SUMPRICE", "0")+ " VND.");
		email = s.getString("EMAIL", "NOTHING");
		
		mDb = new MySQLiteHelper(this);
    	sumBill = new SumBill();
    	bill = new Bill();
		account	= new con.vinacredit.DTO.Account();
    	
    	if(MACROS.TEST_DATABASE){
    		account = mDb.getAccount(email);
    	    imgUsername.setImageBitmap(Library.getBitmapFromByte(account.getImageAcc()));
    	}
	}
	
	public void btnDone(View view){	
    	if (MACROS.TEST_DATABASE) {
			sumBill.setEmail(s.getString("EMAIL", "NOTHING"));
			sumBill.setDateSale(Library.getDate());
			sumBill.setSumBill(s.getString("SUMPRICE", "0").replaceAll(",",""));
			
			bill.setEmail(s.getString("EMAIL", "NOTHING"));
			bill.setDateSale(Library.getDate());
			bill.setSumItem(s.getString("SUMPRICE", "0").replaceAll(",",""));
			bill.setTimeSale(Library.getTime());
			bill.setCheckCard(sCard.getString("CHECKCARD", null));
			try {
				mDb.insertBill(sumBill, bill);
			} catch (SQLException e) {
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
			}
		}
		Intent intent = new Intent(getApplicationContext(),Sale_Activity.class);	
		intent.putExtra("EMAIL",email);
		startActivity(intent);
		finish();
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
