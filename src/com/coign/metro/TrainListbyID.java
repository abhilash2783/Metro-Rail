package com.coign.metro;

import java.io.IOException;
import java.util.ArrayList;


import com.metroinfo.gni.DatabaseHandler;



import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class TrainListbyID extends ListActivity {

	TableLayout country_table;
	 ListView lv;
	View row;
	TextView t1, t2;
	private SQLiteDatabase Mydatabase;
	DatabaseHandler myDbHelper;
	ArrayList<String> res=new ArrayList<String>();
	private ArrayList<String> results = new ArrayList<String>();
	String tablename;
	String Fieldname;
	
		public  void onCreate(Bundle b)
		{
			super.onCreate(b);
			 //requestWindowFeature(Window.); 
			 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
			Bundle bb=getIntent().getExtras();
			
			if(bb!=null)
			{
		
				tablename=bb.getString("values"); 
				Fieldname=bb.getString("trn");
		
			System.out.println("res VALUES   :"+tablename);
			System.out.println("res VALUES   :"+Fieldname);
			}
			 myDbHelper = new DatabaseHandler(this);
			FetchingData();
			display(tablename,Fieldname);
			
			 
			
			
			lv = getListView();
		    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title); 
			 
		    View vv=getLayoutInflater().inflate(R.layout.ah, null,false);
			lv.addHeaderView(vv,null, false);
		    
		    lv.setAdapter(new MyCustomAdapter());
			 
			   lv.clearChoices();
			  lv.setTextFilterEnabled(true);
			 
			
			  lv.setBackgroundColor(R.color.resultbackg1);  
			   LinearLayout ll = (LinearLayout) findViewById(R.id.header);
			      ll.setOnClickListener(new View.OnClickListener() {
			          public void onClick(View view) {
			              Intent intent = new Intent();
			              setResult(RESULT_OK, intent);
			              finish();
			          } 

			      });
			
		}
		
		
		 private void FetchingData()
		 {
			 System.out.println("sadffsdfadsfasdfdsaf");	
			 
		        try {
		 
		        	myDbHelper.onCreateDataBase();
		        	
		        	
		        	
		 	} catch (IOException ioe) {
		 
		 		throw new Error("Unable to create database");
		 
		 	}
		 
		 	try {
		 
		 		myDbHelper.openDataBase();
		 		Mydatabase = myDbHelper.getWritableDatabase();
				System.out.println("executed");
	     	
		 	}catch(SQLException sqle){
		 
		 		throw sqle;
		 
		 	}
		 }
		
		private void display(String TableName,String s) {
	
			
			String Query1="select Station,"+s+" from "+TableName;
			 //Cursor c = Mydatabase.rawQuery("select stations.station_name,Sch1.dep_time from stations INNER JOIN  Sch1 ON stations.station_id=Sch1.station_id where Sch1.train_id='"+trainid+"'", null);
			 	Cursor c = Mydatabase.rawQuery(Query1, null);
		    	//c.getColumnIndex("User")
		    	if (c != null ) {  
		    		if  (c.moveToFirst()) { 
		    			do {
		    				
		    				int i1=c.getColumnIndex("Station");
		    				int i2=c.getColumnIndex(s);
		    				String stationname=c.getString(i1);
		    				
		    				//System.out.println(stationname);
		    				String timings=c.getString(i2);
		    				
		    				//System.out.println(timings);
		    				String hours=timings.substring(0, 2);
		    				//Toast.makeText(getApplicationContext(), ""+hours, 40).show();
		    			String minutes=timings.substring(2, 4);
		    				//Toast.makeText(getApplicationContext(), ""+minutes, 40).show();
		    				String finaltime=hours+":"+minutes;
		    				results.add( stationname+"~" +finaltime ); 
		    				
		    			
		    			
		    			
		    			}while (c.moveToNext());
		    			
		    			
		    			
		    		} 
		    	}
			
		}
		
		
		
		
		
class MyCustomAdapter extends BaseAdapter {
			
			public int getCount() {
				return results.size();
				
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
				
			System.out.println("position  :"+position);
					row = inflater.inflate(R.layout.customelist, parent, false);
					TextView textLabel = (TextView) row.findViewById(R.id.s1);
					TextView textLabel1 = (TextView) row.findViewById(R.id.timess);
					
					String s[]=results.get(position).split("~");
					
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
