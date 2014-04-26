package think.sqlgraphic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DialogAlert extends DialogFragment{
	public String titleDialog, textContent; 
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		builder.setMessage(textContent)
		.setTitle(titleDialog)
		.setPositiveButton("OK",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
		});
		
		return builder.create();
	}
	
	

}
