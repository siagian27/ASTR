package com.android.astra2;

import com.android.astra2.R;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


public class More extends ListActivity {
	private EfficientAdapter adap;
	//private static final String LIST_TEXT = "list_text";

	
	public static String[] mainItems = {"My Account", "Change Password", "My Vehicle", "Log Out"};
	public static String[] mainItemsIcon = {"icon_my_account","icon_change_password","icon_my_vehicle","icon_log_out"};
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       //setContentView(R.layout.main);
       //setListAdapter(new ArrayAdapter<String>(this,R.layout.more, R.id.tv_menu_name, mainItems));
       //setContentView(R.layout.more);
       
       //initList();
       
       adap = new EfficientAdapter(this);
       setListAdapter(adap);       
   }
/*
   private void initList() {
       List<Map<String, ListItem>> listData = new ArrayList<Map<String, ListItem>>(3);

       Map<String, ListItem> itemData1 = new HashMap<String, ListItem>(1);
       ListItem listItem1 = new ListItem();
       listItem1.text = "My Account";
       listItem1.icon = R.drawable.contact48x48copy;
       itemData1.put(LIST_TEXT, listItem1);
       listData.add(itemData1);

       Map<String, ListItem> itemData2 = new HashMap<String, ListItem>(1);
       ListItem listItem2 = new ListItem();
       listItem2.text = "Change Password";
       listItem2.icon = R.drawable.chg_pass48x48copy;
       itemData2.put(LIST_TEXT, listItem2);
       listData.add(itemData2);

       Map<String, ListItem> itemData3 = new HashMap<String, ListItem>(1);
       ListItem listItem3 = new ListItem();
       listItem3.text = "my Vehicle";
       listItem3.icon = R.drawable.myvehi72x72copy;
       itemData3.put(LIST_TEXT, listItem3);
       listData.add(itemData3);

       SimpleAdapter simpleAdapter = new SimpleAdapter(this, listData, R.layout.more,
               new String[] {
                   LIST_TEXT
               }, new int[] {
                   R.id.tv_menu_name
               });
       simpleAdapter.setViewBinder(this);
       setListAdapter(simpleAdapter);
   }
   
   public boolean setViewValue(View view, Object data, String stringRepresetation) {
       ListItem listItem = (ListItem)data;

       TextView menuItemView = (TextView)view;
       menuItemView.setText(listItem.text);
       menuItemView.setCompoundDrawablesWithIntrinsicBounds(this.getResources().getDrawable(listItem.icon), null, null, null);

       return true;
   }

   private class ListItem {
       public String text;

       public int icon;
   }
*/   
   /*
   @Override
   protected void onListItemClick(ListView l, View v, int position, long id) {
    // TODO Auto-generated method stub
    
    if (position == 0){
   	 	Intent intent = new Intent(this, MyAccount.class);
        startActivity(intent);
    }
    else if (position == 1)
    {
   	 	Intent intent = new Intent(this, ChgPassword.class);
        startActivity(intent);
    }
    else if (position == 2)
    {
   	 	Intent intent = new Intent(this, MyVehicle.class);
        startActivity(intent);        
    }
    else if (position == 3)
    {
   	 	Intent intent = new Intent(this, Logout.class);
        startActivity(intent);        
    }
    
   }
   */

   
   public static class EfficientAdapter extends BaseAdapter implements Filterable {
	    private LayoutInflater mInflater;
	    private Bitmap mIcon1;
	    private Context context;

	    public EfficientAdapter(Context context) {
	      // Cache the LayoutInflate to avoid asking for a new one each time.
	      mInflater = LayoutInflater.from(context);
	      this.context = context;
	    }

	    /**
	     * Make a view to hold each row.
	     * 
	     * @see android.widget.ListAdapter#getView(int, android.view.View,
	     *      android.view.ViewGroup)
	     */
	    public View getView(final int position, View convertView, ViewGroup parent) {
	      // A ViewHolder keeps references to children views to avoid
	      // unneccessary calls
	      // to findViewById() on each row.
	      ViewHolder holder;

	      // When convertView is not null, we can reuse it directly, there is
	      // no need
	      // to reinflate it. We only inflate a new View when the convertView
	      // supplied
	      // by ListView is null.
	      if (convertView == null) {
	        convertView = mInflater.inflate(R.layout.more, null);

	        // Creates a ViewHolder and store references to the two children
	        // views
	        // we want to bind data to.
	        holder = new ViewHolder();
	        holder.menu_icon = (ImageView) convertView.findViewById(R.id.iv_menu_icon);
	        holder.menu_name = (TextView) convertView.findViewById(R.id.tv_menu_name);
	        
	        
	        convertView.setOnClickListener(new OnClickListener() {
	          //private int pos = position;

	          public void onClick(View v) {
	            //Toast.makeText(context, "Click-" + String.valueOf(pos), Toast.LENGTH_SHORT).show();    
	        	    if (position == 0){
	        	   	 	Intent intent = new Intent(context, MyAccount.class);
	        	   	 context.startActivity(intent);
	        	    }
	        	    else if (position == 1)
	        	    {
	        	   	 	Intent intent = new Intent(context, ChgPassword.class);
	        	   	 	context.startActivity(intent);
	        	    }
	        	    else if (position == 2)
	        	    {
	        	   	 	Intent intent = new Intent(context, MyVehicle.class);
	        	   	 	context.startActivity(intent);        
	        	    }
	        	    else if (position == 3)
	        	    {
	        	   	 	Intent intent = new Intent(context, Logout.class);
	        	   	 	context.startActivity(intent);        
	        	    }	        	  
	          }
	        });
	        
	        

	        convertView.setTag(holder);
	      } else {
	        // Get the ViewHolder back to get fast access to the TextView
	        // and the ImageView.
	        holder = (ViewHolder) convertView.getTag();
	      }

	      // Get flag name and id
	      String filename = mainItemsIcon[position];
//	      String filename = "flag_" + String.valueOf(position);
	      int id = context.getResources().getIdentifier(filename, "drawable", context.getPackageName());

	      // Icons bound to the rows.
	      if (id != 0x0) {
	        mIcon1 = BitmapFactory.decodeResource(context.getResources(), id);
	      }

	      // Bind the data efficiently with the holder.
	      holder.menu_icon.setImageBitmap(mIcon1);
	      holder.menu_name.setText(mainItems[position]);

	      return convertView;
	    }

	    static class ViewHolder {
	      ImageView menu_icon;
	      TextView menu_name;
	    }

	    public Filter getFilter() {
	      // TODO Auto-generated method stub
	      return null;
	    }

	    public long getItemId(int position) {
	      // TODO Auto-generated method stub
	      return 0;
	    }

	    public int getCount() {
	      // TODO Auto-generated method stub
	      return mainItems.length;
	    }

	    public Object getItem(int position) {
	      // TODO Auto-generated method stub
	      return mainItems[position];
	    }

	  }   

   
}
