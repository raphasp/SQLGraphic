package think.sqlgraphic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

//import android.util.Log;

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
	
	public JSONArray TestConection(){
		//final String LOGTAG = "LogsAndroid";
		String url="http://rafasp020689.byethost11.com/testConection.php?host="+this.host+
				"&port="+this.port+"&username="+this.username+"&password="+this.password;
		HttpEntity httpEntity=null;
		
		//Log.w(LOGTAG, ""+url);
		
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
		//final String LOGTAG = "LogsAndroid";
		String url="http://rafasp020689.byethost11.com/dataBaseAll.php?host="+this.host+
				"&port="+this.port+"&username="+this.username+"&password="+this.password;
		HttpEntity httpEntity=null;
		
		//Log.w(LOGTAG, ""+url);
		
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

	public JSONArray dataBaseAllTables(){
		//final String LOGTAG = "LogsAndroid";
		String url="http://rafasp020689.byethost11.com/allDataBaseInf.php?host="+this.host+
				"&port="+this.port+"&username="+this.username+"&password="+this.password;
		HttpEntity httpEntity=null;
		
		//Log.w(LOGTAG, ""+url);
		
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

	public JSONArray resultDataTable(String query, String database){
		//final String LOGTAG = "LogsAndroid";
		//String url="http://rafasp020689.byethost11.com/infSQLQuery.php?host="+this.host+
				//"&port="+this.port+"&username="+this.username+"&password="+this.password+"&query="+query+"&database="+database;
		//String url="http://rafasp020689.byethost11.com/infSQLQuery.php?host="+this.host+
			//	"&port="+this.port+"&username="+this.username+"&password="+this.password;
		String url="http://rafasp020689.byethost11.com/infSQLQuery.php";
		Log.w("URL", url);
		HttpEntity httpEntity=null;
		
		//Log.w(LOGTAG, ""+url);
		
		try {
			DefaultHttpClient httpclient=new DefaultHttpClient();
			HttpPost httpost=new HttpPost(url);
			
			List<NameValuePair> vlues=new ArrayList<NameValuePair>(2);
			vlues.add(new BasicNameValuePair("host", this.host));
			vlues.add(new BasicNameValuePair("username", this.username));
			vlues.add(new BasicNameValuePair("password", this.password));
			vlues.add(new BasicNameValuePair("port", ""+this.port));
			vlues.add(new BasicNameValuePair("query", query));
			vlues.add(new BasicNameValuePair("database", database));
			
			httpost.setEntity(new UrlEncodedFormEntity(vlues));
			
			HttpResponse httpresponse=httpclient.execute(httpost);
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

	public JSONArray resultDataGraphic(String query, String database){
		//final String LOGTAG = "LogsAndroid";
		//String url="http://rafasp020689.byethost11.com/infSQLQuery.php?host="+this.host+
				//"&port="+this.port+"&username="+this.username+"&password="+this.password+"&query="+query+"&database="+database;
		//String url="http://rafasp020689.byethost11.com/infSQLQuery.php?host="+this.host+
			//	"&port="+this.port+"&username="+this.username+"&password="+this.password;
		String url="http://rafasp020689.byethost11.com/normalizarGraph.php";
		Log.w("URL", url);
		HttpEntity httpEntity=null;
		
		//Log.w(LOGTAG, ""+url);
		
		try {
			DefaultHttpClient httpclient=new DefaultHttpClient();
			HttpPost httpost=new HttpPost(url);
			
			List<NameValuePair> vlues=new ArrayList<NameValuePair>(2);
			vlues.add(new BasicNameValuePair("host", this.host));
			vlues.add(new BasicNameValuePair("username", this.username));
			vlues.add(new BasicNameValuePair("password", this.password));
			vlues.add(new BasicNameValuePair("port", ""+this.port));
			vlues.add(new BasicNameValuePair("query", query));
			vlues.add(new BasicNameValuePair("database", database));
			
			httpost.setEntity(new UrlEncodedFormEntity(vlues));
			
			HttpResponse httpresponse=httpclient.execute(httpost);
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
