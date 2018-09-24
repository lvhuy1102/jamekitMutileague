package com.hcpt.multileagues.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.objects.RankClubsObj;

import java.util.ArrayList;

@SuppressLint("NewApi")
public class SpinnerClubAdapter extends BaseAdapter {

    private ArrayList<RankClubsObj> mArrClub;
    private LayoutInflater mInflater;
    private Activity mActivity;

    public SpinnerClubAdapter(Activity activity, ArrayList<RankClubsObj> arrItem) {
        this.mArrClub = arrItem;
        this.mActivity = activity;
        mInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mArrClub.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        if (mArrClub == null){
            return null;
        }
        return mArrClub.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = mInflater.inflate(R.layout.item_spinner, null);
            holder.lblItem = (TextView) convertView.findViewById(R.id.lbl_item_spinner);
            holder.lblItem.setSelected(true);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        RankClubsObj clubsObj = (RankClubsObj) getItem(position);
        if (clubsObj != null) {
            holder.lblItem.setText(clubsObj.getmNameClubs());

            if (position == 0) {
                holder.lblItem.setTextColor(ContextCompat.getColor(mActivity, R.color.yellow_dark));
            } else {
                holder.lblItem.setTextColor(ContextCompat.getColor(mActivity, R.color.black));
            }
        }

        return convertView;
    }

    class Holder {
        private TextView lblItem;
    }
}
