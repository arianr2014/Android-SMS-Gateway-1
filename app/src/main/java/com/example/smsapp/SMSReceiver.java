package com.example.smsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver  {
	

    @Override
	public void onReceive(Context context, Intent intent) {
		
    	
	    
	    //this stops notifications to others
		    //this.abortBroadcast();

		    //---get the SMS message passed in---
		    Bundle bundle = intent.getExtras();   
		    SmsMessage[] msgs = null;
		    String str = "";       
		    String msg="";
		    String  from="";

		    SharedPreferences prefs =  context.getSharedPreferences("ipdetails",Context.MODE_PRIVATE);
			String ipadd= prefs.getString("ip",
					  "127.0.0.1");
		    
		    if (bundle != null)
		    {
		        //---retrieve the SMS message received---
		        Object[] pdus = (Object[]) bundle.get("pdus");
		        msgs = new SmsMessage[pdus.length];            
		        for (int i=0; i<msgs.length; i++){
		            msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                
		            str += "SMS from " + msgs[i].getOriginatingAddress();
		           from = msgs[i].getOriginatingAddress();
		            str += " :";
		            str += msgs[i].getMessageBody().toString();
		            msg = msgs[i].getMessageBody().toString();
		            str += "\n"; 
		        }
		        
			   		        }
		    	      
		   new SendRequest().execute(msg,from,ipadd);
		   Toast.makeText(context,"success" ,Toast.LENGTH_LONG).show();
		   Toast.makeText(context,ipadd ,Toast.LENGTH_LONG).show();
	        
	        
		    }
		}

	


	


