package org.vgecalumni.Components.Profile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Model.DefaultResponse;
import org.vgecalumni.Model.EduHolder;
import org.vgecalumni.Model.Education;
import org.vgecalumni.Model.Function;
import org.vgecalumni.Model.User;
import org.vgecalumni.R;
import org.vgecalumni.SharedMemory.SharedPrefManager;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Education extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private AppCompatEditText degree, inst, joindate, enddate, stream;
    private TextInputLayout l_degree, l_inst, l_joindate, l_enddate, l_stream;
    private String s_degree, s_inst, s_joindate, s_enddate, s_stream;
    CheckBox curstudy;
    RelativeLayout layout;
    Intent intent;
    private String s_tag, s_id;
    private int i;
    Education education;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity_add_education);
        setTitle(getString(R.string.hint_edu));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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

        curstudy =findViewById(R.id.chek_current);
        curstudy.setOnCheckedChangeListener(this);

        //joindate.setOnClickListener(this);
        //enddate.setOnClickListener(this);

        layout = findViewById(R.id.lay_progress);

        User user = SharedPrefManager.getmInstance(getApplicationContext()).getUser();
        s_id = user.getId();

        intent = getIntent();
        i = intent.getIntExtra("for", 0);
        if (i == 1) {
            education = EduHolder.getInstance().getEducation();
            getDetails();
        }
    }

    private void getDetails() {

        s_tag = education.getTag();
        s_degree = education.getDegree();
        s_stream = education.getStream();
        s_inst = education.getInst();
        s_joindate = education.getJoin();
        s_enddate = education.getEnd();

        if(s_enddate.equals(getResources().getString(R.string.hint_present))){
            disableEdit();
            curstudy.setChecked(true);
        }

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

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_save:
                if (validate()) {
                    clearError();
                    showProgress();
                    Call<DefaultResponse> responseCall = null;

                    if (i == 1) {
                        responseCall = RetrofitClient.getInstance()
                                .getInterPreter().editEdu(s_id, s_tag, s_inst, s_degree, s_stream, s_joindate, s_enddate);
                    } else {
                        s_tag = String.valueOf(TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis()));
                        responseCall = RetrofitClient.getInstance()
                                .getInterPreter().addEdu(s_id, s_tag, s_inst, s_degree, s_stream, s_joindate, s_enddate);
                    }

                    responseCall.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                            endProgress();
                            DefaultResponse response1 = response.body();
                            if (response1.isError()) {
                                Toast.makeText(Add_Education.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Add_Education.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
                                Education nw = new Education(s_degree, s_stream, s_inst, s_joindate, s_enddate, s_tag);
                                EduHolder.getInstance().setEducation(nw);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {
                            endProgress();
                            Toast.makeText(Add_Education.this, "Failure Occurred", Toast.LENGTH_SHORT).show();
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
        String er_join = Function.checkYr(s_joindate);
        //String er_end = Function.checkYr(s_enddate);
        String er_end=null;

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
        }
        if (er_stream != null) {
            l_stream.setError(er_stream);
            x = false;
        }
        if(!curstudy.isChecked()){
            er_end = Function.checkYr(s_enddate);
            if (er_end != null) {
                l_enddate.setError(er_end);
                x = false;
            }
        }
        /*if(er_end==null && er_join==null){
            String er_gap=Function.checkDateGap(s_joindate,s_enddate);
            if(er_gap!=null){
                l_enddate.setError(er_gap);
                x = false;
            }
        }*/

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
                String dt = (year + "-" + (month + 1) + "-" + dayOfMonth);
                date.setText(dt);
            }
        }, y, m, d);
        datePickerDialog.show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            disableEdit();
        }else {
            enableEdit();
        }
    }

    private void enableEdit() {
        enddate.setText("");
        enddate.setFocusableInTouchMode(true);
        enddate.setFocusable(true);
    }

    private void disableEdit() {
        l_enddate.setError(null);
        enddate.setText(R.string.hint_present);
        enddate.setOnClickListener(null);
        enddate.setFocusable(false);
    }
}
