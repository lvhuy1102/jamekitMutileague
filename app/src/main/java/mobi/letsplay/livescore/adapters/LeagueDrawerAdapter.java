package mobi.letsplay.livescore.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import mobi.letsplay.livescore.R;
import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.LeagueObj;

import java.util.ArrayList;

import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.LeagueObj;

public class LeagueDrawerAdapter extends BaseAdapter {

    private Activity mActivity;
    private LayoutInflater mInflater;
    private ImageLoader mImgLoader;
    private ArrayList<LeagueObj> mArrLeague;
    private int mPosition;

    public LeagueDrawerAdapter(Activity activity, ArrayList<LeagueObj> arrLeague) {
        this.mArrLeague = arrLeague;
        mInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mImgLoader = new ImageLoader(activity);
        this.mActivity = activity;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        try {
            return mArrLeague.size();
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mArrLeague.get(position);
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
            convertView = mInflater.inflate(mobi.letsplay.livescore.R.layout.item_drawer_league, null);
            holder.logoLeague = (ImageView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.logo_league);
            holder.lblName = (TextView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_league);
            holder.lblName.setSelected(true);
            holder.separateBar = convertView.findViewById(mobi.letsplay.livescore.R.id.vw_separate);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        LeagueObj league = mArrLeague.get(position);
        if (league != null) {
            if (position == 0){
                holder.lblName.setText(mActivity.getString(mobi.letsplay.livescore.R.string.home));
            }else {
                holder.lblName.setText(league.getmName());
            }


            if (!league.getmLogo().equals("")) {
               Glide.with(mActivity).load(league.getmLogo()).into(holder.logoLeague);
            } else {
                holder.logoLeague.setImageResource(mobi.letsplay.livescore.R.drawable.ic_close_matches);
            }

            // Show/hide separate bar
            if (position == (mArrLeague.size() - 1)) {
                holder.separateBar.setVisibility(View.GONE);
            } else {
                holder.separateBar.setVisibility(View.VISIBLE);
            }
        }

        return convertView;
    }

    class Holder {
        ImageView logoLeague;
        TextView lblName;
        View separateBar;
    }

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }
}
