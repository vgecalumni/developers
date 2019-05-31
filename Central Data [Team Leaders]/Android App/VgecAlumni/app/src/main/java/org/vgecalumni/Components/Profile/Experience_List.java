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

import org.vgecalumni.Adpater.ExperienceListAdpter;
import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Model.Experience;
import org.vgecalumni.Model.ExprResponse;
import org.vgecalumni.Model.SharedUser;
import org.vgecalumni.Model.User;
import org.vgecalumni.R;
import org.vgecalumni.SharedMemory.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Experience_List extends AppCompatActivity {

    List<Experience> experienceList;
    RecyclerView recyclerView;
    String s_id;
    Snackbar snackbar;

    @Override
    protected void onStart() {
        super.onStart();
        getExperience();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity_experience_list);
        setTitle(R.string.hint_expr);
        SharedPrefManager sharedPrefManager= SharedPrefManager.getmInstance(getApplicationContext());
        User user=sharedPrefManager.getUser();
        s_id =user.getId();
        //experienceList=Profile.getExperienceList();
        snackbar= Snackbar.make(findViewById(R.id.expr_lay),"Refreshing...",Snackbar.LENGTH_INDEFINITE);
        recyclerView=findViewById(R.id.recyler_experience);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        /*if (experienceList!=null) {
            setData();
        }*/
        getExperience();
    }

    private void setData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExperienceListAdpter(experienceList,Experience_List.this));
    }

    private void getExperience() {
        showProgress();
        Call<ExprResponse> exprResponseCall = RetrofitClient.getInstance().getInterPreter().getAllExpr(s_id);
        exprResponseCall.enqueue(new Callback<ExprResponse>() {
            @Override
            public void onResponse(Call<ExprResponse> call, Response<ExprResponse> response) {
                ExprResponse exprResponse=response.body();
                if(exprResponse.isError()){
                    //
                    endProgress();
                }else {
                    endProgress();
                    experienceList=exprResponse.getExperience();
                    recyclerView.setAdapter(new ExperienceListAdpter(experienceList,Experience_List.this));
                }
            }

            @Override
            public void onFailure(Call<ExprResponse> call, Throwable t) {
                //
                endProgress();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_save,menu);
        return true;
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
                Intent intent1 = new Intent(this, Add_Experience.class);
                intent1.putExtra("for",0);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProgress() {
        snackbar.show();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void endProgress() {
        snackbar.dismiss();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
