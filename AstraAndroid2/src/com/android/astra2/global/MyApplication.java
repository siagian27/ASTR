package com.android.astra2.global;

import java.io.IOException;

import com.android.astra2.db.DBHelper;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

	public static final String APP_NAME = "Astra Android";

	private DBHelper dbHelper;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(APP_NAME, "APPLICATION onCreate");
		this.dbHelper = new DBHelper(this);
	}

	@Override
	public void onTerminate() {
		Log.d(APP_NAME, "APPLICATION onTerminate");
		super.onTerminate();
	}

	public DBHelper getDBHelper() {

		try {
			dbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		return this.dbHelper;
	}

	public void setDBHelper(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
}
