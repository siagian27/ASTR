package com.android.astra2;

import android.app.ListActivity;
import android.os.Bundle;

public class MyVehicle extends ListActivity {
	//private SimpleAdapter mAdapter;
	
	String[] vehicles = {"B 1234 JK"};
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       //setContentView(R.layout.main);
       //setListAdapter(new ArrayAdapter<String>(this,R.layout.myvehicle, R.id.tv_myvehi_menu_name, vehicles));
   }
   
   /*
   @Override
   protected void onListItemClick(ListView l, View v, int position, long id) {
    // TODO Auto-generated method stub
    
    if (position == 0){
   	 	Intent intent = new Intent(this, MyVehicleDetail.class);
        startActivity(intent);
    }
    
   }
   */
}
