package com.example.smsapp;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.SharedPreferences;

public class MainActivity extends Activity {

    /*
    * Set API URL or IP here
    * */
	EditText ip;
	String ipadd;
	
	private Button loginbutton;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ip);
		setUpViews();
		
      }
	private void setUpViews() {
	ip = (EditText)findViewById(R.id.ip);
	
		loginbutton = (Button)findViewById(R.id.setIPbtn);
		
		loginbutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ipadd = ip.getText().toString();
				if(ipadd.contentEquals("") )
				{
					Toast.makeText(MainActivity.this, "IP or URL is empty ", Toast.LENGTH_LONG).show();
				}
				else
				{
					//SharedPreferences prefs = getSharedPreferences("ipdetails", 0);
					SharedPreferences prefs = getSharedPreferences("ipdetails",MODE_PRIVATE);  
					SharedPreferences.Editor editor = prefs.edit();
					  editor.putString("ip", ipadd);
					  editor.commit(); 
					
				}
			}
		});
		
	
}
    }
   
    

	
	


