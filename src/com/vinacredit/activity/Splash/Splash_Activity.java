package com.vinacredit.activity.Splash;

import com.vinacredit.activity.R;
import com.vinacredit.activity.Welcome.Welcome_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Activity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				/* Create an Intent that will start the Main Activity. */
				Intent mainIntent = new Intent(Splash_Activity.this,
						Welcome_Activity.class);
				Splash_Activity.this.startActivity(mainIntent);
				Splash_Activity.this.finish();
			}
		}, 2500);
	}
	
}
