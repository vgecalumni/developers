package org.vgecalumni;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.MailTo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
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

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.sdsmdg.tastytoast.TastyToast;

import org.vgecalumni.Components.Events.EventsActivity;
import org.vgecalumni.Components.JobOffers.JobOffersMain;
import org.vgecalumni.Components.Magazine.MagazineActivity;
import org.vgecalumni.Components.NewsAndHig.NewsActivity;
import org.vgecalumni.Components.Portfolio.PortfolioActivity;
import org.vgecalumni.Components.Profile.Profile;
import org.vgecalumni.Components.QRCode.Qr_generate_food;
import org.vgecalumni.Components.QRCode.Qr_generate_photo;
import org.vgecalumni.Components.QRCode.Qr_scan_food;
import org.vgecalumni.Components.QRCode.Qr_scan_photo;
import org.vgecalumni.Components.SharingPlatform.sharingmain;
import org.vgecalumni.Components.SplashScreen.SplashActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;
import pl.droidsonroids.gif.GifImageView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int INPUT_FILE_REQUEST_CODE = 1;
    private static final int FILECHOOSER_RESULTCODE = 1;
    private static final String MY_PREFS_NAME = "VgecAlumni";
    String[] list;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    private WebView myWebView;
    private GifImageView mygif;
    private ValueCallback<Uri> mUploadMessage;
    private Uri mCapturedImageURI = null;
    private ValueCallback<Uri[]> mFilePathCallback;
    private String mCameraPhotoPath;
    private Intent jobIntent;
    private Intent sharingIntent;
    private Toolbar toolbar;
    private String APP_PNAME = "org.vgecalumni";

    public static boolean isEqual(Object o1, Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return imageFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("VGEC ALUMNI");
        overridePendingTransition(R.transition.slide_in, R.transition.slide_out);

        FirebaseMessaging.getInstance().subscribeToTopic("legacy");
        FirebaseInstanceId.getInstance().getInstanceId();

        AppRate.with(this)
                .setInstallDays(0) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10
                .setRemindInterval(2) // default 1
                .setShowLaterButton(true) // default true
                .setDebug(false) // default false
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .setTitle(R.string.new_rate_dialog_title)
                .setTextLater(R.string.new_rate_dialog_later)
                .setTextNever(R.string.new_rate_dialog_never)
                .setTextRateNow(R.string.new_rate_dialog_ok)
                .setMessage(R.string.new_rate_dialog_message)
                .monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
        mygif = findViewById(R.id.GifView);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Menu menu = navigationView.getMenu();

        MenuItem nav_userTitle = menu.findItem(R.id.nav_userTitle);
        MenuItem nav_qrcode = menu.findItem(R.id.nav_qrcode);
        MenuItem nav_qrcode1 = menu.findItem(R.id.nav_qrcode1);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("name", null);
        String account = prefs.getString("account", null);
        // set new title to the MenuItem
        nav_userTitle.setTitle("Hello, " + name);

        if (isEqual("admin", account)) {
            nav_qrcode.setTitle("View Photo Coupons");
            nav_qrcode1.setTitle("View Food Coupons");
            nav_qrcode1.setVisible(true);
        } else if (isEqual("food volunteer", account)) {
            nav_qrcode.setTitle("Scan Food Coupon");
        } else if (isEqual("photograph volunteer", account)) {
            nav_qrcode.setTitle("Scan Photo Coupon");
        } else {
            nav_qrcode.setTitle("Generate Photo Coupon");
            nav_qrcode1.setTitle("Generate Food Coupon");
            nav_qrcode1.setVisible(true);
        }
        //WebView
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

        myWebView.addJavascriptInterface(new MainActivity.WebAppInterface(), "Android");
        myWebView.setWebViewClient(new MyWebviewClient());
        myWebView.setWebChromeClient(new ChromeClient());

        if (savedInstanceState != null) {
            myWebView.restoreState(savedInstanceState);
        } else {
            myWebView.loadUrl("https://www.vgecalumni.org/index.jsp");
        }

        list = new String[]{"create post", "view post", "create profile", "view profile",
                "Vishwasmruti 2.0", "View All Events", "Events", "Contact Us", "About Us", "Home", "Portfolio",
                "Event Portfolio", "My Profile", "My Post", "Search Alumni", "Success Stories", "Alumni Committee",
                "Share", "Rate Us", "Application Developer(Prahar)", "Contact Application Developer(Prahar)",
                "App Team", "Vishwakarma Government Engineering College"};

        /*materialSearchView = findViewById(R.id.mysearch);
        materialSearchView.clearFocus();*/
        requestPermission();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    private void requestPermission() {
        Dexter.withActivity(this).withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError error) {
                Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
            }
        }).onSameThread().check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use its extra features. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode != INPUT_FILE_REQUEST_CODE || mFilePathCallback == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }
            Uri[] results = null;
            // Check that the response is a good one
            if (resultCode == Activity.RESULT_OK) {
                if (data == null) {
                    // If there is not data, then we may have taken a photo
                    if (mCameraPhotoPath != null) {
                        results = new Uri[]{Uri.parse(mCameraPhotoPath)};
                    }
                } else {
                    String dataString = data.getDataString();
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                }
            }
            mFilePathCallback.onReceiveValue(results);
            mFilePathCallback = null;
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            if (requestCode != FILECHOOSER_RESULTCODE || mUploadMessage == null) {
                super.onActivityResult(requestCode, resultCode, data);
                return;
            }
            if (requestCode == FILECHOOSER_RESULTCODE) {
                if (null == this.mUploadMessage) {
                    return;
                }
                Uri result = null;
                try {
                    if (resultCode != RESULT_OK) {
                        result = null;
                    } else {
                        // retrieve from the private variable if the intent is null
                        result = data == null ? mCapturedImageURI : data.getData();
                    }
                } catch (Exception e) {

                }
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        }
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu_item_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("search");
        searchView.setFocusable(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.contains("search")) {
                    myWebView.loadUrl("https://www.vgecalumni.org/srchalumni.jsp");
                } else if (query.contains("home")) {
                    myWebView.loadUrl("https://www..vgecalumni.org/index.jsp");
                } else if (query.contains("about")) {
                    myWebView.loadUrl("https://www.vgecalumni.org/aboutUs.jsp");
                } else if (query.contains("events")) {
                    myWebView.loadUrl("https://www.vgecalumni.org/events.jsp");
                } else if (query.contains("portfolio") || query.contains("photos")) {
                    myWebView.loadUrl("https://www.vgecalumni.org/portfolio.jsp");
                } else if (query.contains("job")) {
                    Intent joboffers = new Intent(MainActivity.this, JobOffersMain.class);
                    startActivity(joboffers);
                    return true;
                } else if (query.contains("post") || query.contains("share")) {
                    Intent sharing = new Intent(MainActivity.this, sharingmain.class);
                    startActivity(sharing);
                    return true;
                } else if (query.contains("success")) {
                    myWebView.loadUrl("https://www.vgecalumni.org/successStories.jsp");
                } else if (query.contains("alumni") || query.contains("developers")) {
                    myWebView.loadUrl("https://www.vgecalumni.org/alumnicommitee.jsp");
                } else if (query.contains("about")) {
                    myWebView.loadUrl("https://www.vgecalumni.org/aboutUs.jsp");
                } else if (query.contains("contact")) {
                    myWebView.loadUrl("https://www.vgecalumni.org/contact.jsp");
                } else if (query.contains("developer")) {
                    myWebView.loadUrl("https://www.vgecalumni.org/app_developers.jsp");
                } else if (query.contains("education") || query.contains("profile") || query.contains("experience")) {
                    Intent profile = new Intent(MainActivity.this, Profile.class);
                    startActivity(profile);
                    return true;
                } else if (query.contains("magazine") || query.contains("vrutant")) {
                    Intent magintent = new Intent(MainActivity.this, MagazineActivity.class);
                    startActivity(magintent);
                    return true;
                }else if (query.contains("rate")) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
                    } catch (Exception e) {
                        //
                    }
                } else if (query.contains("ic_logout")) {
                    myWebView.loadUrl("https://www.vgecalumni.org/app_logout.jsp");
                } else {
                    TastyToast.makeText(getApplicationContext(), "Sorry ! No result found !", TastyToast.LENGTH_LONG, TastyToast.INFO);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //You can make change realtime if you typing here
                //See my tutorials for filtering with ListView
                return false;
            }
        });
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        myWebView.saveState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        myWebView.restoreState(outState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        moveTaskToBack(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_item_share){
            Intent myShareIntent = new Intent(android.content.Intent.ACTION_SEND);
            myShareIntent.setType("text/plain");
            myShareIntent.putExtra(Intent.EXTRA_SUBJECT,"INSTALL VGEC ALUMNI APP");
            myShareIntent.putExtra(Intent.EXTRA_TEXT, "INSTALL VGEC ALUMNI APP. https://play.google.com/store/apps/details?id=org.vgecalumni");
            startActivity(Intent.createChooser(myShareIntent,"Share to..."));
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String account = prefs.getString("account", null);

        if (account.equals("admin") || account.equals("food volunteer") || account.equals("photo volunteer")) {
            if (id == R.id.nav_qrcode) {
                Intent photoscanintent = new Intent(this, Qr_scan_photo.class);
                startActivity(photoscanintent);
                return true;
            } else if (id == R.id.nav_qrcode1) {
                Intent foodscanintent = new Intent(this, Qr_scan_food.class);
                startActivity(foodscanintent);
                return true;
            }
       /* } else if (account.equals("food volunteer")) {
            if (id == R.id.nav_qrcode) {
                Intent foodscanintent = new Intent(this, Qr_scan_food.class);
                startActivity(foodscanintent);
                return true;
            }
        } else if (account.equals("photo volunteer")) {
            if (id == R.id.nav_qrcode) {
                Intent photoscanintent = new Intent(this, Qr_scan_photo.class);
                startActivity(photoscanintent);
                return true;
            }*/
        } else {
            if (id == R.id.nav_qrcode) {
                Intent generatePhotoIntent = new Intent(this, Qr_generate_photo.class);
                startActivity(generatePhotoIntent);
                return true;
            } else if (id == R.id.nav_qrcode1) {
                Intent generateFoodIntent = new Intent(this, Qr_generate_food.class);
                startActivity(generateFoodIntent);
                return true;
            }
        }
        if (id == R.id.nav_home) {
            myWebView.loadUrl("https://www.vgecalumni.org/index.jsp");
        } else if (id == R.id.nav_about) {
            myWebView.loadUrl("https://www.vgecalumni.org/aboutUs.jsp");
        } else if (id == R.id.nav_events) {
            Intent intent = new Intent(this, EventsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_portfolio) {
            Intent intent = new Intent(this, PortfolioActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_job) {
            jobIntent = new Intent(MainActivity.this, JobOffersMain.class);
            startActivity(jobIntent);
            return true;
        } else if (id == R.id.nav_sharing) {
            sharingIntent = new Intent(MainActivity.this, sharingmain.class);
            startActivity(sharingIntent);
            return true;
        } else if (id == R.id.nav_news) {
            Intent news = new Intent(this, NewsActivity.class);
            startActivity(news);
            return true;
        } else if (id == R.id.nav_postjob) {
            myWebView.loadUrl("https://www.vgecalumni.org/app_postjob.jsp");
        } else if (id == R.id.nav_myprofile) {
            Intent myprofile = new Intent(this, Profile.class);
            startActivity(myprofile);
            return true;
        } else if (id == R.id.nav_search) {
            myWebView.loadUrl("https://www.vgecalumni.org/srchalumni.jsp");
        } else if (id == R.id.nav_success) {
            myWebView.loadUrl("https://www.vgecalumni.org/successStories.jsp");
        } else if (id == R.id.nav_committee) {
            myWebView.loadUrl("https://www.vgecalumni.org/alumnicommitee.jsp");
        } else if (id == R.id.nav_vrutant) {
            Intent magintent = new Intent(this, MagazineActivity.class);
            startActivity(magintent);
            return true;
        } else if (id == R.id.nav_about) {
            myWebView.loadUrl("https://www.vgecalumni.org/aboutUs.jsp");
        } else if (id == R.id.nav_contact) {
            myWebView.loadUrl("https://www.vgecalumni.org/contact.jsp");
        } else if (id == R.id.nav_rate) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + APP_PNAME)));
            } catch (Exception e) {
            }
        } else if (id == R.id.nav_appteam) {
            myWebView.loadUrl("https://www.vgecalumni.org/app_developers.jsp");
        } else if (id == R.id.nav_logout) {
            myWebView.loadUrl("https://www.vgecalumni.org/app_logout.jsp");
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (myWebView.canGoBack()) {
                        myWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public class ChromeClient extends WebChromeClient {
        // For Android 5.0
        public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> filePath, WebChromeClient.FileChooserParams fileChooserParams) {
            // Double check that we don't have any existing callbacks
            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(null);
            }
            mFilePathCallback = filePath;

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                    takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(photoFile));
                } else {
                    takePictureIntent = null;
                }
            }

            Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
            contentSelectionIntent.setType("image/*");

            Intent[] intentArray;
            if (takePictureIntent != null) {
                intentArray = new Intent[]{takePictureIntent};
            } else {
                intentArray = new Intent[0];
            }

            Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
            chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
            chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
            startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE);
            return true;
        }

        // openFileChooser for Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
            // Create AndroidExampleFolder at sdcard
            // Create AndroidExampleFolder at sdcard
            File imageStorageDir = new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES)
                    , "AndroidExampleFolder");
            if (!imageStorageDir.exists()) {
                // Create AndroidExampleFolder at sdcard
                imageStorageDir.mkdirs();
            }
            // Create camera captured image file path and name
            File file = new File(
                    imageStorageDir + File.separator + "IMG_"
                            + System.currentTimeMillis()
                            + ".jpg");

            mCapturedImageURI = Uri.fromFile(file);

            // Camera capture image intent
            final Intent captureIntent = new Intent(
                    android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);

            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");

            // Create file chooser intent
            Intent chooserIntent = Intent.createChooser(i, "Image Chooser");

            // Set camera intent to file chooser
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS
                    , new Parcelable[]{captureIntent});

            // On select image call onActivityResult method of activity
            startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
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
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            String message = "SSL Certificate error.";
            Intent error_intent = new Intent(MainActivity.this, ErrorActivity.class);

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
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            Intent error_intent = new Intent(MainActivity.this, ErrorActivity.class);
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
            Intent error_intent = new Intent(MainActivity.this, ErrorActivity.class);
            Integer code = error.getErrorCode();
            if (code == -2) {
                TastyToast.makeText(getApplicationContext(), "You are currently offline !", TastyToast.LENGTH_LONG, TastyToast.DEFAULT);
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

    private class WebAppInterface {
        private static final String MY_PREFS_NAME = "VgecAlumni";

        @JavascriptInterface
        public void logout() {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            if (editor.clear().commit()) {
                TastyToast.makeText(getApplicationContext(), "Logout!", TastyToast.LENGTH_LONG, TastyToast.INFO);
                Intent splashintent = new Intent(MainActivity.this, SplashActivity.class);
                splashintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(splashintent);
                finish();
            } else {
                TastyToast.makeText(getApplicationContext(), "Logout Error!", TastyToast.LENGTH_LONG, TastyToast.INFO);
            }
        }
    }
}
