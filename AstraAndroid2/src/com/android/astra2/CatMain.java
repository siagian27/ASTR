package com.android.astra2;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.astra2.util.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;

public class CatMain extends Activity {
	
	public static CatMain self;
	
	public static HashMap<Integer, String> vehicleNameMap = new HashMap<Integer, String>();//123456789=Toyota Yaris
	
	private ExpandableListAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Utility.getSelectedTheme(this,getApplicationContext());
    	
        super.onCreate(savedInstanceState);
        
        self = this;
        
        setContentView(R.layout.catmain);

        // Retrieve the ExpandableListView from the layout
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        
        listView.setOnChildClickListener(new OnChildClickListener()
        {
            
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id)
            {
         		Vehicle vehicle = (Vehicle)parent.getExpandableListAdapter().getChild(groupPosition, childPosition);

//        		String childName = vehicle.getName();
       		 
//        		String groupName = (String) parent.getExpandableListAdapter().getGroup(groupPosition);

    			Intent i = new Intent(getBaseContext(), Catalog_Models.class);
    			i.putExtra("vehicleId", vehicle.getId());	//123456789
//    			i.putExtra("vehicleName", vehicle.getName());//Toyota Yaris
//    			i.putExtra("vehicleGroup", vehicle.getGroup());	//toyota_cars
//    			i.putExtra("vehicleDesc", vehicle.getDesc());	//blabla
//    			i.putExtra("vehicleThumb", vehicle.getResourceThumbName());
//    			i.putExtra("vehicleModelGroup", vehicle.getResourceModelGroup());//toyota_yaris_models
//    			i.putExtra("vehicle360Group", vehicle.getResource360Group());
//    			i.putExtra("vehicleGallery", vehicle.getResourceVehicleGallery());
//    			
    			startActivity(i);

    			switch (groupPosition) {
            	case 0:{
            		 
//            		TextView tv = (TextView) view.findViewById(R.id.tvChild);
//                  String childName = (String)tv.getText();
//                		Toast.makeText(getBaseContext(), "selected:" + groupPosition + "," + childPosition + "," + id + "=" + groupName + "," + childName, Toast.LENGTH_SHORT).show();
            		break;
            	}
            	case 1:{
            		/*
            		AlertDialog.Builder builder = new AlertDialog.Builder(CatalogMain.this);
            		builder.setMessage("Are you sure you want to exit?")
            		       .setCancelable(false)
            		       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            		           public void onClick(DialogInterface dialog, int id) {
            		                //CatalogMain.this.finish();
            		           }
            		       })
            		       .setNegativeButton("No", new DialogInterface.OnClickListener() {
            		           public void onClick(DialogInterface dialog, int id) {
            		                dialog.cancel();
            		           }
            		       });
            		AlertDialog alert = builder.create();
            		alert.show();
            		*/
            		//showDialog(1);
//            		Toast.makeText(getBaseContext(), motors[childPosition][0] + " selected:" + groupPosition + "," + childPosition + "," + id, Toast.LENGTH_SHORT).show();
            		break;
            	}
            	}
                
                return false;
            }
        });
        
        listView.setOnGroupClickListener(new OnGroupClickListener()
        {
            
            public boolean onGroupClick(ExpandableListView arg0, View v, int groupPosition, long id)
            {
//                Toast.makeText(getBaseContext(), "Group clicked " + arg0.getCount() + "," + arg2 + "," + arg3 , Toast.LENGTH_SHORT).show();
                return false;
            }

            
        });

        // Initialize the adapter with blank groups and children
        // We will be adding children on a thread, and then update the ListView
        adapter = new ExpandableListAdapter(this, 
        		 							new ArrayList<String>(),
        		 							new ArrayList<ArrayList<Vehicle>>());

        // Set this blank adapter to the list view
        listView.setAdapter(adapter);
    
        downloadVehicles();
        updateCatalog();
        
        //show screen size:
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int physicalScreenWidth = dm.widthPixels;
        int physicalScreenHeight = dm.heightPixels;
        
        TextView t1 = (TextView) findViewById(R.id.TextViewScreen01);
        t1.setText("Screen size: " + physicalScreenWidth + "x" + physicalScreenHeight);
        
    }
    
    public void downloadVehicles(){
//    	Car c = new Car();
//    	c.setName("Toyota Camry");
//    	c.setDesc("2010   $19,820   4 Doors\n   2000CC");
//    	c.setGroup(getString(R.string.group_toyota_cars));
//    	c.setId(R.drawable.camry11icon72);
//
//    	cars.add(c);
    	
    }
    
    public static String getVehicleLabel(int resId){
    	return vehicleNameMap.get(new Integer(resId));
    }
    
    /**
     * 
     * @param vehicleName be warned, vehicleName is not a label. It is a resource name from arrays.xml
     * @return
     */
    public static Vehicle getVehicleData(int resId){
    	
//		int vehicleDataId = self.getResources().getIdentifier(vehicleResourceName, "array", self.getPackageName());//7 data
		String vehicleData[] = self.getResources().getStringArray(resId);
		
		//max_car_data
		if (vehicleData.length != 11) return null;

		Vehicle v = new Vehicle();	    			

		v.setName(vehicleData[0]);
		v.setGroup(vehicleData[1]);
		v.setDesc(vehicleData[2]);
		v.setResourceLogoName(vehicleData[3]);
		v.setRating(vehicleData[4]);
		v.setResourceThumbName(vehicleData[5]);
		v.setResource360Group(vehicleData[6]);
		v.setResourceModelGroup(vehicleData[7]);
		v.setResourceVehicleGallery(vehicleData[8]);

		//additional store
		v.setId(resId);
    	
    	return v;
    }
    
    public void updateCatalog(){
    	if (adapter == null) return;
    	
    	//ambil daftar mobil yg terdaftar di toyota_group
    	String[] groups = getResources().getStringArray(R.array.toyota_group);	//toyota_cars,toyota_suv_van,...
    	vehicleNameMap.clear();
    	
    	for (int i = 0; i < groups.length; i++){
    		//ambil mobil2 yang terdaftar di toyota_cars
    		int vehicleId = getResources().getIdentifier(groups[i], "array", getPackageName());	//ToyotaYaris
    		String vehicles[] = getResources().getStringArray(vehicleId);//toyotayaris, toyotaprius, toyotacamry...
    		Log.e(null,"Group vehicle = " + groups[i]);
    		
    		Log.e(null,"Jumlah vehicle = " + vehicles.length);
    		for (int j = 0; j < vehicles.length; j++){
    			//ambil datanya "toyotayaris"
    			Vehicle v = getVehicleData(getResources().getIdentifier(vehicles[j], "array", getPackageName()));
    			//buat mapping id utk "toyotayaris"
    			if(v!=null){
    				if(v.getName()!=null){
        				Log.e(null, new Integer(v.getId()).toString()+ "->" + v.getName());
        				vehicleNameMap.put(new Integer(v.getId()), v.getName());
        			}
            		adapter.addItem(v);
            		adapter.notifyDataSetChanged();
    			}
    		}
    	}
    }

	@Override
	protected Dialog onCreateDialog(int id) {
        switch (id) {
        case 1:
        	return new AlertDialog.Builder(CatMain.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("Judul disini")
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked OK so do some stuff */
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked Cancel so do some stuff */
                }
            })
            .create();
        }
        
        return null;
	}

}