package com.nikhil.walkthrough;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Slide 1","Slide 1 Desc", R.drawable.ic_launcher_foreground, Color.RED));
        addSlide(AppIntroFragment.newInstance("Slide 2","Slide 2 Desc", R.drawable.ic_launcher_foreground, Color.GREEN));
        addSlide(AppIntroFragment.newInstance("Slide 3","Slide 3 Desc", R.drawable.ic_launcher_foreground, Color.BLUE));

        setFadeAnimation();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
    }
}
