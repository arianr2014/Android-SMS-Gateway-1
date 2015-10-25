package com.example.smsapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class SendRequest extends AsyncTask<String, String, String> {
	/*
	* arg[0]: Text Message
	* arg[1]: Cell No of sender
	* arg[2]: URL of API to which request to be sent
	* */

	public String response=""; 
	HttpClient httpclient = new DefaultHttpClient();
	
	HttpPost httppost;
	//HttpPost httppost = new HttpPost("http://192.168.137.1:7777/SecondSOAP/MyServlet");
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	@Override
	protected String doInBackground(String... args) {
		 try {
			 String url = "http://"+args[2]+""; //Set API URL or IP here
			 httppost = new HttpPost(url);
			 String msg=args[0]; //SMS body
			 String[] req= new String[2];
			 int i=0;
             // Do whatever you want to do the SMS text before Posting POST request or NOT
			        if(msg.startsWith("#"))
			        {
                        // Processing only messages which starts with '#', Discarding rest
                        // write you business logic here

			        		msg=msg.replace("#", " ");
			        		nameValuePairs.add(new BasicNameValuePair("query", msg));
			        
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			        ResponseHandler<String> responseHandler = new BasicResponseHandler();
			   // Execute HTTP request and get response
			       response = httpclient.execute(httppost,responseHandler);
			       try
			       {
                       //storing response in file for logging
			           File root = new File(Environment.getExternalStorageDirectory(), "Notes");
			           if (!root.exists()) {
			               root.mkdirs();
			           }
			           File gpxfile = new File(root, "respose.txt");
			           FileWriter writer = new FileWriter(gpxfile);
			           response=response.trim();
			           writer.append(response);
			           writer.flush();
			           writer.close();
			           			       }
			       catch(IOException e)
			       {
			            e.printStackTrace();
			            
			       }
			       android.telephony.SmsManager s = android.telephony.SmsManager.getDefault();
			       // send response as text SMS
			       int count=(int) Math.floor(response.length()/133);
			       int startindex=0;
			       int msglimit=133;
			       for(i=1;i<=count;i++){
			    	 String responsemsg=response.substring(startindex, msglimit);
			    	   s.sendTextMessage(args[1], null, responsemsg, null, null);
			    	   startindex=msglimit;
			    	   msglimit+=133;
			    	   
			       }		       

	}
	        }catch (ClientProtocolException e) {
	        	e.printStackTrace();
	        	return null;
	    } catch (IOException e) {
	    	e.printStackTrace();
	    	return null;
	    }
		 return null;
		
	}
	

}
