package com.android.astra2;

import com.android.astra2.db.DBHelper;
import com.android.astra2.global.MyApplication;
import com.android.astra2.util.Utility;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends Activity {
      /** Called when the activity is first created. */
      private static final String TAG = "Login";
      Button signin;
      TextView register;
      String loginmessage = null;
      Thread t;
      ProgressDialog dialog;
      
      private MyApplication myApp;
      
      //private DBHelper dbHelper;
      
      @Override
      public void onCreate(Bundle savedInstanceState) {
            
    	  Utility.getSelectedTheme(this, getApplicationContext());
    	  	
    	  super.onCreate(savedInstanceState);
            setContentView(R.layout.login);

          signin = (Button) findViewById(R.id.btn_sign_in);
          signin.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                      showDialog(0);
                      t=new Thread() {
                            public void run() {
                            	tryLogin();
                            }
                      };
                t.start();
                }
          });
          
          register = (TextView) findViewById(R.id.txt_register);
          register.setOnClickListener(new OnClickListener() {
              public void onClick(View v) {
                  t=new Thread() {
                        public void run() {
                            Intent intent=new Intent(getApplicationContext(),Register.class);
                            startActivity(intent);
                            finish();
                        }
                  };
            t.start();
            }          
          });
          
      }
      @Override
      protected Dialog onCreateDialog(int id) {
            switch (id) {
                  case 0: {
                        dialog = new ProgressDialog(this);
                        dialog.setMessage("Please wait while connecting...");
                        dialog.setIndeterminate(true);
                        dialog.setCancelable(true);
                        return dialog;
                  }
            }
            return null;
      }
      private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                  String loginmsg=(String)msg.obj;
                  if(loginmsg.equals("SUCCESS")) {
                        removeDialog(0);
                        Intent intent=new Intent(getApplicationContext(),Welcome.class);
                        startActivity(intent);
                        finish();
                  }
            }
      };
      public void tryLogin() {
            Log.v(TAG, "Trying to Login");
            
            try {
            	
                EditText etxt_user = (EditText) findViewById(R.id.txt_username);
                EditText etxt_pass = (EditText) findViewById(R.id.txt_password);
                String username = etxt_user.getText().toString();
                String password = etxt_pass.getText().toString();
                
                myApp = (MyApplication)this.getApplication();
                DBHelper dbHelper = myApp.getDBHelper().getDBAdapterInstance(this);
                
            	try {
            		dbHelper.openDataBase();
             	}catch(SQLException sqle){
             		throw sqle;
             	}

             	
            	String query = "select count(1) from t_user where user_id='"+ username +"' and user_pwd='"+ password +"'";
            	Cursor c = dbHelper.selectRecordsFromDB(query, null);
            	int i_login_found = 0;
            	
                if (c.moveToFirst()){
                	i_login_found = c.getInt(0);
                }
            	
            	c.close();
            	dbHelper.close();   	
            	
            	if(i_login_found > 0){
                    Message myMessage=new Message();
                    myMessage.obj="SUCCESS";
                    handler.sendMessage(myMessage);            		
            	}
            	else{
                    Intent intent = new Intent(getApplicationContext(), LoginError.class);
                    intent.putExtra("LoginMessage", "Unrecognize username & password");
                    startActivity(intent);
                    removeDialog(0);                        		
            	}

            } catch (Exception e)
            {
            		Log.v(TAG, "Error : "+e.toString());
            		Log.i(TAG, "Error : "+e.getMessage());
            		
                  Intent intent = new Intent(getApplicationContext(), LoginError.class);
                  intent.putExtra("LoginMessage", "Unable to login");
                  startActivity(intent);
                  removeDialog(0);
            }
      }
}