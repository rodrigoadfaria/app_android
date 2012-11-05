package com.vinacredit.activity.Welcome;


import com.vinacredit.activity.R;
import com.vinacredit.activity.SignIn.SignIn_Activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Welcome_Activity extends Activity {

	private Button btnSignIn;
	private TextView textView1, textView2;
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
		textView1 = (TextView)findViewById(R.id.textView1);
		textView2 = (TextView)findViewById(R.id.textView2);
		imageView1 = (ImageView)findViewById(R.id.imageView1);
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),SignIn_Activity.class);
				startActivity(intent);
			}
		});
	}

}
