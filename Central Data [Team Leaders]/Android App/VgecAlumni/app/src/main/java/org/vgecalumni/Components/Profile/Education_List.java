package org.vgecalumni.Components.Profile;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import org.vgecalumni.Adpater.EducationListAdpter;
import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Model.EduResponse;
import org.vgecalumni.Model.Education;
import org.vgecalumni.Model.SharedUser;
import org.vgecalumni.Model.User;
import org.vgecalumni.R;
import org.vgecalumni.SharedMemory.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Education_List extends AppCompatActivity{

    List<Education> educationList;
    RecyclerView recyclerView;
    String s_id;
    Snackbar snackbar;

    @Override
    protected void onStart() {
        super.onStart();
        getEducation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity_education_list);
        setTitle(R.string.hint_edu);
        SharedPrefManager sharedPrefManager= SharedPrefManager.getmInstance(getApplicationContext());
        User user=sharedPrefManager.getUser();
        s_id =user.getId();
        //educationList=Profile.getEducationList();
        snackbar= Snackbar.make(findViewById(R.id.edu_lay),"Refreshing...",Snackbar.LENGTH_INDEFINITE);
        recyclerView=findViewById(R.id.recyler_education);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        /*if (educationList!=null) {
            setData();
        }*/
        getEducation();
    }

    public void getEducation() {
        showProgress();
        Call<EduResponse> eduResponseCall = RetrofitClient.getInstance().getInterPreter().getAllEdu(s_id);
        eduResponseCall.enqueue(new Callback<EduResponse>() {
            @Override
            public void onResponse(Call<EduResponse> call, Response<EduResponse> response) {
                EduResponse eduResponse=response.body();
                if(eduResponse.isError()){
                    endProgress();
                    //Toast.makeText(Profile.this,eduResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }else {
                    endProgress();
                    educationList=eduResponse.getEducation();
                    recyclerView.setAdapter(new EducationListAdpter(educationList,Education_List.this));
                }
            }

            @Override
            public void onFailure(Call<EduResponse> call, Throwable t) {
                //
                endProgress();
            }
        });
    }

    private void setData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EducationListAdpter(educationList,Education_List.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_save,menu);
        return true;
    }

    private void showProgress() {
        snackbar.show();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void endProgress() {
        snackbar.dismiss();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            /*case R.id.menu_save:
                Intent intent = new Intent(this, Profile.class);
                startActivity(intent);
                finish();
                break;*/
            case R.id.menu_add:
                Intent intent1 = new Intent(this, Add_Education.class);
                intent1.putExtra("for",0);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
