package mobi.letsplay.livescore.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.RankClubsObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.RankClubsObj;

public class RankClubsAdapter extends BaseAdapter {

    private ArrayList<RankClubsObj> mArrRankClubs;
    private RankClubsObj mRankClubs;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private int mRank = 0;
    private Activity mActivity;

    public RankClubsAdapter(Activity activity,
                            ArrayList<RankClubsObj> arrRankClubs) {
        mActivity = activity;
        this.mArrRankClubs = arrRankClubs;
        mImageLoader = new ImageLoader(activity.getApplicationContext());
        mInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (mArrRankClubs != null) {
            return mArrRankClubs.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mArrRankClubs != null && mArrRankClubs.size() > 0) {
            return mArrRankClubs.get(position);
        } else {
            return null;
        }
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
            convertView = mInflater.inflate(R.layout.item_rank_clubs, null);
            holder.llRank = (LinearLayout) convertView
                    .findViewById(R.id.ll_rank_club);
            holder.lblRank = (TextView) convertView
                    .findViewById(R.id.lbl_rank_clubs);
//            holder.imgLogo = (ImageView) convertView
//                    .findViewById(R.id.img_logoClub);
            holder.lblNameClubs = (TextView) convertView
                    .findViewById(R.id.lbl_name_clubs);
            holder.lblNameClubs.setSelected(true);
            holder.lblP = (TextView) convertView.findViewById(R.id.lbl_value_p);
            holder.lblW = (TextView) convertView.findViewById(R.id.lbl_value_w);
            holder.lblD = (TextView) convertView.findViewById(R.id.lbl_value_d);
            holder.lblL = (TextView) convertView.findViewById(R.id.lbl_value_l);
            holder.lblGD = (TextView) convertView
                    .findViewById(R.id.lbl_value_gd);
            holder.lblPTS = (TextView) convertView
                    .findViewById(R.id.lbl_value_pts);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        // Sort list rank clubs by PTS, GD and W.
        Collections.sort(mArrRankClubs, new Comparator<RankClubsObj>() {
            @Override
            public int compare(RankClubsObj lhs, RankClubsObj rhs) {
                Integer pts1 = Integer.valueOf(lhs.getmPTS());
                Integer pts2 = Integer.valueOf(rhs.getmPTS());
                Integer gd1 = Integer.valueOf(lhs.getmGD());
                Integer gd2 = Integer.valueOf(rhs.getmGD());
                Integer w1 = Integer.valueOf(lhs.getmW());
                Integer w2 = Integer.valueOf(rhs.getmW());

                if (pts2.compareTo(pts1) != 0) {
                    return pts2.compareTo(pts1);
                } else if (gd2.compareTo(gd1) != 0) {
                    return gd2.compareTo(gd1);
                }
                return w2.compareTo(w1);
            }
        });

        mRankClubs = mArrRankClubs.get(position);
        mRank = position + 1;
        if (mRankClubs != null && mArrRankClubs.size()>=4) {
            // Set background and text color for top 4
            if (mRank == 1 || mRank == 2 || mRank == 3 || mRank == 4) {
                holder.lblRank.setTextColor(mActivity.getResources().getColor(R.color.yeallow_color_huy));
                holder.lblNameClubs.setTextColor(mActivity.getResources().getColor(R.color.yeallow_color_huy));
//            mImageLoader.DisplayImage(mRankClubs.getmLogo(), holder.imgLogo);
                holder.lblP.setTextColor(mActivity.getResources().getColor(R.color.blue));
                holder.lblW.setTextColor(mActivity.getResources().getColor(R.color.blue));
                holder.lblD.setTextColor(mActivity.getResources().getColor(R.color.blue));
                holder.lblL.setTextColor(mActivity.getResources().getColor(R.color.blue));
                holder.lblGD.setTextColor(mActivity.getResources().getColor(R.color.blue));
                holder.lblPTS.setTextColor(mActivity.getResources().getColor(R.color.blue));
            } else {
                holder.lblRank.setTextColor(mActivity.getResources().getColor(R.color.textColorSecondary));
                holder.lblNameClubs.setTextColor(mActivity.getResources().getColor(R.color.textColorSecondary));
//            mImageLoader.DisplayImage(mRankClubs.getmLogo(), holder.imgLogo);
                holder.lblP.setTextColor(mActivity.getResources().getColor(R.color.textColorSecondary));
                holder.lblW.setTextColor(mActivity.getResources().getColor(R.color.textColorSecondary));
                holder.lblD.setTextColor(mActivity.getResources().getColor(R.color.textColorSecondary));
                holder.lblL.setTextColor(mActivity.getResources().getColor(R.color.textColorSecondary));
                holder.lblGD.setTextColor(mActivity.getResources().getColor(R.color.textColorSecondary));
                holder.lblPTS.setTextColor(mActivity.getResources().getColor(R.color.textColorSecondary));
            }
            holder.lblRank.setText(mRank + "");
            holder.lblNameClubs.setText(mRankClubs.getmNameClubs());
//            mImageLoader.DisplayImage(mRankClubs.getmLogo(), holder.imgLogo);
            holder.lblP.setText(mRankClubs.getmP());
            holder.lblW.setText(mRankClubs.getmW());
            holder.lblD.setText(mRankClubs.getmD());
            holder.lblL.setText(mRankClubs.getmL());
            holder.lblGD.setText(mRankClubs.getmGD());
            holder.lblPTS.setText(mRankClubs.getmPTS());
        }

        return convertView;
    }

    class Holder {
        private TextView lblRank, lblNameClubs, lblP, lblW, lblD, lblL, lblGD,
                lblPTS;
        private ImageView imgLogo;
        private LinearLayout llRank;
    }
}
