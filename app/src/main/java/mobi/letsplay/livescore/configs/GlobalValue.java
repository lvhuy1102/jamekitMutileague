package mobi.letsplay.livescore.configs;

import mobi.letsplay.livescore.objects.LeagueObj;
import mobi.letsplay.livescore.objects.RankClubsObj;

import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

import mobi.letsplay.livescore.objects.LeagueObj;
import mobi.letsplay.livescore.objects.RankClubsObj;

public class GlobalValue {

    public static ArrayList<RankClubsObj> arrRankClubs;
    public static ArrayList<LeagueObj> arrLeague;
    public static String leagueId = "1";
    public static int numberLeague = 4;// 5 league: 0-->4

    public static final String FRUITY_DROID_PREFERENCES = "FRUITY_DROID_PREFERENCES";
    public static FruitySharedPreferences prefs;
    public static int vibrateAlarm = 7;
    public static int vibrateOnReminder = 0;
    public static String alarmFileName = "alarm.mp3";
    public static String deviceTimeZone = TimeZone.getDefault().getDisplayName(
            false, TimeZone.SHORT, Locale.getDefault());
}