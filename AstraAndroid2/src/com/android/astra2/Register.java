package com.android.astra2;

import com.android.astra2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Register extends Activity {
	Button register;
	Thread t;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.register);
          
	    register = (Button) findViewById(R.id.btn_register);
	    register.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	            t=new Thread() {
	                  public void run() {
	                      Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
	                      startActivity(intent);
	                      finish();
	                  }
	            };
	      t.start();
	      }          
	    });	
    }
}
