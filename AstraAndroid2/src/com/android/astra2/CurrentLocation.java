package com.android.astra2;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CurrentLocation extends Activity

{
	private LocationManager locman;
	private LocationListener loclis;
	private List<String> l_providers;
	private String s_provider;
	private TextView tv;
	
	@Override
	public void onCreate(Bundle savedInstanceState)

	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.currentlocation);

    	Intent i = getIntent();
    	Bundle b = i.getExtras();
    	
    	String s_method = b.getString("method");
    	    	
		/* Use the LocationManager class to obtain GPS locations */
	
    	locman = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		loclis = new LocationListener(){
			public void onLocationChanged(Location loc)

			{
				String Text = "Current location is: " +
				"Latitud = " + loc.getLatitude() +
				" Longitud = " + loc.getLongitude() +
				" Accuracy = " + loc.getAccuracy();

				Toast.makeText( getApplicationContext(),Text,Toast.LENGTH_SHORT).show();
				tv.setText(Text);
			}
			
			public void onStatusChanged(String s_provider, int status, Bundle extras)

			{

			}
			
			public void onProviderDisabled(String s_provider)

			{

			}

			public void onProviderEnabled(String s_provider)

			{

			}

		};/* End of Class MyLocationListener */
		 
    	if (("GPS").equals(s_method)){
    		locman.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, loclis);
    	}
    	else{
    		locman.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0, loclis);
    	}
		
		l_providers = locman.getProviders(true);
		Location last_known_location = null;
		
		for (int x=l_providers.size()-1; x>=0; x--) {
			last_known_location = locman.getLastKnownLocation(l_providers.get(x));
			s_provider = l_providers.get(x);
			if (last_known_location != null) break;
		}
		
    	/*
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		provider = locman.getBestProvider(criteria, true);

		Location last_known_location = locman.getLastKnownLocation(provider);
		*/
		
		tv = (TextView) findViewById(R.id.tv_location_info);
		tv.setText("Detecting location...\n\n");
		tv.setText("Last known location : " + last_known_location.toString());
		
		//Destination coordinates
    	
    	String s_lat = b.getString("latitude");
    	String s_lng = b.getString("longitude");		
    	
		
  	  /*
				# * The Possible Query params options are the following:  
				# *  
				# * Show map at location: geo:latitude,longitude  
				# * Show zoomed map at location: geo:latitude,longitude?z=zoom  
				# * Show map at locaiton with point: geo:0,0?q=my+street+address  
				# * Show map of businesses in area: geo:0,0?q=business+near+city  
 	   */
		
  	  	String uri = "http://maps.google.com/maps?saddr="+last_known_location.getLatitude()+","+last_known_location.getLongitude()+"&daddr="+s_lat+","+s_lng+"&z=17";
        startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));			
	}	

	/** Register for the updates when Activity is in foreground */
	@Override
	protected void onResume() {
		super.onResume();
		locman.requestLocationUpdates(s_provider, 20000, 1, loclis);
	}

	/** Stop the updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();
		locman.removeUpdates(loclis);
	}    

	/** Stop the updates when Activity is paused */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		locman.removeUpdates(loclis);
	}    	
}/* End of UseGps Activity */