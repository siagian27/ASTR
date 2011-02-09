package com.android.astra2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.android.astra2.db.DBHelper;
import com.android.astra2.global.MyApplication;
import com.android.astra2.util.Utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ContactList extends Activity {
	TextView headOffice;
	Button back;

	Thread t;

	int MAP = 1;

	private MyApplication myApp;
	private Context ctx;

	private SimpleAdapter mAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences sp_user = getSharedPreferences("USER_PREF", 0);
		int theme = sp_user.getInt("user_theme", 0);

		if (theme == Utility.THEME_DARK) {
			// setTheme(R.style.customThemeBlack);
			setTheme(android.R.style.Theme_Black);
		} else if (theme == Utility.THEME_LIGHT) {
			// setTheme(R.style.customThemeLight);
			setTheme(android.R.style.Theme_Light);
		}

		setContentView(R.layout.contactlist);

		ListView list = (ListView) findViewById(R.id.lv_contact);

		//new ListCompanyTask().execute();

		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;

		map = new HashMap<String, String>();
		map.put("title", "Toyota Astra Motor Head Office");
		map.put("comment", "Kantor Pusat PT. Toyota Astra Motor");
		mylist.add(map);

		map = new HashMap<String, String>();
		map.put("title", "Toyota Astra Motor Branch Bandung");
		map.put("comment", "Kantor Cabang PT. Toyota Astra Motor, Bandung");
		mylist.add(map);

		mAdapter = new SimpleAdapter(this, mylist, R.layout.rows, new String[] {
				"title", "comment" }, new int[] { R.id.title, R.id.comment });
		list.setAdapter(mAdapter);
		list.setClickable(true);
		list.setFocusable(true);
		list.setFocusableInTouchMode(true);

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// HashMap<String, String> map1 = (HashMap<String, String>)
				// mAdapter.getItem(position);

				if (position == 0) {
					Intent intent = new Intent(getApplicationContext(),
							ContactDetail.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(getApplicationContext(),
							ContactDetail2.class);
					startActivity(intent);
				}

				// finish();
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == MAP) {
			System.out.println("Map shown");
		}
	}
/*
	private class ListCompanyTask extends AsyncTask<String, Void, ArrayList> {
		private final ProgressDialog dialog = new ProgressDialog(
				ContactList.this);

		// can use UI thread here
		protected void onPreExecute() {
			this.dialog.setMessage("Selecting data...");
			this.dialog.show();
		}

		// automatically done on worker thread (separate from UI thread)
		@SuppressWarnings("unchecked")
		protected ArrayList doInBackground(final String... args) {
			myApp = (MyApplication) getApplication();
			DBHelper dbHelper = myApp.getDBHelper().getDBAdapterInstance(ctx);

			try {
				dbHelper.openDataBase();
			} catch (SQLException sqle) {
				throw sqle;
			}

			String query = "select comp_title, comp_desc from t_company";
			ArrayList companyList = (ArrayList) dbHelper.selectCompanyList(
					query, null);

			return companyList;
		}

		// can use UI thread here
		protected void onPostExecute(final String result) {
			if (this.dialog.isShowing()) {
				this.dialog.dismiss();
			}
			//ContactList.this.output.setText(result);
		}
	}
	*/
}
