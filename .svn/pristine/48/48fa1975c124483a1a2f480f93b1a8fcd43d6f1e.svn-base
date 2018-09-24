package com.hcpt.multileagues.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.adapters.RankGroupAdapter;
import com.hcpt.multileagues.adapters.SimpleSectionedRecyclerViewAdapter;
import com.hcpt.multileagues.configs.GlobalValue;
import com.hcpt.multileagues.configs.WebservicesConfigs;
import com.hcpt.multileagues.database.DatabaseUtility;
import com.hcpt.multileagues.json.utils.ParserUtils;
import com.hcpt.multileagues.modelmanager.ModelManager;
import com.hcpt.multileagues.modelmanager.ModelManagerListener;
import com.hcpt.multileagues.network.NetworkUtility;
import com.hcpt.multileagues.objects.APIObj;
import com.hcpt.multileagues.objects.RankClubsObj;

import java.util.ArrayList;
import java.util.List;

public class RankCLFragment extends Fragment {

    private static final String TAG = RankCLFragment.class.getSimpleName();

    private View view;

    private RecyclerView mRclRank;
    private RankGroupAdapter mRankGroupAdapter;
    private ArrayList<RankClubsObj> mArrRank;
    private List<SimpleSectionedRecyclerViewAdapter.Section> mSections;
    private SimpleSectionedRecyclerViewAdapter mSectionedAdapter;
    private SimpleSectionedRecyclerViewAdapter.Section[] dummy;
    private TextView lbl_no_data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Allow display option menu in this fragment.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_rank_group, container, false);

        initView();
        initControls();
        initRankClubs();

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        super.onPrepareOptionsMenu(menu);

        // Hide filter menu.
        MenuItem item = menu.findItem(R.id.action_filter);
        item.setVisible(false);
        menu.findItem(R.id.action_save).setVisible(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            initRankClubs();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initView() {
        mRclRank = (RecyclerView) view.findViewById(R.id.rcl_rank);
        mRclRank.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        lbl_no_data = (TextView) view.findViewById(R.id.lbl_no_data);

        // Init adapter
        if (mArrRank == null) {
            mArrRank = new ArrayList<>();
        } else {
            mArrRank.clear();
        }
        mRankGroupAdapter = new RankGroupAdapter(getActivity(), mArrRank);

        //Add your adapter to the sectionAdapter
        if (mSections == null) {
            mSections = new ArrayList<>();
        } else {
            mSections.clear();
        }

        dummy = new SimpleSectionedRecyclerViewAdapter.Section[mSections.size()];
        mSectionedAdapter = new SimpleSectionedRecyclerViewAdapter(getActivity(), R.layout.layout_rank_header,
                R.id.lbl_section, mRankGroupAdapter);

        //Apply this adapter to the RecyclerView
        mRclRank.setAdapter(mSectionedAdapter);
    }

    private void initControls() {
    }

    public void initRankClubs() {
        // Bind data for listview.
        if (NetworkUtility.getInstance(getActivity()).isNetworkAvailable()) {
            getRankFromAPI();
        } else {
            getRankFromDB();
        }

    }

    private void setAdapter() {
        if (mSections == null) {
            mSections = new ArrayList<>();
        } else {
            mSections.clear();
        }

        if (mArrRank == null) {
            mArrRank = new ArrayList<>();
        } else {
            mArrRank.clear();
        }
        if (GlobalValue.arrRankClubs != null && GlobalValue.arrRankClubs.size() > 0) {
            for (int i = 0; i < GlobalValue.arrRankClubs.size(); i++) {
                if (!("539").equals(GlobalValue.arrRankClubs.get(i).getmId())) {
                    mArrRank.add(GlobalValue.arrRankClubs.get(i));
                }
            }
        }
        String header = "";
        for (int i = 0; i < mArrRank.size(); i++) {
            if (!header.equalsIgnoreCase(mArrRank.get(i).getGroup())) {
                header = mArrRank.get(i).getGroup();
                mSections.add(new SimpleSectionedRecyclerViewAdapter.Section(i, header));
            }
        }
        mSectionedAdapter.setSections(mSections.toArray(dummy));

        mSectionedAdapter.notifyDataSetChanged();
    }

    private void getRankFromDB() {
        APIObj apiInfos = DatabaseUtility.getResuftFromApi(getActivity(),
                WebservicesConfigs.URL_GET_RANK_CLUBS + GlobalValue.leagueId);
        if (apiInfos != null) {
            String json = apiInfos.getmResult();
            GlobalValue.arrRankClubs = ParserUtils.parserRankClubs(json);
            setAdapter();
            if (GlobalValue.arrRankClubs.size() == 0) {
                lbl_no_data.setVisibility(View.VISIBLE);
                mRclRank.setVisibility(View.GONE);
            } else {
                lbl_no_data.setVisibility(View.GONE);
                mRclRank.setVisibility(View.VISIBLE);
            }
        }
    }

    private void getRankFromAPI() {
        ModelManager.getGroupStanding(getActivity(), true, "", new ModelManagerListener() {

            @Override
            public void onSuccess(Object object) {
                String json = (String) object;
                GlobalValue.arrRankClubs = ParserUtils.parserRankGroup(json);
                setAdapter();
                if (GlobalValue.arrRankClubs.size() == 0) {
                    lbl_no_data.setVisibility(View.VISIBLE);
                    mRclRank.setVisibility(View.GONE);
                } else {
                    lbl_no_data.setVisibility(View.GONE);
                    mRclRank.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError() {
            }
        });
    }
}
