package mobi.letsplay.livescore.adapters;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.objects.LineUpObj;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoRegular;

import mobi.letsplay.livescore.objects.LineUpObj;
import mobi.letsplay.livescore.widget.textview.TextViewRobotoRegular;

public class LineUpFirstTeamAdapter extends BaseAdapter {

    private ArrayList<LineUpObj> mArrHomeFirstTeam, mArrAwayFirstTeam;
    private LineUpObj mLuHomeObj, mLuAwayObj;
    private LayoutInflater mInfalter;
    // private ArrayList<View> arrView;
    private Activity mActivity;

    public LineUpFirstTeamAdapter(Activity activity,
                                  ArrayList<LineUpObj> arrHome, ArrayList<LineUpObj> arrAway) {
        this.mArrHomeFirstTeam = arrHome;
        this.mArrAwayFirstTeam = arrAway;
        this.mActivity = activity;
        this.mInfalter = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mArrHomeFirstTeam.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;

        if (convertView == null) {
            holder = new Holder();
            convertView = mInfalter.inflate(mobi.letsplay.livescore.R.layout.item_lineups_firstteam,
                    null);

            holder.lbl_player_name_away = (TextViewRobotoRegular) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_player_name_firstteam_away);
            holder.lbl_player_name_away.setSelected(true);
            holder.lbl_player_name_home = (TextViewRobotoRegular) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_player_name_firstteam_home);
            holder.lbl_player_name_home.setSelected(true);
            holder.lbl_player_number_away = (TextView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_player_number_firstteam_away);
            holder.lbl_player_number_home = (TextView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_player_number_firstteam_home);
            holder.lbl_position_away = (TextViewRobotoRegular) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_player_pos_firstteam_away);
            holder.lbl_position_home = (TextViewRobotoRegular) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_player_pos_firstteam_home);
            holder.liner = (LinearLayout) convertView.findViewById(mobi.letsplay.livescore.R.id.liner);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        try {
            mLuAwayObj = mArrAwayFirstTeam.get(position);
            mLuHomeObj = mArrHomeFirstTeam.get(position);
        } catch (IndexOutOfBoundsException ex) {
            Log.e("TAG", ex.getMessage());
        }

        // Set firstteam's data
        // Away.
        if (mLuAwayObj != null) {
            holder.lbl_player_name_away.setText(mLuAwayObj.getmPlayerName());
            holder.lbl_player_number_away
                    .setText(mLuAwayObj.getmPlayerNumber());
            holder.lbl_position_away.setText(
                    mLuAwayObj.getmPosition()
                            .toUpperCase(Locale.getDefault()));

            // Set background odd and even row.
//			if (position % 2 != 0) {
//				holder.liner.setBackgroundColor(mActivity.getResources()
//						.getColor(R.color.list_item_odd));
//			} else {
//				holder.liner.setBackgroundColor(mActivity.getResources()
//						.getColor(android.R.color.transparent));
//			}
        }

        // Home
        if (mLuHomeObj != null) {
            holder.lbl_player_name_home.setText(mLuHomeObj.getmPlayerName());
            holder.lbl_player_number_home
                    .setText(mLuHomeObj.getmPlayerNumber());
            holder.lbl_position_home.setText(mLuHomeObj.getmPosition()
                    .toUpperCase(Locale.getDefault()));
        }

        return convertView;
    }

    class Holder {
        private TextView lbl_player_number_home, lbl_player_number_away;
        private TextViewRobotoRegular lbl_player_name_home;
        private TextViewRobotoRegular lbl_player_name_away;
        private TextViewRobotoRegular lbl_position_home;
        private TextViewRobotoRegular lbl_position_away;
        private LinearLayout liner;
    }
}
