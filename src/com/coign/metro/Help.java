package com.coign.metro;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Help extends Activity{

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	      
	    	 requestWindowFeature(Window.FEATURE_NO_TITLE); 
	    	super.onCreate(savedInstanceState);
	        setContentView(R.layout.help);
	        Button b=(Button)findViewById(R.id.startb);
	        b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent it=new Intent(Help.this,MetroActivity.class);
					startActivity(it);
					finish();
				}
			});
	 }
	 
	 public void onBackPressed(){ 
			//Toast.makeText(getBaseContext(), "You are not supposed to go back", Toast.LENGTH_LONG).show(); 
			}

}
