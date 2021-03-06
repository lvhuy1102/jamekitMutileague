package mobi.letsplay.livescore.activities;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.RankClubsObj;

import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.RankClubsObj;

public class ClubDetailActivity extends AppCompatActivity {

    public static RankClubsObj currentClub;
    private ImageView mLogo;
    private TextView mLblPlayed, mLblGd, mLblPoint, mLblWin, mLblDraw,
            mLblLose, mLblEstablished, mLblManager, mLblNickName, mLblStadium;
    private Button mBtnViewDetail;
    private String url = "";
    private ImageLoader mImageLoader;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mobi.letsplay.livescore.R.layout.activity_club_detail);

        mImageLoader = new ImageLoader(getBaseContext());

        try {
            // Set title(Club's name)
            getSupportActionBar().setTitle(currentClub.getmNameClubs());

            // Show as up button.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        initControls();
        initData();

        // Admob
        MainActivity.initBannerAdsOnActivity(this, mobi.letsplay.livescore.R.id.ll_admob);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initControls() {
//        mLogo = (ImageView) findViewById(R.id.img_club_detail);
        mLblDraw = (TextView) findViewById(mobi.letsplay.livescore.R.id.lbl_value_draw);
        mLblGd = (TextView) findViewById(mobi.letsplay.livescore.R.id.lbl_value_goal_difference);
        mLblLose = (TextView) findViewById(mobi.letsplay.livescore.R.id.lbl_value_lose);
        mLblPlayed = (TextView) findViewById(mobi.letsplay.livescore.R.id.lbl_value_played);
        mLblPoint = (TextView) findViewById(mobi.letsplay.livescore.R.id.lbl_value_point);
        mLblWin = (TextView) findViewById(mobi.letsplay.livescore.R.id.lbl_value_win);
        mLblEstablished = (TextView) findViewById(mobi.letsplay.livescore.R.id.lbl_established);
        mLblManager = (TextView) findViewById(mobi.letsplay.livescore.R.id.lbl_manager);
        mLblNickName = (TextView) findViewById(mobi.letsplay.livescore.R.id.lbl_nick_name);
        mLblStadium = (TextView) findViewById(mobi.letsplay.livescore.R.id.lbl_stadium);
        mBtnViewDetail = (Button) findViewById(mobi.letsplay.livescore.R.id.btn_view_club_detail);

        mBtnViewDetail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ClubDetailActivity.this,
                            "The page is not available!", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void initData() {
//        mImageLoader.DisplayImage(currentClub.getmLogo(), mLogo);
        mLblDraw.setText(currentClub.getmD());
        mLblGd.setText(currentClub.getmGD());
        mLblLose.setText(currentClub.getmL());
        mLblPlayed.setText(currentClub.getmP());
        mLblPoint.setText(currentClub.getmPTS());
        mLblWin.setText(currentClub.getmW());




        if(!currentClub.getEstablished().isEmpty() && !currentClub.getEstablished().equals("n/a")){
            mLblEstablished.setText(String.format(getString(mobi.letsplay.livescore.R.string.text_established), currentClub.getEstablished()));
        } else {
            mLblEstablished.setVisibility(View.GONE);
        }

        if(!currentClub.getManager().isEmpty() && !currentClub.getManager().equals("n/a")){
            mLblManager.setText(String.format(getString(mobi.letsplay.livescore.R.string.text_manager), currentClub.getManager()));
        } else {
            mLblManager.setVisibility(View.GONE);
        }

        if(!currentClub.getNickName().isEmpty() && !currentClub.getNickName().equals("n/a")){
            mLblNickName.setText(String.format(getString(mobi.letsplay.livescore.R.string.text_nickname), currentClub.getNickName()));
        } else {
            mLblNickName.setVisibility(View.GONE);
        }
        if(!currentClub.getStadium().isEmpty() && !currentClub.getStadium().equals("n/a")){
            mLblStadium.setText(String.format(getString(mobi.letsplay.livescore.R.string.text_stadium), currentClub.getStadium()));
        } else {
            mLblStadium.setVisibility(View.GONE);
        }


        url = currentClub.getmClubUrl();
    }
}
