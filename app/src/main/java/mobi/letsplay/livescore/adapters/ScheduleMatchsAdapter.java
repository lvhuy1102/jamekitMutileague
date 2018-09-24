package mobi.letsplay.livescore.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.configs.Constants;
import mobi.letsplay.livescore.configs.JsonConfigs;
import mobi.letsplay.livescore.database.DatabaseUtility;
import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.interfaces.OnNotificationButtonClickListener;
import mobi.letsplay.livescore.objects.MatchObj;
import mobi.letsplay.livescore.utilities.AppUtil;
import mobi.letsplay.livescore.utilities.DateTimeUtility;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoBold;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoRegular;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import mobi.letsplay.livescore.configs.JsonConfigs;
import mobi.letsplay.livescore.database.DatabaseUtility;
import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.MatchObj;
import mobi.letsplay.livescore.utilities.AppUtil;
import mobi.letsplay.livescore.utilities.DateTimeUtility;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoBold;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoRegular;

public class ScheduleMatchsAdapter extends BaseAdapter {

    private ArrayList<MatchObj> mArrMatchs;
    private LayoutInflater mInflate;
    private ImageLoader mImageLoader;
    private Activity mActivity;
    private DatabaseUtility databaseUtility;
    private OnNotificationButtonClickListener onNotificationButtonClickListener;

    public ScheduleMatchsAdapter(Activity activity,
                                 ArrayList<MatchObj> arrMatchs) {
        this.mArrMatchs = arrMatchs;
        mImageLoader = new ImageLoader(activity.getApplicationContext());
        mInflate = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mActivity = activity;
    }

    @Override
    public int getCount() {
        return mArrMatchs.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrMatchs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(mArrMatchs.get(position).getmMatchId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = mInflate.inflate(mobi.letsplay.livescore.R.layout.item_match_new, null);
//            holder.logoT1 = (ImageView) convertView
//                    .findViewById(R.id.img_logo_team1);
//            holder.logoT2 = (ImageView) convertView
//                    .findViewById(R.id.img_logo_team2);
            holder.lblHomeScore = (TextViewRobotoBold) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_home_score);
            holder.lblAwayScore = (TextViewRobotoBold) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_away_score);
            holder.lblDate = (TextViewRobotoRegular) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_date_match);
            holder.lblTeamName1 = (TextViewRobotoRegular) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_name_team1);
            holder.lblTeamName1.setSelected(true);
            holder.lblTeamName2 = (TextViewRobotoRegular) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_name_team2);
            holder.lblTeamName2.setSelected(true);
            holder.lblTime = (TextViewRobotoRegular) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_time_match);
            holder.lblVs = (TextView) convertView.findViewById(mobi.letsplay.livescore.R.id.lbl_vs);
            holder.ivNotification = (ImageView) convertView.findViewById(mobi.letsplay.livescore.R.id.iv_notification);
            holder.ll_iv_notification = (LinearLayout) convertView.findViewById(mobi.letsplay.livescore.R.id.ll_iv_notification);
            holder.tvHomePen = (TextViewRobotoRegular) convertView.findViewById(mobi.letsplay.livescore.R.id.tv_home_pen);
            holder.tvAwayPen = (TextViewRobotoRegular) convertView.findViewById(mobi.letsplay.livescore.R.id.tv_away_pen);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        // Sort match date by ascending.
        Collections.sort(mArrMatchs, new Comparator<MatchObj>() {

            @Override
            public int compare(MatchObj lhs, MatchObj rhs) {
                return lhs.getmTime().compareTo(rhs.getmTime());
            }
        });
        databaseUtility = new DatabaseUtility();
        boolean isSetted;
        final MatchObj m = mArrMatchs.get(position);

        if (m != null) {
            // Declare variables.
            String scoreHome = "?";
            String scoreAway = "?";
            String strTime = "";
            String homePen = "";
            String awayPen = "";
            int timeColor = mActivity.getResources().getColor(mobi.letsplay.livescore.R.color.textColorPrimary);
            int colorBackground = mActivity.getResources().getColor(mobi.letsplay.livescore.R.color.tranparent);

            // Get scores.
            if (m.getmMatchStatus().equals(JsonConfigs.MATCH_STATUS_ACTIVE)
                    || m.getmMatchStatus().equals(
                    JsonConfigs.MATCH_STATUS_FINISHED)) {
                scoreHome = m.getmHomeScore();
                scoreAway = m.getmAwayScore();

            }

            if (m.getmMatchStatus().equals(JsonConfigs.MATCH_STATUS_FINISHED)) {
                if (m.getHomePen() != null && !m.getHomePen().trim().isEmpty()
                        && m.getHomePen().trim().length() != 0 && !m.getHomePen().equals("null")
                        && !m.getHomePen().trim().equals("")) {
                    homePen = "(" + m.getHomePen() + ")";
                }
                if (m.getAwayPen() != null && !m.getAwayPen().trim().isEmpty()
                        && m.getAwayPen().trim().length() != 0 && !m.getAwayPen().equals("null")
                        && !m.getAwayPen().trim().equals("")) {
                    awayPen = "(" + m.getAwayPen() + ")";
                }
            }


            // Set name, date, time, logo, score.
            try {
                mImageLoader.DisplayImage(m.getmHomeImage(), holder.logoT1);
                mImageLoader.DisplayImage(m.getmAwayImage(), holder.logoT2);
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            holder.lblTeamName1.setText(m.getmHomeName());
            holder.lblTeamName2.setText(m.getmAwayName());
            holder.lblTeamName1.setSelected(true);
            holder.lblTeamName2.setSelected(true);
            holder.lblHomeScore.setText(scoreHome);
            holder.lblAwayScore.setText(scoreAway);
            holder.tvHomePen.setText(homePen);
            holder.tvAwayPen.setText(awayPen);
            if (m.getmMinute().trim().equalsIgnoreCase(Constants.POSTPONDED)) {
                strTime = Constants.POSTPONDED;
            } else if (m.getmMinute().trim().equalsIgnoreCase(Constants.CANCELLED)) {
                strTime = Constants.CANCELLED;
            } else if (m.getmMinute().trim().equalsIgnoreCase(Constants.ABANDONED)) {
                strTime = Constants.ABANDONED;
            } else {
                if (m.getmMatchStatus()
                        .equals(JsonConfigs.MATCH_STATUS_NOT_STARTED)) {
                    isSetted = databaseUtility.checkAlarmSetted(m.getmMatchId(), mActivity);
                    strTime = DateTimeUtility.convertTimeStampToDate(m.getmTime(), "HH:mm");
                    timeColor = mActivity.getResources().getColor(
                            mobi.letsplay.livescore.R.color.textColorSecondary);
                    colorBackground = mActivity.getResources().getColor(mobi.letsplay.livescore.R.color.tranparent);
                    if (isSetted) {
                        holder.ivNotification.setImageResource(mobi.letsplay.livescore.R.drawable.ic_red_bell);
                    } else {
                        holder.ivNotification.setImageResource(mobi.letsplay.livescore.R.drawable.ic_bell);
                    }
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AppUtil.convertDpToPixel(mActivity, 13), AppUtil.convertDpToPixel(mActivity, 13));
                    holder.ivNotification.setLayoutParams(layoutParams);

                    holder.ll_iv_notification.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onNotificationButtonClickListener.onNotificationButtonClick(m);
                        }
                    });
                } else if (m.getmMatchStatus().equals(
                        JsonConfigs.MATCH_STATUS_ACTIVE)) {
                    strTime = parent.getContext().getString(mobi.letsplay.livescore.R.string.live) + ", " + m.getmMinute() + "'";
                    timeColor = mActivity.getResources().getColor(mobi.letsplay.livescore.R.color.text_color_green_item_match);
                    colorBackground = mActivity.getResources().getColor(mobi.letsplay.livescore.R.color.background_lable_live);
                    holder.ivNotification.setImageResource(mobi.letsplay.livescore.R.drawable.ic_green_circle);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AppUtil.convertDpToPixel(mActivity, 10), AppUtil.convertDpToPixel(mActivity, 10));
                    holder.ivNotification.setLayoutParams(layoutParams);
                } else if (m.getmMatchStatus().equals(
                        JsonConfigs.MATCH_STATUS_FINISHED)) {
                    strTime = parent.getContext().getString(mobi.letsplay.livescore.R.string.finish);
                    timeColor = mActivity.getResources().getColor(mobi.letsplay.livescore.R.color.textColorPrimary);
                    colorBackground = mActivity.getResources().getColor(mobi.letsplay.livescore.R.color.tranparent);
                    holder.ivNotification.setImageResource(mobi.letsplay.livescore.R.drawable.ic_gray_circle);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AppUtil.convertDpToPixel(mActivity, 10), AppUtil.convertDpToPixel(mActivity, 10));
                    holder.ivNotification.setLayoutParams(layoutParams);
                }
            }


            holder.lblTime.setText(strTime);
            holder.lblTime.setTextColor(timeColor);
//            holder.lblTime.setBackgroundColor(colorBackground);

            holder.lblDate.setText(DateTimeUtility.convertTimeStampToDate(m.getmTime(), "dd - MMM"));
            holder.lblVs.setText(m.getmStadium());
            holder.lblVs.setSelected(true);
        }

        return convertView;
    }

    class Holder {
        private ImageView logoT1, logoT2, ivNotification;
        private LinearLayout ll_iv_notification;
        private TextView lblVs;
        private TextViewRobotoRegular tvHomePen, tvAwayPen;
        private TextViewRobotoBold lblHomeScore, lblAwayScore;
        private TextViewRobotoRegular lblDate, lblTime, lblTeamName1, lblTeamName2;
    }

    public void setOnNotificationButtonClickListener(OnNotificationButtonClickListener onNotificationButtonClickListener) {
        this.onNotificationButtonClickListener = onNotificationButtonClickListener;
    }
}
