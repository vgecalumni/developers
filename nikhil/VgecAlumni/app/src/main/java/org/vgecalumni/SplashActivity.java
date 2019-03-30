package org.vgecalumni;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.MailTo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.atomic.AtomicBoolean;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class SplashActivity extends AppCompatActivity{

    private WebView splashweb;
    private WebView offlineweb;
    private ProgressBar progressBar;
    private int ProgressStatus;
    private TextView progressText;
    private PrefManager prefManager;
    private Intent handleIntent;
    private Intent offlineIntent;
    private SplashActivity activity;
    public static final String MY_PREFS_NAME = "VgecAlumni";
    private Handler handler = new Handler();
    private Context mycontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        overridePendingTransition(R.transition.slide_in,R.transition.slide_out);

        offlineIntent = new Intent(SplashActivity.this, OfflineActivity.class);
        progressText= (TextView) findViewById(R.id.progressText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver(networkChangeReceiver, intentFilter);
    }

    private BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int status = NetworkUtil.getConnectivityStatusString(context);

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String uname = prefs.getString("uname",null);
            String name = prefs.getString("name",null);

            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {

                if (uname != null) {
                    progressBar.setVisibility(View.INVISIBLE);

                    progressText.setText("Welcome "+name+" !");
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    launchHomeScreen();
                                }
                            },
                            1000
                    );
                }else{
                    if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {

                        Intent offlineIntent = new Intent(SplashActivity.this, OfflineActivity.class);
                        startActivity(offlineIntent);
                        finish();
                    }else{


                        splashweb = (WebView) findViewById(R.id.splashWeb);
                        WebSettings webSettings = splashweb.getSettings();
                        webSettings.setJavaScriptEnabled(true);

                        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
                        splashweb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                        webSettings.setDomStorageEnabled(true);
                        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
                        webSettings.setUseWideViewPort(true);
                        webSettings.setSavePassword(true);
                        webSettings.setSaveFormData(true);
                        webSettings.setEnableSmoothTransition(true);

                        splashweb.loadUrl("https://vgecalumni.org/index.jsp");

                        splashweb.setWebChromeClient(new WebChromeClient() {
                            public void onProgressChanged(WebView view, int progress) {

                                setmyProgress(progress);
                            }
                        });

                        splashweb.setWebViewClient(new SplashActivity.MyWebviewClient());
                    }
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

    public void setmyProgress(int progress)
    {
        progressBar.setProgress(progress);
        progressBar.setSecondaryProgress(progress+10);

        if(progress > 80)
        {
            progressText.setText("Almost There..");
        }else if(progress > 60 && progress < 80)
        {
            progressText.setText("Authenticating..");
        }else if(progress > 40 && progress < 60)
        {
            progressText.setText("Starting Our Background Services..");
        }else if(progress > 20 && progress < 40)
        {
            progressText.setText("Initializing The UI..");
        }
    }

    private void launchHomeScreen() {

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String uname = prefs.getString("uname",null);
        if (uname != null) {
            String password = prefs.getString("password", null); //0 is the default value.
            handleIntent = new Intent(SplashActivity.this, Main2Activity.class);

            startActivity(handleIntent);
            handleIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
        }else{
            handleIntent = new Intent(SplashActivity.this, Login2Activity.class);
            handleIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(handleIntent);
            finish();
        }

    }
    public class MyWebviewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {


            // set current progress
            progressBar.setProgress(10);

            // set buffered progress
            progressBar.setSecondaryProgress(20);
            ProgressStatus = 10;
            progressText.setText("Downloading App Contents..");
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {

            // set current progress
            progressBar.setProgress(100);
            // set buffered progress
            progressBar.setSecondaryProgress(100);

            super.onPageFinished(view, url);
            launchHomeScreen();

        }

    }
}
