package com.hcpt.multileagues.adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.configs.Constants;
import com.hcpt.multileagues.fragments.ChatRoomFragment;
import com.hcpt.multileagues.fragments.TimeLineNotPlayedFragment;

public class TabsMatchDetailNotPlayedAdapter extends FragmentPagerAdapter {

    private int TABS = 2;

    private String[] TITLES;
    private String mMatchId;

    public TabsMatchDetailNotPlayedAdapter(FragmentManager fm, Activity act, String matchId) {
        super(fm);
        mMatchId = matchId;
        if (Constants.chatRoom) {
            TABS = 2;
            TITLES = new String[TABS];
            TITLES[0] = act.getResources().getString(R.string.text_timeline);
            TITLES[1] = act.getResources().getString(R.string.text_chatroom);
        } else {
            TABS = 1;
            TITLES = new String[TABS];
            TITLES[0] = act.getResources().getString(R.string.text_timeline);
        }
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
        if (Constants.chatRoom) {
            switch (position) {
                case 0:
                    return new TimeLineNotPlayedFragment();
                case 1:
                    return new ChatRoomFragment();
                default:
                    throw new IllegalArgumentException(
                            "The item position should be less: " + TABS);
            }
        } else {
            switch (position) {
                case 0:
                    return new TimeLineNotPlayedFragment();
                default:
                    throw new IllegalArgumentException(
                            "The item position should be less: " + TABS);
            }
        }
        // return SuperAwesomeCardFragment.newInstance(position);
    }

}
