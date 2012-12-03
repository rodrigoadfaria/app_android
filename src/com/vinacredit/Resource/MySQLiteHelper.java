
package com.vinacredit.Resource;

import java.util.ArrayList;
import java.util.List;

import con.vinacredit.DTO.Account;
import con.vinacredit.DTO.Bill;
import con.vinacredit.DTO.SumBill;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper 
{ 
    
    private static final String DATABASE_NAME = "database";
    private static final String TAG = "DBAdapter";
    private static final int DATABASE_VERSION = 1;
    
    /*----- Set name table database -----*/
    private static final String DATABASE_TABLE_ACCOUNT = "Account";
    private static final String DATABASE_TABLE_BILL = "Bill";
    private static final String DATABASE_TABLE_SUMBILL = "SumBill";
    
    /*----- Set key name in table database ACCOUNT -----*/
    public static final String KEY_EMAIL_ACCOUNT	= "email";
    public static final String KEY_PASSWORD 		= "password";
    public static final String KEY_FIRSTNAME 		= "firstname";
    public static final String KEY_LASTNAME			= "lastname";
    public static final String KEY_COMPANY 			= "company";
    public static final String KEY_ADDRESS 			= "address";
    public static final String KEY_IMAGE			= "image";
    
    /*----- Set key name in table database BILL -----*/
    public static final String KEY_TIMESALE			= "timeSale";
    public static final String KEY_SUMITEM 			= "sumItem";
    public static final String KEY_EMAIL_BILL 		= "email";
    public static final String KEY_DATESALE_BILL	= "dateSale";
    
    /*----- Set key name in table database SUMBILL -----*/
    public static final String KEY_DATESALE_SUMBILL	= "dateSale";
    public static final String KEY_SUMBILL_SUMBILL	= "sumBill";
    public static final String KEY_EMAIL_SUMBILL 	= "email";
    

    private static final String DATABASE_CREATE_ACCOUNT =
        "create table Account ("
        + "email text primary key not null," 
        + "password text not null,"
        + "firstname text,"
        + "lastname text,"
        + "company text,"
        + "address text,"
        + "image blob);";
    
    private static final String DATABASE_CREATE_BILL = 
    	"create table Bill ("
    	+ "timeSale text,"
    	+ "sumItem text,"
    	+ "email text,"
    	+ "dateSale text);";
    
    private static final String DATABASE_CREATE_SUMBILL = 
    	"create table SumBill ( "
    	+ "dateSale text,"
    	+ "sumBill text,"
    	+ "email text);";
    
    
    
    
    private Context context = null;  
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public MySQLiteHelper(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE_ACCOUNT);
            db.execSQL(DATABASE_CREATE_BILL);
            db.execSQL(DATABASE_CREATE_SUMBILL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS Account;");
            db.execSQL("DROP TABLE IF EXISTS Bill;");
            db.execSQL("DROP TABLE IF EXISTS SumBill;");
            onCreate(db);
        }
    }    
    
    
    public void open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
    }

      
    public void close() 
    {
        DBHelper.close();
    }    

    /**
     * @param email
     * @return
     */
    public boolean getAccountCount(String email){
    	open();
    	Cursor cursor = db.rawQuery("SELECT * FROM "+ DATABASE_TABLE_ACCOUNT + " WHERE email=?", new String[]{email});
    	if(cursor.getCount() > 0)
    		return true;
    	cursor.close();
    	close();    	
    	return false;
    }
    
    /**
     * @param account
     * @return
     * @throws SQLException
     */
    public void AddAccount(Account account) throws SQLException 
    {
    	open();
    	ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAIL_ACCOUNT, account.getEmail());
        initialValues.put(KEY_PASSWORD, account.getPass());
        initialValues.put(KEY_FIRSTNAME,account.getFirstName());
        initialValues.put(KEY_LASTNAME,account.getLastName());
        initialValues.put(KEY_COMPANY,account.getCompanyName());
        initialValues.put(KEY_ADDRESS,account.getAddress());
        initialValues.put(KEY_IMAGE,account.getImageAcc());
        
        db.insert(DATABASE_TABLE_ACCOUNT, null, initialValues);
        close();
    }


    /**
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean Login(String email, String password) throws SQLException 
    {
    	open();
    	Cursor mCursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_ACCOUNT + " WHERE email=? AND password=?", new String[]{email,password});
        if (mCursor != null) {           
            if(mCursor.getCount() > 0)
            {
            	return true;
            }
        }
        close();
        mCursor.close();
     return false;
    }
    
    /**
     * @param email
     * @return Account
     * @throws SQLException
     */
    public Account getAccount(String email) throws SQLException{
    	open();
    	Account account = new Account();
    	Cursor mcursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_ACCOUNT + " WHERE email=?", new String[]{email});    	
    	
    	while(mcursor.moveToNext()){
    		account.setEmail(mcursor.getString(0));
    		account.setPass(mcursor.getString(1));
    		account.setFirstName(mcursor.getString(2));
    		account.setLastName(mcursor.getString(3));
    		account.setCompanyName(mcursor.getString(4));
    		account.setAddress(mcursor.getString(5));
    		account.setImageAcc(mcursor.getBlob(6));
    	}
    	mcursor.close();
    	close();
    	return account;
    }

    /**
     * @param bill
     * @return
     * @throws SQLException
     */
    public void AddBill(Bill bill) throws SQLException{
    	open();
    	ContentValues initialValues = new ContentValues();
    	initialValues.put(KEY_TIMESALE,bill.getTimeSale());
    	initialValues.put(KEY_SUMITEM, bill.getSumItem());
    	initialValues.put(KEY_EMAIL_BILL,bill.getEmail());
    	initialValues.put(KEY_DATESALE_BILL,bill.getDateSale());
    	
    	db.insert(DATABASE_TABLE_BILL, null, initialValues);
    	close();
    }
    
    /**
     * @param sumbill
     * @return
     * @throws SQLException
     */
    public void AddSumBill(SumBill sumbill) throws SQLException {
    	open();
    	ContentValues initialValues = new ContentValues();
    	initialValues.put(KEY_DATESALE_SUMBILL, sumbill.getDateSale());
    	initialValues.put(KEY_SUMBILL_SUMBILL, sumbill.getSumBill());
    	initialValues.put(KEY_EMAIL_SUMBILL, sumbill.getEmail());
    	
    	db.insert(DATABASE_TABLE_SUMBILL, null, initialValues);
    	close();
    }
    
    /**
     * @param email
     * @return
     * @throws SQLException
     */
    public List<SumBill> getSumBill(String email) throws SQLException {
    	
    	String update = "UPDATE SumBill SET sumBill = (select sum(Bill.sumItem) from Bill where SumBill.email = Bill.email and SumBill.dateSale = Bill.dateSale)";
    	List<SumBill> sbList = new ArrayList<SumBill>();
    	SumBill sumbill = new SumBill();
    	open();
    	db.execSQL(update);
    	Cursor mcursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_SUMBILL + " WHERE email=?", new String[]{email}); 
    	
    	
    	while(mcursor.moveToNext()){
    		sumbill.setDateSale(mcursor.getString(0));
    		sumbill.setSumBill(mcursor.getString(1));
    		sumbill.setEmail(mcursor.getString(2));
    		
    		sbList.add(sumbill);
    	}
    	mcursor.close();
    	close();
    	return sbList;
    }
    
    /**
     * @param email
     * @param dateSale
     * @return
     * @throws SQLException
     */
    public List<Bill> getBill(String email, String dateSale) throws SQLException {
    	List<Bill> bList = new ArrayList<Bill>();
    	Bill bill = new Bill();
    	open();
    	Cursor mcursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_BILL + " WHERE email=? AND dateSale=?", new String[]{email,dateSale}); 
    	
    	
    	while (mcursor.moveToNext()) {
			bill.setTimeSale(mcursor.getString(0));
			bill.setSumItem(mcursor.getString(1));
			bill.setEmail(mcursor.getString(2));
			bill.setDateSale(mcursor.getString(3));
			
			bList.add(bill);
		}    	
    	mcursor.close();
    	close();
    	return bList;
    }
    
    /**
     * @param email
     * @return
     * @throws SQLException
     */
    public Cursor selectSumBill(String email) throws SQLException {
    	String update = "UPDATE SumBill SET sumBill = (select sum(Bill.sumItem) from Bill where SumBill.email = Bill.email and SumBill.dateSale = Bill.dateSale)";    	
    	open();
    	db.execSQL(update);
    	Cursor mcursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_SUMBILL + " WHERE email=?", new String[]{email});
    	if (mcursor != null) {
    		   mcursor.moveToFirst();
    		  }
    	close();
    	return mcursor;
    }
    
    /**
     * @param email
     * @param dateSale
     * @return
     * @throws SQLException
     */
    public Cursor selectBill(String email, String dateSale) throws SQLException {
    	open();
    	Cursor mcursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE_BILL + " WHERE email=? AND dateSale=?", new String[]{email,dateSale}); 
    	if (mcursor != null) {
 		   mcursor.moveToFirst();
 		  }
    	close();
    	return mcursor;
    }
    
    /**
     * @param email
     * @param dateSale
     * @return
     */
    public boolean isCheckDate(String email, String dateSale) {
    	open();    	
    	Cursor mcursor = db.rawQuery("SELECT dateSale FROM " +DATABASE_TABLE_SUMBILL + " WHERE email=?", new String[]{email});

    	
    	while (mcursor.moveToNext()) {			
			if(dateSale.equals(mcursor.getString(0)))
				return true;
		} 	
    	mcursor.close();
    	close();
    	return false;
    }    
    
    /**
     * @param sumbill
     * @param bill
     * @throws SQLException
     */
    public void insertBill(SumBill sumbill, Bill bill) throws SQLException {
    	if(isCheckDate(sumbill.getEmail(), sumbill.getDateSale())) {
    		AddBill(bill);
    	} else {
			AddSumBill(sumbill);
			AddBill(bill);
		}
    }

}
