package org.vgecalumni;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import org.vgecalumni.Components.SplashScreen.SplashActivity;

import pl.droidsonroids.gif.GifImageView;

public class OfflineActivity extends Activity {

    private GifImageView mygif;
    private Intent splashintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_activity_offline);

        overridePendingTransition(R.transition.slide_in,R.transition.slide_out);
        mygif = findViewById(R.id.offlineGif);

        splashintent = new Intent(OfflineActivity.this, SplashActivity.class);

        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver(networkChangeReceiver, intentFilter);

    }

    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int status = NetworkUtil.getConnectivityStatusString(context);

            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {

                if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {

                }else {
                    splashintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(splashintent);
                    finish();
                }
            }
        }
    };

    @Override
    public void onDestroy() {

        try{
            if(networkChangeReceiver!=null)
                unregisterReceiver(networkChangeReceiver);

        }catch(Exception e){}

        super.onDestroy();
    }

    public void onClickBtn(View v)
    {
        splashintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(splashintent);
        finish();
    }
}
