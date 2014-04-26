package think.sqlgraphic;

import java.util.ArrayList;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends Activity {
	
private ListView lista;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		ArrayList<Lista_entrada> datos =new ArrayList<Lista_entrada>();
		datos.add(new Lista_entrada(R.drawable.ic_database, "BUHO", "Búho es el nombre común de aves de la fa."));
        datos.add(new Lista_entrada(R.drawable.ic_database, "COLIBRÍ", "Los troquilinos (Trochilinae) son una subfamilia ."));
        datos.add(new Lista_entrada(R.drawable.ic_database, "CUERVO", "El cuervo común (Corvus corax) es una especie de av."));
        datos.add(new Lista_entrada(R.drawable.ic_database, "FLAMENCO", "Los fenicopteriformes (Phoenicopteriformes), los "));
        datos.add(new Lista_entrada(R.drawable.ic_database, "KIWI", "Los kiwis (Apterix, gr. 'sin alas') son un género de ."));
        
        lista =(ListView) findViewById(R.id.ListViewDB);
        lista.setAdapter(new Lista_adaptador(this, R.layout.entrada,datos) {
			
			@Override
			public void onEntrada(Object entrada, View view) {
				// TODO Auto-generated method stub
				if (entrada != null) {
				TextView texto_superior_entrada = (TextView) view.findViewById(R.id.hostname); 
	            texto_superior_entrada.setText(((Lista_entrada) entrada).getTextTitle()); 

	            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.hostip); 
	            texto_inferior_entrada.setText(((Lista_entrada) entrada).getTextIp()); 

	            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.icondatabase); 
	            imagen_entrada.setImageResource(((Lista_entrada) entrada).getIdImage());
				}
			}
		});
        
        lista.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
				Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion); 

                CharSequence texto = "Seleccionado: " + elegido.getTextIp();
                Toast toast = Toast.makeText(StartActivity.this, texto, Toast.LENGTH_LONG);
                toast.show();
			}
		});
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
	
	 public void AddData(){
	    	Intent intent = new Intent(this, AddServerData.class);
	    	startActivity(intent);	    	
	    }

}
