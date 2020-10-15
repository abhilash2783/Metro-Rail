package com.coign.metro;

import java.io.IOException;


import com.metroinfo.gni.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SplashUI extends Activity {
    /** Called when the activity is first created. */
    
	DatabaseHandler myDbHelper;
	 private SQLiteDatabase Mydatabase;
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.firstsplash);
        this.myDbHelper = new DatabaseHandler(this);
	     FetchingData();
       
        Thread runnerlog=new Thread()
        {
        	public void run()
        	{
        		try
        		{
        			int logoTimer=0;
        			while(logoTimer<2000)
        			{
        				sleep(100);  
        				logoTimer=logoTimer+100;
        			}
        			startActivity(new Intent("com.metro.CLEARSCREEN"));
        		}catch (Exception e) {
					// TODO: handle exception
        			e.printStackTrace();
				}finally
				{
					finish();
				}
        	}
        	
        };
        
        runnerlog.start();
    }
	
	public void FetchingData() {
		// TODO Auto-generated method stub
      try {  
 
        	int i=myDbHelper.onCreateDataBase();
        	   System.out.println("test data base one"+i);    	
        	
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	} 
 	try {
   	   System.out.println("test data base two");   
 		
 	
 	}catch(SQLException sqle){
 
 		throw sqle;
 
 	}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindDrawables(findViewById(R.id.lll));
	System.gc();
	}

	
	private void unbindDrawables(View view) {
	    if (view.getBackground() != null) {
	    view.getBackground().setCallback(null);
	    }
	    if (view instanceof ViewGroup) {
	        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
	        unbindDrawables(((ViewGroup) view).getChildAt(i));
	        }
	    ((ViewGroup) view).removeAllViews();
	    }
	   }
}