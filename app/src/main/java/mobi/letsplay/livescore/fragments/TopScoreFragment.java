package mobi.letsplay.livescore.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.adapters.TopScoreAdapter;
import mobi.letsplay.livescore.adapters.TopScorerAdapterNew;
import mobi.letsplay.livescore.configs.GlobalValue;
import mobi.letsplay.livescore.configs.WebservicesConfigs;
import mobi.letsplay.livescore.database.DatabaseUtility;
import mobi.letsplay.livescore.json.utils.ParserUtils;
import mobi.letsplay.livescore.modelmanager.ModelManager;
import mobi.letsplay.livescore.modelmanager.ModelManagerListener;
import mobi.letsplay.livescore.network.NetworkUtility;
import mobi.letsplay.livescore.objects.APIObj;
import mobi.letsplay.livescore.objects.TopScorer;
import mobi.letsplay.livescore.objects.TopScorerObj;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoRegular;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mobi.letsplay.livescore.json.utils.ParserUtils;
import mobi.letsplay.livescore.network.NetworkUtility;

public class TopScoreFragment extends Fragment {

    private View view;
    private TopScorerAdapterNew mTopScorerAdapter;
    private ArrayList<TopScorerObj> mArrTopScore;
    private RecyclerView rcvTopScorer;
    private TextViewRobotoRegular txtNotDataTopScorer;

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
        view = inflater.inflate(R.layout.fragment_top_score_new, container, false);
        initControls();
        initTopScorer();

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
            initTopScorer();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initControls() {
        rcvTopScorer = (RecyclerView) view.findViewById(R.id.rcvTopScorer);
        rcvTopScorer.setHasFixedSize(true);
        rcvTopScorer.setLayoutManager(new LinearLayoutManager(getActivity()));


        txtNotDataTopScorer = (TextViewRobotoRegular) view.findViewById(R.id.txtNoDataTopScorer);
    }

    private void initTopScorer() {
        if (NetworkUtility.getInstance(getActivity()).isNetworkAvailable()) {
            getTopScorerFromAPI();
        } else {
            getTopScorerFromDB();
        }
    }

    private void setAdapter() {
        // Bind data for listview.
        if (mArrTopScore != null && mArrTopScore.size() > 0) {
            mTopScorerAdapter = new TopScorerAdapterNew(getActivity(), mArrTopScore);
            rcvTopScorer.setAdapter(mTopScorerAdapter);

            txtNotDataTopScorer.setVisibility(View.GONE);
        } else {
            txtNotDataTopScorer.setVisibility(View.VISIBLE);
        }
    }

    private void getTopScorerFromDB() {
        APIObj apiInfos = DatabaseUtility.getResuftFromApi(getActivity(),
                WebservicesConfigs.URL_GET_TOP_SCORER + GlobalValue.leagueId);
        if (apiInfos != null) {
            String json = apiInfos.getmResult();

            mArrTopScore = ParserUtils.parserTopScorer(json);
            setAdapter();
        }
    }

    private void getTopScorerFromAPI() {
        ModelManager.getTopScorer(getActivity(), false,
                new ModelManagerListener() {

                    @Override
                    public void onSuccess(Object object) {
                        String json = (String) object;
                        mArrTopScore = ParserUtils.parserTopScorer(json);
                        setAdapter();
                    }

                    @Override
                    public void onError() {
                        // TODO Auto-generated method stub
                    }
                });
    }
}
