package com.hcpt.multileagues.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.configs.JsonConfigs;
import com.hcpt.multileagues.objects.EventObject;
import com.hcpt.multileagues.objects.MatchObj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventAdapter extends BaseAdapter {

    private ArrayList<EventObject> mArrEvent;
    private EventObject mEventObj;
    private LayoutInflater mInflater;
    private MatchObj mMatchObj;

    public EventAdapter(Activity activity, ArrayList<EventObject> arrEvent,
                        MatchObj matchObj) {
        this.mArrEvent = arrEvent;
        mInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mMatchObj = matchObj;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mArrEvent.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mArrEvent.get(position);
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
            convertView = mInflater.inflate(R.layout.item_events, null);
            holder.imgEventAwayBottom = (ImageView) convertView
                    .findViewById(R.id.img_event_type_away_bottom);
            holder.imgEventAwayMid = (ImageView) convertView
                    .findViewById(R.id.img_event_type_away_mid);
            holder.imgEventAwayTop = (ImageView) convertView
                    .findViewById(R.id.img_event_type_away_top);
            holder.imgEventHomeTop = (ImageView) convertView
                    .findViewById(R.id.img_event_type_home_top);
            holder.imgEventHomeMid = (ImageView) convertView
                    .findViewById(R.id.img_event_type_home_mid);
            holder.imgEventHomeBottom = (ImageView) convertView
                    .findViewById(R.id.img_event_type_home_bottom);

            holder.lblEventAwayBottom = (TextView) convertView
                    .findViewById(R.id.lbl_event_away_bottom);
            holder.lblEventAwayBottom.setSelected(true);
            holder.lblEventAwayMid = (TextView) convertView
                    .findViewById(R.id.lbl_event_away_mid);
            holder.lblEventAwayMid.setSelected(true);
            holder.lblEventAwayTop = (TextView) convertView
                    .findViewById(R.id.lbl_event_away_top);
            holder.lblEventAwayTop.setSelected(true);
            holder.lblEventHomeBottom = (TextView) convertView
                    .findViewById(R.id.lbl_event_home_bottom);
            holder.lblEventHomeBottom.setSelected(true);
            holder.lblEventHomeMid = (TextView) convertView
                    .findViewById(R.id.lbl_event_home_mid);
            holder.lblEventHomeMid.setSelected(true);
            holder.lblEventHomeTop = (TextView) convertView
                    .findViewById(R.id.lbl_event_home_top);
            holder.lblEventHomeTop.setSelected(true);
            holder.lblEventMinute = (TextView) convertView
                    .findViewById(R.id.lbl_event_minute);

            holder.rlEventHome = (RelativeLayout) convertView
                    .findViewById(R.id.rl_events_home);
            holder.rlEventAway = (RelativeLayout) convertView
                    .findViewById(R.id.rl_events_away);
            holder.llImgAway = (LinearLayout) convertView
                    .findViewById(R.id.ll_img_events_away);
            holder.llImgHome = (LinearLayout) convertView
                    .findViewById(R.id.ll_img_events_home);

            holder.lblEventScores = (TextView) convertView
                    .findViewById(R.id.lbl_event_scores);
            holder.lblEmpty = (TextView) convertView
                    .findViewById(R.id.lbl_empty);
            holder.llEventRow = (LinearLayout) convertView
                    .findViewById(R.id.ll_event_row);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Collections.sort(mArrEvent, new Comparator<EventObject>() {

            @Override
            public int compare(EventObject lhs, EventObject rhs) {
                Integer i = Integer.valueOf(lhs.getmMinute());
                Integer j = Integer.valueOf(rhs.getmMinute());

                return j.compareTo(i);
            }
        });

        mEventObj = mArrEvent.get(position);
        if (mEventObj != null) {
            holder.lblEventMinute.setVisibility(View.VISIBLE);
            holder.lblEventMinute.setText(mEventObj.getmMinute() + "'");
            if (mEventObj.getmClubId().equals(mMatchObj.getmHome())) {
                holder.rlEventHome.setVisibility(View.VISIBLE);
                holder.rlEventAway.setVisibility(View.INVISIBLE);
                holder.llImgHome.setVisibility(View.VISIBLE);
                holder.llImgAway.setVisibility(View.INVISIBLE);

                if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_GOAL)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    holder.lblEventHomeTop.setVisibility(View.INVISIBLE);
                    holder.lblEventHomeBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventHomeBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventHomeTop.setVisibility(View.INVISIBLE);
                    holder.lblEmpty.setVisibility(View.INVISIBLE);

                    holder.lblEventScores.setVisibility(View.VISIBLE);
                    holder.lblEventScores.setText(mEventObj.getmScores());

                    holder.imgEventHomeMid.setVisibility(View.VISIBLE);
                    holder.lblEventHomeMid.setVisibility(View.VISIBLE);
                    holder.imgEventHomeMid.setImageResource(R.drawable.ic_ball);
                    holder.lblEventHomeMid.setText(mEventObj.getmPlayer());
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_OWN_GOAL)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    holder.lblEventHomeTop.setVisibility(View.INVISIBLE);
                    holder.lblEventHomeBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventHomeBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventHomeTop.setVisibility(View.INVISIBLE);
                    holder.lblEmpty.setVisibility(View.INVISIBLE);

                    holder.lblEventScores.setVisibility(View.VISIBLE);
                    holder.lblEventScores.setText(mEventObj.getmScores());

                    holder.imgEventHomeMid.setVisibility(View.VISIBLE);
                    holder.lblEventHomeMid.setVisibility(View.VISIBLE);
                    holder.imgEventHomeMid
                            .setImageResource(R.drawable.ic_own_goal);
                    holder.lblEventHomeMid.setText(mEventObj.getmPlayer());
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_RED_CARD)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    holder.lblEventHomeTop.setVisibility(View.INVISIBLE);
                    holder.lblEventHomeBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventHomeBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventHomeTop.setVisibility(View.INVISIBLE);
                    holder.lblEmpty.setVisibility(View.INVISIBLE);

                    holder.lblEventScores.setVisibility(View.INVISIBLE);

                    holder.imgEventHomeMid.setVisibility(View.VISIBLE);
                    holder.lblEventHomeMid.setVisibility(View.VISIBLE);
                    holder.imgEventHomeMid
                            .setImageResource(R.drawable.ic_red_card);
                    holder.lblEventHomeMid.setText(mEventObj.getmPlayer());
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_YELLOW_CARD)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    holder.lblEventHomeTop.setVisibility(View.INVISIBLE);
                    holder.lblEventHomeBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventHomeBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventHomeTop.setVisibility(View.INVISIBLE);
                    holder.lblEmpty.setVisibility(View.INVISIBLE);

                    holder.lblEventScores.setVisibility(View.INVISIBLE);

                    holder.imgEventHomeMid.setVisibility(View.VISIBLE);
                    holder.lblEventHomeMid.setVisibility(View.VISIBLE);
                    holder.imgEventHomeMid
                            .setImageResource(R.drawable.ic_yellow_card);
                    holder.lblEventHomeMid.setText(mEventObj.getmPlayer());
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_PENALTY)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    holder.lblEventHomeTop.setVisibility(View.INVISIBLE);
                    holder.lblEventHomeBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventHomeBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventHomeTop.setVisibility(View.INVISIBLE);
                    holder.lblEmpty.setVisibility(View.INVISIBLE);

                    if (mEventObj.getmIsScore().equals(JsonConfigs.IS_SCORE)) {
                        holder.lblEventScores.setVisibility(View.VISIBLE);
                        holder.lblEventScores.setText(mEventObj.getmScores());
                    } else {
                        holder.lblEventScores.setVisibility(View.INVISIBLE);
                    }

                    holder.imgEventHomeMid.setVisibility(View.VISIBLE);
                    holder.lblEventHomeMid.setVisibility(View.VISIBLE);
                    holder.imgEventHomeMid
                            .setImageResource(R.drawable.ic_penalty);
                    holder.lblEventHomeMid.setText(mEventObj.getmPlayer());
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_IN)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    for (int i = 0; i < mArrEvent.size(); i++) {
                        if (mEventObj.getmMinute().equals(
                                mArrEvent.get(i).getmMinute())
                                && mArrEvent.get(i).getmEventType()
                                .equals(JsonConfigs.EVENTS_TYPE_OUT)) {
                            holder.lblEventHomeTop.setVisibility(View.VISIBLE);
                            holder.lblEventHomeTop.setText(mEventObj
                                    .getmPlayer());
                            holder.lblEventHomeBottom
                                    .setVisibility(View.VISIBLE);
                            holder.lblEventHomeBottom.setText(mArrEvent.get(i)
                                    .getmPlayer());
                            holder.imgEventHomeTop.setVisibility(View.VISIBLE);
                            holder.imgEventHomeTop
                                    .setImageResource(R.drawable.ic_arrow_up);
                            holder.imgEventHomeBottom
                                    .setVisibility(View.VISIBLE);
                            holder.imgEventHomeBottom
                                    .setImageResource(R.drawable.ic_arrow_down);

                            holder.lblEventHomeMid
                                    .setVisibility(View.INVISIBLE);
                            holder.imgEventHomeMid
                                    .setVisibility(View.INVISIBLE);

                            holder.lblEventScores.setVisibility(View.INVISIBLE);
                            holder.lblEmpty.setVisibility(View.INVISIBLE);

                            break;
                        }
                    }
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_OUT)) {
                    holder.llEventRow.setVisibility(View.GONE);
                }
            } else {
                holder.rlEventHome.setVisibility(View.INVISIBLE);
                holder.rlEventAway.setVisibility(View.VISIBLE);
                holder.llImgAway.setVisibility(View.VISIBLE);
                holder.llImgHome.setVisibility(View.INVISIBLE);

                if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_GOAL)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    holder.lblEventAwayTop.setVisibility(View.INVISIBLE);
                    holder.lblEventAwayBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventAwayBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventAwayTop.setVisibility(View.INVISIBLE);
                    holder.lblEmpty.setVisibility(View.INVISIBLE);

                    holder.lblEventScores.setVisibility(View.VISIBLE);
                    holder.lblEventScores.setText(mEventObj.getmScores());

                    holder.imgEventAwayMid.setVisibility(View.VISIBLE);
                    holder.lblEventAwayMid.setVisibility(View.VISIBLE);
                    holder.imgEventAwayMid.setImageResource(R.drawable.ic_ball);
                    holder.lblEventAwayMid.setText(mEventObj.getmPlayer());
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_OWN_GOAL)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    holder.lblEventAwayTop.setVisibility(View.INVISIBLE);
                    holder.lblEventAwayBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventAwayBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventAwayTop.setVisibility(View.INVISIBLE);
                    holder.lblEmpty.setVisibility(View.INVISIBLE);

                    holder.lblEventScores.setVisibility(View.VISIBLE);
                    holder.lblEventScores.setText(mEventObj.getmScores());

                    holder.imgEventAwayMid.setVisibility(View.VISIBLE);
                    holder.lblEventAwayMid.setVisibility(View.VISIBLE);
                    holder.imgEventAwayMid
                            .setImageResource(R.drawable.ic_own_goal);
                    holder.lblEventAwayMid.setText(mEventObj.getmPlayer());
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_RED_CARD)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    holder.lblEventAwayTop.setVisibility(View.INVISIBLE);
                    holder.lblEventAwayBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventAwayBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventAwayTop.setVisibility(View.INVISIBLE);
                    holder.lblEmpty.setVisibility(View.INVISIBLE);

                    holder.lblEventScores.setVisibility(View.INVISIBLE);

                    holder.imgEventAwayMid.setVisibility(View.VISIBLE);
                    holder.lblEventAwayMid.setVisibility(View.VISIBLE);
                    holder.imgEventAwayMid
                            .setImageResource(R.drawable.ic_red_card);
                    holder.lblEventAwayMid.setText(mEventObj.getmPlayer());
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_YELLOW_CARD)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    holder.lblEventAwayTop.setVisibility(View.INVISIBLE);
                    holder.lblEventAwayBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventAwayBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventAwayTop.setVisibility(View.INVISIBLE);
                    holder.lblEmpty.setVisibility(View.INVISIBLE);

                    holder.lblEventScores.setVisibility(View.INVISIBLE);

                    holder.imgEventAwayMid.setVisibility(View.VISIBLE);
                    holder.lblEventAwayMid.setVisibility(View.VISIBLE);
                    holder.imgEventAwayMid
                            .setImageResource(R.drawable.ic_yellow_card);
                    holder.lblEventAwayMid.setText(mEventObj.getmPlayer());
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_PENALTY)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    holder.lblEventAwayTop.setVisibility(View.INVISIBLE);
                    holder.lblEventAwayBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventAwayBottom.setVisibility(View.INVISIBLE);
                    holder.imgEventAwayTop.setVisibility(View.INVISIBLE);
                    holder.lblEmpty.setVisibility(View.INVISIBLE);

                    if (mEventObj.getmIsScore().equals(JsonConfigs.IS_SCORE)) {
                        holder.lblEventScores.setVisibility(View.VISIBLE);
                        holder.lblEventScores.setText(mEventObj.getmScores());
                    } else {
                        holder.lblEventScores.setVisibility(View.INVISIBLE);
                    }

                    holder.imgEventAwayMid.setVisibility(View.VISIBLE);
                    holder.lblEventAwayMid.setVisibility(View.VISIBLE);
                    holder.imgEventAwayMid
                            .setImageResource(R.drawable.ic_penalty);
                    holder.lblEventAwayMid.setText(mEventObj.getmPlayer());
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_IN)) {
                    holder.llEventRow.setVisibility(View.VISIBLE);

                    for (int i = 0; i < mArrEvent.size(); i++) {
                        if (mEventObj.getmMinute().equals(
                                mArrEvent.get(i).getmMinute())
                                && mArrEvent.get(i).getmEventType()
                                .equals(JsonConfigs.EVENTS_TYPE_OUT)) {
                            holder.lblEventAwayTop.setVisibility(View.VISIBLE);
                            holder.lblEventAwayTop.setText(mEventObj
                                    .getmPlayer());
                            holder.lblEventAwayBottom
                                    .setVisibility(View.VISIBLE);
                            holder.lblEventAwayBottom.setText(mArrEvent.get(i)
                                    .getmPlayer());
                            holder.imgEventAwayTop.setVisibility(View.VISIBLE);
                            holder.imgEventAwayTop
                                    .setImageResource(R.drawable.ic_arrow_up);
                            holder.imgEventAwayBottom
                                    .setVisibility(View.VISIBLE);
                            holder.imgEventAwayBottom
                                    .setImageResource(R.drawable.ic_arrow_down);

                            holder.lblEventAwayMid
                                    .setVisibility(View.INVISIBLE);
                            holder.imgEventAwayMid
                                    .setVisibility(View.INVISIBLE);

                            holder.lblEventScores.setVisibility(View.INVISIBLE);
                            holder.lblEmpty.setVisibility(View.INVISIBLE);
                            break;
                        }
                    }
                } else if (mEventObj.getmEventType().equals(
                        JsonConfigs.EVENTS_TYPE_OUT)) {
                    holder.llEventRow.setVisibility(View.GONE);
                }
            }
        }

        return convertView;
    }

    class Holder {
        private RelativeLayout rlEventHome, rlEventAway;
        private LinearLayout llImgHome, llImgAway, llEventRow;
        private TextView lblEventHomeTop, lblEventHomeMid, lblEventHomeBottom,
                lblEventAwayTop, lblEventAwayMid, lblEventAwayBottom,
                lblEventMinute, lblEventScores, lblEmpty;
        private ImageView imgEventHomeTop, imgEventHomeMid, imgEventHomeBottom,
                imgEventAwayTop, imgEventAwayMid, imgEventAwayBottom;
    }
}
