package mobi.letsplay.livescore.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.fragments.NewsFragment;
import mobi.letsplay.livescore.fragments.RankCLFragment;
import mobi.letsplay.livescore.fragments.ScheduleCLFragment;
import mobi.letsplay.livescore.fragments.SettingsFragment;
import mobi.letsplay.livescore.fragments.TopScoreFragment;

import mobi.letsplay.livescore.fragments.RankCLFragment;
import mobi.letsplay.livescore.fragments.ScheduleCLFragment;
import mobi.letsplay.livescore.fragments.SettingsFragment;
import mobi.letsplay.livescore.fragments.TopScoreFragment;

public class TabsMainCLAdapter extends FragmentStatePagerAdapter {

    private static final int TABS = 5;

    private String[] TITLES = new String[TABS];

    public TabsMainCLAdapter(FragmentManager fm, Activity act) {
        super(fm);

        TITLES[0] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_rank);
        TITLES[1] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_schedule);
        TITLES[2] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_top_scorers);
        TITLES[3] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_news);
        TITLES[4] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_settings);
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
