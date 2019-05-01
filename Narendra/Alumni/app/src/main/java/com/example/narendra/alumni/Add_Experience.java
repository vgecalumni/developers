package com.example.narendra.alumni;

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

import com.example.narendra.alumni.Api.RetrofitClient;
import com.example.narendra.alumni.Model.DefaultResponse;
import com.example.narendra.alumni.Model.EduHolder;
import com.example.narendra.alumni.Model.Education;
import com.example.narendra.alumni.Model.Experience;
import com.example.narendra.alumni.Model.ExprHolder;
import com.example.narendra.alumni.Model.Function;
import com.example.narendra.alumni.Model.SharedUser;
import com.example.narendra.alumni.Model.User;
import com.example.narendra.alumni.SharedMemory.SharedPrefManager;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Experience extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private AppCompatEditText company, designation, descri, joindate, enddate;
    private TextInputLayout l_company, l_designation, l_descri, l_joindate, l_enddate;
    private String s_company, s_designation, s_descri, s_joindate, s_enddate;
    CheckBox curwork;
    RelativeLayout layout;
    Intent intent;
    private String s_tag, s_id;
    private int i;
    private Experience experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experience);
        setTitle(getString(R.string.hint_expr));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        company=findViewById(R.id.user_expr_comp);
        designation=findViewById(R.id.user_expr_desig);
        joindate=findViewById(R.id.user_expr_join);
        enddate=findViewById(R.id.user_expr_end);
        descri =findViewById(R.id.user_expr_desc);

        curwork =findViewById(R.id.chek_current);
        curwork.setOnCheckedChangeListener(this);

        l_company=findViewById(R.id.lay_expr_comp);
        l_designation=findViewById(R.id.lay_expr_desig);
        l_joindate=findViewById(R.id.lay_expr_join);
        l_enddate=findViewById(R.id.lay_expr_end);
        l_descri =findViewById(R.id.lay_expr_desc);

        layout=findViewById(R.id.lay_progress);

        // joindate.setOnClickListener(this);
        // enddate.setOnClickListener(this);

        User user = SharedPrefManager.getmInstance(getApplicationContext()).getUser();
        s_id=user.getId();

        intent = getIntent();
        i = intent.getIntExtra("for", 0);
        if (i == 1) {
            experience = ExprHolder.getInstance().getExperience();
            getDetails();
        }
    }

    private void getDetails() {
        s_tag = experience.getTag();
        s_company = experience.getCompany();
        s_designation = experience.getDesig();
        s_descri = experience.getDesc();
        s_joindate = experience.getJoin();
        s_enddate = experience.getEnd();

        if(s_enddate.equals(getResources().getString(R.string.hint_present))){
            disableEdit();
            curwork.setChecked(true);
        }

        company.setText(s_company);
        designation.setText(s_designation);
        descri.setText(s_descri);
        joindate.setText(s_joindate);
        enddate.setText(s_enddate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save,menu);
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
                    Call<DefaultResponse> responseCall=null;
                    if (i == 1) {
                        responseCall = RetrofitClient.getInstance()
                                .getInterPreter().editExpr(s_id, s_tag, s_company, s_designation, s_descri, s_joindate, s_enddate);
                    } else {
                        s_tag = String.valueOf(TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis()));
                        responseCall = RetrofitClient.getInstance()
                                .getInterPreter().addExpr(s_id, s_tag, s_company, s_designation, s_descri, s_joindate, s_enddate);
                    }
                    responseCall.enqueue(new Callback<DefaultResponse>() {
                        @Override
                        public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                            endProgress();
                            DefaultResponse response1 = response.body();
                            if (response1.isError()) {
                                Toast.makeText(Add_Experience.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Add_Experience.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
                                Experience nw = new Experience(s_company,s_designation,s_descri,s_joindate, s_enddate,s_tag);
                                ExprHolder.getInstance().setExperience(nw);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultResponse> call, Throwable t) {
                            endProgress();
                            Toast.makeText(Add_Experience.this, "Failure Occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_expr_join:
                getDate(joindate);
                break;
            case R.id.user_expr_end:
                getDate(enddate);
                break;
        }
    }

    private void clearError() {
        l_company.setError(null);
        l_designation.setError(null);
        l_descri.setError(null);
        l_enddate.setError(null);
        l_joindate.setError(null);
    }

    private boolean validate() {
        boolean x = true;
        clearError();

        s_company = company.getText().toString().trim();
        s_designation = designation.getText().toString().trim();
        s_descri = descri.getText().toString().trim();
        s_joindate = joindate.getText().toString().trim();
        s_enddate = enddate.getText().toString().trim();

        String er_company = Function.checkAddress(s_company);
        String er_desig = Function.checkAddress(s_designation);
        String er_desc = Function.checkAddress(s_descri);
        String er_end=null;

        String er_join = Function.checkYr(s_joindate);

        if (er_company != null) {
            l_company.setError(er_company);
            x = false;
        }
        if (er_desig != null) {
            l_designation.setError(er_desig);
            x = false;
        }
        if (er_desc != null) {
            l_descri.setError(er_desc);
            x = false;
        }
        if (er_join != null) {
            l_joindate.setError(er_join);
            x = false;
        }

        if(!curwork.isChecked()){
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
