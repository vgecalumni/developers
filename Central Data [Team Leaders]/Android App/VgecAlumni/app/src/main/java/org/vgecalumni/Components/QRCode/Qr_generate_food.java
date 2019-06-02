package org.vgecalumni.Components.QRCode;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import org.vgecalumni.Api.InterPreter;
import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Main2Activity;
import org.vgecalumni.Model.Education;
import org.vgecalumni.Model.Experience;
import org.vgecalumni.Model.RegEventResponse;
import org.vgecalumni.Model.RegEvents;
import org.vgecalumni.Model.User;
import org.vgecalumni.Model.UserResponse;
import org.vgecalumni.OfflineActivity;
import org.vgecalumni.R;
import org.vgecalumni.SharedMemory.SharedPrefManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Qr_generate_food extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "VgecAlumni";
    String s_event, s_photo, s_food;
    ListView listView;
    String temp,temp1;
    TextView title;
    TextView desc;
    ImageView qrimage;
    Bitmap bitmap;
    String TAG = "GenerateQRCode";
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_generate_status);

        setTitle("Food Coupon");

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.transition.slide_in,R.transition.slide_out);

        showProgress();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String uname = prefs.getString("uname", null);

        title = (TextView)findViewById(R.id.qr_generate_title);
        desc = (TextView)findViewById(R.id.qr_generate_desc);
        qrimage = (ImageView)findViewById(R.id.qr_image);

        
        Call<RegEventResponse> RegEventResponsecall = RetrofitClient.getInstance().getInterPreter().getRegEvents(uname);
        RegEventResponsecall.enqueue(new Callback<RegEventResponse>() {
            @Override
            public void onResponse(Call<RegEventResponse> call, Response<RegEventResponse> response) {

                RegEventResponse regEventResponse = response.body();
                if (regEventResponse.isError()) {

                    qrimage.setImageResource(R.mipmap.not_found);
                    title.setText("Sorry !");
                    desc.setText("You have not registered for any events !");

                } else {

                    RegEvents rg = regEventResponse.getRegEvents();
                    s_event = rg.getEvent();
                    s_food = rg.getFood();
                    if(s_food.equals("0")){

                        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                        Display display = manager.getDefaultDisplay();
                        Point point = new Point();
                        display.getSize(point);
                        int width = point.x;
                        int height = point.y;
                        int smallerDimension = width < height ? width : height;
                        smallerDimension = smallerDimension * 3 / 4;

                        // Format : event , food , uname  ....
                        String qrcode = s_event + ",food," + uname +",id," + getAlphaNumericString(5);
                        QRGEncoder qrgEncoder = new QRGEncoder(qrcode, null, QRGContents.Type.TEXT,smallerDimension);

                        try {
                            // Getting QR-Code as Bitmap
                            bitmap = qrgEncoder.encodeAsBitmap();
                            // Setting Bitmap to ImageView
                            qrimage.setImageBitmap(bitmap);
                        } catch (WriterException e) {
                            Log.v(TAG, e.toString());
                        }

                        title.setText("Coupon Generated !");
                        desc.setText("Your food coupon has been generated for " +s_event + " ! To take dinner, you have to just verify this coupon at food counter on the event day!");

                    }else{

                        qrimage.setImageResource(R.mipmap.tickmark);
                        title.setText("Coupon Applied !");
                        desc.setText("Your food coupon has been applied ! Thanks for your co-operation. Now enjoy " + s_event + " 's dinner !");

                    }

                }

            }

            @Override
            public void onFailure(Call<RegEventResponse> call, Throwable t) {

                Intent generateFoodIntent = new Intent(Qr_generate_food.this, OfflineActivity.class);
                startActivity(generateFoodIntent);
            }
        });

    }
    static String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void showProgress() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    private void endProgress() {
        Toast.makeText(Qr_generate_food.this, "try again!", Toast.LENGTH_SHORT).show();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


}