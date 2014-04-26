package think.sqlgraphic;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AddServerData extends Activity {
	
	public EditText name;
	public EditText host;
	public EditText port;
	public EditText username;
	public EditText password;
	public EditText database;
	public EditText description;

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
		
		// Show the Up button in the action bar.
		setupActionBar();
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
			
			return true;
		case R.id.saveData:
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void SaveData_Server(){
		String Sname=name.getText().toString();
		String Shost=host.getText().toString();
		String Susername=username.getText().toString();
		String Spassword=password.getText().toString();
		String Sdatabase=database.getText().toString();
		String Sdescription=description.getText().toString();
		if(Sname==""){
			
		}else{
			
		}
	}

}
