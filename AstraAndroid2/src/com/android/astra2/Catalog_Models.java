package com.android.astra2;


import java.util.Iterator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.astra2.see360.See360;
import com.android.astra2.util.Utility;

public class Catalog_Models extends Activity {

	public static int LAST_GALLERY_POSITION = 0;
	
	public static int SELECTED_VEHICLE_ID = 0;
	
	private static boolean isStartUp = false;
	
//	public static String[] vehicles;
    public static CVehicle[] vehicles; 
	
	public class CVehicle{
		public int vehicleId;
		public String vehicleName;
		public String vehicle360Group;
		public String vehicleGallery;
		
		public CVehicle(int vehicleId, String vehicleName, String vehicle360Group, String vehicleGallery){
			this.vehicleId = vehicleId;
			this.vehicleName = vehicleName;
			this.vehicle360Group = vehicle360Group;
			this.vehicleGallery = vehicleGallery;
		}
		
		public String toString(){
			return vehicleName;
		}
	}
	
//	public void getVehicleData(String vehicle){
//        final int vehicleId 		= getIntent().getExtras().getInt("vehicleId");
//        final String vehicle360Group = getIntent().getExtras().getString("vehicle360Group");
//        String vehicleGallery = getIntent().getExtras().getString("vehicleGallery");
//	}	
	

	@Override
	protected void onStart() {
		super.onStart();
//		Toast.makeText(getBaseContext(), "onStart", Toast.LENGTH_SHORT).show();
	}


	public void loadVehicleDetail(int resId){
        final Vehicle v = CatMain.getVehicleData(resId);        

        TextView tv = (TextView)findViewById(R.id.TextViewModelHeader);
        tv.setText(v.getName());
        tv.setTypeface(null, Typeface.BOLD_ITALIC);
        
        TextView tvDesc = (TextView)findViewById(R.id.TextViewModelMainDesc);
        tvDesc.setText(v.getDesc());

        ImageView im = (ImageView)findViewById(R.id.ImageViewModelMain);
        int resThumbId = getResources().getIdentifier(v.getResourceThumbName(), "drawable", getPackageName());
        im.setImageResource(resThumbId);    	   
        Animation fadeImage = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeImage.reset();
        im.clearAnimation();
        im.startAnimation(fadeImage);
        
        setupGallery(v.getResourceVehicleGallery());

//        final int vehicleId 		= v.getId();
//        final String vehicle360Group = v.getResource360Group();

        ImageButton btn360 = (ImageButton)findViewById(R.id.ButtonModelSee360);
        btn360.setOnClickListener(new View.OnClickListener() { 		
 		public void onClick(View view) {
 				if (v.getId() > 0){
 	          		Intent intent = new Intent(Catalog_Models.this, See360.class);
 					intent.putExtra("vehicleId", v.getId());
 					intent.putExtra("vehicle360Group", v.getResource360Group());
 					startActivity(intent);
 				}
 		}
        });
        
        //ketemu bug kalo ganti orientasi selalu balik ke yaris, jadi harus diupdate variable vehicleId
        getIntent().putExtra("vehicleId", resId);

        //setup car models
        String[] vehicleModels = getResources().getStringArray(getResources().getIdentifier(v.getResourceModelGroup(), "array", getPackageName()));
        TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);

        table.removeAllViews();
        
        for (int i = 0; i < vehicleModels.length; i++){

//            <!--Ada 11 data 
//        	data[0]=Nama vehicle
//        	data[1]=Nama Group
//        	data[2]=Deskripsi
//        	data[3]=nama link resource iconnya
//        	data[4]=rating (float)
//        	data[5]=nama link resource gambar utamanya
//        	data[6]=nama link group warna 
//        	data[7]=nama link group car models
//        	data[8]=nama link group car gallery
//        	data[9]=reserved#1
//        	data[10]=reserved#2
//        -->
            String[] data = getResources().getStringArray(getResources().getIdentifier(vehicleModels[i], "array", getPackageName()));
            
            //ambil data ke-5, berisi link resource gambarnya
            ImageView t = new ImageView(this);
            // set the text to "text xx"
            t.setImageBitmap(BitmapFactory.decodeResource(getResources(), 
            		getResources().getIdentifier(data[5], "drawable", getPackageName())));
//            LayoutParams imLay = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//            t.setLayoutParams(imLay);
            
            TextView c = new TextView(this);
            c.setText(data[0] + "\n" + data[2]);
//            c.setText(Html.fromHtml("<big><b>" + data[0] + "</b></big>\n" + data[2]));
            c.setTextSize(12);

            // add the TextView and the CheckBox to the new TableRow
            TableRow row = new TableRow(this);

                row.addView(t);
                row.addView(c);
//                Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
//                row.startAnimation(fade1);

                LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                lp.setMargins(10, 18, 10, 10);

                // add the TableRow to the TableLayout
                table.addView(row, lp);
//                table.startAnimation(fade1);

        }
        
        

	}
	
    @Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
	}


	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Utility.getSelectedTheme(this,getApplicationContext());
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.models);
        
//        Button btnSearch = (Button)findViewById(R.id.ButtonSearch01);
//        btnSearch.setOnClickListener(new View.OnClickListener() { 		
//	 		public void onClick(View v) {
//	     		Toast.makeText(Catalog_Models.this, "Sorry, Search under construction !", Toast.LENGTH_SHORT).show();	
//	 		}
//        });
   	
        Button btnVehicles = (Button)findViewById(R.id.ButtonVehicles);
        btnVehicles.setOnClickListener(new View.OnClickListener() { 		
 		public void onClick(View v) {
 			finish();		
 		}
        });

        TextView tv1 = (TextView)findViewById(R.id.TextViewBottom1);
        MovementMethod m = tv1.getMovementMethod();	//m always null when orientation changed
        if (m == null)
        	tv1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView tv2 = (TextView)findViewById(R.id.TextViewBottom2);
	    tv2.setMovementMethod(LinkMovementMethod.getInstance());
		
        
        if (vehicles == null || vehicles.length < 1){
        	//ambil daftar mobil di CatMain.vehicleNameMap
        	vehicles = new CVehicle[CatMain.vehicleNameMap.size()];
        	Iterator<?> it = CatMain.vehicleNameMap.keySet().iterator();
        	int i = 0;
        	while (it.hasNext()){
        		Integer key = ( Integer ) it.next();
//        		String value = CatMain.vehicleNameMap.get( key );
        		Vehicle v = CatMain.getVehicleData(key.intValue());
        		vehicles[i] = new CVehicle(v.getId(), v.getName(),  v.getResource360Group(), v.getResourceVehicleGallery());

        		i++;
        	}
        }
        
        if (vehicles.length < 1) return;

        //always take parameter from CatMain
        SELECTED_VEHICLE_ID = getIntent().getExtras().getInt("vehicleId");

        loadVehicleDetail(SELECTED_VEHICLE_ID);

        isStartUp = true;
        //isi daftar kendaraan untuk combobox
        ArrayAdapter<CVehicle> adapter = new ArrayAdapter<CVehicle>(this, android.R.layout.simple_spinner_item, vehicles); 
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spin 		= (Spinner)findViewById(R.id.SpinnerSelectCar);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new OnItemSelectedListener() {
      	   	public void onItemSelected(AdapterView<?> parent, View v, int position,
      			long id) {
      	   		CVehicle v1 = (CVehicle)parent.getAdapter().getItem(position);
      	   		
      	   		//gw ga mau perintah ini dieksekusi pada waktu startup. gw hanya mau aktif saat user mengklik manual 
      	   		if (!isStartUp){
//      	   			Toast.makeText(getBaseContext(), v1.vehicleName, Toast.LENGTH_SHORT).show();
//      	   		selectVehicle(position);
      	   			loadVehicleDetail(v1.vehicleId);
      	   		}
	      	}

			public void onNothingSelected(AdapterView<?> arg0) {
				
			}

        });

        //autoselect on startup
      	int pos = -1;
        for (int i = 0; i < spin.getCount(); i++){
        	CVehicle _v = (CVehicle)spin.getItemAtPosition(i);
     	   if (_v.vehicleId == SELECTED_VEHICLE_ID){
     		   pos = i;
     		   break;
     	   }
        }
        spin.setSelection(pos, true);

       //hide keyboard on startup
       this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
       
       isStartUp = false;
       System.gc();
    }
    
    public void showPicture(int imageResId, String description){

//      final Dialog dialog = new Dialog(Catalog_Models.this, android.R.style.Theme_Translucent_NoTitleBar);// <= transparent dialog
        final Dialog dialog = new Dialog(Catalog_Models.this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  
        dialog.setContentView(R.layout.model_dialog_gallery);

        ImageView img = (ImageView) dialog.findViewById(R.id.ImageViewGallery01);
        img.setImageResource(imageResId);
        img.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
        img.setOnLongClickListener(new View.OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				dialog.dismiss();
				return false;
			}
		});

        TextView desc = (TextView) dialog.findViewById(R.id.TextViewGallery01);
        desc.setText(description);

        dialog.setTitle("");
        dialog.setCancelable(true);

        android.view.WindowManager.LayoutParams lp=dialog.getWindow().getAttributes();
        
//        lp.x=100;
//        lp.y=100;
//        lp.width=android.view.WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.height=android.view.WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.gravity=Gravity.TOP | Gravity.LEFT;
//        lp.gravity= Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;
       // lp.dimAmount=0;            
        lp.flags=
        	android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | 
        	android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
        	android.view.WindowManager.LayoutParams.FLAG_BLUR_BEHIND
        	;
//        dialog.getWindow().setBackgroundDrawableResource(imageResId);
        
        dialog.getWindow().setAttributes(lp);

        dialog.show();
    	
    }
    
    //vehicleGallery=toyota_yaris_gallery
    public void setupGallery(String vehicleGallery){
        String[] photos = getResources().getStringArray(getResources().getIdentifier(vehicleGallery, "array", getPackageName())); 
        //photos[0]:ToyotaYarisGallery1
        //photos[1]:ToyotaYarisGallery2
        
        if (photos.length < 1) return;
        
        CarGallery gallery =  (CarGallery) findViewById(R.id.CarGallery01);

        ImageAdapter coverImageAdapter =  new ImageAdapter(this);

	    coverImageAdapter.mImageIds = new int[photos.length];
	    coverImageAdapter.mImageLarge = new int[photos.length];
	    coverImageAdapter.description = new String[photos.length];

//	    gallery.setAdapter(coverImageAdapter);
//	    gallery.setAdapter(new ImageAdapter(this));
	    
	    for (int i = 0; i < photos.length; i++){
//			Ada 3 data yang harus diisi:
//				data[0]:	gambar resource ukuran besar
//				data[1]:	gambar resource ukuran kecil
//		 		data[2]:	deskripsi
		 		String[] data = getResources().getStringArray(getResources().getIdentifier(photos[i], "array", getPackageName()));
		 		
		 		//max_car_gallery_data
		 		if (data.length != 3) continue;
		 		//ambil id-nya saja
		 		coverImageAdapter.mImageIds[i] = getResources().getIdentifier(data[1], "drawable", getPackageName());
		 		coverImageAdapter.mImageLarge[i] = getResources().getIdentifier(data[0], "drawable", getPackageName());
		 		coverImageAdapter.description[i] = data[2];
        }

	    coverImageAdapter.initiate();

	    coverImageAdapter.createReflectedImages();
	    
	    gallery.setAdapter(coverImageAdapter);
	    
	    gallery.setSpacing(-70);	//makin minus makin berdempetan gambarnya
//	    coverFlow.setSpacing(-15);
	    gallery.setSelection(LAST_GALLERY_POSITION, true);
//	    coverFlow.setSelection(8, true);
	    gallery.setAnimationDuration(1000);

	    gallery.setOnItemClickListener(new OnItemClickListener() {	    	
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				ImageAdapter adapter =(ImageAdapter)parent.getAdapter();
				
//                Toast.makeText(Catalog_Models.this, "" + position + ": " + adapter.description[position], Toast.LENGTH_SHORT).show();
                
                LAST_GALLERY_POSITION = position;
                
                showPicture(adapter.mImageLarge[position], adapter.description[position]);
			}
	    	
		});
    	
    }

	public class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;
        private Context mContext;

//        private FileInputStream fis;
           
        public int[] mImageIds;
        public String[] description;
        public int[] mImageLarge;
        /*
        = {
	    		R.drawable.t_priuse_ext_image1,
	    		R.drawable.t_priuse_ext_image3,
	    		R.drawable.t_priuse_ext_image4,
	    		R.drawable.t_priuse_ext_image5,
	    		R.drawable.t_priuse_ext_image6,
	    		R.drawable.t_priuse_ext_image7,
	    		R.drawable.t_priuse_ext_image8,
	    		R.drawable.t_priuse_ext_image9,
	    		R.drawable.t_priuse_ext_image10,
	    		R.drawable.t_priuse_ext_image11,
	    		R.drawable.t_priuse_ext_image12
        };*/

        private ImageView[] mImages;
        
        public ImageAdapter(Context c) {
         mContext = c;
//         mImages = new ImageView[mImageIds.length];
        }
        
       //make sure, mImageIds if filled with resource ids.
     public void initiate(){
    	 mImages = new ImageView[mImageIds.length];
     }
        
     public boolean createReflectedImages() {
             //The gap we want between the reflection and the original image
             final int reflectionGap = 1;
             
             
             int index = 0;
             for (int imageId : mImageIds) {
            	 
                 BitmapFactory.Options options=new BitmapFactory.Options();
                 options.inSampleSize = 2;

            	 Bitmap originalImage = BitmapFactory.decodeResource(getResources(), imageId, options);
            	 
              int width = originalImage.getWidth();
              int height = originalImage.getHeight();
              
        
              //This will not scale but will flip on the Y axis
              Matrix matrix = new Matrix();
              matrix.preScale(1, -1);
              
              //Create a Bitmap with the flip matrix applied to it.
              //We only want the bottom half of the image
              Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height/2, width, height/2, matrix, false);

              //Create a new bitmap with same width but taller to fit reflection
              Bitmap bitmapWithReflection = Bitmap.createBitmap(width 
                , (height + height/2), Config.ARGB_8888);
            
             //Create a new Canvas with the bitmap that's big enough for
             //the image plus gap plus reflection
             Canvas canvas = new Canvas(bitmapWithReflection);
             //Draw in the original image
             canvas.drawBitmap(originalImage, 0, 0, null);
             //Draw in the gap
             Paint deafaultPaint = new Paint();
             canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
             //Draw in the reflection
             canvas.drawBitmap(reflectionImage,0, height + reflectionGap, null);
             
             //Create a shader that is a linear gradient that covers the reflection
             Paint paint = new Paint(); 
             LinearGradient shader = new LinearGradient(0, originalImage.getHeight(), 0, 
               bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, 
               TileMode.CLAMP); 
             //Set the paint to use this shader (linear gradient)
             paint.setShader(shader); 
             //Set the Transfer mode to be porter duff and destination in
             paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); 
             //Draw a rectangle using the paint with our linear gradient
             canvas.drawRect(0, height, width, 
               bitmapWithReflection.getHeight() + reflectionGap, paint); 
             
             ImageView imageView = new ImageView(mContext);
             imageView.setImageBitmap(bitmapWithReflection);
             imageView.setLayoutParams(new CarGallery.LayoutParams(120, 180));
             imageView.setScaleType(ScaleType.MATRIX);
             mImages[index++] = imageView;
             
             originalImage.recycle();
             reflectionImage.recycle();
             
             }
          return true;
     }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

         //Use this code if you want to load from resources
            ImageView i = new ImageView(mContext);
            i.setImageResource(mImageIds[position]);
            i.setLayoutParams(new CarGallery.LayoutParams(200, 200));	//modify these. sebenarnya dimensi ini adalah dimensi total 1 frame utk gambar dan refleksinya. sedangkan gambar aslinya sendiri berdimensi 200x200 maka otomatis jika diset frame pada ukuran 210x210 gambarnya akan tetap mengecil.
            i.setScaleType(ImageView.ScaleType.CENTER_INSIDE); 
            
            i.setImageDrawable( mImages[position].getDrawable() );	//to visible reflection
            
            //Make sure we set anti-aliasing otherwise we get jaggies
            BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
            drawable.setAntiAlias(true);

            return i;
         
//         return mImages[position];
        }
      /** Returns the size (0.0f to 1.0f) of the views 
         * depending on the 'offset' to the center. */ 
         public float getScale(boolean focused, int offset) { 
           /* Formula: 1 / (2 ^ offset) */ 
             return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset))); 
         } 

    }

	@Override
	public boolean onSearchRequested() {
		return super.onSearchRequested();
	}
	
	

}