package mobi.letsplay.livescore.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import mobi.letsplay.livescore.fragments.DashboardFragment;

public class DashboardMainAdapter extends FragmentStatePagerAdapter {

    public DashboardMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DashboardFragment();
            default:
                throw new IllegalArgumentException(
                        "The item position should be less: " + 1);
        }
        // return SuperAwesomeCardFragment.newInstance(position);
    }
}
