package com.coign.metro;

import java.io.IOException;
import java.util.ArrayList;


import com.metroinfo.gni.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FaresDetails extends Activity {

	DatabaseHandler myDbHelper;
	SQLiteDatabase Mydatabase;
	String ss[];
	ArrayList<String> stattionlist;
	TextView tv,tv1;
	public  void onCreate(Bundle b)
	
	{
		
		super.onCreate(b);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.faresdata);
		
		tv=(TextView)findViewById(R.id.optfone);
		tv1=(TextView)findViewById(R.id.opftwo);
		
		 LinearLayout ll = (LinearLayout) findViewById(R.id.flldata);
	      ll.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View view) {
	              Intent intent = new Intent();
	              setResult(RESULT_OK, intent);
	              finish();
	          }

	      });
		this.myDbHelper = new DatabaseHandler(this);
		FetchingData();
		System.out.println("fetchdata completed");
		stattionlist=this.myDbHelper.selectFares(Mydatabase);
	ss=new String[stattionlist.size()];
		for(int l=0;l<stattionlist.size();l++)
		{
		ss[l]=stattionlist.get(l);
		}
		for(int l1=0;l1<ss.length;l1++)
		{
		System.out.println("values  :"+l1+"valuessss"+ss[l1]);
		}
		String txt="";
		for(int l2=0;l2<ss.length;l2=l2+2)
		{
			txt=txt+ss[l2].toString()+"\n\n";
		}
		tv.setText(txt);
		
		String txt1="";
		for(int l2=1;l2<ss.length;l2=l2+2)
		{
			txt1=txt1+ss[l2].toString()+"\n\n";
		}
		tv1.setText(txt1);
	}
	private void FetchingData() {
		
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
		// TODO Auto-generated method stub
		
	}
}
