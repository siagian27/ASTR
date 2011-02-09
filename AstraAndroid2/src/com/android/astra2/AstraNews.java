package com.android.astra2;

import java.util.ArrayList;
import java.util.HashMap;

import com.android.astra2.util.Utility;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AstraNews extends Activity {
	private SimpleAdapter mAdapter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	Utility.getSelectedTheme(this,getApplicationContext());
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        ListView list = (ListView) findViewById(R.id.ListView01);
        
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
                
        map = new HashMap<String, String>();
        map.put("title", "Prius Plug-in Insentif Rp 69 Juta");
        map.put("timestamp", "29 December 2010");
        map.put("url", "http://www.toyota.co.id/company/news/article.php?article_id=4681");
        map.put("comment", "Satu pengakuan terhadap keunggulan Prius Plug-in Concept datang dari Department for Transport (DfT) dan Office for Low Emssion Vehicles Inggris, mengumumkan bahwa model yang sekarang sedang menjalani program uji coba di London dan kota-kota besar lainnya di seluruh dunia, memenuhi kriteria untuk mendapat Plug-in Car Grant.\n\nIni artinya, versi produksinya kelak berhak menerima subsidi hingga £ 5000 (Rp 69,2 juta). Dengan demikian saat model ini dipasarkan tahun 2012, pembeli bisa diskon £ 5000.");
        mylist.add(map);
        
        map = new HashMap<String, String>();
        map.put("title", "Foto Interior Prius Baru");
        map.put("timestamp", "27 December 2010");
        map.put("url", "http://www.toyota.co.id/company/news/article.php?article_id=4679");
        map.put("comment", "Semakin dekat ajang pameran 2011 Detroit Auto Show Toyota merilis lagi satu foto lagi mengenai keluarga baru Prius yang memperlihatkan bagian belakang minivan/wagon. Kali ini bukan bagian eksteriornya seperti foto pertama, tapi interiornya, khusunya cargo area yang punya kapasitas 50% lebih besar dari Prius yang ada sekarang.");
        mylist.add(map);
        
        map = new HashMap<String, String>();
        map.put("title", "45 Model Toyota di China Auto Show");
        map.put("timestamp", "23 December 2010");
        map.put("url", "http://www.toyota.co.id/company/news/article.php?article_id=4669");
        map.put("comment", "Toyota Motor Corp. akan memamerkan 45 model  baik yang sudah di produksi maupun  mobi l konsep di  8th China (Guangzhou) International Automobile Exhibition. Dalam  pameran yang berlangsung  satu minggu ini  (20 – 27 Desember), Toyota juga akan menampilkan dua model baru yang akan diluncurkan di China tahun 2011.\n\nDalam pameran bertema “Lingkungan dan masa depan”,  Toyota akan memperkenalkan E`Z  jenis mobi l baru  yaitu FUV (fashionable utility vehicle) dan Zelas  sports coupe dua pintu untuk  perkotaan yang akan ditampilkan di China pertama kali.");
        mylist.add(map);
        
        map = new HashMap<String, String>();
        map.put("title", "All-new Vitz benchmark baru di kelasnya");
        map.put("timestamp", "23 December 2010");
        map.put("url", "http://www.toyota.co.id/company/news/article.php?article_id=4671");
        map.put("comment", "Vitz generasi ketiga yang diluncurkan Toyota di Jepang, Rabu (22/12) hadir dengan konsep baru tentang mobil kompak yang sangat efisien, namun tetap lapang, menyenangkan sekaligus artistik. Berdasarkan pengujian Kementrian Pertanahan, Infrastruktur, dan Pariwisata Jepang, konsumsi bahan bakar Vitz baru mencapai 26.5km/liter. Ini buah dari teknologi Toyota Stop & Start idling -stop system.\n\nEksteriornya diredisain total dengan menonjolkan kesan lincah namun sophisticated. Disainnya juga aerodinamis, dengan coefficient of drag 0.285 yang berkontribusi terhadap efisiensi bahan bakar yang tinggi dan stabilitas di kecepatan tinggi.");
        mylist.add(map);         
        
        map = new HashMap<String, String>();
        map.put("title", "Bonus Akhir Tahun Avanza – AutoBild");
        map.put("timestamp", "22 December 2010");
        map.put("url", "http://www.toyota.co.id/company/mediarelations/media/article.php?article_id=4665");
        map.put("comment", "Sebuah bonus akhir tahun diberikan Toyota Avanza. Tanpa banyak pemberitahuan, PT Toyota-Astra Motor (TAM) memasang shift lock pada semua Avanza otomatis terhitung mulai awal November kemarin.");
        mylist.add(map);
        
        map = new HashMap<String, String>();
        map.put("title", "Dua Yaris Baru");
        map.put("timestamp", "20 December 2010");
        map.put("url", "http://www.toyota.co.id/company/news/article.php?article_id=4659");
        map.put("comment", "Memasuki tahun baru, penggemar Toyota Yaris di Inggris di suguhi dua varian baru -top-of-range – yang memberi keleluasaan pada konsumen untuk memilih varian yang lebih mewah atau lebih kental aroma sportynya. Keduanya berbasis Yaris TR – varian paling laris- dan akan di pasarkan pada Januari 2011.");
        mylist.add(map);
                
        map = new HashMap<String, String>();
        map.put("title", "The Style By Toyota");
        map.put("timestamp", "11 November 2010");
        map.put("url", "http://www.toyota.astra.co.id/company/news/article.php?article_id=4564");
        map.put("comment", "The Style by Toyota, pusat pengetahuan otomotif dan teknologi Toyota di Siam Square kini punya tampilan baru.\n\nKyoichi Tanada, president of Toyota Motor Thailand mengatakan, Toyota merenovasi The Style by Toyota dengan konsep 'Discover Beyond', yang menyajikan pada pengunjung pengalaman menyenangkan dengan teknologi-teknologi Toyota terbaru.");
        mylist.add(map);   

        mAdapter = new SimpleAdapter(this, mylist, R.layout.rows,
                    new String[] {"title", "timestamp", "comment"}, new int[] {R.id.title, R.id.timestamp, R.id.comment});
        list.setAdapter(mAdapter);        
        list.setClickable(true);
        list.setFocusable(true);
        list.setFocusableInTouchMode(true);        
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {		
          @SuppressWarnings("unchecked")
		public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        	  
        	  HashMap<String, String> map1 = (HashMap<String, String>) mAdapter.getItem(position);
              
              Intent i = new Intent(Intent.ACTION_VIEW,
            		   Uri.parse(map1.get("url")));
    	
    		  startActivity(i);
          }
        });        	        	        
    }
}