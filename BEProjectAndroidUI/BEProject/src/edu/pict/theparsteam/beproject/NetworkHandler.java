package edu.pict.theparsteam.beproject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.widget.Toast;

@SuppressWarnings("rawtypes")
class NetworkHandler extends AsyncTask 
{
	String j_source=null,j_destination=null;
	//SourceDestination d = new SourceDestination();
	String str = null;
	@Override
	protected Object doInBackground(Object... params) 
	{
		
		try
		{
			HttpClient hc = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://192.168.1.6:7001/BEProject1/Servlet1");
			//HttpPost post = new HttpPost("http://gmail.com");
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
	        nameValuePairs.add(new BasicNameValuePair("s_long",(String)params[0]));
	        nameValuePairs.add(new BasicNameValuePair("s_latt",(String)params[1]));
	        nameValuePairs.add(new BasicNameValuePair("d_long",(String)params[2]));
	        nameValuePairs.add(new BasicNameValuePair("d_latt",(String)params[3]));
	        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse rp = hc.execute(post);
			if(rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				str = EntityUtils.toString(rp.getEntity());
			}
			else
			{
				/*Toast toast=Toast.makeText(MyApplication.getAppContext(), "Error : " + rp.getStatusLine().getStatusCode() , Toast.LENGTH_SHORT);
				toast.show();*/
				str=rp.getStatusLine().toString();
			}
		}
		catch(IOException e)
		{/*
			e.printStackTrace();
			Toast toast=Toast.makeText(MyApplication.getAppContext(), "Error : " + e.toString() , Toast.LENGTH_SHORT);  
			toast.show();*/
			str=e.toString();
		}
		catch(Exception e)
		{
			/*e.printStackTrace();
			Toast toast=Toast.makeText(MyApplication.getAppContext(), "Error : " + e.toString() , Toast.LENGTH_SHORT);
			toast.show();*/
			str=e.toString();
		}
		return str;
	}

	@Override
	protected void onPostExecute(Object result) 
	{
		//Toast toast=Toast.makeText(MyApplication.getAppContext(), "Button Pressed !!! \nSource: "+j_source+"\nDestination: "+j_destination+"\n Response : "+ str , Toast.LENGTH_SHORT);
		Toast toast=Toast.makeText(MyApplication.getAppContext(), "Button Pressed !!! Response : "+ str , Toast.LENGTH_SHORT);
		toast.show();
	}
	
	/*public static String getString()
	{
		return str;
	}*/
	
	
}
