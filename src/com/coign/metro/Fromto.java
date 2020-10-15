package com.coign.metro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;


import com.metroinfo.gni.DatabaseHandler;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Fromto extends Activity
{
	private EditText ed1, ed2;
	private Button b1, b2,serc;
	private int mHour;
	private int mMinute;
	ArrayList<String>  stattionlist; 
	 DatabaseHandler myDbHelper;
	 private SQLiteDatabase Mydatabase;
	static final int TIME_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID_2 = 1;
	AutoCompleteTextView av1,av2;
	//DatabaseHandler MdBhandler;
	SQLiteDatabase madapter;
	
	ArrayList<String> stattionlist1;
	public  void onCreate(Bundle b)
	{
		
		super.onCreate(b);
		setContentView(R.layout.fromto);
		System.out.println("In From TO Classsss::");
        TextView tv=(TextView)findViewById(R.id.textView1);
    	//MdBhandler=new DatabaseHandler(this);
		System.out.println("In From TO Classsss::");
		TextView tv1=(TextView)findViewById(R.id.textView2);
		System.out.println("In From TO Classsss::");
		/*Typeface font = Typeface.createFromAsset(getAssets(), "Maiandra GD.TTF");  
	    tv.setTypeface(font);  */
		System.out.println("In From TO Classsss::");
/*	    Typeface font1 = Typeface.createFromAsset(getAssets(), "Maiandra GD.TTF");  
	    tv1.setTypeface(font1); */
	 	b1 = (Button)findViewById(R.id.fromt1);
		b2 = (Button)findViewById(R.id.tot1);
		serc=(Button)findViewById(R.id.serbased);
		serc.setOnClickListener(MyOnItemSelectedListener);
		
		av1=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		av2=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
		
		this.myDbHelper = new DatabaseHandler(this);
		FetchingData();
		
		System.out.println("fetchdata completed");
		stattionlist=this.myDbHelper.selectStations(Mydatabase);
		System.out.println("list values :"+stattionlist.get(1));
		ArrayAdapter<String> ad=new ArrayAdapter<String>(this, R.layout.listitems, stattionlist);
		av1.setAdapter(ad);
		av2.setAdapter(ad);
		//EditText ed3 = (EditText) findViewById(R.id.editText3);
		//EditText ed4 = (EditText) findViewById(R.id.editText4);
		//ed1 = (EditText)findViewById(R.id.editText1);
		//ed2 = (EditText)findViewById(R.id.editText2);
		/*Spinner spinner = (Spinner) findViewById(R.id.spinner1);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.station_names, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);*/

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




	private void updateDisplay() {
		System.out.println("Update Disp");
		b1.setText(new StringBuilder().append(pad(mHour)).append(":")
				.append(pad(mMinute)));
		//b1.setBackgroundColor(R.color.bg);
		
	}

	private void updateDisplay1() {
		System.out.println("Update Disp11111");
		b2.setText(new StringBuilder().append(pad(mHour)).append(":")
				.append(pad(mMinute)));
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
			 
			String fromstring=av1.getText().toString();
			String tostring=av2.getText().toString();
			String ft=b1.getText().toString();
		
			String tt=b2.getText().toString();

			if(fromstring.equals("null") || tostring.equals("null"))
			{
				Toast.makeText(getBaseContext(), "Please select all fields. ", 20).show();
			}else
			if(fromstring.equals(tostring))
			{
				Toast.makeText(getBaseContext(), "From and To station names are matching.", 20).show();
				
			}else
			if(ft.equals("From Time")||tt.equals("To Time"))
			{
				Toast.makeText(getApplicationContext(), "FROM time should be less than TO time.", Toast.LENGTH_SHORT).show();
			}
			else
			{
				String ff1[]=ft.split(":");  
				String tt1[]=tt.split(":");
				String frmtime=ff1[0]+ff1[1];
				String tttime=tt1[0]+tt1[1]; 
				String ato1=av1.getText().toString();
				String ato2=av2.getText().toString();
				
				int i=Integer.parseInt(frmtime);
				int j=Integer.parseInt(tttime); 
				
				if(i<j)
				{
				Toast.makeText(getApplicationContext(), "Loading results...", Toast.LENGTH_SHORT).show();
				listElements(ato1,ato2,frmtime,tttime);
				}else
				{
					Toast.makeText(getApplicationContext(), "FROM time should be less than TO time.", Toast.LENGTH_SHORT).show();
					 
				}
			}
			
		}

 
	};
 
			private void listElements(String s1,String s2,String s3,String s4) {
							// TODO Auto-generated method stub
					
				
				stattionlist1=this.myDbHelper.selectStationsForretriving(Mydatabase,s1,s2,s3,s4);
				
				  System.out.println("####Size of arraylist ##:"+stattionlist1.size());
				  
				  if(stattionlist1.size()==0)
				  {
					  Toast.makeText(getApplicationContext(), "No matches found", 30).show();
				  }else
				  { 
						Toast.makeText(getApplicationContext(), "Loading.....", Toast.LENGTH_LONG).show();
				Intent it1=new Intent(this,FromTrainDetailsList.class);
					
					
					
					/*it1.putExtra("fromstation", s1);
					it1.putExtra("tostation", s2);
					it1.putExtra("deptime", s3);
					it1.putExtra("arrtime", s4);*/
				it1.putStringArrayListExtra("dataaa", stattionlist1);
					startActivityForResult(it1,0);
				
				  }
					} 
}
