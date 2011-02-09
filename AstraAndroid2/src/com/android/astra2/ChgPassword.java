package com.android.astra2;

import com.android.astra2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChgPassword extends Activity {
	Button submit;
	Thread t;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.chgpassword);
          
  	    submit = (Button) findViewById(R.id.btn_chgpwd);
  	    submit.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	            t=new Thread() {
	                  public void run() {
	                      Intent intent=new Intent(getApplicationContext(),More.class);
	                      startActivity(intent);
	                      finish();
	                  }
	            };
	      t.start();
	      }          
	    });	          
    }
}
