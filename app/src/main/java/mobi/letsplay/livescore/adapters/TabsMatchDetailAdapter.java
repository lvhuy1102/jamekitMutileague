package mobi.letsplay.livescore.adapters;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.configs.Constants;
import mobi.letsplay.livescore.configs.JsonConfigs;
import mobi.letsplay.livescore.fragments.ChatRoomFragment;
import mobi.letsplay.livescore.fragments.LineupsFragment;
//import com.hcpt.multileagues.fragments.TimeLineFragment;
import mobi.letsplay.livescore.fragments.TimelineFragmentNew;

import mobi.letsplay.livescore.fragments.ChatRoomFragment;
import mobi.letsplay.livescore.fragments.LineupsFragment;

public class TabsMatchDetailAdapter extends FragmentPagerAdapter {

    private int TABS = 2;

    private String[] TITLES;
    private String mMatchId;
    private Activity mAct;
    private String matchStatus;

    public TabsMatchDetailAdapter(FragmentManager fm, Activity act, String matchId, final String mMatchStatus) {
        super(fm);

        this.mAct = act;

        mMatchId = matchId;
        this.matchStatus = mMatchStatus;
        if (Constants.chatRoom) {
            if (matchStatus.equals(JsonConfigs.MATCH_STATUS_ACTIVE) || matchStatus.equals(JsonConfigs.MATCH_STATUS_NOT_STARTED)) {
                TABS = 3;
                TITLES = new String[TABS];
                TITLES[0] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_timeline);
                TITLES[1] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_lineups);
                TITLES[2] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_chatroom);
            } else {
                TABS = 2;
                TITLES = new String[TABS];
                TITLES[0] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_timeline);
                TITLES[1] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_chatroom);
            }

        } else {
            TABS = 2;
            TITLES = new String[TABS];
            TITLES[0] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_timeline);
            TITLES[1] = act.getResources().getString(mobi.letsplay.livescore.R.string.text_lineups);
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
            if (matchStatus.equals(JsonConfigs.MATCH_STATUS_ACTIVE) || matchStatus.equals(JsonConfigs.MATCH_STATUS_NOT_STARTED)) {
                switch (position) {
                    case 0:
                        return new TimelineFragmentNew();
                    case 1:
                        return new LineupsFragment();
                    case 2:
                        return new ChatRoomFragment();
                    default:
                        throw new IllegalArgumentException(
                                "The item position should be less: " + TABS);
                }
            } else {
                switch (position) {
                    case 0:
                        return new TimelineFragmentNew();
                    case 1:
                        return new ChatRoomFragment();
                    default:
                        throw new IllegalArgumentException(
                                "The item position should be less: " + TABS);
                }
            }
        } else {
            switch (position) {
                case 0:
                    return new TimelineFragmentNew();
                case 1:
                    return new LineupsFragment();
                default:
                    throw new IllegalArgumentException(
                            "The item position should be less: " + TABS);
            }
        }
        // return SuperAwesomeCardFragment.newInstance(position);
    }

}
