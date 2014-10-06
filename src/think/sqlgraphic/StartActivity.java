package think.sqlgraphic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.AsyncTask;
//import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toast;

public class StartActivity extends Activity {
	
private ListView lista;	
private DBAdapter dataDB;
protected ActionMode mActionMode;
private DBListViewAdapter mlistAdapter;
private ArrayList<Lista_entrada> datos;
public int selectItem=1;
public int idAux=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		lista =(ListView) findViewById(R.id.ListViewDB);
		
		openServer();
		
		if (dataDB.countServis()==true) {
			dataDB.insertRow("localhost", "127.0.0.1", 80, "root", "", "", "");
		}else{
			//Toast.makeText(this, "Don't exist data of the server", Toast.LENGTH_SHORT).show();
		}
		
		datos=new ArrayList<Lista_entrada>();
		
		Cursor cursor=dataDB.getAllRows();
		if (cursor.moveToFirst()) {
			String names;
			String hosts;
			String number;
			do {
				number=cursor.getString(0);
				names=cursor.getString(1);
				hosts=cursor.getString(2);
				datos.add(new Lista_entrada(R.drawable.ic_item, names, hosts, number));
			} while (cursor.moveToNext());
		}
		
		cursor.close();	
		mlistAdapter=new DBListViewAdapter(this, R.layout.entrada, datos){
			@Override
			public void onEntrada(Object entrada, View view) {
				if (entrada != null) {
					TextView texto_superior_entrada = (TextView) view.findViewById(R.id.hostname); 
		            texto_superior_entrada.setText(((Lista_entrada) entrada).getTextTitle()); 
		            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.hostip); 
		            texto_inferior_entrada.setText(((Lista_entrada) entrada).getTextIp()); 
		            TextView texto_number= (TextView) view.findViewById(R.id.hostnumber);
		            texto_number.setText(((Lista_entrada) entrada).getTextNumber());
		            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.icondatabase); 
		            imagen_entrada.setImageResource(((Lista_entrada) entrada).getIdImage());
				}
			}
		};
		
        lista.setAdapter(mlistAdapter);
        
        /*lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (mActionMode!= null) {
					return false;
				}
				selectItem=position;
				//parent.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
				//TextView text = (TextView) view.findViewById(R.id.hostname);
	            //hostname = text.getText().toString().trim();

				mActionMode=startActionMode(new ActionModeCallBack());
				view.setSelected(true);
				
				return true;
			}			
			
		});*/
        
        registerForContextMenu(lista);
        
        
        lista.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
				Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion); 

                String texto = elegido.getTextNumber();
                try {
                	idAux=Integer.parseInt(texto);
                	//Log.e("RAFASSSS", ""+idAux);
                	Cursor cursorsearch=dataDB.getRow(idAux);
                	String hostax,userax,passax,portax;
                	do {
						hostax=cursorsearch.getString(2);
						portax=cursorsearch.getString(3);
						userax=cursorsearch.getString(4);
						passax=cursorsearch.getString(5);
						int ports=Integer.parseInt(portax);
						//Log.e("cadena",hostax +" "+ ports +" "+ userax +" "+passax);
						new TaskConnection().execute(new testConection(hostax, userax, passax, ports));
					} while (cursorsearch.moveToNext());
                	
                } catch (Exception e) {
					e.printStackTrace();
				}	 
			}
        	
		});     
	}

	private void openServer() {
		dataDB=new DBAdapter(this);
		dataDB.open();
	}
	
	private void closeServer(){
		dataDB.close();
	}

	@Override
	protected void onDestroy() {
		closeServer();
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.start, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case R.id.menu_new:
	           AddData();
	            return true;
	         default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	/*class ActionModeCallBack implements ActionMode.Callback{
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			 switch (item.getItemId()) {
	            case R.id.editData:
	                
	                mode.finish(); // Action picked, so close the CAB
	                return true;
	            case R.id.deleteData:
	            	mode.finish();
	            	return true;
			default:
	                return false;
	        }
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			MenuInflater inflater=mode.getMenuInflater();
			inflater.inflate(R.menu.contextmenustart, menu);
			return true;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			mActionMode=null;
		}

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			
			mode.setTitle("Options: ");
			return false;
		}
		
	}
*/		

	public void AddData(){
	    	Intent intent = new Intent(this, AddServerData.class);
	    	startActivity(intent);	    	
	}
	 
	 public void Editdata() {
		Intent intent=new Intent(this, EditActivity.class);
		startActivity(intent);
	} 

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		 AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		 int position=info.position;
     	Lista_entrada values= datos.get(position);
		    switch (item.getItemId()) {
		        case R.id.editData:
		        	Intent intent=new Intent(getApplicationContext(), EditActivity.class);
		        	intent.putExtra("idDatabase", values.getTextNumber());
		        	startActivity(intent);
		            return true;
		        case R.id.deleteData:
		        	try {
		        		String id=values.getTextNumber();
		        		long ids=Long.parseLong(id);
		        		/*Toast toast = Toast.makeText(StartActivity.this, position+"----"+values.getTextNumber(), Toast.LENGTH_LONG);
		                toast.show();*/
		        		dataDB.deleteRow(ids);
						datos.remove(position);
						//String dateIp=datos.get(position).getTextIp().toString();
						dialoMesajeOK();
						mlistAdapter.notifyDataSetChanged();
					} catch (Exception e) {
						 dialoMesaje();
					}
		            return true;
		        default:
		            return super.onContextItemSelected(item);
		    }
	}

	@Override
	public void onContextMenuClosed(Menu menu) {
		// TODO Auto-generated method stub
		super.onContextMenuClosed(menu);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.contextmenustart, menu);
	}
	
	public void dialoMesaje(){
		AlertDialog.Builder alertAction=new AlertDialog.Builder(StartActivity.this);
		LayoutInflater inflater= StartActivity.this.getLayoutInflater();
		alertAction.setView(inflater.inflate(R.layout.layaut_alert, null));
		alertAction.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		
		alertAction.show();
	}
	
	public void dialoMesajeOK(){
		AlertDialog.Builder alertAction=new AlertDialog.Builder(StartActivity.this);
		//LayoutInflater inflater= StartActivity.this.getLayoutInflater();
		alertAction.setTitle(R.string.imgErrorOK);
		alertAction.setIcon(R.drawable.ic_ok);
		alertAction.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		
		alertAction.show();
	}
	
	private class TaskConnection extends AsyncTask<testConection, Long, JSONArray>{

		private ProgressDialog progressDialog;
		@Override
		protected JSONArray doInBackground(testConection... params) {
			return params[0].TestConection();
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPreExecute()
		 */
		@Override
		protected void onPreExecute() {
			
			progressDialog=new ProgressDialog(StartActivity.this);
			progressDialog.setMessage("Connecting to server wait a moment.");
			progressDialog.show();
			super.onPreExecute();
		}

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(JSONArray result) {
			boolean resultado=setJsonArrayResultConection(result);
			if(resultado==true && idAux!=0){
				progressDialog.dismiss();
				//Toast.makeText(StartActivity.this, ""+idAux, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getBaseContext(), MainActivity.class);
				intent.putExtra("keyId", idAux);
    	    	startActivity(intent);
			}
			else{
				progressDialog.dismiss();
				message_ConnectionError();
			}
			super.onPostExecute(result);
		}

		private boolean setJsonArrayResultConection(JSONArray result) {
			boolean resultado=false;
			if (result!=null) {
					JSONObject objectJson=null;
					try {
						objectJson=result.getJSONObject(0);
						
						String title=objectJson.getString("titulo");
						//Log.e("Resultado", ""+title);
						if ("MySQLError".equals(title) || "EmptyError".equals(title)) {
							resultado=false;
						}else{
							resultado=true;
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
			}else{
				resultado=false;
			}
				
			return resultado;
		}
		
	}
	
	private void message_ConnectionError(){
		ConnectionErrorMessage errormessage=new ConnectionErrorMessage();
		errormessage.show(getFragmentManager(), "Error Conection");
	}

}