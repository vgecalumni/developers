package com.example.narendra.alumni;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.narendra.alumni.Api.RetrofitClient;
import com.example.narendra.alumni.Model.DefaultResponse;
import com.example.narendra.alumni.Model.Function;
import com.example.narendra.alumni.Model.SharedUser;
import com.example.narendra.alumni.SharedMemory.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Edit extends AppCompatActivity {

    private AppCompatEditText fname, mname, lname, address, district, pin, city, state;
    private TextInputLayout l_fname, l_mname, l_lname, l_address, l_district, l_pin, l_city, l_state;
    private String s_id, s_fname, s_mname, s_lname, s_address, s_district, s_pin, s_city, s_state;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        setTitle("Basic Information");

        fname = findViewById(R.id.user_firstname);
        mname = findViewById(R.id.user_midlename);
        lname = findViewById(R.id.user_lastname);

        address =findViewById(R.id.user_address);
        district =findViewById(R.id.user_district);
        pin=findViewById(R.id.user_pincode);
        city=findViewById(R.id.user_city);
        state=findViewById(R.id.user_state);

        l_fname = findViewById(R.id.lay_firstname);
        l_mname = findViewById(R.id.lay_midlename);
        l_lname = findViewById(R.id.lay_lastname);

        l_address=findViewById(R.id.lay_address);
        l_district =findViewById(R.id.lay_district);
        l_pin =findViewById(R.id.lay_pincode);
        l_city=findViewById(R.id.lay_city);
        l_state =findViewById(R.id.lay_state);

        layout =findViewById(R.id.lay_progress);

        getDetails();
    }

    private void getDetails() {
        SharedPrefManager sharedPrefManager= SharedPrefManager.getmInstance(getApplicationContext());
        SharedUser sharedUser=sharedPrefManager.getSharedUser();

        s_id=sharedUser.getId();
        fname.setText(sharedUser.getFname());
        mname.setText(sharedUser.getMname());
        lname.setText(sharedUser.getLname());
        address.setText(sharedUser.getAddress());
        district.setText(sharedUser.getDistrict());
        pin.setText(sharedUser.getPincode());
        city.setText(sharedUser.getCity());
        state.setText(sharedUser.getState());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                if (validate()) {
                    clearError();
                    showProgress();
                    Call<DefaultResponse> responseCall = RetrofitClient.getInstance()
                            .getInterPreter().editInfo(s_id, s_fname,s_mname, s_lname, s_address, s_district, s_pin, s_city, s_state);
                    responseCall.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                            endProgress();
                            DefaultResponse response1=response.body();
                            if(response1.isError()){
                                Toast.makeText(Profile_Edit.this,"No Changes", Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(Profile_Edit.this,response1.getMessage(), Toast.LENGTH_SHORT).show();
                                SharedPrefManager sharedPrefManager = SharedPrefManager.getmInstance(getApplicationContext());
                                SharedUser sharedUser = new SharedUser(s_id, s_fname,s_mname,s_lname, s_address, s_district, s_pin, s_city, s_state);
                                sharedPrefManager.saveSharedUser(sharedUser);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {
                            endProgress();
                            //todo
                        }
                    });
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearError() {
        l_fname.setError(null);
        l_mname.setError(null);
        l_lname.setError(null);

        l_address.setError(null);
        l_district.setError(null);
        l_pin.setError(null);
        l_city.setError(null);
        l_state.setError(null);
    }

    private boolean validate() {
        boolean x = true;
        clearError();

        s_fname = fname.getText().toString().trim();
        s_mname = mname.getText().toString().trim();
        s_lname = lname.getText().toString().trim();

        s_district = district.getText().toString().trim();
        s_pin = pin.getText().toString().trim();
        s_address = address.getText().toString().trim();
        s_city = city.getText().toString().trim();
        s_state = state.getText().toString().trim();

        String er_fname = Function.checkName(s_fname);
        String er_mname = Function.checkName(s_mname);
        String er_lname = Function.checkName(s_lname);
        String er_district = Function.checkAddress(s_district);
        String er_address = Function.checkAddress(s_address);
        String er_pin = Function.checkpin(s_pin);
        String er_city = Function.checkAddress(s_city);
        String er_state = Function.checkAddress(s_state);
       /* String er_enroll = Function.checkEnroll(s_enroll);
        String er_dob = Function.checkDOB(s_dob);*/

        if (er_fname != null) {
            l_fname.setError(er_fname);
            x = false;
        }
        if (er_mname != null) {
            l_mname.setError(er_mname);
            x = false;
        }
        if (er_lname != null) {
            l_lname.setError(er_lname);
            x = false;
        }
        if (er_address != null) {
            l_address.setError(er_address);
            x = false;
        }
        if (er_district != null) {
            l_district.setError(er_district);
            x = false;
        }
        if (er_pin != null) {
            l_pin.setError(er_pin);
            x = false;
        }
        if (er_city != null) {
            l_city.setError(er_city);
            x = false;
        }
        if (er_state != null) {
            l_state.setError(er_state);
            x = false;
        }

        return x;
    }

    private void showProgress() {
        layout.setVisibility(View.VISIBLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void endProgress() {
        layout.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
