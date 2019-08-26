package org.vgecalumni.Components.Portfolio;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.vgecalumni.Components.Events.CentralEventsFragment;
import org.vgecalumni.Components.Events.DepartmentalEventsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        if(i == 0) {
            return new EventsPortfolioFragment();
        } else if(i == 1) {
            return new AlumniPortfolioFragment();
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
