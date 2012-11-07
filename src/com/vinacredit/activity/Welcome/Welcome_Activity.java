package com.vinacredit.activity.Welcome;


import com.vinacredit.activity.R;
import com.vinacredit.activity.SignIn.SignIn_Activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.vinacredit.Resource.*;
public class Welcome_Activity extends Activity {

	private Button btnSignIn;
	private Button btnEnglish;
	private Button btnVietnam;
	private TextView txt_welcome, txt_content;
	private ImageView imageView1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initialize();
    }

	private void initialize() {
		// TODO Auto-generated method stub
		btnSignIn = (Button)findViewById(R.id.btnSignIn);
		
		btnEnglish 	= (Button)findViewById(R.id.btnLanguageEnglish);
		btnVietnam 	= (Button)findViewById(R.id.btnLanguageVietnam);
		txt_welcome 	= (TextView)findViewById(R.id.txt_welcome);
		txt_content 	= (TextView)findViewById(R.id.txt_content);
		imageView1 	= (ImageView)findViewById(R.id.imageView1);
		
		
		//Log.i("debug","bl_language : true");
		Library.Translate(MACROS.bl_language);	
		translate();
		
		//action btnSignIn
		btnSignIn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),SignIn_Activity.class);
				startActivity(intent);
			}
		});
		btnEnglish.setOnClickListener(new View.OnClickListener() {
		    
		    @Override
		    public void onClick(View v) {
			// TODO Auto-generated method stub
			MACROS.bl_language = true;
			//Log.i("debug","bl_language : true");
			Library.Translate(MACROS.bl_language);	
			translate();
		    }
		});
		btnVietnam.setOnClickListener(new View.OnClickListener() {
		    
		    @Override
		    public void onClick(View v) {
			// TODO Auto-generated method stub
			MACROS.bl_language = false;
			//Log.i("debug","bl_language : false");
			Library.Translate(MACROS.bl_language);	
			translate();
		    }
		});
		
	}
	public void translate(){
	    txt_welcome.setText(MACROS.WELCOME_TO_VINACREDIT_LBL);
	    txt_content.setText(MACROS.WELCOME_TEXT_LBL);
	    btnSignIn.setText(MACROS.WELCOME_SIGNIN_BTN);
	}
}
