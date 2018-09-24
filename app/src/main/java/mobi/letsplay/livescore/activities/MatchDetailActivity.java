package mobi.letsplay.livescore.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
//import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
//import android.util.TypedValue;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.adapters.TabsMatchDetailAdapter;
import mobi.letsplay.livescore.configs.Constants;
import mobi.letsplay.livescore.configs.JsonConfigs;
//import com.hcpt.multileagues.fragments.LineupsFragment;
//import com.hcpt.multileagues.fragments.TimelineFragmentNew;
import mobi.letsplay.livescore.json.utils.ParserUtils;
import mobi.letsplay.livescore.modelmanager.ModelManager;
import mobi.letsplay.livescore.modelmanager.ModelManagerListener;
import mobi.letsplay.livescore.objects.MatchObj;
//import com.hcpt.multileagues.pagerslidingstabstrip.PagerSlidingTabStrip;
import mobi.letsplay.livescore.utilities.DateTimeUtility;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoRegular;

import mobi.letsplay.livescore.adapters.TabsMatchDetailAdapter;
import mobi.letsplay.livescore.configs.JsonConfigs;
import mobi.letsplay.livescore.json.utils.ParserUtils;
import mobi.letsplay.livescore.modelmanager.ModelManager;
import mobi.letsplay.livescore.modelmanager.ModelManagerListener;
import mobi.letsplay.livescore.objects.MatchObj;
import mobi.letsplay.livescore.utilities.DateTimeUtility;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoRegular;

public class MatchDetailActivity extends AppCompatActivity {

    public static MatchObj currentMatch;

    private TabLayout tabs;
    public ViewPager pager;
    private TabsMatchDetailAdapter adapter;
    private TextViewRobotoRegular txtDay, txtStatus, txtStadium, txtTeam1, txtTeam2;
    private TextView txtScoreTeam1, txtScoreTeam2, tvMinute, tvDate;
    private TextView tvHomePen, tvAwayPen;
    private ImageButton btnBack, btnRefresh;
    private ImageView ivStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mobi.letsplay.livescore.R.layout.activity_match_detail);

        // Enable Up button.
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(mobi.letsplay.livescore.R.string.dash_board);
            getSupportActionBar().hide();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        initViews();
        initData();
        initControl();
        // Admob
        MainActivity.initBannerAdsOnActivity(this, mobi.letsplay.livescore.R.id.ll_admob);
    }

    private void initViews() {
        txtScoreTeam1 = (TextView) findViewById(mobi.letsplay.livescore.R.id.txtScoreTeam1MatchDetail);
        txtScoreTeam2 = (TextView) findViewById(mobi.letsplay.livescore.R.id.txtScoreTeam2MatchDetail);
        txtTeam1 = (TextViewRobotoRegular) findViewById(mobi.letsplay.livescore.R.id.txtTeam1MatchDetail);
        txtTeam1.setSelected(true);
        txtTeam2 = (TextViewRobotoRegular) findViewById(mobi.letsplay.livescore.R.id.txtTeam2MatchDetail);
        txtTeam2.setSelected(true);
        txtStadium = (TextViewRobotoRegular) findViewById(mobi.letsplay.livescore.R.id.txtStadiumMatchDetail);
        tvMinute = (TextView) findViewById(mobi.letsplay.livescore.R.id.tv_minute);
        tvDate = (TextView) findViewById(mobi.letsplay.livescore.R.id.tv_date);
        btnBack = (ImageButton) findViewById(mobi.letsplay.livescore.R.id.bt_back);
        btnRefresh = (ImageButton) findViewById(mobi.letsplay.livescore.R.id.btnRefreshMatchDetail);
        ivStatus = (ImageView) findViewById(mobi.letsplay.livescore.R.id.iv_status);
        tvHomePen = (TextView) findViewById(mobi.letsplay.livescore.R.id.tv_home_pen);
        tvAwayPen = (TextView) findViewById(mobi.letsplay.livescore.R.id.tv_away_pen);
    }

    // Get current match info.
    private void getCurrentMatch(String mMatchId) {
        ModelManager.getMatchById(MatchDetailActivity.this, mMatchId, true,
                new ModelManagerListener() {

                    @Override
                    public void onSuccess(Object object) {
                        // TODO Auto-generated method stub
                        String json = (String) object;
                        currentMatch = ParserUtils.parserMatchDetail(json);
                        Constants.MATCH_STATUS = currentMatch.getmMatchStatus();
                        initDataMatch();
                        initTabs(currentMatch.getmMatchStatus());
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    // Get current match info.
    private void refresh(String mMatchId) {
        ModelManager.getMatchById(MatchDetailActivity.this, mMatchId, true,
                new ModelManagerListener() {

                    @Override
                    public void onSuccess(Object object) {
                        // TODO Auto-generated method stub
                        String json = (String) object;
                        currentMatch = ParserUtils.parserMatchDetail(json);
                        Constants.MATCH_STATUS = currentMatch.getmMatchStatus();
                        initDataMatch();
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    private void initData() {
        Intent intent = getIntent();
        String action = intent.getAction();
        if (intent != null && action != null) {
            Bundle b = intent.getBundleExtra(Constants.DATA);
            String matchId = b.getString(Constants.MATCH_ID);
            if (matchId != null) {
                currentMatch.setmMatchId(matchId);
                getCurrentMatch(matchId);
            } else {
                initDataMatch();
                initTabs(currentMatch.getmMatchStatus());
            }
        } else {
            initDataMatch();
            initTabs(currentMatch.getmMatchStatus());
        }
    }

    private void initDataMatch() {
        txtTeam1.setText(currentMatch.getmHomeName());
        txtTeam2.setText(currentMatch.getmAwayName());
        String homePen = "";
        String awayPen = "";
        if (currentMatch.getmMatchStatus().equals(JsonConfigs.MATCH_STATUS_FINISHED)) {
            if (currentMatch.getHomePen() != null && !currentMatch.getHomePen().trim().isEmpty()
                    && currentMatch.getHomePen().trim().length() != 0 && !currentMatch.getHomePen().equals("null")
                    && !currentMatch.getHomePen().trim().equals("")) {
                homePen = "(" + currentMatch.getHomePen() + ")";
            }
            if (currentMatch.getAwayPen() != null && !currentMatch.getAwayPen().trim().isEmpty()
                    && currentMatch.getAwayPen().trim().length() != 0 && !currentMatch.getAwayPen().equals("null")
                    && !currentMatch.getAwayPen().trim().equals("")) {
                awayPen = "(" + currentMatch.getAwayPen() + ")";
            }
        }
        if (currentMatch.getmMatchStatus().equals(JsonConfigs.MATCH_STATUS_NOT_STARTED)) {
            txtScoreTeam1.setText("?");
            txtScoreTeam2.setText("?");
            tvHomePen.setText(homePen);
            tvAwayPen.setText(awayPen);
        } else {
            txtScoreTeam1.setText(currentMatch.getmHomeScore());
            txtScoreTeam2.setText(currentMatch.getmAwayScore());
            tvHomePen.setText(homePen);
            tvAwayPen.setText(awayPen);
        }

        String status = currentMatch.getmMatchStatus();
        String strTime = "";
        String date = DateTimeUtility.convertTimeStampToDate(currentMatch.getmTime(), "dd MMM");
        tvDate.setText(date);
        if (currentMatch.getmMinute().trim().equalsIgnoreCase(Constants.POSTPONDED)) {
            strTime = Constants.POSTPONDED;
        } else if (currentMatch.getmMinute().trim().equalsIgnoreCase(Constants.CANCELLED)) {
            strTime = Constants.CANCELLED;
        } else if (currentMatch.getmMinute().trim().equalsIgnoreCase(Constants.ABANDONED)) {
            strTime = Constants.ABANDONED;
        } else {
            if (status.equals(JsonConfigs.MATCH_STATUS_NOT_STARTED)) {
                strTime = DateTimeUtility.convertTimeStampToDate(currentMatch.getmTime(), "HH:mm");
                ivStatus.setImageResource(mobi.letsplay.livescore.R.drawable.bg_gray_circle);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvMinute.setTextColor(getColor(mobi.letsplay.livescore.R.color.textColorPrimary));
                    tvDate.setTextColor(getColor(mobi.letsplay.livescore.R.color.textColorPrimary));
                } else {
                    tvMinute.setTextColor(ContextCompat.getColor(this, mobi.letsplay.livescore.R.color.textColorPrimary));
                    tvDate.setTextColor(ContextCompat.getColor(this, mobi.letsplay.livescore.R.color.textColorPrimary));
                }
            } else if (status.equals(JsonConfigs.MATCH_STATUS_ACTIVE)) {
                strTime = getResources().getString(mobi.letsplay.livescore.R.string.live) + ", " + currentMatch.getmMinute() + "'";
                ivStatus.setImageResource(mobi.letsplay.livescore.R.drawable.bg_green_circle);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvMinute.setTextColor(getColor(mobi.letsplay.livescore.R.color.white));
                    tvDate.setTextColor(getColor(mobi.letsplay.livescore.R.color.white));
                } else {
                    tvMinute.setTextColor(ContextCompat.getColor(this, mobi.letsplay.livescore.R.color.white));
                    tvDate.setTextColor(ContextCompat.getColor(this, mobi.letsplay.livescore.R.color.white));
                }
            } else if (status.equals(JsonConfigs.MATCH_STATUS_FINISHED)) {
                strTime = getResources().getString(mobi.letsplay.livescore.R.string.finish);
                ivStatus.setImageResource(mobi.letsplay.livescore.R.drawable.bg_gray_circle);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tvMinute.setTextColor(getColor(mobi.letsplay.livescore.R.color.textColorPrimary));
                    tvDate.setTextColor(getColor(mobi.letsplay.livescore.R.color.textColorPrimary));
                } else {
                    tvMinute.setTextColor(ContextCompat.getColor(this, mobi.letsplay.livescore.R.color.textColorPrimary));
                    tvDate.setTextColor(ContextCompat.getColor(this, mobi.letsplay.livescore.R.color.textColorPrimary));
                }
            }
        }

        tvMinute.setText(strTime);
        txtStadium.setText(currentMatch.getmStadium());
    }

    private void initControl() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.IS_PUSH) {
                    if (Constants.IS_APP_RUNNING) {
                        Constants.IS_PUSH = false;
                        finish();
                    } else {
                        Intent i = new Intent(MatchDetailActivity.this, MainActivity.class);
                        startActivity(i);
                        Constants.IS_PUSH = false;
                        finish();
                    }
                } else {
                    finish();
                }
                overridePendingTransition(mobi.letsplay.livescore.R.anim.slide_in_right, mobi.letsplay.livescore.R.anim.slide_out_right);
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh(currentMatch.getmMatchId());
//                getCurrentMatch(currentMatch.getmMatchId());
                Intent intent = new Intent();
                intent.setAction(Constants.REFRESH_DATA);
                sendBroadcast(intent);
            }
        });
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // Hide menus.
//        MenuItem item = menu.findItem(R.id.action_filter);
//        item.setVisible(false);
//
//        return super.onPrepareOptionsMenu(menu);
//    }

    @Override
    public void onBackPressed() {
        if (Constants.IS_PUSH) {
            if (Constants.IS_APP_RUNNING) {
                Constants.IS_PUSH = false;
                super.onBackPressed();
            } else {
                Intent i = new Intent(MatchDetailActivity.this, MainActivity.class);
                startActivity(i);
                Constants.IS_PUSH = false;
                super.onBackPressed();
            }
        } else {

            super.onBackPressed();
        }
        overridePendingTransition(mobi.letsplay.livescore.R.anim.slide_in_right, mobi.letsplay.livescore.R.anim.slide_out_right);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    // Create tabs.
    private void initTabs(String matchStatus) {
        tabs = (TabLayout) findViewById(mobi.letsplay.livescore.R.id.tabs);
        pager = (ViewPager) findViewById(mobi.letsplay.livescore.R.id.pager);
        FragmentManager manager = getSupportFragmentManager();
        adapter = new TabsMatchDetailAdapter(manager, this, currentMatch.getmMatchId(), matchStatus);
        pager.setOffscreenPageLimit(2);
//        if (pager.getAdapter() == null) {
            pager.setAdapter(null);
            pager.setAdapter(adapter);
            tabs.setupWithViewPager(pager);
            final int pageMargin = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
            pager.setPageMargin(pageMargin);
            pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
            tabs.setTabsFromPagerAdapter(adapter);
//        } else {
//            pager.getAdapter().notifyDataSetChanged();
//        }
    }
}