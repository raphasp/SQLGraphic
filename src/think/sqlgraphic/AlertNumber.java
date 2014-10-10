package think.sqlgraphic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.widget.Toast;

public class AlertNumber  extends DialogFragment{

	public AlertNumber()  {
		// TODO Auto-generated constructor stub
	}
	
	private String title="Error";
	private String Message="El puerto no es un numero.";
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		builder.setTitle(title);
		builder.setIcon(getResources().getDrawable(R.drawable.ic_alert));
		builder.setMessage(Message);
		builder.setPositiveButton(R.string.ok_Save, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				//Toast.makeText(getActivity(), "OK button was cliked", Toast.LENGTH_SHORT).show();
				dialog.cancel();
			}
		});
		Dialog dialog=builder.create();
		return dialog;
	}

}
