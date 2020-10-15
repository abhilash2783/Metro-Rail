package com.coign.metro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


import com.metroinfo.gni.DatabaseHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FesilitiesMain extends Activity {
	
	 DatabaseHandler myDbHelper;
	 private SQLiteDatabase Mydatabase;

	 static final String[] Stations = new String[] {"Falaknuma","Huppuguda","Yakutpura","Dabirpura","Malakpet","Khacheguda","Vidyanagar","Jamai Osmania","Arts College",
		 "Sitafalmandi","Secunderabad","James Street","Hitech City","Hafeezpet","Chandanagar","Lingampally"};
	 
	 
		ArrayList<String>  stattionlist;
	 public void onCreate(Bundle savedInstanceState) {
		 requestWindowFeature(Window.FEATURE_NO_TITLE);    
		 super.onCreate(savedInstanceState);
	       setContentView(R.layout.fesilist);
	        //this.myDbHelper = new DatabaseHandler(this);
			//FetchingData();

			//stattionlist=this.myDbHelper.selectStations(Mydatabase);
			
	        ListView lv=(ListView)findViewById(R.id.lfesi);
	        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listitems, Stations));
	       // getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title); 
	      //  lv.setBackgroundResource(R.drawable.thk);
	        lv.setTextFilterEnabled(true);
	        lv.setOnItemClickListener(new OnItemClickListener() {
				 
				@Override
				public void onItemClick(AdapterView<?> arg0,
						android.view.View view, int arg2, long arg3) {
					// TODO Auto-generated method stub
					//Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
					  //        Toast.LENGTH_SHORT).show();
					String liststring=((TextView) view).getText().toString();
					
					DataGetting(liststring);
				}
			  });
		
		
	   	 ImageView ll = (ImageView) findViewById(R.id.fesihead);
	      ll.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	              Intent intent = new Intent();
	              setResult(RESULT_OK, intent);
	              finish();
	          }

	      });
	        
		    /*  ImageView next = (ImageView) findViewById(R.id.Button02);
		      next.setOnClickListener(new View.OnClickListener() {
		          public void onClick(View view) {
		              Intent intent = new Intent();
		              setResult(RESULT_OK, intent);
		              finish();
		          }

		      });*/
	     
	 }
		
	 public void DataGetting(String s1)
		{
		 
		 Intent itt=new Intent(this,Fesilitiesoutput.class);
		 itt.putExtra("clickvalue", s1);
			startActivityForResult(itt, 0);
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


}
