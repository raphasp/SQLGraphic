package think.sqlgraphic;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class DBListViewAdapter extends BaseAdapter {
	private ArrayList<?> entradas; 
    private int R_layout_IdView; 
    private Context contexto;
    
    public DBListViewAdapter(Context contexto, int R_layout_IdView, ArrayList<?> entradas) {
        super();
        this.contexto = contexto;
        this.entradas = entradas; 
        this.R_layout_IdView = R_layout_IdView; 
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return entradas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return entradas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
		    LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
	            convertView = vi.inflate(R_layout_IdView, null); 
	    }
		
		onEntrada (entradas.get(position), convertView);
	    return convertView; 
	}

	public void onEntrada(Object object, View convertView) {
	}

}
