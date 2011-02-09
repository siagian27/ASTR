package com.android.astra2;

import com.android.astra2.util.Utility;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ContactDetail extends Activity {
	Button back;
	Button show_map;
	
	Thread t;
	Button b_phone;
	Button b_email;
	EditText txt_phone;
	EditText txt_email;
	
    EditText add;
    EditText add2;
    EditText add3;
    
	int CALL = 1;
	int EMAIL = 2;
	int MAP = 3;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	Utility.getSelectedTheme(this,getApplicationContext());
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.contactdetail);
        
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    
        b_phone = (Button) findViewById(R.id.btn_phone);
        txt_phone = (EditText) findViewById(R.id.txt_phone);
        b_phone.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
            t=new Thread() {
                  public void run() {
                      
                      String phoneNo = txt_phone.getText().toString();
                      phoneNo = "tel:"+phoneNo;
                      
                      Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse(phoneNo));
                      startActivityForResult(intent, CALL);
                      
                      //finish();
                  }
            };
      t.start();
      }          
    });
       
        b_email = (Button) findViewById(R.id.btn_email);
        txt_email = (EditText) findViewById(R.id.txt_email);
        b_email.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
            t=new Thread() {
                  public void run() {
                      Intent intent=new Intent(Intent.ACTION_SEND);
                      
                      intent.setType("plain/text");
                      intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ txt_email.getText().toString()});
                      intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                      intent.putExtra(android.content.Intent.EXTRA_TEXT, "");

                      startActivityForResult(Intent.createChooser(intent, "Send mail..."), EMAIL);
                      //finish();
                  }
            };
      t.start();
      }          
    });
        
        
        show_map = (Button) findViewById(R.id.btn_show_map);
       	/*
           add = (EditText) findViewById(R.id.txt_address);
       	add2 = (EditText) findViewById(R.id.txt_address_02);
       	add3 = (EditText) findViewById(R.id.txt_address_03);
       	
       	final String full_address = add.getText().toString()+" "+add2.getText().toString()+" "+add3.getText().toString();
       	*/
       	
           show_map.setOnClickListener(new OnClickListener() {
           public void onClick(View v) {
               t=new Thread() {
                     public void run() {
                         Intent intent=new Intent(getApplicationContext(),Map.class);
                         intent.putExtra("latitude", "-6.1415455");
                         intent.putExtra("longitude", "106.8900942");
                         startActivityForResult(intent,MAP);
                     }
               };
         t.start();
         }          
       });      
        
    }	
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if(resultCode==RESULT_OK && requestCode==CALL){
    	 System.out.println("Call finished");
     }
     else if (resultCode==RESULT_OK && requestCode==EMAIL){
    	 System.out.println("Email sent");
     }
     else if (resultCode==RESULT_OK && requestCode==MAP){
    	 System.out.println("Map shown");
     }      
    }    
        

}