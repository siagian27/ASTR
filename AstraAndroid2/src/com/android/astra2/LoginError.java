package com.android.astra2;

import com.android.astra2.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginError extends Activity {
      Button button;
      @Override
      public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            System.gc();
            setContentView(R.layout.loginerror);
            TextView textview = (TextView) findViewById(R.id.tv);
            String loginMessage = getIntent().getStringExtra("LoginMessage");
            textview.setText(loginMessage);
            button = (Button) findViewById(R.id.btn_ok);
            button.setOnClickListener(new View.OnClickListener() {
                  public void onClick(View v) {
                        finish();
                  }
            });
      }
}