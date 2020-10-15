package com.coign.metro;



import java.io.IOException;


import com.metroinfo.gni.DatabaseHandler;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.Toast;

public class TrainSchedule extends TabActivity 
{
	
	 public void onCreate(Bundle savedInstanceState) 
	 {
	       super.onCreate(savedInstanceState);
	       requestWindowFeature(Window.FEATURE_NO_TITLE);  
	    
	     
	       TabHost tabhost=getTabHost();
			Resources res=getResources();
			TabHost.TabSpec spec;
			Intent intent;
			intent=new Intent().setClass(this,Station.class);
			spec=tabhost.newTabSpec("Home").setIndicator("By Station",res.getDrawable(R.drawable.home)).setContent(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			tabhost.addTab(spec);
			intent=new Intent().setClass(this,Fromto.class);
			spec=tabhost.newTabSpec("Services").setIndicator("From-To",res.getDrawable(R.drawable.fromto1)).setContent(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			tabhost.addTab(spec);
			intent=new Intent().setClass(this,TrainNo.class);
			spec=tabhost.newTabSpec("Others").setIndicator("By Train",res.getDrawable(R.drawable.metroicon)).setContent(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
			tabhost.addTab(spec);
			
			tabhost.setCurrentTab(0);
			
	 }

	 
	 
	 
}
