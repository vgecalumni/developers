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
import android.widget.TextView;
import android.widget.Toast;

import org.vgecalumni.Components.SplashScreen.SplashActivity;
import org.vgecalumni.Components.Temp.ErrorCheck;

import pl.droidsonroids.gif.GifImageView;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class ErrorActivity extends Activity {

    private GifImageView mygif;
    private Intent splashintent;
    private TextView error_desc;
    private String error_code;
    private String error_name;
    private TextView error_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_activity);

        error_title = null;
        overridePendingTransition(R.transition.slide_in,R.transition.slide_out);
        mygif = findViewById(R.id.errorGif);
        error_desc = (TextView)findViewById(R.id.error_desc);

        Intent intent = getIntent ();
        Bundle extras = intent.getExtras();
        error_code = extras.getString("errorcode");
        error_name = extras.getString("errorname");

        if(error_code != null) {
            if(!error_code.equals("-8")){
                error_title = (TextView) findViewById(R.id.error_title);
                error_title.setText("Error Code : " + error_code);
                error_desc.setText("Sorry for the inconvenience ! \n We are currently under maintenance.\n This error may arrive due to lots of request or internal server error. \n Please try again later.");
            } else {
                error_title = (TextView) findViewById(R.id.error_title);
                error_title.setText("Error Code : " + error_code);
                error_desc.setText("Sorry for the inconvenience ! \n The activity you are trying to open is taking too much time to load.\n Seems poor internet connection. \n Please try again later.");
            }
        }else if(error_name != null) {
            error_title = (TextView) findViewById(R.id.error_title);
            error_title.setText("Secure Certificate Error");
            error_desc.setText(error_name + "\n Sorry for the inconvenience ! \n We are currently renewing our certificate and it will take only 1-2 days.");
        }

    }

    public void onClickBtn(View v)
    {
        this.finishAffinity();
    }
}
