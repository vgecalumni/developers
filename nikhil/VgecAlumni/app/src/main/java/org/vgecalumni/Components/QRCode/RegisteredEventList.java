package org.vgecalumni.Components.QRCode;

        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.vgecalumni.Api.InterPreter;
        import org.vgecalumni.Api.RetrofitClient;
        import org.vgecalumni.Model.Education;
        import org.vgecalumni.Model.Experience;
        import org.vgecalumni.Model.RegEventResponse;
        import org.vgecalumni.Model.RegEvents;
        import org.vgecalumni.Model.User;
        import org.vgecalumni.Model.UserResponse;
        import org.vgecalumni.R;
        import org.vgecalumni.SharedMemory.SharedPrefManager;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Objects;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class RegisteredEventList extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "VgecAlumni";
    String s_event, s_photo, s_food;
    ListView listView;
    String temp,temp1;
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
                    s_photo = rg.getPhoto();
                    if(s_photo.equals("0")){
                        temp = "no";
                    }else{

                        qrimage.setImageResource(R.mipmap.tickmark);
                        title.setText("Suceess !");
                        desc.setText("Your food coupon has been applied ! Thanks for your co-operation. Now enjoy " + s_event + " 's dinner !");

                    }

                    s_food = rg.getFood();
                    if(s_food.equals("0")){
                        temp1 = "no";
                    }else{
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
    private void endProgress() {
        Toast.makeText(RegisteredEventList.this, "try again!", Toast.LENGTH_SHORT).show();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


}