package com.coign.metro;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class Fesilitiesoutput extends Activity {
	
	ArrayList<String>  stattionlist; 
	
	ArrayList<String> itemslist=new ArrayList<String>();
	ArrayList<String> vvv=new ArrayList<String>();
	 String items[]=new String[itemslist.size()];
	 TextView tv;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE); 
	       
	        setContentView(R.layout.fesillitiesout);
	        tv=(TextView)findViewById(R.id.widgetrr);
	        Bundle btn=getIntent().getExtras();  
	       
	       String s=btn.getString("clickvalue");
	    
	       Toast.makeText(getBaseContext(),""+s, 20).show();
	       dataFetch(s);
	  
	       //Toast.makeText(getBaseContext(),""+items[0], 20).show();
	   
	       String localities[]=items[0].split("-");
	       
	       for(int h=0;h<localities.length;h++){
	       
	    	  tv.append("\n-"+localities[h]+"\n");
	       }
	          
	    	  LinearLayout ll2= (LinearLayout) findViewById(R.id.adv);
	    	  ll2.setOnClickListener(new OnClickListener(){
	 	         public void onClick(View v) {
	 	             Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://market.android.com/details?id=com.coign.calltracker&feature=search_result"));
	 	             startActivity(i);
	 	         }
	 	     });
	 	
	       
	
	      // getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title); 
	 
	 LinearLayout ll = (LinearLayout) findViewById(R.id.widgetlll);
		      ll.setOnClickListener(new View.OnClickListener() {
		          public void onClick(View view) {
		              Intent intent = new Intent();
		              setResult(RESULT_OK, intent);
		              finish();
		          }

		      });
	 }
private void dataFetch(String sw)
{
try {
	InputStream is =getResources().openRawResource(R.raw.fecilites);
	BufferedReader br = new BufferedReader(new InputStreamReader(is));
	String s;
	String ss[];
    int linecount=0;
    String line;
    int i2=0;
    while ((s=br.readLine())!=null)
 				  {
 				  linecount++;
 				  int indexfound=s.indexOf(sw);
 				  if(indexfound>-1)
 							  {
 					  ss=s.split("~");
 					  for(int g=1;g<ss.length;g++)
 					  {
 						 itemslist.add(ss[g]);
 					  }
 					
 							  }
 				  }
    for(int h=0;h<itemslist.size();h++)
	{
	
		
		items=itemslist.toArray(new String[itemslist.size()]);
	
		//Toast.makeText(getBaseContext(), ""+items[h], 20).show();	
	} 
    


}
catch (Exception e) {

	e.printStackTrace();
}
  	
}
}



