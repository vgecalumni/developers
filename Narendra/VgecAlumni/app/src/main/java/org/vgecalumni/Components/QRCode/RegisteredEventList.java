package org.vgecalumni.Components.QRCode;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Model.RegEventResponse;
import org.vgecalumni.Model.RegEvents;
import org.vgecalumni.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisteredEventList extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "VgecAlumni";
    String s_event, s_photo, s_food;
    String temp, temp1;
    TextView title;
    TextView desc;
    ImageView qrimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_generate_status);

        setTitle("Registered Events");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        showProgress();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String uname = prefs.getString("uname", null);

        title = findViewById(R.id.qr_generate_title);
        desc = findViewById(R.id.qr_generate_desc);
        qrimage = findViewById(R.id.qr_image);

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
                    s_photo = rg.getPhoto();
                    if (s_photo.equals("0")) {
                        temp = "no";
                    } else {

                        qrimage.setImageResource(R.mipmap.tickmark);
                        title.setText("Suceess !");
                        desc.setText("Your food coupon has been applied ! Thanks for your co-operation. Now enjoy " + s_event + " 's dinner !");

                    }

                    s_food = rg.getFood();
                    if (s_food.equals("0")) {
                        temp1 = "no";
                    } else {
                        temp1 = "yes";
                    }
                }
            }

            @Override
            public void onFailure(Call<RegEventResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgress() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}