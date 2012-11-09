package com.vinacredit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import con.vinacredit.DTO.Account;
 
 
public class DataBaseManager extends SQLiteOpenHelper {
 
    // The Android's default system path of your application database.
    //data/data/ and /databases remain the same always. The one that must be changed is com.example which represents
    //the MAIN package of your project
    private static String DB_PATH = "/data/data/com.vinacredit/databases/";
 
    //the name of your database
    private static String DB_NAME = "database";
 
    private static SQLiteDatabase mDataBase;
 
    private static DataBaseManager sInstance = null;
    // database version    
    private static final int DATABASE_VERSION = 1;
 
    /**
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     */
    private DataBaseManager() {
        super(ApplicationContextProvider.getContext(), DB_NAME, null, DATABASE_VERSION);
 
        try {
            createDataBase();
            openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Singleton for DataBase
     *
     * @return singleton instance
     */
    public static DataBaseManager instance() {
 
        if (sInstance == null) {
            sInstance = new DataBaseManager();
        }
        return sInstance;
    }
 
 
    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     *
     * @throws java.io.IOException io exception
     */
    private void createDataBase() throws IOException {
 
        boolean dbExist = checkDataBase();
 
        if (dbExist) {
            // do nothing - database already exist
        } else {
 
            // By calling this method an empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();
 
            try {
 
                copyDataBase();
 
            } catch (IOException e) {
 
                throw new Error("Error copying database");
            }
        }
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
 
        SQLiteDatabase checkDB = null;
 
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
 
        } catch (SQLiteException e) {
 
            // database doesn't exist yet.
 
        }
 
        if (checkDB != null) {
 
            checkDB.close();
 
        }
 
        return checkDB != null;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     *
     * @throws java.io.IOException io exception
     */
    public void copyDataBase() throws IOException {
 
        // Open your local db as the input stream
        InputStream myInput = ApplicationContextProvider.getContext().getAssets().open(DB_NAME);
 
        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;
 
        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
 
        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
 
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
 
    }
 
    private void openDataBase() throws SQLException {
 
        // Open the database
        String myPath = DB_PATH + DB_NAME;
        mDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE);
    }    
    /**
     * Let you make a raw query
     *
     * @param command - the sql comand you want to run
     */
    public void sqlCommand(String command) {
        mDataBase.execSQL(command);
    }
 
    @Override
    public synchronized void close() {
 
        if (mDataBase != null)
            mDataBase.close();
 
        super.close();
 
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
 
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
    }
    
    /*
     * get single Account where email 
     */
    public Account getAccount(String email){
    	Account account = new Account();
    	mDataBase = this.getReadableDatabase();
    	String select = "SELECT * FROM Account WHERE email = '" + email+"'";    	
    	Cursor  cursor = mDataBase.rawQuery(select, null);
    	if(cursor != null)
    		cursor.moveToFirst();
    	try {
			account.setEmail(cursor.getString(0));
			account.setFirstName(cursor.getString(1));
			account.setLastName(cursor.getString(2));
			account.setCompanyName(cursor.getString(3));
			account.setPass(cursor.getString(4));
			account.setImageAcc(cursor.getBlob(5));
			account.setAddress(cursor.getString(6));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	mDataBase.close();
    	cursor.close();
    	return account;
    }
    
    /*
     * get All Account
     */
    public ArrayList<Account> getAllAccount(){
    	ArrayList<Account> accountList = new ArrayList<Account>();
    	String selectQuery = "SELECT email,pass FROM Account";
    	mDataBase = this.getReadableDatabase();
    	Cursor cursor = mDataBase.rawQuery(selectQuery, null);
    	
    	if(cursor.moveToFirst()){
    		do {
    			Account account = new Account();
    			account.setEmail(cursor.getString(0));
    			account.setPass(cursor.getString(1));
    			//Adding account to list
    			accountList.add(account);
    		} while(cursor.moveToNext());
    	}
    	cursor.close();
    	mDataBase.close();
    	return accountList;
    }
 
}