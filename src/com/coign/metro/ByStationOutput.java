package com.coign.metro;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.coign.metro.TrainListbyID.MyCustomAdapter;

import com.metroinfo.gni.DatabaseHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path.FillType;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ByStationOutput extends Activity{
	ArrayList<String> res;
	ArrayList<String> reslist=new ArrayList<String>();
	ArrayList<String> reslist1=new ArrayList<String>();
	String res1;
	String stationname;
	String a1[];
	String b1[];
	String c1[];
	String d1[];
	View row;
	String ssss[];
	String a[];
	String sss;
	TableLayout country_table;
	  ListView lv;
	TextView t1, t2,t3;
	 DatabaseHandler myDbHelper;
	 String tablename,fromtime,totime;
	 private SQLiteDatabase Mydatabase;
 	public  void onCreate(Bundle b)
	{
		super.onCreate(b);
		//requestWindowFeature(Window.FEATURE_NO_TITLE); 
		 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
		setContentView(R.layout.by);
		//TextView tv=(TextView)findViewById(R.id.sid1); 
		this.myDbHelper = new DatabaseHandler(this);  
		 lv = (ListView)findViewById(R.id.bystat);
		 
		  View vv=getLayoutInflater().inflate(R.layout.placetime, null,false);
			lv.addHeaderView(vv,null, false);
		Bundle bb=getIntent().getExtras();
		if(bb!=null)
		{
			res=bb.getStringArrayList("values");
			System.out.println("valuesssssssssssssssssssssss :"+res.toString());
			tablename=bb.getString("tablename");
			fromtime=bb.getString("fromtime");
			totime=bb.getString("totime");
			stationname=bb.getString("stationname");
			System.out.println("table name from spinner :"+tablename+""+fromtime+""+totime+""+stationname);
			a1=new String[res.size()];
			b1=new String[res.size()];
			c1=new String[res.size()]; 
			d1=new String[res.size()]; 
			ssss=new String[res.size()];
		} 
		
		FetchingData();
		
		System.out.println("****res******"+res.size());
		for(int ii=0;ii<res.size();ii++)
		{
			System.out.println("****res******"+res.get(ii));
		res1=this.myDbHelper.selectStationsBaseTime(Mydatabase,res.get(ii),tablename,fromtime,totime,stationname); 
		
				System.out.println("**********"+res1.length());
		 
				a=res1.split("~");  
				for(int i=0;i<a.length;i++)
				{
					System.out.println("String values "+i+" va :"+a[i]);
				
					if(a[1].equals("null")|| a[1].equals(" ") )
				{
					System.out.println(":asdasdasdsaas        :");
					
				//reslist1.add("No results founded");
					
				}
				else
				{
					//System.out.println("values  asadsaassadasda :"+a[0]+":        :"+a[1]);
					
					
					
					String j1=a[1].substring(0,2);
					String j2=a[1].substring(2,4);
					String j11=j1+":"+j2;
					
					sss=a[0]+"~"+j11;
					
					
					 System.out.println(sss);
					
					 reslist.add(sss);
					 
					 Iterator iterator = reslist.iterator();
					 
					 while (iterator.hasNext())
				        {
						 String o = (String) iterator.next();
				            if(!reslist1.contains(o)) reslist1.add(o);
				        }

					 				 
					 
					 }
				
							}
				

				}
				
				
				

	

	
		  
				  lv.setAdapter(new MyCustomAdapter());
		  lv.setTextFilterEnabled(true);
		 
		
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title); 
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
		
	public void FetchingData() {
		// TODO Auto-generated method stub
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
 	
 	class MyCustomAdapter extends BaseAdapter {
		
		public int getCount() {
			return reslist1.size();
			
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
				String s1[]={"FL8","FH4","FL9","FL10","HF3","HL10","HL11","HL12","HL13","LF7","LF25","LH9","LH10","LH11","LH12","LH13"};
				String s2[]={"FL18","LF14"};
				String s[]=reslist1.get(position).split("~");
				for(int i=0;i<s.length;i++)
				{
					System.out.println("%%%%"+s[i]);
				}
				
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
				System.out.println("$$$$$"+s[i]);
					}
					else if(flag==2)
					{
						textLabel.setText(s[i]+"\t*Mathruboomi Ladies Special*");
						System.out.println("$$$$$"+s[i]);
							}
					else{
						
						textLabel.setText(s[i]);
						System.out.println("*((((:"+s[i]);
					}
					
				}
				
				for(int i=1;i<s.length;i=i+2)
					
				{
					textLabel1.setText(s[i]);
				}
				
				
				
		
			
			return (row);
		}
	} 

 	
	
 	
}
