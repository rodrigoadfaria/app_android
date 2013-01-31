package com.vinacredit.activity.Welcome;


import java.util.Locale;

import com.vinacredit.activity.R;
import com.vinacredit.activity.SignIn.SignIn_Activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.vinacredit.Resource.*;
public class Welcome_Activity extends Activity {

	private Button btnSignIn;
	private TextView txt_welcome, txt_content;
	Locale myLocale;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initialize();
    }

	private void initialize() {
		// TODO Auto-generated method stub
		btnSignIn = (Button)findViewById(R.id.btnSignIn);
//		txt_welcome 	= (TextView)findViewById(R.id.txt_welcome);
//		txt_content 	= (TextView)findViewById(R.id.txt_content);

	}
	public void translate(){
	    txt_welcome.setText(MACROS.WELCOME_TO_VINACREDIT_LBL);
	    txt_content.setText(MACROS.WELCOME_TEXT_LBL);
	    btnSignIn.setText(MACROS.WELCOME_SIGNIN_BTN);
	}

	/**
	 * sign in
	 * @param view
	 */
	public void signin(View view){
		Intent intent = new Intent(getApplicationContext(),SignIn_Activity.class);
		startActivity(intent);		
		finish();
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
//		return super.onOptionsItemSelected(item);
		
		switch(item.getItemId()){
		case R.id.menu_btnVietnam:
			setLocale("vi");
			return true;
		case R.id.menu_btnEnglish:
			setLocale("");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}		
	}
	
	public void setLocale(String lang) {

		myLocale = new Locale(lang);
		Resources res = getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		res.updateConfiguration(conf, dm);
		Intent refresh = new Intent(this, Welcome_Activity.class);
		startActivity(refresh);
	}
	
}
