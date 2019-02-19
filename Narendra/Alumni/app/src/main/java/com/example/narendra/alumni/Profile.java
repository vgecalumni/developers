package com.example.narendra.alumni;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.narendra.alumni.Adpater.EducationAdpater;
import com.example.narendra.alumni.Adpater.ExperienceAdpater;
import com.example.narendra.alumni.Api.RetrofitClient;
import com.example.narendra.alumni.Model.EduResponse;
import com.example.narendra.alumni.Model.Education;
import com.example.narendra.alumni.Model.Experience;
import com.example.narendra.alumni.Model.ExprResponse;
import com.example.narendra.alumni.Model.SharedUser;
import com.example.narendra.alumni.Model.User;
import com.example.narendra.alumni.Model.UserResponse;
import com.example.narendra.alumni.SharedMemory.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    String s_email = "divyesha29@gmail.com";
    String s_fname, s_mname, s_lname, s_enroll, s_id,  s_branch, s_gender, s_dob;
    String s_mob,s_address, s_district, s_pincode, s_city, s_state;
    String s_fullname, s_addr1, s_addr2;
    TextView t_fullname, t_enroll, t_branch, t_gender, t_dob, t_mob, t_email, t_addr1, t_addr2;
    RelativeLayout layout;
    RecyclerView recyclerViewEdu, recyclerViewExpr;
    static List<Education> educationList;
    static List<Experience> experienceList;

    @Override
    protected void onStart() {
        super.onStart();

        getEducation();
        getExperience();
        getDetails();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle(R.string.hint_profile);

        layout = findViewById(R.id.lay_progress);
        findViewById(R.id.basic_edit).setOnClickListener(this);
        findViewById(R.id.edu_edit).setOnClickListener(this);
        findViewById(R.id.expr_edit).setOnClickListener(this);

        recyclerViewEdu = findViewById(R.id.recyler_education);
        recyclerViewEdu.hasFixedSize();
        recyclerViewEdu.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewExpr = findViewById(R.id.recyler_experience);
        recyclerViewExpr.hasFixedSize();
        recyclerViewExpr.setLayoutManager(new LinearLayoutManager(this));

        t_fullname=findViewById(R.id.user_name);
        t_email=findViewById(R.id.user_email);
        t_mob=findViewById(R.id.user_mobile);
        t_enroll=findViewById(R.id.user_enroll);
        t_branch=findViewById(R.id.user_branch);
        t_gender=findViewById(R.id.user_gender);
        t_dob=findViewById(R.id.user_dob);
        t_addr1=findViewById(R.id.user_addr);
        t_addr2=findViewById(R.id.user_addr2);

        getUser();
    }

    private void getUser() {
        showProgress();

        Call<UserResponse> userResponseCall = RetrofitClient.getInstance().getInterPreter().getUser(s_email);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                endProgress();
                UserResponse userResponse = response.body();
                if (userResponse.isError()) {
                    Toast.makeText(Profile.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    User user = userResponse.getUser();
                    s_id=user.getId();
                    s_fname = user.getFname();
                    s_mname = user.getMname();
                    s_lname = user.getLname();
                    s_mob = user.getMob();
                    s_enroll = user.getEnroll();
                    s_branch = user.getBranch();
                    s_gender = user.getGender();
                    s_dob = user.getDob();
                    s_address = user.getAddress();
                    s_city = user.getCity();
                    s_district = user.getDistrict();
                    s_state = user.getState();
                    s_pincode = user.getPincode();
                    getEducation();
                    getExperience();
                    SharedPrefManager sharedPrefManager = SharedPrefManager.getmInstance(getApplicationContext());
                    sharedPrefManager.saveUser(user);
                    getDetails();
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                endProgress();
            }
        });
    }

    private void getDetails() {
        SharedPrefManager sharedPrefManager= SharedPrefManager.getmInstance(getApplicationContext());
        SharedUser sharedUser=sharedPrefManager.getSharedUser();

        s_fullname= sharedUser.getFname() +" "+ sharedUser.getMname() +" "+ sharedUser.getLname();
        s_addr1=sharedUser.getAddress()+" "+sharedUser.getCity();
        s_addr2= sharedUser.getDistrict() +"-"+ sharedUser.getPincode() +","+ sharedUser.getState();
        setField();
    }

    private void setField() {
        t_fullname.setText(s_fullname);
        t_email.setText(s_email);
        t_mob.setText(s_mob);
        t_enroll.setText(s_enroll);
        t_branch.setText(s_branch);
        t_gender.setText(s_gender);
        t_dob.setText(s_dob);
        t_addr1.setText(s_addr1);
        t_addr2.setText(s_addr2);
    }

    private void getEducation() {
        Call<EduResponse> eduResponseCall = RetrofitClient.getInstance().getInterPreter().getAllEdu(s_id);
        eduResponseCall.enqueue(new Callback<EduResponse>() {
            @Override
            public void onResponse(Call<EduResponse> call, Response<EduResponse> response) {
                EduResponse eduResponse=response.body();
                if(eduResponse.isError()){
                    //Toast.makeText(Profile.this,eduResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }else {
                    educationList=eduResponse.getEducation();
                    recyclerViewEdu.setAdapter(new EducationAdpater(educationList,Profile.this));
                }
            }

            @Override
            public void onFailure(Call<EduResponse> call, Throwable t) {
                //
            }
        });
    }

    private void getExperience() {
        Call<ExprResponse> exprResponseCall = RetrofitClient.getInstance().getInterPreter().getAllExpr(s_id);
        exprResponseCall.enqueue(new Callback<ExprResponse>() {
            @Override
            public void onResponse(Call<ExprResponse> call, Response<ExprResponse> response) {
                ExprResponse exprResponse=response.body();
                if(exprResponse.isError()){
                    //
                }else {
                    experienceList=exprResponse.getExperience();
                    recyclerViewExpr.setAdapter(new ExperienceAdpater(experienceList,Profile.this));
                }
            }

            @Override
            public void onFailure(Call<ExprResponse> call, Throwable t) {
               //
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edu_edit:
                Intent intent = new Intent(this, Education_List.class);
                startActivity(intent);
                break;
            case R.id.expr_edit:
                Intent intent2 = new Intent(this, Experience_List.class);
                startActivity(intent2);
                break;
            case R.id.basic_edit:
                Intent intent3 = new Intent(this, Profile_Edit.class);
                startActivity(intent3);
                break;
        }
    }

    private void showProgress() {
        layout.setVisibility(View.VISIBLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void endProgress() {
        layout.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static List<Education> getEducationList() {
        return educationList;
    }

    public static List<Experience> getExperienceList() {
        return experienceList;
    }
}
