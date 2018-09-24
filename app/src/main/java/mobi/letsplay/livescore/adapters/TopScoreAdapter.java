package mobi.letsplay.livescore.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.TopScorerObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mobi.letsplay.livescore.image.utils.ImageLoader;

public class TopScoreAdapter extends BaseAdapter {

    private ArrayList<TopScorerObj> mArrTopScore;
    private LayoutInflater mInflater;
    private ImageLoader mImgLoader;
    private int mRank = 0;
    private Activity mActivity;

    public TopScoreAdapter(Activity activity,
                           ArrayList<TopScorerObj> arrTopScore) {
        mActivity = activity;
        this.mArrTopScore = arrTopScore;
        mInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mImgLoader = new ImageLoader(activity);
    }

    @Override
    public int getCount() {
        return mArrTopScore.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrTopScore.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = mInflater.inflate(mobi.letsplay.livescore.R.layout.item_top_score, null);
            holder.tblTopScorer = (TableLayout) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.tbl_top_scorer);
            holder.lblRank = (TextView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_rank_player);
            holder.lblPlayer = (TextView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_player_name);
            holder.lblScore = (TextView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_scores);
            holder.lblPlayer.setSelected(true);
//            holder.imgLogo = (ImageView) convertView
//                    .findViewById(R.id.img_logo);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        // Sorted top scorers order score by desc.
        Collections.sort(mArrTopScore, new Comparator<TopScorerObj>() {

            @Override
            public int compare(TopScorerObj lhs, TopScorerObj rhs) {
                Integer i = Integer.valueOf(rhs.getmScore());
                Integer j = Integer.valueOf(lhs.getmScore());
                return i.compareTo(j);
            }
        });

        TopScorerObj player = mArrTopScore.get(position);
        mRank = position + 1;
        if (player != null) {
            // Set background and text color for top 4
            if (mRank == 1 || mRank == 2 || mRank == 3) {
//                holder.tblTopScorer.setBackgroundColor(mActivity.getResources()
//                        .getColor(R.color.background_top_4));
                holder.lblRank.setTextColor(mActivity.getResources().getColor(
                        mobi.letsplay.livescore.R.color.yeallow_color_huy));
                holder.lblPlayer.setTextColor(mActivity.getResources().getColor(
                        mobi.letsplay.livescore.R.color.yeallow_color_huy));
                holder.lblScore.setTextColor(mActivity.getResources().getColor(
                        mobi.letsplay.livescore.R.color.yeallow_color_huy));
            } else {
                holder.tblTopScorer.setBackgroundColor(mActivity.getResources()
                        .getColor(mobi.letsplay.livescore.R.color.background_spiner));
                holder.lblRank.setTextColor(mActivity.getResources().getColor(
                        mobi.letsplay.livescore.R.color.textColorSecondary));
                holder.lblPlayer.setTextColor(mActivity.getResources().getColor(
                        mobi.letsplay.livescore.R.color.textColorSecondary));
                holder.lblScore.setTextColor(mActivity.getResources().getColor(
                        mobi.letsplay.livescore.R.color.textColorSecondary));
            }

            holder.lblRank.setText(mRank + "");
            holder.lblPlayer.setText(player.getmPlayer());
            holder.lblScore.setText(player.getmScore());

            // Set logo.
//            try {
//                mImgLoader.DisplayImage(player.getmClub(), holder.imgLogo);
//            } catch (NullPointerException ex) {
//                ex.printStackTrace();
//            }
        }

        return convertView;
    }

    class Holder {
        private TextView lblRank, lblPlayer, lblScore;
        private ImageView imgLogo;
        private TableLayout tblTopScorer;
    }
}
