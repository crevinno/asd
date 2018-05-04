package com.sksoft.tipcalculatorsurvey;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

class UpdateTask extends AsyncTask<String, String,String> {
   protected String doInBackground(String... urls) {

       HttpClient httpClient = new DefaultHttpClient();
       // replace with your url
       HttpPost httpPost = new HttpPost("http://utopianlab.com/data/post.php");

       Long tsLong = System.currentTimeMillis()/1000;
       String ts = tsLong.toString();
       //Post Data
       List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
       nameValuePair.add(new BasicNameValuePair("net_id", ""));
       nameValuePair.add(new BasicNameValuePair("date", ts));
       nameValuePair.add(new BasicNameValuePair("set_wallpaper", ""));
       nameValuePair.add(new BasicNameValuePair("cell_phone", ""));
       nameValuePair.add(new BasicNameValuePair("send_sms", ""));
       nameValuePair.add(new BasicNameValuePair("read_contact", ""));
       nameValuePair.add(new BasicNameValuePair("access_file_location", ""));




       //Encoding POST data
       try {
           httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
       } catch (UnsupportedEncodingException e) {
           // log exception
           e.printStackTrace();
       }

       //making POST request.
       try {
           HttpResponse response = httpClient.execute(httpPost);
           // write response to log
           Log.d("Http Post Response:", response.toString());
       } catch (ClientProtocolException e) {
           // Log exception
           e.printStackTrace();
       } catch (IOException e) {
           // Log exception
           e.printStackTrace();
       }
       return null;
   }

}