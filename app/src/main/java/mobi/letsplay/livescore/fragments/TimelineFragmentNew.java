package mobi.letsplay.livescore.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.activities.MatchDetailActivity;
import mobi.letsplay.livescore.adapters.TimelineAdapter;
import mobi.letsplay.livescore.configs.Constants;
import mobi.letsplay.livescore.json.utils.ParserUtils;
import mobi.letsplay.livescore.modelmanager.ModelManager;
import mobi.letsplay.livescore.modelmanager.ModelManagerListener;
import mobi.letsplay.livescore.objects.MatchObj;
import mobi.letsplay.livescore.objects.Timeline;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoRegular;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mobi.letsplay.livescore.json.utils.ParserUtils;
import mobi.letsplay.livescore.modelmanager.ModelManager;
import mobi.letsplay.livescore.modelmanager.ModelManagerListener;
import mobi.letsplay.livescore.objects.MatchObj;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoRegular;

public class TimelineFragmentNew extends Fragment {
    private Context mContext;
    private RecyclerView rcvTimeline;
    private ArrayList<Timeline> arrTimeline = new ArrayList<Timeline>();
    private TimelineAdapter timelineAdapter;
    private TextViewRobotoRegular txtNoData;
    private String matchId;
    private String idTeamA, idTeamB;

    private MatchObj mMatchObj;
    private MatchObj mMatchDetailObj;
    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    public TimelineFragmentNew() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(mobi.letsplay.livescore.R.layout.frm_timeline, container, false);
        initUI(v);
        mContext = getActivity();
        mMatchObj = MatchDetailActivity.currentMatch;
        matchId = MatchDetailActivity.currentMatch.getmMatchId();
        idTeamA = MatchDetailActivity.currentMatch.getmHome();
        idTeamB = MatchDetailActivity.currentMatch.getmAway();
        if (matchId != null && idTeamA != null && idTeamB != null) {
            initData();
        }
        mListener();
        return v;
    }

    private void initUI(View view) {

        rcvTimeline = (RecyclerView) view.findViewById(mobi.letsplay.livescore.R.id.rcvTimeline);
        txtNoData = (TextViewRobotoRegular) view.findViewById(mobi.letsplay.livescore.R.id.txtNotDataTimeline);
    }

    private void initData() {
        timelineAdapter = new TimelineAdapter(mContext, arrTimeline, idTeamA, idTeamB);
        rcvTimeline.setAdapter(timelineAdapter);
        rcvTimeline.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshData();
    }

    private void mListener() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Constants.REFRESH_DATA)) {
                    refreshData();
                }
            }
        };
        filter = new IntentFilter();
        filter.addAction(Constants.REFRESH_DATA);
        mContext.registerReceiver(broadcastReceiver, filter);
    }

    public void refreshData() {
        ModelManager.getEventsByMatch(mContext, matchId, true, new ModelManagerListener() {
            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(Object object) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject((String) object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                arrTimeline.clear();
                arrTimeline.addAll(ParserUtils.parserTimelines(jsonObject));
                timelineAdapter.notifyDataSetChanged();
                checkNoData();
            }
        });

    }

    private void checkNoData() {

        if (arrTimeline.size() == 0) {
            txtNoData.setVisibility(View.VISIBLE);
        } else {
            txtNoData.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (broadcastReceiver != null)
            mContext.unregisterReceiver(broadcastReceiver);
    }
}
