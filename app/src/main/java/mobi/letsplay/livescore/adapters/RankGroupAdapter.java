package mobi.letsplay.livescore.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.activities.ClubDetailActivity;
import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.RankClubsObj;

import java.util.ArrayList;

import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.RankClubsObj;

/**
 * Created by NaPro on 12/27/2015.
 */
public class RankGroupAdapter extends RecyclerView.Adapter<RankGroupAdapter.ViewHolder> {

    private ArrayList<RankClubsObj> mArrRankClubs;
    private ImageLoader mImageLoader;
    private Activity mActivity;

    public RankGroupAdapter(Activity activity,
                            ArrayList<RankClubsObj> arrRankClubs) {
        mActivity = activity;
        this.mArrRankClubs = arrRankClubs;
        mImageLoader = new ImageLoader(activity.getApplicationContext());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mobi.letsplay.livescore.R.layout.item_rank_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (getItemCount() > 0) {
            RankClubsObj clubsObj = mArrRankClubs.get(position);
            if (clubsObj != null) {
                holder.lblRank.setText(clubsObj.getPosition());
                holder.lblNameClubs.setText(clubsObj.getmNameClubs());
//                mImageLoader.DisplayImage(clubsObj.getmLogo(), holder.imgLogo);
                holder.lblP.setText(clubsObj.getmP());
                holder.lblW.setText(clubsObj.getmW());
                holder.lblD.setText(clubsObj.getmD());
                holder.lblL.setText(clubsObj.getmL());
                holder.lblGD.setText(clubsObj.getmGD());
                holder.lblPTS.setText(clubsObj.getmPTS());

                // Click on club
                final int pos = position;
                holder.llRank.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        ClubDetailActivity.currentClub = mArrRankClubs.get(pos);
//                        mActivity.startActivity(new Intent(mActivity, ClubDetailActivity.class));
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        try {
            return mArrRankClubs.size();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblRank, lblNameClubs, lblP, lblW, lblD, lblL, lblGD,
                lblPTS;
        private ImageView imgLogo;
        private LinearLayout llRank;

        public ViewHolder(View view) {
            super(view);

            llRank = (LinearLayout) view.findViewById(mobi.letsplay.livescore.R.id.ll_rank_club);
            lblRank = (TextView) view.findViewById(mobi.letsplay.livescore.R.id.lbl_rank_clubs);
//            imgLogo = (ImageView) view.findViewById(R.id.img_logoClub);
            lblNameClubs = (TextView) view.findViewById(mobi.letsplay.livescore.R.id.lbl_name_clubs);
            lblNameClubs.setSelected(true);
            lblP = (TextView) view.findViewById(mobi.letsplay.livescore.R.id.lbl_value_p);
            lblW = (TextView) view.findViewById(mobi.letsplay.livescore.R.id.lbl_value_w);
            lblD = (TextView) view.findViewById(mobi.letsplay.livescore.R.id.lbl_value_d);
            lblL = (TextView) view.findViewById(mobi.letsplay.livescore.R.id.lbl_value_l);
            lblGD = (TextView) view.findViewById(mobi.letsplay.livescore.R.id.lbl_value_gd);
            lblPTS = (TextView) view.findViewById(mobi.letsplay.livescore.R.id.lbl_value_pts);
        }
    }
}
