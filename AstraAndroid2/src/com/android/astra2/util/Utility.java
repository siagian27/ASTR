package com.android.astra2.util;

import com.android.astra2.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Utility {

	//private static int sTheme;

	public final static int THEME_DARK = 0;
	public final static int THEME_LIGHT = 1;
	

	/**
	 * Set the theme of the Activity, and restart it by creating a new Activity
	 * of the same type.
	 */
	public static void changeToTheme(Activity activity, int theme, Context ctx)
	{
	    SharedPreferences sp_user = ctx.getSharedPreferences("USER_PREF",0); 
	    SharedPreferences.Editor editor = sp_user.edit();
	    editor.putInt("user_theme", theme);
	    editor.commit();
	      
		//sTheme = theme;
		
		activity.finish();
		activity.startActivity(new Intent(activity, activity.getClass()));
	}
	
	public static void getSelectedTheme(Activity activity, Context ctx){
        SharedPreferences sp_user = ctx.getSharedPreferences("USER_PREF", 0);
        int theme = sp_user.getInt("user_theme", 0);
        
        if (theme==Utility.THEME_DARK){
        	activity.setTheme(R.style.customThemeBlack);
        }
        else if (theme==Utility.THEME_LIGHT){
        	activity.setTheme(R.style.customThemeLight);
        }		
	}
	
	/** Set the theme of the activity, according to the configuration. */
	/*
	public static void onActivityCreateSetTheme(Activity activity)
	{
		switch (sTheme)
		{
		default:
		case THEME_DARK:
			activity.setTheme(R.style.customThemeBlack);
			break;
		case THEME_LIGHT:
			activity.setTheme(R.style.customThemeLight);
			break;
		}
	}
	*/
	
    public static String setSomethingWhenNull(String str_param){
        
        if (str_param==null){
            str_param = "-";
        }
        
        return str_param;
    }	
}
