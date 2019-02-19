package com.example.narendra.alumni;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.narendra.alumni.Api.RetrofitClient;
import com.example.narendra.alumni.Model.DefaultResponse;
import com.example.narendra.alumni.Model.Education;
import com.example.narendra.alumni.Model.Function;
import com.example.narendra.alumni.Model.SharedUser;
import com.example.narendra.alumni.SharedMemory.SharedPrefManager;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Education extends AppCompatActivity implements View.OnClickListener {

    private AppCompatEditText degree, inst, joindate, enddate, stream;
    private TextInputLayout l_degree, l_inst, l_joindate, l_enddate, l_stream;
    private String s_degree, s_inst, s_joindate, s_enddate, s_stream, s_email;
    List<Education> educationList;
    RelativeLayout layout;
    Intent intent;
    private String s_tag, s_id;
    Snackbar snackbar;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education);
        setTitle(getString(R.string.hint_edu));

        degree = findViewById(R.id.user_degree);
        inst = findViewById(R.id.user_inst);
        joindate = findViewById(R.id.user_edu_join);
        enddate = findViewById(R.id.user_edu_end);

        stream = findViewById(R.id.user_stream);

        l_degree = findViewById(R.id.lay_degree);
        l_inst = findViewById(R.id.lay_inst);
        l_stream = findViewById(R.id.lay_user_stream);
        l_joindate = findViewById(R.id.lay_edu_join);
        l_enddate = findViewById(R.id.lay_edu_end);

        joindate.setOnClickListener(this);
        enddate.setOnClickListener(this);

        layout = findViewById(R.id.lay_progress);
        educationList = Profile.getEducationList();

        SharedUser sharedUser = SharedPrefManager.getmInstance(getApplicationContext()).getSharedUser();
        s_id = sharedUser.getId();

        intent = getIntent();
        i = intent.getIntExtra("for", 0);
        if (i == 1) {
            getDetails();
        }
    }

    private void getDetails() {
        s_tag = intent.getStringExtra("tag");
        s_degree = intent.getStringExtra("degree");
        s_stream = intent.getStringExtra("stream");
        s_inst = intent.getStringExtra("inst");
        s_joindate = intent.getStringExtra("join");
        s_enddate = intent.getStringExtra("end");


        degree.setText(s_degree);
        inst.setText(s_inst);
        stream.setText(s_stream);
        joindate.setText(s_joindate);
        enddate.setText(s_enddate);
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
                    Call<DefaultResponse> responseCall=null;
                    if (i == 1) {
                        responseCall = RetrofitClient.getInstance()
                                .getInterPreter().editEdu(s_id, s_tag, s_inst, s_degree, s_stream, s_joindate, s_enddate);
                    } else {
                        s_tag= String.valueOf(TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis()));
                        responseCall = RetrofitClient.getInstance()
                                .getInterPreter().addEdu(s_id, s_tag, s_inst, s_degree, s_stream, s_joindate, s_enddate);
                    }

                    responseCall.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                            endProgress();
                            DefaultResponse response1 = response.body();
                            if (response1.isError()) {
                                //todo
                            } else {
                                Toast.makeText(Add_Education.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
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
        l_degree.setError(null);
        l_inst.setError(null);
        l_stream.setError(null);
        l_enddate.setError(null);
        l_joindate.setError(null);
    }

    private boolean validate() {
        boolean x = true;
        clearError();

        s_degree = degree.getText().toString().trim();
        s_inst = inst.getText().toString().trim();
        s_joindate = joindate.getText().toString().trim();
        s_enddate = enddate.getText().toString().trim();
        s_stream = stream.getText().toString().trim();

        String er_degree = Function.checkAddress(s_degree);
        String er_inst = Function.checkAddress(s_inst);
        String er_stream = Function.checkAddress(s_stream);
        String er_join = Function.checkDOB(s_joindate);
        String er_end = Function.checkDOB(s_enddate);

        if (er_degree != null) {
            l_degree.setError(er_degree);
            x = false;
        }
        if (er_inst != null) {
            l_inst.setError(er_inst);
            x = false;
        }
        if (er_join != null) {
            l_joindate.setError(er_join);
            x = false;
        }
        if (er_end != null) {
            l_enddate.setError(er_end);
            x = false;
        }if (er_stream != null) {
            l_stream.setError(er_stream);
            x = false;
        }if(er_end==null && er_join==null){
            String er_gap=Function.checkDateGap(s_joindate,s_enddate);
            if(er_gap!=null){
                l_enddate.setError(er_gap);
                x = false;
            }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_edu_join:
                getDate(joindate);
                break;
            case R.id.user_edu_end:
                getDate(enddate);
                break;
        }
    }

    private void getDate(final AppCompatEditText date) {
        final Calendar c = Calendar.getInstance();
        int y, m, d;
        y = c.get(Calendar.YEAR);
        m = c.get(Calendar.MONTH);
        d = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String dt= (year + "-" + (month + 1) + "-" + dayOfMonth);
                date.setText(dt);
            }
        }, y, m, d);
        datePickerDialog.show();
    }
}
