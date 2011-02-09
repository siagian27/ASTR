package com.android.astra2;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
/*
import java.util.List;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import android.graphics.drawable.Drawable;
*/
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Map extends MapActivity {
	
    MapController mc;
    GeoPoint p;
    MapView mapView;
    
    String s_lat;
    String s_lng;
	
	int LOCATION = 3;
	
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.map);
	    
	    mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    mc = mapView.getController();
	    
        //Geocoder geoCoder = new Geocoder(this, Locale.getDefault());    
        try{
        	/*
        	Intent i = getIntent();
        	Bundle b = i.getExtras();
        	
	        List<Address> addresses = geoCoder.getFromLocationName(b.getString("full_address"), 3);
	        
	        if (addresses.size() > 0) {
	            p = new GeoPoint(
	                    (int) (addresses.get(0).getLatitude() * 1E6), 
	                    (int) (addresses.get(0).getLongitude() * 1E6));
	            mc.animateTo(p);   
	            mc.setZoom(17); 
	            mapView.invalidate();
	        }    
	        */
        	Intent i = getIntent();
        	Bundle b = i.getExtras();
        	
        	s_lat = b.getString("latitude");
        	s_lng = b.getString("longitude");
        	
	        String coordinates[] = {s_lat, s_lng};
	        double lat = Double.parseDouble(coordinates[0]);
	        double lng = Double.parseDouble(coordinates[1]);
	 
	        p = new GeoPoint(
	            (int) (lat * 1E6), 
	            (int) (lng * 1E6));
	 
	        mc.animateTo(p);
	        mc.setZoom(17); 
	        mapView.invalidate();        
	        
			

	        List<Overlay> mapOverlays = mapView.getOverlays();
	        Drawable drawable = this.getResources().getDrawable(R.drawable.toyota_logo);
	        MapOverlay itemizedoverlay = new MapOverlay(drawable,this);        
	        
	        OverlayItem overlayitem = new OverlayItem(p, "Info", "Toyota Astra Motor");
	        
	        itemizedoverlay.addOverlay(overlayitem);
	        mapOverlays.add(itemizedoverlay);
	        
        }
        catch(Exception e){
        	e.printStackTrace();
        }	        
        
	}
	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     super.onActivityResult(requestCode, resultCode, data);
     if (resultCode==RESULT_OK && requestCode==LOCATION){
    	 System.out.println("Location detected");
     }  
    }  	

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	Intent intent;
    	
        switch (item.getItemId()) {
            case R.id.item_show_route_gps:     
                intent=new Intent(getApplicationContext(),CurrentLocation.class);
                intent.putExtra("latitude", s_lat);
                intent.putExtra("longitude", s_lng);                       
                intent.putExtra("method", "GPS");
                startActivityForResult(intent,LOCATION);
                break;
            case R.id.item_show_route_network:    
                intent=new Intent(getApplicationContext(),CurrentLocation.class);
                intent.putExtra("latitude", s_lat);
                intent.putExtra("longitude", s_lng);                    
                intent.putExtra("method", "NETWORK");
                startActivityForResult(intent,LOCATION);
                break;
            default:
                return super.onOptionsItemSelected(item);                    
        }
        return true;
    }        
	
}
