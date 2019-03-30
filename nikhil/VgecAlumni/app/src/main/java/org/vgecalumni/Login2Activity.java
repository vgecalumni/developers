package org.vgecalumni;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.Arrays;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class Login2Activity extends AppCompatActivity {

    private Bundle webViewBundle;
    private WebView loginweb;
    private Intent mainIntent;
    private Intent offlineIntent;
    private GifImageView mygif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        overridePendingTransition(R.transition.slide_in,R.transition.slide_out);
        mygif = findViewById(R.id.Processing);

        mainIntent = new Intent(Login2Activity.this, Main2Activity.class);
        offlineIntent = new Intent(Login2Activity.this, OfflineActivity.class);
        loginweb = (WebView) findViewById(R.id.loginid);
        WebSettings webSettings = loginweb.getSettings();
        webSettings.setJavaScriptEnabled(true);

        this.loginweb.getSettings().setUserAgentString(
                this.loginweb.getSettings().getUserAgentString()
                        + " "
                        + getString(R.string.user_agent_suffix)
        );

        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        loginweb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportZoom(true);


        loginweb.addJavascriptInterface(new WebAppInterface(),"Android");


        Intent offlineIntent = new Intent(Login2Activity.this, OfflineActivity.class);
        int status = NetworkUtil.getConnectivityStatusString(Login2Activity.this);

        if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
            startActivity(offlineIntent);
            finish();
        }else {
            if (savedInstanceState != null) {
                loginweb.restoreState(savedInstanceState);
            } else {
                loginweb.loadUrl("https://vgecalumni.org/app_login.jsp");
            }
            loginweb.setWebViewClient(new Login2Activity.MyWebviewClient());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        loginweb.saveState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        loginweb.restoreState(outState);
    }


    private class MyWebviewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            mygif.setVisibility(View.VISIBLE);

            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            mygif.setVisibility(View.INVISIBLE);

            super.onPageFinished(view, url);
        }
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
            //Your code to do

            startActivity(offlineIntent);
            finish();
        }


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (loginweb.canGoBack()) {
                        loginweb.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        moveTaskToBack(true);
    }


    private class WebAppInterface{

        private Context loginContext;
        private static final String MY_PREFS_NAME = "VgecAlumni";

        @JavascriptInterface
        public void show(String msg){

            String data=msg;
            List<String> result = Arrays.asList(data.split("\\s*,\\s*"));
            String name = result.get(0);
            String uname = result.get(1);
            String pw = result.get(2);
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("uname", uname);
            editor.putString("name",name);
            editor.putString("password", pw);
            editor.apply();
            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
            finish();
        }
    }
}
