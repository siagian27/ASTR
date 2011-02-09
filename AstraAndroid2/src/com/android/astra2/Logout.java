package com.android.astra2;

import com.android.astra2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Logout extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          System.gc();
          setContentView(R.layout.login);
          
          Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
          startActivity(intent);
          finish();          
    }
    
}
