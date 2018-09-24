package com.hcpt.multileagues.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.activities.ClubDetailActivity;
import com.hcpt.multileagues.configs.Args;
import com.hcpt.multileagues.configs.Constants;
import com.hcpt.multileagues.configs.GlobalValue;
import com.hcpt.multileagues.configs.JsonConfigs;
import com.hcpt.multileagues.database.DatabaseUtility;
import com.hcpt.multileagues.image.utils.ImageLoader;
import com.hcpt.multileagues.interfaces.OnNotificationButtonClickListener;
import com.hcpt.multileagues.objects.MatchObj;
import com.hcpt.multileagues.objects.RankClubsObj;
import com.hcpt.multileagues.objects.RankGroupObject;
import com.hcpt.multileagues.sticky.StickyListHeadersAdapter;
import com.hcpt.multileagues.utilities.AppUtil;
import com.hcpt.multileagues.utilities.DateTimeUtility;
import com.hcpt.multileagues.widget.textview.TextViewRobotoBold;
import com.hcpt.multileagues.widget.textview.TextViewRobotoRegular;

import java.util.ArrayList;
import java.util.Locale;

public class StickyRankGroupAdapter extends BaseAdapter implements
        StickyListHeadersAdapter, SectionIndexer {

    private Activity activity;
    private ArrayList<RankGroupObject> arrRankGroup;
    private LayoutInflater mInflate;
    private int[] mSectionIndices;
    private Integer[] mSectionLetters;
    private ImageLoader mImageLoader;
    private DatabaseUtility databaseUtility;
    private OnNotificationButtonClickListener onNotificationButtonClickListener;

    public StickyRankGroupAdapter(Activity a, ArrayList<RankGroupObject> arr) {
        this.activity = a;
        this.arrRankGroup = arr;
        this.mInflate = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageLoader = new ImageLoader(activity.getApplicationContext());
        // mHeaders = a.getResources().getStringArray(R.array.tickets_header);
        mSectionIndices = getSectionIndices();
        mSectionLetters = getSectionLetters();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        try {
            return arrRankGroup.size();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView,
                        final ViewGroup parent) {
        // TODO Auto-generated method stub
        final HolderView holder;
        if (convertView == null) {
            holder = new HolderView();
            convertView = mInflate.inflate(R.layout.item_rank_clubs, null);

            holder.llRank = (LinearLayout) convertView.findViewById(R.id.ll_rank_club);
            holder.lblRank = (TextView) convertView.findViewById(R.id.lbl_rank_clubs);
//            imgLogo = (ImageView) view.findViewById(R.id.img_logoClub);
            holder.lblNameClubs = (TextView) convertView.findViewById(R.id.lbl_name_clubs);
            holder.lblNameClubs.setSelected(true);
            holder.lblP = (TextView) convertView.findViewById(R.id.lbl_value_p);
            holder.lblW = (TextView) convertView.findViewById(R.id.lbl_value_w);
            holder.lblD = (TextView) convertView.findViewById(R.id.lbl_value_d);
            holder.lblL = (TextView) convertView.findViewById(R.id.lbl_value_l);
            holder.lblGD = (TextView) convertView.findViewById(R.id.lbl_value_gd);
            holder.lblPTS = (TextView) convertView.findViewById(R.id.lbl_value_pts);
            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }

        final RankGroupObject m = arrRankGroup.get(position);

        if (m != null) {
            // Declare variables.
            holder.lblRank.setText(m.getPosition());
            holder.lblNameClubs.setText(m.getmNameClubs());
            holder.lblP.setText(m.getmP());
            holder.lblW.setText(m.getmW());
            holder.lblD.setText(m.getmD());
            holder.lblL.setText(m.getmL());
            holder.lblGD.setText(m.getmGD());
            holder.lblPTS.setText(m.getmPTS());

            // Click on club
            final int pos = position;
            holder.llRank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ClubDetailActivity.currentClub = mArrRankClubs.get(pos);
//                    mActivity.startActivity(new Intent(mActivity, ClubDetailActivity.class));
                }
            });
        }
        return convertView;
    }

    public class HolderView {
        private TextView lblRank, lblNameClubs, lblP, lblW, lblD, lblL, lblGD,
                lblPTS;
        private ImageView imgLogo;
        private LinearLayout llRank;
    }

    class HeaderViewHolder {
        TextView lblHeader;
    }

    @Override
    public Object[] getSections() {
        // TODO Auto-generated method stub
        return mSectionLetters;
    }

    @Override
    public int getPositionForSection(int section) {
        // TODO Auto-generated method stub
        if (mSectionIndices.length == 0) {
            return 0;
        }

        if (section >= mSectionIndices.length) {
            section = mSectionIndices.length - 1;
        } else if (section < 0) {
            section = 0;
        }
        return mSectionIndices[section];
    }

    @Override
    public int getSectionForPosition(int position) {
        // TODO Auto-generated method stub
        for (int i = 0; i < mSectionIndices.length; i++) {
            if (position < mSectionIndices[i]) {
                return i - 1;
            }
        }
        return mSectionIndices.length - 1;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        HeaderViewHolder holder;

        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = mInflate.inflate(R.layout.item_league_header, parent,
                    false);
            holder.lblHeader = (TextView) convertView
                    .findViewById(R.id.lblHeader);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        // set header text as first char in name
        String groupId = arrRankGroup.get(position).getGroupId();
        for (int i = 1; i < arrRankGroup.size(); i++) {
            if (groupId.equals(arrRankGroup.get(position).getGroupId()))
                holder.lblHeader.setText(arrRankGroup.get(position).getGroupName());
            break;
        }
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        // TODO Auto-generated method stub
        return Long.parseLong(arrRankGroup.get(position).getGroupId());
    }

    private int[] getSectionIndices() {
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        int lastFirstChar = Integer.parseInt(arrRankGroup.get(0).getGroupId());
        sectionIndices.add(0);
        for (int i = 1; i < arrRankGroup.size(); i++) {
            if (Integer.parseInt(arrRankGroup.get(i).getGroupId()) != lastFirstChar) {
                lastFirstChar = Integer.parseInt(arrRankGroup.get(i).getGroupId());
                sectionIndices.add(i);
            }
        }
        int[] sections = new int[sectionIndices.size()];
        for (int i = 0; i < sectionIndices.size(); i++) {
            sections[i] = sectionIndices.get(i);
        }
        return sections;
    }

    private Integer[] getSectionLetters() {
        Integer[] letters = new Integer[mSectionIndices.length];
        for (int i = 0; i < mSectionIndices.length; i++) {
            letters[i] = Integer.valueOf(arrRankGroup.get(mSectionIndices[i]).getGroupId());
        }
        return letters;
    }

    public void setOnNotificationButtonClickListener(OnNotificationButtonClickListener onNotificationButtonClickListener) {
        this.onNotificationButtonClickListener = onNotificationButtonClickListener;
    }
}
