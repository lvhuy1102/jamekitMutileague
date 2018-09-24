package com.hcpt.multileagues.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.objects.OddObj;

import java.util.ArrayList;

public class OddsAdapter extends BaseAdapter {

    private ArrayList<OddObj> mOdds;
    private LayoutInflater inflater;

    public OddsAdapter(Activity activity, ArrayList<OddObj> odds) {
        this.mOdds = odds;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mOdds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;

        if (convertView == null) {
            holder = new Holder();
            convertView = inflater.inflate(R.layout.item_odd, null);

            holder.lblBookmarker = (TextView) convertView.findViewById(R.id.lbl_bookmarker);
            holder.lblHome = (TextView) convertView.findViewById(R.id.lbl_home);
            holder.lblDraw = (TextView) convertView.findViewById(R.id.lbl_draw);
            holder.lblAway = (TextView) convertView.findViewById(R.id.lbl_away);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        // Fill data
        OddObj oddObj = mOdds.get(position);
        if (oddObj != null) {
            holder.lblBookmarker.setText(oddObj.getName().trim());
            holder.lblHome.setText(oddObj.getHome().trim());
            holder.lblDraw.setText(oddObj.getDraw().trim());
            holder.lblAway.setText(oddObj.getAway().trim());
        }

        return convertView;
    }

    class Holder {
        private TextView lblBookmarker, lblHome, lblDraw, lblAway;
    }
}
