package think.sqlgraphic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class Lista_adaptador extends ArrayAdapter<String> {
	private String[] hostName;
	private String[] hostIP;
	private String[] hostNumber;
    private Context contexto;

    public Lista_adaptador(Context con, String[] hostN, String[] hostIp, String[] hostNo) {
        super(con, R.layout.entrada,R.id.hostname,hostN );
        this.contexto = con;
        this.hostName = hostN;
        this.hostIP = hostIp;
        this.hostNumber = hostNo;
    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup pariente) {
    	View row=convertView;
        if (row == null) {
        	LayoutInflater inflate = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
            row= inflate.inflate(R.layout.entrada,pariente, false);
        } 
            ImageView DataImg=(ImageView) row.findViewById(R.id.icondatabase);
            TextView DataName = (TextView) row.findViewById(R.id.hostname);
            TextView DataIP = (TextView) row.findViewById(R.id.hostip);
            TextView DataNumber = (TextView) row.findViewById(R.id.hostnumber);
            
            DataImg.setImageResource(R.drawable.ic_item);
            DataName.setText(hostName[posicion]);
            DataIP.setText(hostIP[posicion]);
            DataNumber.setText(hostNumber[posicion]);
        
        return row; 
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }
    
	/** Devuelve cada una de las entradas con cada una de las vistas a la que debe de ser asociada
     * @param entrada La entrada que será la asociada a la view. La entrada es del tipo del paquete/handler
     * @param view View particular que contendrá los datos del paquete/handler
     */
    public abstract void onEntrada (Object entrada, View view);
}
