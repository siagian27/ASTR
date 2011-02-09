package com.android.astra2;

import com.android.astra2.util.Utility;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

public class Welcome extends TabActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	  Utility.getSelectedTheme(this,getApplicationContext());
    	  
          super.onCreate(savedInstanceState);
          setContentView(R.layout.welcome);
          
          Resources res = getResources(); // Resource object to get Drawables
          TabHost tabHost = getTabHost();  // The activity TabHost
          TabHost.TabSpec spec;  // Reusable TabSpec for each tab
          Intent intent;  // Reusable Intent for each tab

          // Create an Intent to launch an Activity for the tab (to be reused)
          intent = new Intent().setClass(this, ContactList.class);
          // Initialize a TabSpec for each tab and add it to the TabHost
          spec = tabHost.newTabSpec("contact").setIndicator("",
                            res.getDrawable(R.drawable.icon_tab_contact))
                        .setContent(intent);
          tabHost.addTab(spec);

          intent = new Intent().setClass(this, CatMain.class);
          spec = tabHost.newTabSpec("catalogue").setIndicator("",
                  res.getDrawable(R.drawable.icon_tab_catalogue))
              .setContent(intent);
          tabHost.addTab(spec);
          
          intent = new Intent().setClass(this, ContactList.class);
          spec = tabHost.newTabSpec("service").setIndicator("",
                  res.getDrawable(R.drawable.icon_tab_service))
              .setContent(intent);
          tabHost.addTab(spec);          
          
          intent = new Intent().setClass(this, AstraNews.class);
          spec = tabHost.newTabSpec("news").setIndicator("",
                  res.getDrawable(R.drawable.icon_tab_news))
              .setContent(intent);
          tabHost.addTab(spec);  

          intent = new Intent().setClass(this, More.class);
          spec = tabHost.newTabSpec("more").setIndicator("",
                  res.getDrawable(R.drawable.icon_tab_more))
              .setContent(intent);
          tabHost.addTab(spec);  
          
          tabHost.setCurrentTab(0);        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.theme_menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_theme_dark:     
            	Utility.changeToTheme(this, Utility.THEME_DARK, getApplicationContext());
                break;
            case R.id.item_theme_light:    
            	Utility.changeToTheme(this, Utility.THEME_LIGHT, getApplicationContext());
                break; 
            default:
                return super.onOptionsItemSelected(item);                    
        }
        return true;
    }   
}
