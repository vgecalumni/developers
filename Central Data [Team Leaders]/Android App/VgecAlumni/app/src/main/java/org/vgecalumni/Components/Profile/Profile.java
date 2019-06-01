package org.vgecalumni.Components.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.vgecalumni.Adpater.ViewPagerAdapter;
import org.vgecalumni.Api.RetrofitClient;
import org.vgecalumni.Fragment.BasicInfo;
import org.vgecalumni.Fragment.EducationInfo;
import org.vgecalumni.Fragment.ExperienceInfo;
import org.vgecalumni.Model.Myinterface;
import org.vgecalumni.Model.User;
import org.vgecalumni.Model.UserResponse;
import org.vgecalumni.R;
import org.vgecalumni.SharedMemory.SharedPrefManager;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private String s_uname;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton fab,fab1,fab2;

    public CircleImageView imageView;
    public TextView t_mob, t_email;
    ViewPagerAdapter viewPagerAdapter;

    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    private static final String MY_PREFS_NAME = "VgecAlumni";

    Myinterface myinterface,myinterface2,myinterface3;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity_profile);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        s_uname = prefs.getString("uname", null);

        toolbar=findViewById(R.id.tool);
        appBarLayout=findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset)==appBarLayout.getTotalScrollRange()){
                    toolbar.setBackground(ContextCompat.getDrawable(getBaseContext(),R.drawable.pr_back));
                }
            }
        });
        collapsingToolbarLayout=findViewById(R.id.colltoolbar);
        collapsingToolbarLayout.setExpandedTitleColor(Color.argb(0,0,0,0));
        setSupportActionBar(toolbar);
        setTitle(R.string.hint_profile);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        t_email = findViewById(R.id.user_email);
        t_mob = findViewById(R.id.user_mobile);
        imageView = findViewById(R.id.img_profile);

        tabLayout = findViewById(R.id.tablay);
        viewPager = findViewById(R.id.viewPager);

        fab = findViewById(R.id.fab0);
        fab.setOnClickListener(this);
        fab1 = findViewById(R.id.fab1);
        fab1.setOnClickListener(this);
        fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(this);

        getUser();

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new BasicInfo(), "Basic Info");
        viewPagerAdapter.addFragment(new EducationInfo(), "Education");
        viewPagerAdapter.addFragment(new ExperienceInfo(), "Experience");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(this);
        onPageSelected(0);
    }

    private void getUser() {
        Call<UserResponse> userResponseCall = RetrofitClient.getInstance().getInterPreter().getUser(s_uname);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (userResponse.isError()) {
                    Toast.makeText(Profile.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    User user = userResponse.getUser();
                    SharedPrefManager sharedPrefManager = SharedPrefManager.getmInstance(getApplicationContext());
                    sharedPrefManager.saveUser(user);
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab0:
                Intent intent = new Intent(this, Profile_Edit.class);
                startActivityForResult(intent,300);
                break;
            case R.id.fab1:
                Intent intent1 = new Intent(this, Add_Education.class);
                intent1.putExtra("for",0);
                startActivityForResult(intent1,100);
                break;
            case R.id.fab2:
                Intent intent2 = new Intent(this, Add_Experience.class);
                intent2.putExtra("for",0);
                startActivityForResult(intent2,200);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==0){
            fab.setVisibility(View.VISIBLE);
            fab1.setVisibility(View.GONE);
            fab2.setVisibility(View.GONE);
        } if(position==1) {
            fab.setVisibility(View.GONE);
            fab1.setVisibility(View.VISIBLE);
            fab2.setVisibility(View.GONE);
        } if(position==2) {
            fab.setVisibility(View.GONE);
            fab1.setVisibility(View.GONE);
            fab2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100){
            myinterface.myAction(requestCode);
        }if (requestCode==200) {
            myinterface2.myAction(requestCode);
        }if (requestCode==300){
            myinterface3.myAction(requestCode);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setListner(Myinterface myinterface){
        this.myinterface=myinterface;
    }
    public void setListner2(Myinterface myinterface){
        this.myinterface2=myinterface;
    }
    public void setListner3(Myinterface myinterface){
        this.myinterface3=myinterface;
    }

    public int getFabState(){
        return fab.isShown()?fab.getId():fab1.isShown()?fab1.getId():fab2.getId();
    }

    public void hideFab() {
        fab.hide();
    }

    public void showFab() {
        fab.show();
    }

    public CircleImageView getImageView() {
        return imageView;
    }

    public TextView getT_mob() {
        return t_mob;
    }

    public TextView getT_email() {
        return t_email;
    }
}
