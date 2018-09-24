package com.hcpt.multileagues.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.fragments.NewsFragment;
import com.hcpt.multileagues.fragments.RankCLFragment;
import com.hcpt.multileagues.fragments.ScheduleCLFragment;
import com.hcpt.multileagues.fragments.SettingsFragment;
import com.hcpt.multileagues.fragments.TopScoreFragment;

public class TabsMainCLAdapter extends FragmentStatePagerAdapter {

    private static final int TABS = 5;

    private String[] TITLES = new String[TABS];

    public TabsMainCLAdapter(FragmentManager fm, Activity act) {
        super(fm);

        TITLES[0] = act.getResources().getString(R.string.text_rank);
        TITLES[1] = act.getResources().getString(R.string.text_schedule);
        TITLES[2] = act.getResources().getString(R.string.text_top_scorers);
        TITLES[3] = act.getResources().getString(R.string.text_news);
        TITLES[4] = act.getResources().getString(R.string.text_settings);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TABS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RankCLFragment();
            case 1:
                return new ScheduleCLFragment();
            case 2:
                return new TopScoreFragment();
            case 3:
                return new NewsFragment();
            case 4:
                return new SettingsFragment();
            default:
                throw new IllegalArgumentException(
                        "The item position should be less: " + TABS);
        }
        // return SuperAwesomeCardFragment.newInstance(position);
    }

}
