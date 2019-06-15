package org.vgecalumni.Components.QRCode;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;
import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Main2Activity;
import org.vgecalumni.Model.DefaultResponse;
import org.vgecalumni.Model.RegEventResponse;
import org.vgecalumni.Model.RegEvents;
import org.vgecalumni.OfflineActivity;
import org.vgecalumni.R;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Qr_scan_photo extends AppCompatActivity implements View.OnClickListener{
    private Button buttonScan;
    private TextView qr_total, qr_last;

    //qr code scanner object
    private IntentIntegrator qrScan;
    private ImageView qrimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_scan);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        overridePendingTransition(R.transition.slide_in,R.transition.slide_out);

        //View objects
        buttonScan = (Button) findViewById(R.id.scan_btn);
        qr_total = (TextView) findViewById(R.id.qr_scan_total);
        qr_last = (TextView) findViewById(R.id.qr_gscan_last);
        qrimage = (ImageView)findViewById(R.id.qr_image);


        qrimage.setImageResource(R.mipmap.qrscan);
        qr_total.setText("Coupon Scanner Initiated !");
        qr_last.setText("Scanner is ready to scan photograph coupons !");

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //attaching onclick listener
        buttonScan.setOnClickListener(this);
    }
    public static boolean isEqual(Object o1, Object o2) {
        return o1 == o2 || (o1 != null && o1.equals(o2));
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                String result1 = result.getContents();
                List<String> items = Arrays.asList(result1.split("\\s*,\\s*"));
                String coupon = items.get(1);
                final String uname = items.get(2);

                if(isEqual("photo",coupon)) {
                    Call<DefaultResponse> updatecoupon = RetrofitClient.getInstance().getInterPreter().updatecoupon(uname, coupon);
                    updatecoupon.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                            DefaultResponse regEventResponse = response.body();
                            if (regEventResponse.isError()) {

                                qrimage.setImageResource(R.mipmap.not_found);
                                qr_total.setText("Coupon Error !");
                                qr_last.setText("Coupon may applied already or wrong coupon !");

                            } else {

                                qrimage.setImageResource(R.mipmap.tickmark);
                                qr_total.setText("Coupon Scan Finish Successfully !");
                                qr_last.setText("Last Scan Result : " + uname);

                            }

                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {

                            Intent generateFoodIntent = new Intent(Qr_scan_photo.this, OfflineActivity.class);
                            startActivity(generateFoodIntent);
                        }
                    });


                }else{
                    qrimage.setImageResource(R.mipmap.not_found);
                    qr_total.setText("Coupon Error !");
                    qr_last.setText("Wrong Coupon !");
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
