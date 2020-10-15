package com.coign.metro;









import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MetroActivity extends Activity {
    /** Called when the activity is first created. */
	//  Animation mAnimation; 
	    TextView about,services,wru;
	    AlertDialog aa;
	
	    
	    ListView lv;
	    
	    Button s1,s2,s3,s4,s5,s6;
    @Override
    public void onCreate(Bundle savedInstanceState) {
      
    	 requestWindowFeature(Window.FEATURE_NO_TITLE); 
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.nailes);
    
        
	       s1=(Button)findViewById(R.id.schedules);
	       s2=(Button)findViewById(R.id.fares);
	       s3=(Button)findViewById(R.id.fesi);
	       //s4=(Button)findViewById(R.id.help);
	         
	          s6=(Button)findViewById(R.id.rmapp);
    
    }
	 public void onClick(View v)
	 {
		
		 if(v.getId()==R.id.schedules){
			 Intent it1=new Intent(this,TrainSchedule.class);
			 startActivity(it1);
		 }
		 if(v.getId()==R.id.fares)
		 {
			 Intent it1=new Intent(this,FaresDetails.class);
			 startActivityForResult(it1,0);
		 }
		 if(v.getId()==R.id.fesi)
		 {
			 Intent it1=new Intent(this,FesilitiesMain.class);
			 startActivityForResult(it1,0);
		 }
		
       
		 if(v.getId()==R.id.rmapp)
		 {
			 Intent it1=new Intent(this,Rmap.class);
			 startActivityForResult(it1,0);
		 }
		
	 }
		 
	 
		private void sendUrl(int s) {
			// TODO Auto-generated method stub
			if(s==0)
			{
				 Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://market.android.com/details?id=com.gini.coign&feature=more_from_developer"));
                 startActivity(i);
			}else
				if(s==1)
				{
					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://market.android.com/details?id=com.coign.calltracker&feature=more_from_developer"));
	                 startActivity(i);
					
				}else
				{
					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://market.android.com/details?id=G.S&feature=more_from_developer"));
	                 startActivity(i);
				}
		}
}