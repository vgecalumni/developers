package org.vgecalumni;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.MailTo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;

public class joboffersmain extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private WebView myWebView;
    private GifImageView mygif;
    private static final String MY_PREFS_NAME = "VgecAlumni";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joboffers_layout);

        overridePendingTransition(R.transition.slide_in, R.transition.slide_out);

        mygif = findViewById(R.id.GifView);
        myWebView = (WebView) findViewById(R.id.myWebView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportZoom(true);

        myWebView.setWebViewClient(new MyWebviewClient());

        if (savedInstanceState != null) {
            myWebView.restoreState(savedInstanceState);
        } else {
            myWebView.loadUrl("https://vgecalumni.org/app_joboffers.jsp");
        }

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setBackgroundDrawable(
                    new ColorDrawable(Color.parseColor("#2d3090")));
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.show();
        } else {
            Toast.makeText(getApplicationContext(), "actionbar not supported !",
                    Toast.LENGTH_LONG).show();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        displayFrag(new JobOffers());
    }

    private void displayFrag(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_joboffers_main, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_post_job:
                myWebView.loadUrl("https://vgecalumni.org/app_joboffers.jsp");
                fragment = new JobOffers();
                break;
            case R.id.nav_posted_job:
//                myWebView.loadUrl("https://vgecalumni.org/app_myjobapplications.jsp");
                TastyToast.makeText(getApplicationContext(), "This feature is under maintenance !", TastyToast.LENGTH_LONG, TastyToast.INFO);
                fragment = new MyJobApplications();
                break;
        }
        displayFrag(fragment);
        return true;
    }

    private class MyWebviewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            mygif.setVisibility(View.VISIBLE);

            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            // ignore ssl error
            if (handler != null) {
                handler.proceed();
            } else {
                super.onReceivedSslError(view, null, error);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String uname = prefs.getString("uname", null);
            String name = prefs.getString("name", null);
            String pw = prefs.getString("password", null);

            mygif.setVisibility(View.INVISIBLE);
            super.onPageFinished(view, url);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                myWebView.evaluateJavascript("check('" + uname + "','" + pw + "','" + name + "');", null);

            } else {
                myWebView.loadUrl("javascript:check('" + uname + "','" + pw + "','" + name + "');");

            }


        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("mailto:")) {
                MailTo mt = MailTo.parse(url);
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{mt.getTo()});
                i.putExtra(Intent.EXTRA_SUBJECT, mt.getSubject());
                i.putExtra(Intent.EXTRA_CC, mt.getCc());
                i.putExtra(Intent.EXTRA_TEXT, mt.getBody());
                startActivity(i);
                view.reload();
                return true;
            }
            view.loadUrl(url);
            return true;
        }


    }
}
