package think.sqlgraphic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends Activity {
	
	private DBAdapter dataDB;
	public EditText name;
	public EditText host;
	public EditText port;
	public EditText username;
	public EditText password;
	public EditText database;
	public EditText description;
	public Button btndatabase;
	private long id;
	public JSONObject databaseJSON=null;
	public String nameserver="";
	public ArrayList<String> databaseList;
	public int btnclick=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		name=(EditText) findViewById (R.id.nombreUpdata);
		host=(EditText) findViewById (R.id.hostingUpdata);
		port=(EditText) findViewById (R.id.portUpdata);
		username=(EditText) findViewById (R.id.usernameUpdata);
		password=(EditText) findViewById (R.id.passwordUpdata);
		database=(EditText) findViewById (R.id.databaseUpdata);
		description=(EditText) findViewById (R.id.descriptionUpdata);
		btndatabase=(Button)findViewById(R.id.btndatabaseUpdata);
		
		dataOpen();
		
		Intent intent=getIntent();
		String number=intent.getStringExtra("idDatabase");
		id=Long.parseLong(number);
		Cursor datas=dataDB.getRow(id);
		updataData(datas);
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
	
	
	private void dataOpen() {
		dataDB=new DBAdapter(EditActivity.this);
		dataDB.open();
	}


	@Override
	protected void onDestroy() {
		dataClose();
		super.onDestroy();
	}

	private void dataClose() {
		dataDB.close();		
	}


	private void updataData(Cursor datas) {		
		try {
			name.setText(""+datas.getString(1),TextView.BufferType.EDITABLE);
			host.setText(""+datas.getString(2),TextView.BufferType.EDITABLE);
			port.setText(""+datas.getString(3),TextView.BufferType.EDITABLE);
			username.setText(""+datas.getString(4),TextView.BufferType.EDITABLE);
			password.setText(""+datas.getString(5),TextView.BufferType.EDITABLE);
			database.setText(""+datas.getString(6),TextView.BufferType.EDITABLE);	
		} catch (Exception e) {
			message_Load();
		}
		
		nameserver=name.getText().toString();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.testConnectionUpdate:
			 textOfConnection();
			return true;
		case R.id.updataData:
			updataData_Server();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}


	private void updataData_Server() {
		String Sname=name.getText().toString().trim();
		String Shost=host.getText().toString().trim();
		String Sport=port.getText().toString().trim();
		String Susername=username.getText().toString().trim();
		String Spassword=password.getText().toString().trim();
		String Sdatabase=database.getText().toString().trim();
		String Sdescription=description.getText().toString();	
		
		if("".equals(Sname) || "".equals(Shost) || "".equals(Sport)
			|| "".equals(Susername)){
			dialogAlertSQL dialog=new dialogAlertSQL();
			dialog.show(getFragmentManager(), "Alert field Empty");
		}else{
			int portAux=0;
			try {
				portAux=Integer.parseInt(Sport);
				boolean nameExist=dataDB.searchName(Sname);
				String xname=name.getText().toString();
				if(nameserver!=xname && nameExist==false){
					message_Exist();
				}else{
					dataDB.updateRow(id, Sname, Shost, portAux, Susername, Spassword, Sdatabase, Sdescription);
					message_Updata();
				}
			} catch (NumberFormatException e) {
				message_Alert();
			}			
		}
	}
	
	private void message_Updata(){
		AlertDialog.Builder alertAction=new AlertDialog.Builder(EditActivity.this);
		alertAction.setTitle("Mensaje de Confirmación");
		alertAction.setIcon(R.drawable.ic_ok);
		alertAction.setMessage(R.string.imgOkUpdata);
		/*LayoutInflater inflater= EditActivity.this.getLayoutInflater();
		alertAction.setView(inflater.inflate(R.layout.updataok, null));*/
		alertAction.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		
		alertAction.show();
	}
	
	private void message_Load(){
		AlertDialog.Builder alertAction=new AlertDialog.Builder(EditActivity.this);
		alertAction.setTitle("Alerta!");
		alertAction.setIcon(R.drawable.ic_alert);
		alertAction.setMessage(R.string.imgErrorUpdata);
		/*LayoutInflater inflater= EditActivity.this.getLayoutInflater();
		alertAction.setView(inflater.inflate(R.layout.errorloaddataupdate, null));*/
		alertAction.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				closeActivity();
			}

			private void closeActivity() {
				EditActivity.this.finish();				
			}
		});
		
		alertAction.show();
	}
	
	private void message_Alert(){
		AlertDialog.Builder alertAction=new AlertDialog.Builder(EditActivity.this);
		alertAction.setTitle("Error");
		alertAction.setIcon(R.drawable.ic_edit);
		alertAction.setMessage(R.string.imgUpdataError);
		/*LayoutInflater inflater= EditActivity.this.getLayoutInflater();
		alertAction.setView(inflater.inflate(R.layout.updataload, null));*/
		alertAction.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		
		alertAction.show();
	}
	
	private void message_Exist(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Alert!");
		builder.setIcon(R.drawable.ic_alert);
		builder.setMessage(R.string.imgExist);
        /*LayoutInflater inflater=EditActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.updataexist, null));*/
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();		
			}			
		});
        builder.show();
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
	
	private class TaskTestConecction extends AsyncTask<testConection, Long, JSONArray>{
		
		private ProgressDialog progressDialog;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog=new ProgressDialog(EditActivity.this);
			progressDialog.setMessage("Conectando al servidor. Espere un momento.");
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

	private void message_ConnectionOK(){
		messageConnectionOK okmessage=new messageConnectionOK();
		okmessage.show(getFragmentManager(), "OK conection");
	}
	
	private void message_ConnectionError(){
		ConnectionErrorMessage errormessage=new ConnectionErrorMessage();
		errormessage.show(getFragmentManager(), "Error Conection");
	}
	
	private void DialogAlertMessage(String title, String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setIcon(R.drawable.ic_alert);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();
	}
	


}
