package com.hcpt.multileagues.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.objects.FavoriteObj;

import java.util.ArrayList;

/**
 * Created by NaPro on 12/27/2015.
 */
public class FavTeamAdapter extends RecyclerView.Adapter<FavTeamAdapter.ViewHolder> {

    private ArrayList<FavoriteObj> mArrFavTeam;
    private Activity mActivity;
    private CheckBox mChkAll;

    public FavTeamAdapter(Activity activity, ArrayList<FavoriteObj> arrFavTeam, CheckBox chkAll) {
        mActivity = activity;
        this.mArrFavTeam = arrFavTeam;
        mChkAll = chkAll;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav_team, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final FavoriteObj clubsObj = mArrFavTeam.get(position);
        if (clubsObj != null) {
//            holder.chkTeam.setText(clubsObj.getName());
            holder.tvName.setText(clubsObj.getName());

            // In some cases, it will prevent unwanted situations
            holder.chkTeam.setOnCheckedChangeListener(null);

            holder.chkTeam.setChecked(clubsObj.isFav());

            // Checked changed listener
            holder.chkTeam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    clubsObj.setIsFav(isChecked);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        try {
            return mArrFavTeam.size();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox chkTeam;
        private TextView tvName;

        public ViewHolder(View view) {
            super(view);

            chkTeam = (CheckBox) view.findViewById(R.id.chk_team);
            tvName = (TextView) view.findViewById(R.id.tv_name);
        }
    }
}
