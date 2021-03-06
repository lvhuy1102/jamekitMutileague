package mobi.letsplay.livescore.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.adapters.TabsMatchDetailAdapter;
import mobi.letsplay.livescore.adapters.TabsMatchDetailNotPlayedAdapter;
import mobi.letsplay.livescore.configs.FruitySharedPreferences;
import mobi.letsplay.livescore.configs.GlobalFunctions;
import mobi.letsplay.livescore.objects.MatchObj;
import mobi.letsplay.livescore.pagerslidingstabstrip.PagerSlidingTabStrip;

import mobi.letsplay.livescore.objects.MatchObj;

public class MatchDetailNotPlayed extends AppCompatActivity {

    private MatchDetailNotPlayed self;
    private TabLayout tabs;
    public ViewPager pager;
    private TabsMatchDetailNotPlayedAdapter adapter;

    public static MatchObj currentMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail_not_played);
        self = this;

        try {
            // Enable Up button.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Set title
            getSupportActionBar().setTitle(R.string.dash_board);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        initTabs();
        // Admob
        MainActivity.initBannerAdsOnActivity(this, R.id.ll_admob);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Hide menus.
        MenuItem item = menu.findItem(R.id.action_filter);
        item.setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            if (GlobalFunctions.checkMatchTime(currentMatch) >= 0) {
                startActivity(new Intent(self, MatchDetailActivity.class));
                finish();
            }
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    // Create tabs.
    private void initTabs() {
        tabs = (TabLayout) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new TabsMatchDetailNotPlayedAdapter(getSupportFragmentManager(), this, currentMatch.getmMatchId());
        pager.setOffscreenPageLimit(2);
        pager.setAdapter(adapter);

        final int pageMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        pager.setPageMargin(pageMargin);

        tabs.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.setTabsFromPagerAdapter(adapter);
    }
}
