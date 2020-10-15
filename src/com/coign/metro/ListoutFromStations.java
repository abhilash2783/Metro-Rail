package com.coign.metro;

import java.util.ArrayList;





import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class ListoutFromStations extends ListActivity{
	
	ArrayList<String>  stattionlist;
	ArrayList<String>  stattionlist1;
	ArrayList<String>  stattionlist2;
	View row;
	 public void onCreate(Bundle savedInstanceState) {
		 
	
		 
		 super.onCreate(savedInstanceState);
	        
		 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
 
	        Bundle b=getIntent().getExtras();
	        if(b!=null)
	        {
	        	stattionlist=b.getStringArrayList("val");
	        	//System.out.println("adasdfdfadsadsfsd"+stattionlist.toString());
	        }
	        
	 
	       // setListAdapter(new ArrayAdapter<String>(this, R.layout.listitems, stattionlist));
	        ListView lv=getListView();
	        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title); 
	        View vv=getLayoutInflater().inflate(R.layout.ah, null,false);
			lv.addHeaderView(vv,null, false);
	        
	        
	        lv.setAdapter(new MyCustomAdapter());  
	      
	    	
	  	  lv.setBackgroundColor(R.color.resultbackg1);  
	        lv.setTextFilterEnabled(true);
	    
	    
	        
	        
	        LinearLayout ll = (LinearLayout) findViewById(R.id.header);
		      ll.setOnClickListener(new View.OnClickListener() {
		          public void onClick(View view) {
		              Intent intent = new Intent();
		              setResult(RESULT_OK, intent);
		              finish();
		          } 

		      });
		
	        
	 }
	 
	 class MyCustomAdapter extends BaseAdapter {
			
			public int getCount() {
				return stattionlist.size();
				
			}

			public String getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			public View getView(int position, View convertView, ViewGroup parent) {
				LayoutInflater inflater = getLayoutInflater();
				
			System.out.println("positionss  :"+position);
					row = inflater.inflate(R.layout.customelist, parent, false);
					TextView textLabel = (TextView) row.findViewById(R.id.s1);
					TextView textLabel1 = (TextView) row.findViewById(R.id.timess);
					
					String s[]=stattionlist.get(position).split("~");
				
					for(int i=0;i<s.length;i=i+2)
						
					{
					textLabel.setText(s[i]);
					}
					
					
					for(int i=1;i<s.length;i=i+2)
						
					{
						textLabel1.setText(s[i]);
					}
					
					
					
			
				
				return (row);
			}
		} 
}
