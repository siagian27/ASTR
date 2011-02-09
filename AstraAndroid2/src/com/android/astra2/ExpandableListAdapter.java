package com.android.astra2;

import java.util.ArrayList;

import com.android.astra2.see360.See360;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    private Context context;

    private ArrayList<String> groups;

    private ArrayList<ArrayList<Vehicle>> children;

    public ExpandableListAdapter(Context context, ArrayList<String> groups,
            ArrayList<ArrayList<Vehicle>> children) {
        this.context = context;
        this.groups = groups;
        this.children = children;
    }

    /**
     * A general add method, that allows you to add a Vehicle to this list
     * 
     * Depending on if the category opf the vehicle is present or not,
     * the corresponding item will either be added to an existing group if it 
     * exists, else the group will be created and then the item will be added
     * @param vehicle
     */
    public void addItem(Vehicle vehicle) {
    	
        if (!groups.contains(vehicle.getGroup())) {
            groups.add(vehicle.getGroup());
        }
        
        int index = groups.indexOf(vehicle.getGroup());
        if (children.size() < index + 1) {
            children.add(new ArrayList<Vehicle>());
        }
        children.get(index).add(vehicle);
    }

    public Object getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    
    // Return a child view. You can load your custom layout here.
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
    	
        final Vehicle vehicle = (Vehicle) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_layout, null);
        }
        
        RatingBar rb = (RatingBar) convertView.findViewById(R.id.ratingbar1);
        if (vehicle.getRating().length() > 0)
        	rb.setRating(Float.parseFloat(vehicle.getRating()));
        else
        	rb.setRating(0);
        
        TextView tv = (TextView) convertView.findViewById(R.id.tvChild);
        tv.setText(vehicle.getName());
        tv.setTypeface(null, Typeface.BOLD);

        TextView tv2 = (TextView) convertView.findViewById(R.id.tvChild2);
        tv2.setText(vehicle.getDesc());
        
        ImageView im = (ImageView) convertView.findViewById(R.id.ImageView01);
        im.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), 
        		context.getResources().getIdentifier(vehicle.getResourceLogoName(), "drawable", context.getString(R.string.package_str))));

        Button btn360 = (Button)convertView.findViewById( R.id.button360 );
        //kalo motor ga usah pake 360
//        if (vehicle.getGroupId() == R.array.hondamotors){
//        	btn360.setVisibility(View.INVISIBLE);
//        }
        btn360.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
//				Toast.makeText(context, "button for " + vehicle.getName(), Toast.LENGTH_SHORT);
//	            if (vehicle.getResourceLogoName() == 0)
	            {            	
	              	Intent intent;
					try {
						intent = new Intent(context, See360.class);
						intent.putExtra("vehicleId", vehicle.getId());
						intent.putExtra("vehicle360Group", vehicle.getResource360Group());
//						intent = new Intent(context, Class.forName(className360));
//						intent = new Intent(context,Class.forName(context.getResources().getString(R.string.yaris2011)));
						context.startActivity(intent);

					} catch (NotFoundException e) {
						e.printStackTrace();
					}
	               }				
			}
		});

        // Depending upon the child type, set the imageTextView01
        tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        
//        if (vehicle instanceof Car) {
        //disabled due to gambar ga perlu, but maybe need in future
        //	tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.car, 0, 0, 0);
            
//        } else if (vehicle instanceof Bus) {
          //  tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bus, 0, 0, 0);
//        } else if (vehicle instanceof Bike) {
           // tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bike, 0, 0, 0);
//        }
        
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    public int getGroupCount() {
        return groups.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // Return a group view. You can load your custom layout here.
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        String group = (String) getGroup(groupPosition);
        
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_layout, null);
        }
        
        TextView tv = (TextView) convertView.findViewById(R.id.tvGroup);
        tv.setText(group);
        
        ImageView im = (ImageView) convertView.findViewById(R.id.ImageViewGroup);
        if (group.toLowerCase().indexOf("toyota") > -1)
        	im.setImageResource(R.drawable.toyota_logo);
        else
            if (group.toLowerCase().indexOf("honda motor") > -1)
            	im.setImageResource(R.drawable.hondamotorlogo);
            
        
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

}
