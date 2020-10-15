package com.coign.metro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;


import com.metroinfo.gni.DatabaseHandler;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Station extends Activity {
	private EditText ed1, ed2;
	private Button b1, b2,ser;
	private int mHour;
	private int mMinute;
	ImageView gre,msear,callt,sharee;
	String sta;
	 EditText d;
	String sr;
	String frmtime,tttime;
	ArrayList<String>  stattionlist; 
	static final int TIME_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID_2 = 1;
	Spinner spinner,spinner1;
	 DatabaseHandler myDbHelper;
	 private SQLiteDatabase Mydatabase;
	 String stations[];
	 String routes[]={"SelectRoute","Hyderabad-Lingampally","Lingampally-Hyderabad","Lingampally-Falaknuma","Falaknuma-Lingampally","Falaknuma-Hyderabad","Hyderabad-Falaknuma"};
	 ArrayList<String> trainvalslist;
	TextView pcc;
	
	String mainroute;
	
	 public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
             "[a-zA-Z0-9+._%-+]{1,256}" +
             "@" +
             "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
             "(" +	              "." +
             "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
             ")+"
         );
	
	
	
	 public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stationschedule);
		b1 = (Button) findViewById(R.id.fromt);
		b2 = (Button) findViewById(R.id.tot);
		ser = (Button) findViewById(R.id.searchbut);
		pcc=(TextView)findViewById(R.id.pc);
		
		
		
		
		
		this.myDbHelper = new DatabaseHandler(this);
		
		 //= {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};
		//ed1 = (EditText) findViewById(R.id.editText1);
		//ed2 = (EditText) findViewById(R.id.editText2);
		spinner1 = (Spinner) findViewById(R.id.droppdownroute);
		
		spinner = (Spinner) findViewById(R.id.droppdown);
	     spinner.setVisibility(View.GONE);
	     pcc.setVisibility(View.GONE);
	     FetchingData();
	     //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	       //     this, R.array.station_names, android.R.layout.simple_spinner_item);
	     @SuppressWarnings("unchecked")
		//ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,stattionlist);
	     ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, routes);
	     
	     adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     
	     spinner1.setAdapter(adapter1);
	     spinner1.setOnItemSelectedListener(new OnItemSelectedListener()
	     {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long arg3) {
				// TODO Auto-generated method stub
				
				sr=parent.getItemAtPosition(pos).toString();
				//Toast.makeText(getBaseContext(), "value "+sr, 20).show();
			if(sr.equals("SelectRoute")){	
			
				 spinner.setVisibility(View.GONE);
			}
			else
				if(sr.equals("Hyderabad-Lingampally"))
			{
					mainroute="HyderabadToLingampally"; 
					System.out.println("sr vale"+mainroute); 
					viewSpinnerData();
					spinner.setVisibility(View.VISIBLE);
					pcc.setVisibility(View.VISIBLE);
			}
				else
					if(sr.equals("Lingampally-Hyderabad"))
				{
						mainroute="LingampallyToHyderabad";
						viewSpinnerData();
						spinner.setVisibility(View.VISIBLE);
						pcc.setVisibility(View.VISIBLE);
				}
					else
						if(sr.equals("Lingampally-Falaknuma"))
					{
							mainroute="LingampallyToFalaknuma";
							System.out.println("sr vale"+mainroute); 
							viewSpinnerData();
							spinner.setVisibility(View.VISIBLE);
							pcc.setVisibility(View.VISIBLE);
					}
					
						else
							if(sr.equals("Falaknuma-Lingampally"))
						{
								mainroute="FalaknumaToLingampally";
								System.out.println("sr vale"+mainroute);
								viewSpinnerData();
								spinner.setVisibility(View.VISIBLE);
								pcc.setVisibility(View.VISIBLE);
						}
							else
								if(sr.equals("Falaknuma-Hyderabad"))
							{
									mainroute="FalaknumaToHyderabad";
									System.out.println("sr vale"+mainroute); 
									viewSpinnerData();
									spinner.setVisibility(View.VISIBLE);
									pcc.setVisibility(View.VISIBLE);
							}
								else
									if(sr.equals("Hyderabad-Falaknuma"))
								{
										mainroute="HyderabadToFalaknuma";
										System.out.println("sr vale"+mainroute);
										viewSpinnerData();
										spinner.setVisibility(View.VISIBLE);
										pcc.setVisibility(View.VISIBLE);
								}
								
						

								/*System.out.println("sr vale"+mainroute); 
								viewSpinnerData();
								spinner.setVisibility(View.VISIBLE);
								pcc.setVisibility(View.VISIBLE);*/
									
				
				
				
				
			
			}

			

			


			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    	 
	     });  
	     
	     
	  
	     
	    ser.setOnClickListener(MyOnItemSelectedListener);
		b1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println("In On Click Listener");
				showDialog(TIME_DIALOG_ID);
				updateDisplay();
			}
		});
		b2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println("In On Click Listener222");
				showDialog(TIME_DIALOG_ID_2);
				updateDisplay1();
			}
		});
		final Calendar c = Calendar.getInstance();
		mHour = c.get(Calendar.HOUR_OF_DAY);
		mMinute = c.get(Calendar.MINUTE);

		// display the current date
	}
	
	 public void onClick(View v)
	 {
		
	
			// startActivityForResult(it1,0);

				// Intent it1=new Intent(this,NewApp.class);
				// startActivityForResult(it1,0);
			 TextView tv=new TextView(this);
			 tv.setText("TO");
				 d=new EditText(this);
				 
				 new AlertDialog.Builder(Station.this)              
	             .setTitle("Share Apps To your Friends")
	             .setMessage("Please enter your friend mail addresses")
	             .setView(tv)
	             .setView(d)
	              .setPositiveButton ("Send", new DialogInterface.OnClickListener(){
                    public void onClick (DialogInterface dialog, int whichButton){
                    	String s[]=d.getText().toString().split(";");
                    	if(s.length==0)
                    	{
                    		 Toast.makeText(getApplicationContext(), "Please provide Email Address.", 30).show();
                    	}
                    	else 
                    	{
                    		for(int j=0;j<s.length;j++){
                    			if(checkEmail(s[j])){
                    				sendMail(s);
                    			}else{
                    				 Toast.makeText(Station.this,"Invalid Email Addresss", Toast.LENGTH_SHORT).show();
                    				
                    				
                    			}
                    		}
                        
                         }
                    	}

				

					
                })
	            .show();
	                 
	             
		 }
	 
	
		private boolean checkEmail(String email) {
			// TODO Auto-generated method stub
		 return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
		}
	 
	 private void sendMail(String s[]) {
			// TODO Auto-generated method stub
			
		 
		 
		 final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
         
         emailIntent.setType("plain/text");
    
         emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, s);
  
         emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Nice Android Apps For Download");
  
         emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Hi\n\n\nLook at the urls for new apps found from market!!!\n\n\nhttps://market.android.com/details?id=com.gini.coign&feature=more_from_developer\nhttps://market.android.com/details?id=com.coign.calltracker&feature=more_from_developer\nhttps://market.android.com/details?id=G.S&feature=more_from_developer\n\n\nBy\nCOIGN EDU & IT SERVICES PVT LTD.\n\nwww.coign.net\n\nFor Android Training Contact: srinathreddy@coign.net ");

         Station.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
         Toast.makeText(getApplicationContext(), "completed", 20);
		 
		 
		 
		}
	 
	private void viewSpinnerData() {
		// TODO Auto-generated method stub
		  System.out.println(" out of on state"+mainroute);
		     
		    
			stattionlist=this.myDbHelper.selectStations(Mydatabase,mainroute);   
			stations=new String[stattionlist.size()];
			
		stattionlist.toArray(stations);
	     
	     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, stations);
	    
	     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     
	     spinner.setAdapter(adapter);
	}

	
	
	

	
	
	/*
		ArrayAdapter<String> adaptertest = null;
 Cursor c = Mydatabase.rawQuery("select station_name from stations", null);    
	//c.getColumnIndex("User")
	if (c != null ) {  
		if  (c.moveToFirst()) { 
			do {
				
				int i1=c.getColumnIndex("station_name");
			
				
				String stationname=c.getString(i1);
		
				
				adaptertest.add(stationname); 
		
			
			}while (c.moveToNext());
			System.out.println("val"+adaptertest.getItem(0));
				
		} 
	}
	
	}*/
 	
	public void FetchingData() {
		// TODO Auto-generated method stub
      try {  
 
        	myDbHelper.onCreateDataBase();
        	   	
        	
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	} 
 	try {
 		 System.out.println("test data base two");    
 		myDbHelper.openDataBase();
 		Mydatabase = myDbHelper.getWritableDatabase();
		System.out.println("executed");
 	
 	}catch(SQLException sqle){
 
 		throw sqle;
 
 	}
	}


	private void updateDisplay() {
		System.out.println("Update Disp");
		b1.setText(new StringBuilder().append(pad(mHour)).append(":")
				.append(pad(mMinute)));
		//b1.setTextColor(R.color.tx);
		//b1.setBackgroundColor(R.color.bg);
		
		
	}

	private void updateDisplay1() {
		System.out.println("Update Disp11111");
		b2.setText(new StringBuilder().append(pad(mHour)).append(":")
				.append(pad(mMinute)));
		//b2.setTextColor(R.color.tx);
		//b2.setBackgroundColor(R.color.bg);
	
		}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	// the callback received when the user "sets" the time in the dialog
	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateDisplay();
		}
	};

	// the callback received when the user "sets" the time in the dialog
	private TimePickerDialog.OnTimeSetListener mTimeSetListener1 = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateDisplay1();
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,false);
		case TIME_DIALOG_ID_2:
			return new TimePickerDialog(this, mTimeSetListener1, mHour, mMinute,false);
			
		}
		return null;
	}
	OnClickListener MyOnItemSelectedListener =new  OnClickListener()
	{

	  

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 
		
			
		String ft=b1.getText().toString();
		System.out.println("String value"+ft);
		String tt=b2.getText().toString();
		System.out.println("String value"+tt);
		if(ft.equals("From Time")||tt.equals("To Time"))
		{
			Toast.makeText(getBaseContext(), "Please select all fields. ", 20).show();
		}
		else
		{
			sta=spinner.getSelectedItem().toString();
			String ff1[]=ft.split(":");  
			String tt1[]=tt.split(":");
			frmtime=ff1[0]+ff1[1];
			tttime=tt1[0]+tt1[1]; 
			int i=Integer.parseInt(frmtime);
			int j=Integer.parseInt(tttime); 
			
			if(i<j)
			{
			Toast.makeText(getApplicationContext(), "Loading results...", 10).show();
			
			DataFetching(sta,frmtime,tttime);
			SendingIntent(frmtime,tttime);
			
			}else
			{
				Toast.makeText(getApplicationContext(), "FROM time should be less than TO time.", Toast.LENGTH_SHORT).show();
				 
			}
		}
		
		
		
		
		}

	

	};
	private void DataFetching(String sta,String i,String j) {
		// TODO Auto-generated method stub
		trainvalslist=this.myDbHelper.selectSchedules(Mydatabase,mainroute,sta,i,j);
		
		
	} 
private void SendingIntent(String s1,String s2)
	{
	System.out.println("sending intent method  :"+s1);
	System.out.println("sending intent method  :"+s2);
	
	Intent itt=new Intent(this,ByStationOutput.class);
	itt.putExtra("tablename", mainroute);
	itt.putExtra("fromtime", s1);
	itt.putExtra("totime", s2);
	itt.putExtra("stationname", sta);
	itt.putStringArrayListExtra("values", trainvalslist);
	startActivityForResult(itt,0);		
	}

}
