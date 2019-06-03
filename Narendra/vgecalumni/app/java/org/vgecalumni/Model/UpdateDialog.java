package org.vgecalumni.Model;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.vansuita.library.CheckNewAppVersion;

import org.vgecalumni.R;

public class UpdateDialog implements View.OnClickListener {
    ActivityManager am;
    Context context;
    Dialog dialog;
    private UpdateDialogListener listener;

    public void showDialogAddRoute(Activity activity, UpdateDialogListener listener) {
        context = activity;
        this.listener = listener;

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.update_dialog);
        am = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        WindowManager.LayoutParams lp= new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width =  WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        //dialog.getWindow().setAttributes(lp);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        Button update = dialog.findViewById(R.id.but_update);
        TextView whatnew = dialog.findViewById(R.id.whats_new);
        update.setOnClickListener(this);
        whatnew.setOnClickListener(this);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_update:
                listener.onUpdateClick();
                break;
            case R.id.whats_new:
                this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.vgecg.ac.in")));
                break;
        }

    }

    public interface UpdateDialogListener {
        void onUpdateClick();
    }
}
