package com.android.astra2.see360;

import com.android.astra2.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class See360 extends Activity{
	private int pointer = 0;
	private int lastProgress = 0;
	
	public int[] mPhotoIds;
	
	public int[] colorButtons = new int[]{R.id.ButtonBlack, R.id.ButtonBlueSea, R.id.ButtonGold,
										  R.id.ButtonRed, R.id.ButtonSilver, R.id.ButtonWhite								
	};


	protected String getColorName(String groupName){
		return groupName.substring(groupName.indexOf("_360_") + "_360_".length()
								, groupName.length()).replaceAll("_", " ");
	}
	
	protected void removeButtons(String[] exceptions){
		
		if (exceptions.length > 6) throw new RuntimeException("color button list out of bounds");

		for (int i = 0; i < colorButtons.length; i++){
			boolean _found = false;
			
			for (int j = 0; j < exceptions.length; j++)
	    		if (getButtonID(exceptions[j]) == colorButtons[i]){
	    			_found = true;
	    			break;
	    		}				

			if (!_found){
				ImageButton b = (ImageButton)findViewById(colorButtons[i]);
				b.setVisibility(View.GONE);
			}
		}


	}
	
	protected int getButtonID(String groupName){
        if (groupName.toLowerCase().indexOf("_black_") > -1)
        	return R.id.ButtonBlack;
        	else if (groupName.toLowerCase().indexOf("_blue_") > -1)
            	return R.id.ButtonBlueSea;
            	else if (groupName.toLowerCase().indexOf("_gold_") > -1)
                	return R.id.ButtonGold;
                	else if (groupName.toLowerCase().indexOf("_red_") > -1)
                    	return R.id.ButtonRed;
                    	else if (groupName.toLowerCase().indexOf("_silver_") > -1)
                        	return R.id.ButtonSilver;
	                    	else if (groupName.toLowerCase().indexOf("_white_") > -1)
	                        	return R.id.ButtonWhite;
        
		return 0;
	}
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see360);

//        int vehicleId = getIntent().getExtras().getInt("vehicleId");
        String vehicle360Group = getIntent().getExtras().getString("vehicle360Group");	//toyota_yaris_3dlb_color360
        int id = getResources().getIdentifier(vehicle360Group, "array", getPackageName());
        final String[] colors = getResources().getStringArray(id);

        removeButtons(colors);
        
        //ambil warna apa saja yg tersedia di "Toyota_Yaris_3dlb_White_360_Super_White"
        for (int i = 0; i < colors.length; i++){
        	
        	final int _i = i;
        	//utk mendptkan warna yg tersedia, kita perlu parsing kata, misal XXX_Red_123, dgn mendapatkan kata "_red_" brarti pake tombol merah
          	ImageButton btnColor = (ImageButton)findViewById(getButtonID(colors[i]));
          	
          	final String colorName = getColorName(colors[i]);
          	btnColor.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
		            int colorId = getResources().getIdentifier(colors[_i], "array", getPackageName());	//Toyota_Yaris_3dlb_Red_360
		            String[] pics360 = getResources().getStringArray(colorId);

		            mPhotoIds = new int[pics360.length];

		    		for (int j = 0; j < pics360.length; j++){
	    				//ambil idnya
		    			int _id = getResources().getIdentifier(pics360[j], "drawable", getPackageName());
		    			mPhotoIds[j] = _id;
		    		}
		    		
	            	updatePhoto();
	            	Toast.makeText(getBaseContext(), colorName, Toast.LENGTH_SHORT).show();	            	

				}
			});
          	
          	//click first button
          	if (i == 0) btnColor.performClick();

        }

    }

    
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		int action = event.getAction();
		int progress = 0;
        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();        
        float width = d.getWidth();
		float x_mouse = event.getX();
        
		if (action == MotionEvent.ACTION_DOWN){
			//avoid flicker
			lastProgress = Math.round((float) mPhotoIds.length * (x_mouse / width));
		}
		if (action == MotionEvent.ACTION_MOVE){			
			
			progress = Math.round((float) mPhotoIds.length * (x_mouse / width));

            if (progress > lastProgress) {
                //right
                	pointer += 1;
                }else
                if (progress < lastProgress) {
                //left
                	pointer -= 1;
                }
			
//	        TextView statusText = (TextView) findViewById(R.id.status_text);
//            statusText.setText("p:" + progress 
//            					+ "/" + x_mouse 
//            					+ "+" + pointer
//            					);
            
            if (lastProgress != progress){
            	updatePhoto();

            	lastProgress = progress;            	
            }
		}
        return true;
	}

    public void updatePhoto(){
    	if (pointer < 0) pointer = mPhotoIds.length-1;
    	if (pointer > mPhotoIds.length-1) pointer = 0;
    	
        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        imageView.setImageResource(mPhotoIds[pointer]);
    	
    }    	
	
}
