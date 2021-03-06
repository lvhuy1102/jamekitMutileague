package mobi.letsplay.livescore.adapters;

import android.annotation.SuppressLint;
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
import mobi.letsplay.livescore.objects.FeedsObj;
import mobi.letsplay.livescore.utilities.DateTimeUtility;
import mobi.letsplay.livescore.utilities.StringUtility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import mobi.letsplay.livescore.image.utils.ImageLoader;
import mobi.letsplay.livescore.objects.FeedsObj;
import mobi.letsplay.livescore.utilities.StringUtility;

public class NewsAdapter extends BaseAdapter {

    private ArrayList<FeedsObj> mArrNews;
    private LayoutInflater mInflater;
    private FeedsObj mNewsObj;
    private ImageLoader mImageLoader;
    private Calendar mCal;
    Date currentDate, pubDate;
    private Context context;


    public NewsAdapter(Context context, ArrayList<FeedsObj> arrNews) {
        this.mArrNews = arrNews;
        this.context = context;
        mImageLoader = new ImageLoader(context.getApplicationContext());
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mCal = Calendar.getInstance();
    }

    @Override
    public int getCount() {
        try {
            return mArrNews.size();
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return mArrNews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = mInflater.inflate(mobi.letsplay.livescore.R.layout.custom_news_card, parent, false);
            holder.lblNewsTitle = (TextView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_news_title);
            holder.lblNewsTitle.setSelected(true);
            holder.imgNews = (ImageView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.img_news);
            holder.lblNewsTime = (TextView) convertView
                    .findViewById(mobi.letsplay.livescore.R.id.lbl_news_time);
            holder.lblDescription = (TextView) convertView.findViewById(mobi.letsplay.livescore.R.id.lbl_description);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        mNewsObj = mArrNews.get(position);
        if (mNewsObj != null) {
            mImageLoader.DisplayImage(mNewsObj.getmUriImg(), holder.imgNews);
            holder.lblNewsTitle.setSelected(true);
            holder.lblNewsTitle.setText(mNewsObj.getmNewsTitle());
            holder.lblDescription.setText(mNewsObj.getmDescription());
//            Glide.with(context).load(mNewsObj.getmUriImg()).into(holder.imgNews);
            mImageLoader.DisplayImage(mNewsObj.getmUriImg(), holder.imgNews);

            // Display date.
            String strDate = "";
            String strPubDate = StringUtility.formatDate(
                    "EEE, dd MMM yyyy HH:mm:ss ZZZ", mNewsObj.getmPubDate(),
                    "MM-dd-yyyy");
            int year = Integer.parseInt(strPubDate.split("-")[2]);
            int day = Integer.parseInt(strPubDate.split("-")[1]);
            int month = Integer.parseInt(strPubDate.split("-")[0]);
            pubDate = new Date(year, month, day);
            currentDate = new Date(mCal.get(Calendar.YEAR),
                    (mCal.get(Calendar.MONTH) + 1), mCal.get(Calendar.DATE));

//            if (DateTimeUtility
//                    .getDateDiff(currentDate, pubDate, TimeUnit.DAYS) == 0) {
//                strDate = parent.getContext().getString(R.string.today);
//            } else if (DateTimeUtility.getDateDiff(currentDate, pubDate,
//                    TimeUnit.DAYS) == 1) {
//                strDate = parent.getContext().getString(R.string.yesterday);
//            } else if (DateTimeUtility.getDateDiff(currentDate, pubDate,
//                    TimeUnit.DAYS) == 2) {
//                strDate = parent.getContext().getString(R.string.two_days_ago);
//            } else if (DateTimeUtility.getDateDiff(currentDate, pubDate,
//                    TimeUnit.DAYS) == 3) {
//                strDate = parent.getContext().getString(R.string.three_days_ago);
//            } else {
            strDate = StringUtility.formatDate("MM-dd-yyyy", strPubDate,
                    "MMM-dd-yyyy");
//            }

            holder.lblNewsTime.setText(strDate);
        }

        return convertView;
    }

    class Holder {
        TextView lblNewsTitle, lblNewsTime, lblDescription;
        ImageView imgNews;
    }
}
