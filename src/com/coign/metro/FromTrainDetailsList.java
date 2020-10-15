package com.coign.metro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;



import com.metroinfo.gni.DatabaseHandler;


import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;


import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;



import android.widget.ListView;


import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class FromTrainDetailsList extends ListActivity{
	ArrayList<String> stattionlist=new ArrayList<String>();
	 ArrayList<String> values=new ArrayList<String>();
	 
	 ArrayList<String> values123=new ArrayList<String>();
	String n1,n2,n3,n4;
	DatabaseHandler MdBhandler;
	SQLiteDatabase madapter;
	View row;
	private ListView lv1;

	String ss[];
	String sr;
	String txt;
		ListView lv;
	String aa[]=new String[]{};
	
	 ArrayList<String> li1=new ArrayList<String>();
	public  void onCreate(Bundle b)
	{
		super.onCreate(b);
        
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
		 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
		//setContentView(R.layout.fromstationoptions);
	//	lv1=(ListView)findViewById(R.id.slistw);
		Bundle btn=getIntent().getExtras();
		if(btn!=null)
		{
			stattionlist=btn.getStringArrayList("dataaa");
		}

		//lv1=(ListView)findViewById(R.id.listval);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
		MdBhandler=new DatabaseHandler(this);
		FetchingData();
		// lv = (ListView) findViewById(R.id.filessss);
		lv=getListView();
		
		lv.setBackgroundResource(R.drawable.milky);
		
		View vv=getLayoutInflater().inflate(R.layout.heedd, null,false);
		lv.addHeaderView(vv,null, false);
		//View vvf=getLayoutInflater().inflate(R.layout.foot, null,false);
		//lv.addFooterView(vvf, null, false);
		//stattionlist=this.MdBhandler.selectStationsForretriving(madapter,n1,n2,n3,n4);
		//Toast.makeText(getBaseContext(), ""+n1+""+n2+""+n3+""+n4, 20).show();
		
	
	  
	
		
	  for(int a=0;a<stattionlist.size();a=a+2)
	  {		
			
			
			values.add(stattionlist.get(a));
			Collections.sort(values);
	  }			
		
	  removeDuplicates(values);
	  int flag=0;
	  String s1[]={"FL8","FH4","FL9","FL10","HF3","HL10","HL11","HL12","HL13","LF7","LF25","LH9","LH10","LH11","LH12","LH13"};
		String s2[]={"FL18","LF14"};
	  

	   
	  
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listitems, values);
		 // System.out.println("test 1234");  
		lv.setAdapter(new MyCustomAdapter());
		  //.out.println("test 12345");  
			
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				int s=Integer.parseInt(arg0.getItemAtPosition(arg2).toString());
				sr=values.get(s);
				//Toast.makeText(getBaseContext(), sr, Toast.LENGTH_LONG).show();
				
				//Toast.makeText(getApplicationContext(), "asa"+arg0.getItemAtPosition(arg2), 20).show();
				values123=retriveFromArray(sr);
				
				
				sending(values123);
				
			}
		});
		
		
			getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title); 
			  LinearLayout ll = (LinearLayout) findViewById(R.id.header);
		      ll.setOnClickListener(new View.OnClickListener() {
		          public void onClick(View view) {
		              Intent intent = new Intent();
		              setResult(RESULT_OK, intent);
		              finish();
		          } 

		      });
		    
	
	}
	
	
	private void sending(ArrayList<String> values123) {
		// TODO Auto-generated method stub
		Intent it=new Intent(this,ListoutFromStations.class);
		it.putStringArrayListExtra("val", values123);
		startActivityForResult(it, 0);
		
	}
	
	 private ArrayList<String> retriveFromArray(String sr) {
			// TODO Auto-generated method stub
		 ArrayList<String> values1=new ArrayList<String>();
	
		 for(int i=0;i<stattionlist.size();i++)
		 {
			 if(stattionlist.get(i).equals(sr))
			 {
				
				 values1.add(stattionlist.get(i+1));
				 
			 }  
		 }
		 
		 return values1;
		}

	
	public static void removeDuplicates(ArrayList<String> list) {
       
		HashSet<String> set = new HashSet<String>(list);
        list.clear();
        list.addAll(set);
	}
	
	public void FetchingData() {
		// TODO Auto-generated method stub
      try {  
 
    	  MdBhandler.onCreateDataBase();
        	       	
        	
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	} 
 	try {
 
 		MdBhandler.openDataBase();
 		madapter = MdBhandler.getWritableDatabase();
		
 	
 	}catch(SQLException sqle){
 
 		throw sqle;
 
 	}
	}
	
 	class MyCustomAdapter extends BaseAdapter {
		
		public int getCount() {
			return values.size();
			
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			
		System.out.println("position  :"+position);
				row = inflater.inflate(R.layout.fromlisttttttt, parent, false);
				TextView textLabel = (TextView) row.findViewById(R.id.sfromto);
				
				String s1[]={"FL8","FH4","FL9","FL10","HF3","HL10","HL11","HL12","HL13","LF7","LF25","LH9","LH10","LH11","LH12","LH13"};
				String s2[]={"FL18","LF14"};
				String s[]=values.get(position).split("~");
				
			
				
				int flag=0;
				
				for(int i=0;i<s.length;i=i+2)					
				{
					flag=0;
					for(int j=0;j<s1.length;j++)
					{
						if(s[i].trim()==s1[j]||s[i].equals(s1[j]))
						{
					flag=1;
					break;
						}
					}
					for(int j=0;j<s2.length;j++)
					{
						if(s[i].trim()==s2[j]||s[i].equals(s2[j]))
						{
					flag=2;
					break;
						}
					}
					if(flag==1)
					{
				textLabel.setText(s[i]+"\t* no service on sunday *");
				
					}
					else if(flag==2)
					{
						textLabel.setText(s[i]+"\t*Mathruboomi Ladies Special*");
						
							}
					else{
						
						textLabel.setText(s[i]);
	
					}
					
				}
			return (row);
		}
	} 
	
 	class IgnoreCaseComparator implements Comparator<String> {
 		  public int compare(String strA, String strB) {
 		    return strA.compareToIgnoreCase(strB);
 		  }
 		}

 		
 	

}
