package think.sqlgraphic;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class testConection {
	
	private String host;
	private String username; 
	private String password;
	private int port;
	
	public testConection(String host, String username, String password, int port) {
		this.host=host;
		this.username=username;
		this.password=password;
		this.port=port;
	}
	
	public JSONArray TestConectinString(){
		final String LOGTAG = "LogsAndroid";
		String url="http://rafasp020689.byethost11.com/dataBaseAll.php?host="+this.host+
				"&port="+this.port+"&username="+this.username+"&password="+this.password;
		HttpEntity httpEntity=null;
		
		Log.w(LOGTAG, ""+url);
		
		try {
			DefaultHttpClient httpclient=new DefaultHttpClient();
			HttpGet httpget=new HttpGet(url);
			HttpResponse httpresponse=httpclient.execute(httpget);
			httpEntity=httpresponse.getEntity();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONArray jsonarray=null;
		
		if (httpEntity!=null) {
			try {
				String entity=EntityUtils.toString(httpEntity);
				jsonarray=new JSONArray(entity);
			} catch (JSONException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		} 

		return jsonarray;
	}
	
	public JSONArray dataBaseAll(){
		final String LOGTAG = "LogsAndroid";
		String url="http://rafasp020689.byethost11.com/dataBaseAll.php?host="+this.host+
				"&port="+this.port+"&username="+this.username+"&password="+this.password;
		HttpEntity httpEntity=null;
		
		Log.w(LOGTAG, ""+url);
		
		try {
			DefaultHttpClient httpclient=new DefaultHttpClient();
			HttpGet httpget=new HttpGet(url);
			HttpResponse httpresponse=httpclient.execute(httpget);
			httpEntity=httpresponse.getEntity();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONArray jsonarray=null;
		
		if (httpEntity!=null) {
			try {
				String entity=EntityUtils.toString(httpEntity);
				jsonarray=new JSONArray(entity);
			} catch (JSONException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		} 

		return jsonarray;
	}

}
