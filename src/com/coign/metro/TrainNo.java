package com.coign.metro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;


import com.metroinfo.gni.DatabaseHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TrainNo extends Activity {
	Spinner avv;
	private String tableName = "Sch1";
	private SQLiteDatabase Mydatabase;
	DatabaseHandler myDbHelper;
	String TableName;
	int trainid;
	String s;
	EditText d;
	ImageView gre, msear, callt, sharee;
	String updated;
	 public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
             "[a-zA-Z0-9+._%-+]{1,256}" +
             "@" +
             "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
             "(" +	              "." +
             "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
             ")+"
         );
	private ArrayList<String> results = new ArrayList<String>();

	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.trainno);
		

		avv = (Spinner) findViewById(R.id.autoCompleteTextView3);
		ArrayAdapter<String> adda = new ArrayAdapter<String>(this,
				R.layout.listitems, TRAINS);
		avv.setAdapter(adda);

	}

	String s1[] = { "FL8", "FH4", "FL9", "FL10", "HF3", "HL10", "HL11", "HL12",
			"HL13", "LF7", "LF25", "LH9", "LH10", "LH11", "LH12", "LH13" };
	String s2[] = { "FL18", "LF14" };

	static final String[] TRAINS = new String[] { "HL1", "HL2", "HL3", "HL4",
			"HL5", "HL6", "HL7", "HL8", "HL9", "HL10", "HL11",
			"HL12 *no service on Sunday*", "HL13 *no service on Sunday*",
			"HL14", "HL15", "HL16", "HL17", "HL18", "HL19", "HL20", "HL21",
			"HL22", "HL23", "HL24", "HL25", "HL26", "FL1", "FL2", "FL3", "FL4",
			"FL5", "FL6", "FL7", "FL8 *no service on Sunday*",
			"FL9 *no service on Sunday*", "FL10 *no service on Sunday*",
			"FL11 *no service on Sunday*", "FL12", "FL13", "FL14", "FL15",
			"FL16", "FL18 *Mathruboomi Ladies Special*", "FL19", "FL20",
			"FL21", "FL22", "FL23", "FL24", "FL25", "FL26", "FL27", "FL28",
			"FL29", "FL30", "FL31", "FL32", "FL33", "LF1", "LF2", "LF3", "LF4",
			"LF5", "LF6", "LF7 *no service on Sunday*", "LF8", "LF9", "LF10",
			"LF11", "LF12", "LF13", "LF14 *Mathruboomi Ladies Special*",
			"LF15", "LF16", "LF17", "LF18", "LF19", "LF20", "LF21", "LF22",
			"LF23", "LF24", "LF25 *no service on Sunday*", "LF26", "LF27",
			"LF28", "LF29", "LF30", "LF31", "LH1", "LH2", "LH3", "LH4", "LH5",
			"LH6", "LH7", "LH8", "LH9 *no service on Sunday*",
			"LH10 *no service on Sunday*", "LH11 *no service on Sunday*",
			"LH12 *no service on Sunday*", "LH13 *no service on Sunday*",
			"LH14", "LH15", "LH16", "LH17", "LH18", "LH19", "LH20", "LH21",
			"FH1", "FH2", "FH3", "FH4 *no service on Sunday*", "FH5", "FH6",
			"FH7", "HF1", "HF2", "HF3" };

	public void onClick(View v) {


			// startActivityForResult(it1,0);

			// Intent it1=new Intent(this,NewApp.class);
			// startActivityForResult(it1,0);
			TextView tv = new TextView(this);
			tv.setText("TO");
			d = new EditText(this);

			

		// FetchingData(avv.getSelectedItem().toString());
		s = avv.getSelectedItem().toString();

		// Toast.makeText(getBaseContext(), "hai"+s.length(), 20).show();

		if (s.length() == 3) {
			updated = s.substring(0, 3);
			// Toast.makeText(getBaseContext(), "hai  :"+updated, 20).show();
		} else if (s.length() == 4) {
			updated = s.substring(0, 4);
			// Toast.makeText(getBaseContext(), "hai  :"+updated, 20).show();
		} else if (s.length() > 4) {
			// s.IndexOf("*");
			int i = s.indexOf("*");
			updated = s.substring(0, i).trim();

			// Toast.makeText(getBaseContext(), "hai  :"+updated, 20).show();
		}

		String TBname = s.substring(0, 2);
		System.out.println("sub string name   :" + TBname);
		if (TBname.equals("HL")) {
			TableName = "HyderabadToLingampally";
		} else if (TBname.equals("FL")) {
			TableName = "FalaknumaToLingampally";
		} else if (TBname.equals("LH")) {
			TableName = "LingampallyToHyderabad";
		} else if (TBname.equals("FH")) {
			TableName = "FalaknumaToHyderabad";
		} else if (TBname.equals("HF")) {
			TableName = "HyderabadToFalaknuma";
		} else if (TBname.equals("LF")) {
			TableName = "LingampallyToFalaknuma";
		}

		System.out.println("Sub string value  :" + TBname);

		sendtiList(TableName);

	}

	private boolean checkEmail(String email) {
		// TODO Auto-generated method stub
	 return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}
 
	
	private void sendMail(String s[]) {
		// TODO Auto-generated method stub

		final Intent emailIntent = new Intent(
				android.content.Intent.ACTION_SEND);

		emailIntent.setType("plain/text");

		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, s);

		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				"Nice Android Apps For Download");

		emailIntent
				.putExtra(
						android.content.Intent.EXTRA_TEXT,
						"Hi\nLook at the urls for new apps found from market\nhttps://market.android.com/details?id=com.gini.coign&feature=more_from_developer\nhttps://market.android.com/details?id=com.coign.calltracker&feature=more_from_developer\nhttps://market.android.com/details?id=G.S&feature=more_from_developer\n\n\nBy\nCoign Edu & IT Services Pvt Ltd.\nwww.coign.net\nfor android Training Contect:srinathreddy@coign.net ");

		TrainNo.this.startActivity(Intent.createChooser(emailIntent,
				"Send mail..."));
		Toast.makeText(getApplicationContext(), "completed", 20);

	}

	private void sendtiList(String TableName) {
		// TODO Auto-generated method stub

		Intent itt = new Intent(this, TrainListbyID.class);
		itt.putExtra("values", TableName);
		itt.putExtra("trn", updated);
		startActivityForResult(itt, 0);

	}

}
