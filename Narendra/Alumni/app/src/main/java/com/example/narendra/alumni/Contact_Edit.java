package com.example.narendra.alumni;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.Menu;
import android.view.MenuItem;

public class Contact_Edit extends AppCompatActivity {
    private AppCompatEditText mob, email, flat, landmark, area, pin;
    private AppCompatSpinner city, state;
    private TextInputLayout l_mob, l_email, l_flat, l_landmark, l_area, l_pin;
    private String s_mob, s_email, s_flat, s_landmark, s_area, s_pin, s_city, s_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        setTitle("Contact Information");

       /* mob=findViewById(R.id.user_mobile);
        email=findViewById(R.id.user_email);
        flat=findViewById(R.id.user_flat);
        landmark=findViewById(R.id.user_landmark);
        area =findViewById(R.id.user_area);
        pin=findViewById(R.id.user_pincode);

        city=findViewById(R.id.user_city);
        state=findViewById(R.id.user_state);

        l_mob=findViewById(R.id.lay_mobile);
        l_email=findViewById(R.id.lay_email);
        l_flat=findViewById(R.id.lay_flat);
        l_landmark=findViewById(R.id.lay_landmark);
        l_area =findViewById(R.id.lay_area);
        l_pin =findViewById(R.id.lay_pincode);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_save:
                //todo:add save
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
