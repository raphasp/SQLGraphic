package think.sqlgraphic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.view.LayoutInflater;

public class messageConnectionOK extends DialogFragment{
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //LayoutInflater inflater=getActivity().getLayoutInflater();
		builder.setTitle("Mensaje de confirmación");
		builder.setIcon(getResources().getDrawable(R.drawable.ic_ok));
		builder.setMessage(getResources().getText(R.string.imgOkAddTest));
        //builder.setView(inflater.inflate(R.layout.addoktest, null));
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
