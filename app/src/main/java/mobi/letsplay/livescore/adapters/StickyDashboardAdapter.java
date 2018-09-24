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
import android.widget.SectionIndexer;
import android.widget.TextView;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.configs.Args;
import mobi.letsplay.livescore.configs.Constants;
import mobi.letsplay.livescore.configs.GlobalValue;
import mobi.letsplay.livescore.configs.JsonConfigs;
import mobi.letsplay.livescore.database.DatabaseUtility;
import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.interfaces.OnNotificationButtonClickListener;
import mobi.letsplay.livescore.objects.MatchObj;
import mobi.letsplay.livescore.sticky.StickyListHeadersAdapter;
import mobi.letsplay.livescore.utilities.AppUtil;
import mobi.letsplay.livescore.utilities.DateTimeUtility;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoBold;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoRegular;

import java.util.ArrayList;
import java.util.Locale;

import mobi.letsplay.livescore.configs.Args;
import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.MatchObj;
import mobi.letsplay.livescore.sticky.StickyListHeadersAdapter;
import mobi.letsplay.livescore.utilities.AppUtil;
import mobi.letsplay.livescore.utilities.DateTimeUtility;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoBold;

public class StickyDashboardAdapter extends BaseAdapter implements
        StickyListHeadersAdapter, SectionIndexer {

    private Activity activity;
    private ArrayList<MatchObj> arrMatch;
    private LayoutInflater mInflate;
    private int[] mSectionIndices;
    private Integer[] mSectionLetters;
    private ImageLoader mImageLoader;
    private DatabaseUtility databaseUtility;
    private OnNotificationButtonClickListener onNotificationButtonClickListener;

    public StickyDashboardAdapter(Activity a, ArrayList<MatchObj> arr) {
        this.activity = a;
        this.arrMatch = arr;
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
            return arrMatch.size();
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
            convertView = mInflate.inflate(mobi.letsplay.livescore.R.layout.item_match_new, null);

//            holder.logoT1 = (ImageView) convertView
//                    .findViewById(R.id.img_logo_team1);
//            holder.logoT2 = (ImageView) convertView
//                    .findViewById(R.id.img_logo_team2);
            holder.lblHomeScore = (TextViewRobotoBold) convertView.findViewById(mobi.letsplay.livescore.R.id.lbl_home_score);
            holder.lblAwayScore = (TextViewRobotoBold) convertView.findViewById(mobi.letsplay.livescore.R.id.lbl_away_score);
            holder.lblDate = (TextViewRobotoRegular) convertView.findViewById(mobi.letsplay.livescore.R.id.lbl_date_match);
            holder.lblTeamName1 = (TextViewRobotoRegular) convertView.findViewById(mobi.letsplay.livescore.R.id.lbl_name_team1);
            holder.lblTeamName1.setSelected(true);
            holder.lblTeamName2 = (TextViewRobotoRegular) convertView.findViewById(mobi.letsplay.livescore.R.id.lbl_name_team2);
            holder.lblTeamName2.setSelected(true);
            holder.lblTime = (TextViewRobotoRegular) convertView.findViewById(mobi.letsplay.livescore.R.id.lbl_time_match);
            holder.lblVs = (TextView) convertView.findViewById(mobi.letsplay.livescore.R.id.lbl_vs);
            holder.ivNotification = (ImageView) convertView.findViewById(mobi.letsplay.livescore.R.id.iv_notification);
            holder.ll_iv_notification = (LinearLayout) convertView.findViewById(mobi.letsplay.livescore.R.id.ll_iv_notification);
            holder.tvHomePen = (TextViewRobotoRegular) convertView.findViewById(mobi.letsplay.livescore.R.id.tv_home_pen);
            holder.tvAwayPen = (TextViewRobotoRegular) convertView.findViewById(mobi.letsplay.livescore.R.id.tv_away_pen);
            convertView.setTag(holder);
        } else {
            holder = (HolderView) convertView.getTag();
        }

        final MatchObj m = arrMatch.get(position);

        if (m != null) {
            // Declare variables.
            String scoreHome = "?";
            String scoreAway = "?";
            String strTime = "";
            String homePen = "";
            String awayPen = "";
            int timeColor = activity.getResources().getColor(mobi.letsplay.livescore.R.color.textColorPrimary);
            int colorBackground = activity.getResources().getColor(mobi.letsplay.livescore.R.color.tranparent);

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

            //if null = blank
            if (m.getmHomeName().toUpperCase(Locale.US).equals("NULL")) {
                holder.lblTeamName1.setText("");
            } else {
                //holder.lblTeamName1.setText(m.getmHomeName().toUpperCase(Locale.US));
                holder.lblTeamName1.setText(m.getmHomeName());
            }
            if (m.getmHomeName().toUpperCase(Locale.US).equals("NULL")) {
                holder.lblTeamName2.setText("");
            } else {
                holder.lblTeamName2.setText(m.getmAwayName());
            }

//            mImageLoader.DisplayImage(m.getmHomeImage(), holder.logoT1);
//            mImageLoader.DisplayImage(m.getmAwayImage(), holder.logoT2);
            holder.lblTeamName1.setSelected(true);
            holder.lblTeamName2.setSelected(true);
            holder.lblHomeScore.setText(scoreHome);
            holder.lblAwayScore.setText(scoreAway);
            holder.tvHomePen.setText(homePen);
            holder.tvAwayPen.setText(awayPen);
            databaseUtility = new DatabaseUtility();
            boolean isSetted;
            if (m.getmMinute().trim().equalsIgnoreCase(Constants.POSTPONDED)) {
                strTime = Constants.POSTPONDED;
            } else if (m.getmMinute().trim().equalsIgnoreCase(Constants.CANCELLED)) {
                strTime = Constants.CANCELLED;
            } else if (m.getmMinute().trim().equalsIgnoreCase(Constants.ABANDONED)) {
                strTime = Constants.ABANDONED;

            } else {
                if (m.getmMatchStatus()
                        .equals(JsonConfigs.MATCH_STATUS_NOT_STARTED)) {
                    isSetted = databaseUtility.checkAlarmSetted(m.getmMatchId(), activity);
                    strTime = DateTimeUtility.convertTimeStampToDate(m.getmTime(), "HH:mm");
                    timeColor = activity.getResources().getColor(
                            mobi.letsplay.livescore.R.color.textColorSecondary);
                    colorBackground = activity.getResources().getColor(mobi.letsplay.livescore.R.color.tranparent);
                    if (isSetted) {
                        holder.ivNotification.setImageResource(mobi.letsplay.livescore.R.drawable.ic_red_bell);
                    } else {
                        holder.ivNotification.setImageResource(mobi.letsplay.livescore.R.drawable.ic_bell);
                    }
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AppUtil.convertDpToPixel(activity, 13), AppUtil.convertDpToPixel(activity, 13));
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
                    timeColor = activity.getResources().getColor(
                            mobi.letsplay.livescore.R.color.text_color_green_item_match);
                    holder.ivNotification.setImageResource(mobi.letsplay.livescore.R.drawable.ic_green_circle);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AppUtil.convertDpToPixel(activity, 10), AppUtil.convertDpToPixel(activity, 10));
                    holder.ivNotification.setLayoutParams(layoutParams);
                } else if (m.getmMatchStatus().equals(
                        JsonConfigs.MATCH_STATUS_FINISHED)) {
                    strTime = parent.getContext().getString(mobi.letsplay.livescore.R.string.finish);
                    timeColor = activity.getResources().getColor(mobi.letsplay.livescore.R.color.textColorPrimary);
                    holder.ivNotification.setImageResource(mobi.letsplay.livescore.R.drawable.ic_gray_circle);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(AppUtil.convertDpToPixel(activity, 10), AppUtil.convertDpToPixel(activity, 10));
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

    public class HolderView {
        private ImageView logoT1, logoT2, ivNotification;
        private LinearLayout ll_iv_notification;
        private TextView lblVs;
        private TextViewRobotoRegular lblDate, lblTime, lblTeamName1, lblTeamName2;
        private TextViewRobotoRegular tvHomePen, tvAwayPen;
        private TextViewRobotoBold lblHomeScore, lblAwayScore;
        private CardView cv_item_match;
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
            convertView = mInflate.inflate(mobi.letsplay.livescore.R.layout.item_league_header, parent,
                    false);
            holder.lblHeader = (TextView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lblHeader);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }

        // set header text as first char in name
        String leagueId = arrMatch.get(position).getLeagueId() + "";
        for (int i = 1; i < GlobalValue.arrLeague.size(); i++) {
            if (leagueId.equals(GlobalValue.arrLeague.get(i).getmId())) {
                if (GlobalValue.prefs.getStringValue(Args.LANGUAGE).isEmpty() ||
                        GlobalValue.prefs.getStringValue(Args.LANGUAGE).equals(Constants.ENGLISH)) {
                    holder.lblHeader.setText(GlobalValue.arrLeague.get(i).getmName());
                } else if (GlobalValue.prefs.getStringValue(Args.LANGUAGE).equals(Constants.CHINESE)) {
                    holder.lblHeader.setText(GlobalValue.arrLeague.get(i).getmNameChinese());
                } else if (GlobalValue.prefs.getStringValue(Args.LANGUAGE).equals(Constants.GERMAN)) {
                    holder.lblHeader.setText(GlobalValue.arrLeague.get(i).getmNameGerman());
                } else if (GlobalValue.prefs.getStringValue(Args.LANGUAGE).equals(Constants.SPANISH)) {
                    holder.lblHeader.setText(GlobalValue.arrLeague.get(i).getmNameSpanish());
                } else if (GlobalValue.prefs.getStringValue(Args.LANGUAGE).equals(Constants.FRENCH)) {
                    holder.lblHeader.setText(GlobalValue.arrLeague.get(i).getmNameFrench());
                } else if (GlobalValue.prefs.getStringValue(Args.LANGUAGE).equals(Constants.ITALIAN)) {
                    holder.lblHeader.setText(GlobalValue.arrLeague.get(i).getmNameItalian());
                }
                break;
            }
        }

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        // TODO Auto-generated method stub
        return arrMatch.get(position).getLeagueId();
    }

    private int[] getSectionIndices() {
        ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
        int lastFirstChar = arrMatch.get(0).getLeagueId();
        sectionIndices.add(0);
        for (int i = 1; i < arrMatch.size(); i++) {
            if (arrMatch.get(i).getLeagueId() != lastFirstChar) {
                lastFirstChar = arrMatch.get(i).getLeagueId();
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
            letters[i] = arrMatch.get(mSectionIndices[i]).getLeagueId();
        }
        return letters;
    }

    public void setOnNotificationButtonClickListener(OnNotificationButtonClickListener onNotificationButtonClickListener) {
        this.onNotificationButtonClickListener = onNotificationButtonClickListener;
    }
}