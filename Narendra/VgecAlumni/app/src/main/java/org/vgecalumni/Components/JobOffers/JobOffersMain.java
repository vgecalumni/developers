package org.vgecalumni.Components.JobOffers;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.MailTo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import org.vgecalumni.ErrorActivity;
import org.vgecalumni.R;

import pl.droidsonroids.gif.GifImageView;

public class JobOffersMain extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final int REQUEST_SELECT_FILE = 100;
    private static final String MY_PREFS_NAME = "VgecAlumni";
    private final static int FILECHOOSER_RESULTCODE = 1;
    public ValueCallback<Uri[]> uploadMessage;
    private WebView myWebView;
    private GifImageView mygif;
    private ValueCallback<Uri> mUploadMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_joboffers_layout);

        overridePendingTransition(R.transition.slide_in, R.transition.slide_out);

        mygif = findViewById(R.id.GifView);
        myWebView = findViewById(R.id.myWebView);
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

        // settings.setPluginsEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowContentAccess(true);

        myWebView.setWebViewClient(new MyWebviewClient());

        myWebView.setWebChromeClient(new JobOffersMain.ChromeClient());

        if (savedInstanceState != null) {
            myWebView.restoreState(savedInstanceState);
        } else {
            myWebView.loadUrl("https://www.vgecalumni.org/app_joboffers.jsp");
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
                myWebView.loadUrl("https://www.vgecalumni.org/app_joboffers.jsp");
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null || resultCode != JobOffersMain.RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        } else
            Toast.makeText(JobOffersMain.this.getApplicationContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }

    //flipscreen not loading again
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public class ChromeClient extends WebChromeClient {

        protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
        }


        // For Lollipop 5.0+ Devices
        public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (uploadMessage != null) {
                uploadMessage.onReceiveValue(null);
                uploadMessage = null;
            }

            uploadMessage = filePathCallback;

            Intent intent = fileChooserParams.createIntent();
            try {
                startActivityForResult(intent, REQUEST_SELECT_FILE);
            } catch (ActivityNotFoundException e) {
                uploadMessage = null;
                Toast.makeText(JobOffersMain.this.getApplicationContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        //For Android 4.1 only
        protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
        }

        protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
        }
    }

    private class MyWebviewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            mygif.setVisibility(View.VISIBLE);

            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(JobOffersMain.this);
            String message = "SSL Certificate error.";
            Intent error_intent = new Intent(JobOffersMain.this, ErrorActivity.class);

            switch (error.getPrimaryError()) {
                case SslError.SSL_UNTRUSTED:
                    myWebView.stopLoading();
                    myWebView.setVisibility(View.INVISIBLE);
                    message = "The certificate authority is not trusted.";
                    error_intent.putExtra("errorname", message);
                    error_intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(error_intent);
                    finish();

                    break;
                case SslError.SSL_EXPIRED:

                    myWebView.stopLoading();
                    myWebView.setVisibility(View.INVISIBLE);
                    message = "The certificate has expired.";
                    error_intent.putExtra("errorname", message);
                    error_intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(error_intent);
                    finish();
                    break;
                case SslError.SSL_IDMISMATCH:
                    message = "The certificate Hostname mismatch.";
                    myWebView.stopLoading();
                    myWebView.setVisibility(View.INVISIBLE);
                    error_intent.putExtra("errorname", message);
                    error_intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(error_intent);
                    finish();
                    break;
                case SslError.SSL_NOTYETVALID:
                    message = "The certificate is not yet valid.";
                    myWebView.stopLoading();
                    myWebView.setVisibility(View.INVISIBLE);
                    error_intent.putExtra("errorname", message);
                    error_intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(error_intent);
                    finish();
                    break;
            }
            message += " Do you want to continue anyway?";

            builder.setTitle("SSL Certificate Error");
            builder.setMessage(message);
            builder.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.proceed();
                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handler.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            dialog.show();
        }

        @Override
        public void onReceivedHttpError(WebView view,
                                        WebResourceRequest request, WebResourceResponse errorResponse) {
            Intent error_intent = new Intent(JobOffersMain.this, ErrorActivity.class);
            Integer code = errorResponse.getStatusCode();

            String headers = errorResponse.getResponseHeaders().toString();

            if (headers.contains("Connection=close")) {
                myWebView.stopLoading();
                myWebView.setVisibility(View.INVISIBLE);
                error_intent.putExtra("errorcode", code.toString());
                error_intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(error_intent);
                finish();
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

        @Override
        @TargetApi(23)
        public void onReceivedError(WebView view, WebResourceRequest request,
                                    WebResourceError error) {
            Intent error_intent = new Intent(JobOffersMain.this, ErrorActivity.class);
            Integer code = error.getErrorCode();
            if (code == -2) {
                myWebView.loadUrl("https://www.vgecalumni.org/offline.jsp");
            } else {
                myWebView.stopLoading();
                myWebView.setVisibility(View.INVISIBLE);
                error_intent.putExtra("errorcode", code.toString());
                error_intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(error_intent);
                finish();
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
