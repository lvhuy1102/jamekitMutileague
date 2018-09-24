package mobi.letsplay.livescore.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.adapters.OddsAdapter;
import mobi.letsplay.livescore.configs.GlobalFunctions;
import mobi.letsplay.livescore.configs.WebservicesConfigs;
import mobi.letsplay.livescore.database.DatabaseUtility;
import mobi.letsplay.livescore.json.utils.ParserUtils;
import mobi.letsplay.livescore.modelmanager.ModelManager;
import mobi.letsplay.livescore.modelmanager.ModelManagerListener;
import mobi.letsplay.livescore.network.NetworkUtility;
import mobi.letsplay.livescore.objects.APIObj;
import mobi.letsplay.livescore.objects.OddObj;

import java.util.ArrayList;

import mobi.letsplay.livescore.adapters.OddsAdapter;
import mobi.letsplay.livescore.configs.GlobalFunctions;
import mobi.letsplay.livescore.configs.WebservicesConfigs;
import mobi.letsplay.livescore.database.DatabaseUtility;
import mobi.letsplay.livescore.json.utils.ParserUtils;
import mobi.letsplay.livescore.modelmanager.ModelManager;
import mobi.letsplay.livescore.modelmanager.ModelManagerListener;
import mobi.letsplay.livescore.network.NetworkUtility;
import mobi.letsplay.livescore.objects.APIObj;
import mobi.letsplay.livescore.objects.OddObj;

public class OddsFragment extends Fragment {

    private static final String KEY_MATCH_ID = "matchId";

    private View view;
    private ArrayList<OddObj> mOdds;
    private OddsAdapter mOddsAdapter;
    private ListView mLvOdds;

    private String mMatchId;

    private TextView mLblNoData;

    public static OddsFragment getInstance(String matchId) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MATCH_ID, matchId);

        OddsFragment oddsFragment = new OddsFragment();
        oddsFragment.setArguments(bundle);

        return oddsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get match id
        try {
            mMatchId = getArguments().getString(KEY_MATCH_ID);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        // Allow display option menu in this fragment.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(mobi.letsplay.livescore.R.layout.fragment_odds, container, false);

        initControls();
        initData();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(mobi.letsplay.livescore.R.menu.menu_main, menu);
        menu.findItem(mobi.letsplay.livescore.R.id.action_save).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == mobi.letsplay.livescore.R.id.action_refresh) {
            initData();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initControls() {
        mLvOdds = (ListView) view.findViewById(mobi.letsplay.livescore.R.id.lv_odds);
        mLblNoData = (TextView) view.findViewById(mobi.letsplay.livescore.R.id.lbl_no_data);

        // Init adapter
        if (mOdds == null) {
            mOdds = new ArrayList<>();
        } else {
            mOdds.clear();
        }
        mOddsAdapter = new OddsAdapter(getActivity(), mOdds);
        mLvOdds.setAdapter(mOddsAdapter);

        // Set listener
        mLvOdds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = mOdds.get(position).getUrl();

                GlobalFunctions.openBrowser(getActivity(), url);
            }
        });
    }

    private void initData() {
        if (NetworkUtility.getInstance(getActivity()).isNetworkAvailable()) {
            getOddsFromAPI();
        } else {
            getOddsFromDB();
        }
    }

    private void getOddsFromDB() {
        APIObj apiInfos = DatabaseUtility.getResuftFromApi(getActivity(),
                WebservicesConfigs.URL_GET_ODDS + mMatchId);
        if (apiInfos != null) {
            String json = apiInfos.getmResult();

            ArrayList<OddObj> arrTemp = ParserUtils.parseOdds(json);
            if (arrTemp.size() > 0) {
                mOdds.addAll(arrTemp);
                mOddsAdapter.notifyDataSetChanged();
            }
        }

        // Show/hide textview
        if (mOdds.size() > 0) {
            mLblNoData.setVisibility(View.GONE);
        } else {
            mLblNoData.setVisibility(View.VISIBLE);
        }
    }

    private void getOddsFromAPI() {
        ModelManager.getOddsByMatch(getActivity(), mMatchId,
                true, new ModelManagerListener() {

                    @Override
                    public void onSuccess(Object object) {
                        String json = (String) object;
                        ArrayList<OddObj> arrTemp = ParserUtils.parseOdds(json);
                        if (arrTemp.size() > 0) {
                            mOdds.addAll(arrTemp);
                            mOddsAdapter.notifyDataSetChanged();
                        }

                        // Show/hide textview
                        if (mOdds.size() > 0) {
                            mLblNoData.setVisibility(View.GONE);
                        } else {
                            mLblNoData.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError() {
                    }
                });
    }
}
