package org.vgecalumni.Components.Temp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.ncorti.slidetoact.SlideToActView;

import org.vgecalumni.Components.SplashScreen.SplashActivity;
import org.vgecalumni.R;


public class reveal extends AppCompatActivity {

    private Intent handleIntent;
    public static final String MY_PREFS_NAME = "VgecAlumni";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.temp_welcome_slide1);
        overridePendingTransition(R.transition.slide_in,R.transition.slide_out);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String first = prefs.getString("first",null);



        if(first == null) {

            SlideToActView sta = (SlideToActView) findViewById(R.id.revealbutton);
            sta.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
                @Override
                public void onSlideComplete(@NonNull SlideToActView view) {

                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("first", "done");
                    editor.apply();
                    handleIntent = new Intent(reveal.this, SplashActivity.class);
                    startActivity(handleIntent);
                    finish();

                }
            });
        }else{
            handleIntent = new Intent(reveal.this, SplashActivity.class);
            startActivity(handleIntent);
            finish();
        }
    }


}
