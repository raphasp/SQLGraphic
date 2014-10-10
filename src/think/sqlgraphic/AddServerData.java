package think.sqlgraphic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import think.sqlgraphic.R.string;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddServerData extends Activity {
	
	public EditText name;
	public EditText host;
	public EditText port;
	public EditText username;
	public EditText password;
	public EditText database;
	public EditText description;
	public Button btndatabase;
	public DBAdapter data_sever;
	public JSONObject databaseJSON=null;
	public ArrayList<String> databaseList;
	public int btnclick=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_server_data);
				
		name=(EditText)findViewById(R.id.nombre);
		host=(EditText)findViewById(R.id.hosting);
		port=(EditText)findViewById(R.id.port);
		username=(EditText)findViewById(R.id.username);
		password=(EditText)findViewById(R.id.password);
		database=(EditText)findViewById(R.id.database);
		description=(EditText)findViewById(R.id.description);
		btndatabase=(Button)findViewById(R.id.btndatabase);
		dataOpen();
		setupActionBar();
		registerForContextMenu(btndatabase);
		btndatabase.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				String Sname=name.getText().toString().trim();
				String Shost=host.getText().toString().trim();
				String Sport=port.getText().toString().trim();
				String Susername=username.getText().toString().trim();
				if("".equals(Sname) || "".equals(Shost) || "".equals(Sport)
					|| "".equals(Susername)){
					dialogAlertSQL dialog=new dialogAlertSQL();
					dialog.show(getFragmentManager(), "Alert field Empty");
				}else{
					try {
						if (btnclick==1) {
							Integer.parseInt(Sport);
							v.showContextMenu();
						}
					} catch (NumberFormatException e) {
						AlertNumber numberalert=new AlertNumber();
						numberalert.show(getFragmentManager(), "Alert Error field");
					}			
				}
					
			}
		});
	}

	@Override
	protected void onDestroy() {
			dataClose();
			super.onDestroy();
	}
	
	private void dataOpen() {
		data_sever=new DBAdapter(this);
		data_sever.open();		
	}
	
	private void dataClose(){
		data_sever.close();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_server_data, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.testConnection:
				textOfConnection();
			//AddServerData.this.finish();
			return true;
		case R.id.saveData:
			SaveData_Server();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	private void textOfConnection() {
		String Sname=name.getText().toString().trim();
		String Shost=host.getText().toString().trim();
		String Sport=port.getText().toString().trim();
		String Susername=username.getText().toString().trim();
		String Spassword=password.getText().toString().trim();	
		if("".equals(Sname) || "".equals(Shost) || "".equals(Sport)
			|| "".equals(Susername)){
			dialogAlertSQL dialog=new dialogAlertSQL();
			dialog.show(getFragmentManager(), "Alert field Empty");
		}else{
			int portAux=0;
			try {
				portAux=Integer.parseInt(Sport);
				new TaskTestConecction().execute(new testConection(Shost, Susername, Spassword, portAux));
			} catch (NumberFormatException e) {
				AlertNumber numberalert=new AlertNumber();
				numberalert.show(getFragmentManager(), "Alert Error field");
			}			
		}
	}

	public void SaveData_Server(){
		String Sname=name.getText().toString().trim();
		String Shost=host.getText().toString().trim();
		String Sport=port.getText().toString().trim();
		String Susername=username.getText().toString().trim();
		String Spassword=password.getText().toString().trim();
		String Sdatabase=database.getText().toString().trim();
		String Sdescription=description.getText().toString();		
		int portAux=0;
		try {
			if("".equals(Sname) || "".equals(Shost)){
				dialogAlertSQL dialog=new dialogAlertSQL();
				dialog.show(getFragmentManager(), "Alert field Empty");
			}else{
				portAux=Integer.parseInt(Sport);
				boolean nameExist=data_sever.searchName(Sname);
				if(nameExist==false){
					DialogAlertMessage("Alerta!", "El nombre de la base de datos existe.");
				}else{
					data_sever.insertRow(Sname, Shost, portAux, Susername, Spassword, Sdatabase, Sdescription);
					DialogMessage("Los datos del servidor se guardaron correctamente.", "Mesaje de confirmación");
				}
			}
		} catch (NumberFormatException e) {
			AlertNumber numberalert=new AlertNumber();
			numberalert.show(getFragmentManager(), "Alert Error field");
		}			
		
	}
	
	private void DialogAlertMessage(String title, String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setIcon(getResources().getDrawable(R.drawable.ic_alert));
        builder.setPositiveButton("OK", null);
        builder.show();
	}
	
	private void DialogMessage(String message, String title){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setIcon(getResources().getDrawable(R.drawable.ic_ok));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				cleanAllTextview();
				dialog.cancel();		
			}

			private void cleanAllTextview() {
				name.setText("");
				host.setText("");
				port.setText("");
				username.setText("");
				password.setText("");
				database.setText("");
				description.setText("");
			}
		});
        builder.show();
	}
	
	private void message_ConnectionOK(){
		messageConnectionOK okmessage=new messageConnectionOK();
		okmessage.show(getFragmentManager(), "OK conection");
	}
	
	private void message_ConnectionError(){
		ConnectionErrorMessage errormessage=new ConnectionErrorMessage();
		errormessage.show(getFragmentManager(), "Error Conection");
	}
	
	private class TaskTestConecction extends AsyncTask<testConection, Long, JSONArray>{
				
		private ProgressDialog progressDialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog=new ProgressDialog(AddServerData.this);
			progressDialog.setMessage("Conectando al Servidor. Espere un momento.");
			progressDialog.show();
		}



		@Override
		protected JSONArray doInBackground(testConection... params) {
			return params[0].dataBaseAll();
		}

				@Override
		protected void onPostExecute(JSONArray result) {
			boolean resultado=setJsonArrayResultConection(result);
			if(resultado==true){
				progressDialog.dismiss();
				try {
					databaseJSON=result.getJSONObject(0);
					btnclick=1;
				} catch (JSONException e) {
					e.printStackTrace();
				}
				message_ConnectionOK();
			}
			else{
				progressDialog.dismiss();
				message_ConnectionError();
			}
		}			
	}
	
	public boolean setJsonArrayResultConection(JSONArray result) {
		boolean resultado=false;
		if (result!=null) {
				JSONObject objectJson=null;
				try {
					objectJson=result.getJSONObject(0);
					String title=objectJson.getString("Database");
					if ("MySQLError".equals(title) || "EmptyError".equals(title)) {
						resultado=false;
					}else{
						database.setText("");
						objectJson=null;
						for (int i = 1; i < result.length(); i++) {
							JSONObject objJSON=null;;
							try {
								objJSON=result.getJSONObject(i);
								String Databases=objJSON.getString("Database");
								databaseList.add(Databases);
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
						
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

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		database.setText(""+item.getTitle());
		return super.onContextItemSelected(item);
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
		if (databaseJSON!=null ) {
			database.setText("");
			try {
				String title=databaseJSON.getString("Database");
				if("MySQLErrorData".equals(title)){
					DialogAlertMessage("Alerta!", "El servidor no contiene base de datos!");
					database.setText("");
				}else{
					for (int i = 0; i < databaseList.size(); i++) {
						String Databases=databaseList.get(i);
						menu.add(0, v.getId(), 0, Databases);
					}					
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}			
		}else{
			message_ConnectionError();
		}
	}	
}



