package com.hcpt.multileagues.configs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.hcpt.multileagues.objects.MatchObj;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Na Pro on 10/06/2015.
 */
public class GlobalFunctions {

    public static void changeLang(Context context, String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public static void openBrowser(Activity activity, String url) {
        if (url != null && !url.equals("")) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(i);
        }
    }

    public static boolean isShowOdds() {
        return GlobalValue.prefs.getStringValue(Args.SHOW_ODDS).equals(Constants.IS_SHOW_ODDS);
    }

    public static void saveBanner(Activity activity, String image, String link) {
        if (GlobalValue.prefs == null) {
            GlobalValue.prefs = new FruitySharedPreferences(activity);
        }
        GlobalValue.prefs.putStringValue(Args.BANNER_IMAGE, image);
        GlobalValue.prefs.putStringValue(Args.BANNER_LINK, link);
    }

    public static long checkMatchTime(MatchObj matchObj) {
        Calendar cal = Calendar.getInstance();
        long currentTime = cal.getTimeInMillis() / 1000;
        long matchTime = Long.parseLong(matchObj.getmTime());

        return currentTime - matchTime;
    }
}
