package com.hcpt.multileagues.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.activities.MatchDetailActivity;
import com.hcpt.multileagues.adapters.EventAdapter;
import com.hcpt.multileagues.configs.Args;
import com.hcpt.multileagues.configs.GlobalValue;
import com.hcpt.multileagues.configs.JsonConfigs;
import com.hcpt.multileagues.configs.WebservicesConfigs;
import com.hcpt.multileagues.database.DatabaseUtility;
import com.hcpt.multileagues.image.utils.ImageLoader;
import com.hcpt.multileagues.json.utils.ParserUtils;
import com.hcpt.multileagues.modelmanager.ModelManager;
import com.hcpt.multileagues.modelmanager.ModelManagerListener;
import com.hcpt.multileagues.network.NetworkUtility;
import com.hcpt.multileagues.objects.APIObj;
import com.hcpt.multileagues.objects.EventObject;
import com.hcpt.multileagues.objects.MatchObj;
import com.hcpt.multileagues.utilities.DateTimeUtility;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TimeLineFragment extends Fragment {

    private static final String TAG = TimeLineFragment.class.getSimpleName();

    private View view;
    private MatchObj mMatchObj;
    private MatchObj mMatchDetailObj;
    private String mMatchId;
    private TextView mLblEventsNo;
    private TextView mLblScores, mLblHomeName, mLblAwayName, mLblStadium;
    private ImageView mLogoHome, mLogoAway;
    private ListView mLvEvents;
    private EventAdapter mEventAdapter;
    private static ArrayList<EventObject> mArrEvent;
    private static Timer timer;

    private TextView mLblDateTimeline, mLblTimeTimeline;
    private ImageLoader mImgLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMatchObj = MatchDetailActivity.currentMatch;
        mMatchId = MatchDetailActivity.currentMatch.getmMatchId();
        mImgLoader = new ImageLoader(getActivity());

        // Allow display option menu in this fragment.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_timeline, container, false);

        initControls();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_save).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            onResume();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        getCurrentMatch();
        initEvents();

        if (GlobalValue.prefs != null && timer == null) {
            autoRefreshEvents(GlobalValue.prefs.getBooleanValue(Args.KEY_UI_AUTO_REFRESH));
        }

    }

    @Override
    public void onStop() {
        super.onStop();

        // Stopping refresh events action.
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void initControls() {
        mLblEventsNo = (TextView) view.findViewById(R.id.lbl_events_no);
        mLvEvents = (ListView) view.findViewById(R.id.lv_events);

//        mLogoHome = (ImageView) view.findViewById(R.id.img_homeclub_timeline);
//        mLogoAway = (ImageView) view.findViewById(R.id.img_awayclub_timeline);
        mLblScores = (TextView) view.findViewById(R.id.lbl_scores_timeline);
        mLblHomeName = (TextView) view.findViewById(R.id.lbl_name_team1);
        mLblHomeName.setSelected(true);
        mLblAwayName = (TextView) view.findViewById(R.id.lbl_name_team2);
        mLblAwayName.setSelected(true);
        mLblStadium = (TextView) view.findViewById(R.id.lbl_stadium_timeline);
        mLblStadium.setSelected(true);

        mLblDateTimeline = (TextView) view.findViewById(R.id.lbl_date_timeline);
        mLblTimeTimeline = (TextView) view.findViewById(R.id.lbl_time_timeline);
    }

    private void initMatchData() {
        // Get name and home's logo.
        if (mMatchObj.getmHomeName().equals("null")) {
            mLblHomeName.setText("");
        } else {
            mLblHomeName.setText(mMatchObj.getmHomeName());
        }
//        setLargeLogoClubs(mMatchObj.getmHomeImage(), mLogoHome);

        // Get name and away's logo.

        if (mMatchObj.getmAwayName().equals("null")) {
            mLblAwayName.setText("");
        } else {
            mLblAwayName.setText(mMatchObj.getmAwayName());
        }
//        setLargeLogoClubs(mMatchObj.getmAwayImage(), mLogoAway);

        // Get stadium
        mLblStadium.setText(mMatchObj.getmStadium());

        // Set time.
        String date = DateTimeUtility.convertTimeStampToDate(mMatchObj.getmTime(), "yyyy-MM-dd");
        String time = DateTimeUtility.convertTimeStampToDate(mMatchObj.getmTime(), "HH:mm");

        mLblDateTimeline.setText(date);

        // set Time or set status
        String strTime = "";
        int timeColor = getResources().getColor(R.color.black);
        int colorBackground = getResources().getColor(R.color.tranparent);
        if (mMatchObj.getmMatchStatus()
                .equals(JsonConfigs.MATCH_STATUS_NOT_STARTED)) {
            strTime = DateTimeUtility.convertTimeStampToDate(mMatchObj.getmTime(), "HH:mm");
            timeColor = getResources().getColor(
                    R.color.textColorSecondary);
            colorBackground = getResources().getColor(R.color.tranparent);
        } else if (mMatchObj.getmMatchStatus().equals(
                JsonConfigs.MATCH_STATUS_ACTIVE)) {
            strTime = getContext().getString(R.string.live);
            timeColor = getResources().getColor(R.color.white);
            colorBackground = getResources().getColor(R.color.background_lable_live);
        } else if (mMatchObj.getmMatchStatus().equals(
                JsonConfigs.MATCH_STATUS_FINISHED)) {
            strTime = getContext().getString(R.string.final_text);
            timeColor = getResources().getColor(R.color.textColorSecondary);
            colorBackground = getResources().getColor(R.color.tranparent);
        }
        mLblTimeTimeline.setText(strTime);
        mLblTimeTimeline.setTextColor(timeColor);
        mLblTimeTimeline.setBackgroundColor(colorBackground);

        // Get scores.
        if (mMatchObj.getmMatchStatus().equals(
                JsonConfigs.MATCH_STATUS_NOT_STARTED)) {
            mLblScores.setText("? - ?");
        } else {
            mLblScores.setText(mMatchDetailObj.getmHomeScore() + " - "
                    + mMatchDetailObj.getmAwayScore());
        }

    }

    private void initEvents() {
        if (NetworkUtility.getInstance(getActivity()).isNetworkAvailable()) {
            getEventsFromAPI();
        } else {
            getEventsFromDB();
        }
    }

    private void getEventsFromDB() {
        APIObj apiInfos = DatabaseUtility.getResuftFromApi(
                getActivity(),
                WebservicesConfigs.URL_GET_EVENTS_BY_MATCH
                        + mMatchObj.getmMatchId());
        if (apiInfos != null) {
            String json = apiInfos.getmResult();

            mArrEvent = ParserUtils.parserEvents(json);

            // Bind data for listview.
            if (mArrEvent.size() > 0) {
                mLvEvents.setVisibility(View.VISIBLE);
                mLblEventsNo.setVisibility(View.GONE);

                mEventAdapter = new EventAdapter(getActivity(), mArrEvent,
                        mMatchObj);
                mLvEvents.setAdapter(mEventAdapter);
            } else {
                mLvEvents.setVisibility(View.GONE);
                mLblEventsNo.setVisibility(View.VISIBLE);
            }
        }
    }

    private void getEventsFromAPI() {
        ModelManager.getEventsByMatch(getActivity(), mMatchObj.getmMatchId(),
                true, new ModelManagerListener() {

                    @Override
                    public void onSuccess(Object object) {
                        String json = (String) object;
                        mArrEvent = ParserUtils.parserEvents(json);

                        if (mArrEvent.size() > 0) {
                            mLvEvents.setVisibility(View.VISIBLE);
                            mLblEventsNo.setVisibility(View.GONE);

                            mEventAdapter = new EventAdapter(getActivity(),
                                    mArrEvent, mMatchObj);
                            mLvEvents.setAdapter(mEventAdapter);
                        } else {
                            mLvEvents.setVisibility(View.GONE);
                            mLblEventsNo.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    private class RefreshEvents extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    onResume();
                }
            });
        }

    }

    private void autoRefreshEvents(boolean autoRefresh) {
        if (autoRefresh
                && mMatchObj.getmMatchStatus().equalsIgnoreCase(
                JsonConfigs.MATCH_STATUS_ACTIVE)) {
            // ONLY set timer when timer is null.
            timer = new Timer();
            RefreshEvents refresh = new RefreshEvents();
            try {
                timer.scheduleAtFixedRate(
                        refresh,
                        GlobalValue.prefs
                                .getIntValue(Args.KEY_UI_PROGRESS_VALUE) * 60 * 1000,
                        GlobalValue.prefs
                                .getIntValue(Args.KEY_UI_PROGRESS_VALUE) * 60 * 1000);
            } catch (IllegalArgumentException ex) {
                Log.e("Error refresh", ex.toString());
            }
        }
    }

    // Get current match info.
    private void getCurrentMatch() {
        ModelManager.getMatchById(getActivity(), mMatchId, true,
                new ModelManagerListener() {

                    @Override
                    public void onSuccess(Object object) {
                        // TODO Auto-generated method stub
                        String json = (String) object;

                        mMatchDetailObj = ParserUtils.parserMatchDetail(json);

                        initMatchData();
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    private void setLargeLogoClubs(String imageUrl, ImageView imgLogo) {
        try {
            mImgLoader.DisplayImage(imageUrl, imgLogo);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}
