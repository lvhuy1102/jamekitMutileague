package mobi.letsplay.livescore.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import mobi.letsplay.livescore.R;

import java.util.ArrayList;

@SuppressLint("NewApi")
public class SpinnerAdapter extends BaseAdapter {

    private ArrayList<String> mArrItem;
    private LayoutInflater mInflater;
    private Activity mActivity;
    private int mCurrentRound;

    public SpinnerAdapter(Activity activity, ArrayList<String> arrItem,
                          int currentRound) {
        this.mArrItem = arrItem;
        this.mActivity = activity;
        mInflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mCurrentRound = currentRound;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mArrItem.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mArrItem.get(position);
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
            convertView = mInflater.inflate(mobi.letsplay.livescore.R.layout.item_spinner, null);
            holder.lblItem = (TextView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_item_spinner);
            holder.lblItem.setSelected(true);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        String item = "";
        try {
            item = mArrItem.get(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.toString();
        }
        if (!item.equals("") && !item.isEmpty()) {
            holder.lblItem.setText(item);

            // Signal round.
            if (mCurrentRound == (position)) {
                holder.lblItem.setTextColor(mActivity.getResources().getColor(
                        mobi.letsplay.livescore.R.color.textColor_curentRound));
            } else if (mCurrentRound > (position)) {
                holder.lblItem.setTextColor(mActivity.getResources().getColor(
                        mobi.letsplay.livescore.R.color.gray));
            } else {
                holder.lblItem.setTextColor(mActivity.getResources().getColor(
                        mobi.letsplay.livescore.R.color.black));
            }

            if (position == 0) {
                holder.lblItem.setTextColor(mActivity.getResources().getColor(
                        mobi.letsplay.livescore.R.color.yellow_dark));
            }
        }

        return convertView;
    }

    class Holder {
        private TextView lblItem;
    }
}
