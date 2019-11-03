package org.vgecalumni.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class CustomDialog {
    private Context context;
    private String string;
    private CustomDialogListener listener;

    public CustomDialog(Context context, String string, CustomDialogListener listener) {
        this.context = context;
        this.string = string;
        this.listener = listener;
    }

    public void showDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(string);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onPositiveClick(dialog, position);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNegativeClick(dialog);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public interface CustomDialogListener {
        void onPositiveClick(DialogInterface dialogInterface, int position);

        void onNegativeClick(DialogInterface dialogInterface);
    }
}
