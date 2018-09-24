package mobi.letsplay.livescore.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import mobi.letsplay.livescore.R;

public class AboutUsActivity extends AppCompatActivity {

    private TextView mLblHomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mobi.letsplay.livescore.R.layout.activity_about_us);

        // Show as up button
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(mobi.letsplay.livescore.R.string.text_btn_about);
        } catch(NullPointerException ex){
            ex.printStackTrace();
        }

        // Go to home page
        mLblHomePage = (TextView) findViewById(mobi.letsplay.livescore.R.id.lblHomePage);

        mLblHomePage.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getResources().getString(
                        mobi.letsplay.livescore.R.string.about_link));
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.about_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finishPage();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finishPage();
    }

    private void finishPage() {
        // TODO Auto-generated method stub
        finish();
        overridePendingTransition(mobi.letsplay.livescore.R.anim.slide_in_right, mobi.letsplay.livescore.R.anim.slide_out_right);
    }
}
