package org.vgecalumni.Components.Events;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.vgecalumni.R;

public class EventsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_activity_main);

        setUpToolbar();
        setUpTabbedLayout();
        setUpViewPager();
    }

    private void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Events");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setUpTabbedLayout() {
        tabLayout = findViewById(R.id.tablayout);

        tabLayout.addTab(tabLayout.newTab().setText("Centralize"));
        tabLayout.addTab(tabLayout.newTab().setText("Departmental"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void setUpViewPager() {
        viewPager = findViewById(R.id.viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPageTransformer(true, new ZoomOutViewPagerTransformation());
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    class ZoomOutViewPagerTransformation implements ViewPager.PageTransformer {

        private static final float MIN_SCALE = 0.65f;
        private static final float MIN_ALPHA = 0.3f;

        @Override
        public void transformPage(@NonNull View view, float v) {
            if (v < -1) {
                view.setAlpha(0);
            } else if (v <= 1) {
                view.setScaleX(Math.max(MIN_SCALE, 1 - Math.abs(v)));
                view.setScaleY(Math.max(MIN_SCALE, 1 - Math.abs(v)));
                view.setAlpha(Math.max(MIN_ALPHA, 1 - Math.abs(v)));
            } else {
                view.setAlpha(0);
            }
        }
    }
}
