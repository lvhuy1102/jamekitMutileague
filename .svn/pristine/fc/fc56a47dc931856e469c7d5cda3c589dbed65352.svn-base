package com.hcpt.multileagues.fragments;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.activities.MatchDetailActivity;
import com.hcpt.multileagues.activities.MatchDetailNotPlayed;
import com.hcpt.multileagues.activities.MatchDetailNotPlayed;
import com.hcpt.multileagues.activities.MatchDetailNotPlayedActivity;
import com.hcpt.multileagues.configs.Constants;
import com.hcpt.multileagues.configs.FruitySharedPreferences;
import com.hcpt.multileagues.configs.GlobalFunctions;
import com.hcpt.multileagues.configs.GlobalValue;
import com.hcpt.multileagues.database.DatabaseUtility;
import com.hcpt.multileagues.image.utils.ImageLoader;
import com.hcpt.multileagues.interfaces.UpdateDatabaseListener;
import com.hcpt.multileagues.objects.AlarmMatch;
import com.hcpt.multileagues.objects.MatchObj;
import com.hcpt.multileagues.services.AlarmMatchService;
import com.hcpt.multileagues.utilities.DateTimeUtility;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class TimeLineNotPlayedFragment extends Fragment {

    private static final String TAG = TimeLineNotPlayedFragment.class.getSimpleName();

    private ImageView mLogoHome, mLogoAway;
    private TextView mLblStatidum, mLblHome, mLblAway, mLblScores,
            mLblReminderOntime, mLblReminder15, mLblReminder30, mLblReminder60,
            mLblDate, mLblTime;
    private ImageView mBtnAlarm;

    private FruitySharedPreferences checkReminder;
    private MediaPlayer mMediaPlayer;
    private boolean mCheckMedia = true;
    private Long serverUptimeSeconds;

    private ImageLoader mImgLoader;

    public static MatchObj currentMatch;
    private UpdateDatabaseListener mCallBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkReminder = new FruitySharedPreferences(getActivity());

        mImgLoader = new ImageLoader(getActivity());
        currentMatch = MatchDetailNotPlayed.currentMatch;

        // Allow display option menu in this fragment.
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_timeline_not_played, container, false);

        initControls(view);
        initData();
        showHideReminder();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_save).setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            if (GlobalFunctions.checkMatchTime(currentMatch) >= 0) {
                startActivity(new Intent(getActivity(), MatchDetailActivity.class));
                getActivity().finish();
            }
        } else if (id == android.R.id.home) {
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private void initControls(View view) {
        mBtnAlarm = (ImageView) view.findViewById(R.id.img_alarm);
//        mLogoHome = (ImageView) view.findViewById(R.id.img_homeclub_timeline);
//        mLogoAway = (ImageView) view.findViewById(R.id.img_awayclub_timeline);
        mLblAway = (TextView) view.findViewById(R.id.lbl_name_team2);
        mLblAway.setSelected(true);
        mLblHome = (TextView) view.findViewById(R.id.lbl_name_team1);
        mLblHome.setSelected(true);
        mLblScores = (TextView) view.findViewById(R.id.lbl_scores_timeline);
        mLblStatidum = (TextView) view.findViewById(R.id.lbl_stadium_timeline);
        mLblStatidum.setSelected(true);
        mLblReminder15 = (TextView) view.findViewById(R.id.lbl_reminder_15);
        mLblReminder30 = (TextView) view.findViewById(R.id.lbl_reminder_30);
        mLblReminder60 = (TextView) view.findViewById(R.id.lbl_reminder_60);
        mLblReminderOntime = (TextView) view.findViewById(R.id.lbl_reminder_ontime);
        mLblDate = (TextView) view.findViewById(R.id.lbl_date_timeline);
        mLblTime = (TextView) view.findViewById(R.id.lbl_time_timeline);

        mBtnAlarm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialogAlarm(MatchDetailNotPlayed.currentMatch.getmTime(), "");
            }
        });
    }

    private void initData() {
        try {
            // Get name and home's logo.
            if (MatchDetailNotPlayed.currentMatch.getmHomeName().equals("null")) {
                mLblHome.setText("");
            } else {
                mLblHome.setText(MatchDetailNotPlayed.currentMatch.getmHomeName());
            }
//            mImgLoader.DisplayImage(MatchDetailNotPlayed.currentMatch.getmHomeImage(), mLogoHome);

            // Get name and away's logo.
            if (MatchDetailNotPlayed.currentMatch.getmAwayName().equals("null")) {
                mLblAway.setText("");
            } else {
                mLblAway.setText(MatchDetailNotPlayed.currentMatch.getmAwayName());
            }

//            mImgLoader.DisplayImage(MatchDetailNotPlayed.currentMatch.getmAwayImage(), mLogoAway);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        // Get stadium
        mLblStatidum.setText(MatchDetailNotPlayed.currentMatch.getmStadium());

        // Get date and time.
        mLblDate.setText(DateTimeUtility.convertTimeStampToDate(currentMatch.getmTime(), "EEE, MM/dd"));
        mLblTime.setText(DateTimeUtility.convertTimeStampToDate(currentMatch.getmTime(), "HH:mm"));
    }

    private void showDialogAlarm(final String times, final String titleReminder) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setTitle(DateTimeUtility.convertTimeStampToDate(currentMatch.getmTime(), "HH:mm EEE, yyyy-MM-dd"));
        dialog.setContentView(R.layout.dialog_settings_reminder);

        final Vibrator vibrate = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibrate.vibrate(GlobalValue.vibrateOnReminder * 1000);
        mMediaPlayer = new MediaPlayer();

        if (!mMediaPlayer.isPlaying()) {
            try {
                AssetFileDescriptor descriptor = getActivity().getAssets().openFd("alarm.mp3");
                mMediaPlayer.setDataSource(descriptor.getFileDescriptor(),
                        descriptor.getStartOffset(), descriptor.getLength());
                descriptor.close();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                mMediaPlayer.setLooping(true);
                mMediaPlayer.prepare();
                mMediaPlayer.setVolume(0, 0);
                mMediaPlayer.start();
                mCheckMedia = true;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Init dialog's controls.
        Button btnOk = (Button) dialog.findViewById(R.id.btn_reminder_ok);
        final Button btnCancel = (Button) dialog
                .findViewById(R.id.btn_reminder_cancel);
        final CheckBox chkBefore15 = (CheckBox) dialog
                .findViewById(R.id.chk_reminder_15);
        final CheckBox chkBefore30 = (CheckBox) dialog
                .findViewById(R.id.chk_reminder_30);
        final CheckBox chkBefore60 = (CheckBox) dialog
                .findViewById(R.id.chk_reminder_60);
        final CheckBox chkOnTime = (CheckBox) dialog
                .findViewById(R.id.chk_reminder_on_time);

//        // Display Cancel button.
//        if (checkReminder.getIntValue("check15" + MatchDetailNotPlayed.currentMatch.getmMatchId()) == Integer
//                .valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId())) {
//            chkBefore15.setChecked(true);
//            btnCancel.setVisibility(View.VISIBLE);
//        }
//        if (checkReminder.getIntValue("check30" + MatchDetailNotPlayed.currentMatch.getmMatchId()) == Integer
//                .valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId())) {
//            chkBefore30.setChecked(true);
//            btnCancel.setVisibility(View.VISIBLE);
//        }
//        if (checkReminder.getIntValue("check60" + MatchDetailNotPlayed.currentMatch.getmMatchId()) == Integer
//                .valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId())) {
//            chkBefore60.setChecked(true);
//            btnCancel.setVisibility(View.VISIBLE);
//        }
//        if (checkReminder.getIntValue("checkOntime" + MatchDetailNotPlayed.currentMatch.getmMatchId()) == Integer
//                .valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId())) {
//            chkOnTime.setChecked(true);
//            btnCancel.setVisibility(View.VISIBLE);
//        }

        chkBefore15
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {

                        checkAlarm(btnCancel, "check15", "15 is exist already",
                                times, 15, vibrate,
                                "The match will be started after 15 mins",
                                chkBefore15, chkBefore30, chkBefore60,
                                chkOnTime);
                    }
                });

        chkBefore30
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {

                        checkAlarm(btnCancel, "check30", "30 is exist already",
                                times, 30, vibrate,
                                "The match will be started after 30 mins",
                                chkBefore30, chkBefore15, chkBefore60,
                                chkOnTime);

                    }
                });

        chkBefore60
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {

                        checkAlarm(btnCancel, "check60", "60 is exist already",
                                times, 60, vibrate,
                                "The match will be started after 60 mins",
                                chkBefore60, chkBefore30, chkBefore15,
                                chkOnTime);
                    }
                });

        chkOnTime
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {

                        checkAlarm(btnCancel, "checkOntime",
                                "On time is exist already", times, 0, vibrate,
                                "The match is started", chkOnTime, chkBefore30,
                                chkBefore60, chkBefore15);

                    }
                });
        DatabaseUtility databaseUtility = new DatabaseUtility();
        if (databaseUtility.checkAlarmSetted(currentMatch.getmMatchId(), getActivity())) {
            AlarmMatch alarmMatchPrevious = databaseUtility.getAlarmMatchById(currentMatch.getmMatchId(), getActivity()).get(0);
            chkOnTime.setChecked(alarmMatchPrevious.isOnTime());
            chkBefore15.setChecked(alarmMatchPrevious.isBefore15Min());
            chkBefore60.setChecked(alarmMatchPrevious.isBefore60Min());
            chkBefore30.setChecked(alarmMatchPrevious.isBefore30Min());
            if (alarmMatchPrevious.isOnTime() || alarmMatchPrevious.isBefore15Min() || alarmMatchPrevious.isBefore30Min() || alarmMatchPrevious.isBefore60Min()) {
                btnCancel.setVisibility(View.VISIBLE);
            }
        }

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mCheckMedia == true) {
                    mMediaPlayer.stop();
                    mMediaPlayer.release();
                    mMediaPlayer = null;
                    vibrate.cancel();
                    System.runFinalizersOnExit(true);
                    mCheckMedia = false;
                }
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showHideReminder();
                AlarmMatch item = new AlarmMatch();
                item.setTime(MatchDetailNotPlayed.currentMatch.getmTime());
                item.setMatchId(MatchDetailNotPlayed.currentMatch.getmMatchId());
                item.setMatchTitle(MatchDetailNotPlayed.currentMatch.getmHomeName() + " - " + MatchDetailNotPlayed.currentMatch.getmAwayName());
                item.setOnTime(chkOnTime.isChecked());
                item.setBefore15Min(chkBefore15.isChecked());
                item.setBefore30Min(chkBefore30.isChecked());
                item.setBefore60Min(chkBefore60.isChecked());
                if (chkOnTime.isChecked() || chkBefore15.isChecked() || chkBefore30.isChecked() || chkBefore60.isChecked()) {
                    saveSettingToDatabase(item);
                } else {
                    // remove from database
                    removeSettingFromDatabase(MatchDetailNotPlayed.currentMatch.getmMatchId());
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cancelAllAlarm(MatchDetailNotPlayed.currentMatch);
                dialog.dismiss();
                showHideReminder();
            }
        });

        dialog.show();
    }

    @SuppressWarnings("deprecation")
    private void checkAlarm(final Button cancel, String strValue,
                            String strExist, String matchTime, int typeReminder,
                            Vibrator vibrate, String strStarted, final CheckBox chkChecked,
                            final CheckBox chk1, final CheckBox chk2, final CheckBox chk3) {
        if (chkChecked.isChecked()) {
            if (checkReminder.getIntValue(strValue + MatchDetailNotPlayed.currentMatch.getmMatchId()) == Integer
                    .valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId())) {

                Toast.makeText(getActivity(), strExist, Toast.LENGTH_LONG).show();
            } else {
                SimpleDateFormat formattera = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm", Locale.getDefault());
                Date oldDate;
                try {
                    oldDate = formattera.parse(matchTime);
                    long time60 = oldDate.getTime()
                            - (typeReminder * 60 * 1000);
                    serverUptimeSeconds = ((time60 - System.currentTimeMillis()) / 1000);
                    if (serverUptimeSeconds <= 0) {
                        Toast.makeText(getActivity(),
                                "Elapsed time, you can not reminder",
                                Toast.LENGTH_LONG).show();
                        chkChecked.setChecked(false);
                        if (mCheckMedia == true) {
                            mMediaPlayer.stop();
                            mMediaPlayer.release();
                            mMediaPlayer = null;
                            vibrate.cancel();
                            System.runFinalizersOnExit(true);
                            mCheckMedia = false;
                        }

                    } else {
                        // Show cancel button.
                        cancel.setVisibility(View.VISIBLE);

                        checkReminder.putIntValue(
                                strValue + MatchDetailNotPlayed.currentMatch.getmMatchId(),
                                Integer.valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId()));
                        // dialog.dismiss();
                        long abc = oldDate.getTime()
                                - (typeReminder * 60 * 1000);
                        String timeReminder60Min = strStarted;
                        setAlarm(abc, timeReminder60Min, MatchDetailNotPlayed.currentMatch.getmMatchId()
                                + "", strValue);
                        if (mCheckMedia == true) {
                            mMediaPlayer.stop();
                            mMediaPlayer.release();
                            mMediaPlayer = null;
                            vibrate.cancel();
                            System.runFinalizersOnExit(true);
                            mCheckMedia = false;
                        }
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, serverUptimeSeconds + "");
            }
        } else {
            if (checkReminder.getIntValue(strValue + MatchDetailNotPlayed.currentMatch.getmMatchId()) == Integer
                    .valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId())) {
                checkReminder.putIntValue(strValue + MatchDetailNotPlayed.currentMatch.getmMatchId(), 0);
            }

            // Hide cancel button when have no any checkbox is checked.
            if (!chk1.isChecked() && !chk2.isChecked() && !chk3.isChecked()) {
                cancel.setVisibility(View.GONE);
            }
        }
    }

    private void setAlarm(long abc, String title, String mathId, String type) {
        PendingIntent pendingIntent;
        Random r = new Random();
        Intent intent = new Intent(getActivity(), AlarmMatchService.class);
        Bundle b = new Bundle();
        b.putString("titleReminder", title);
        b.putString("matchId", mathId);
        b.putString("type", type);
        intent.putExtras(b);
        pendingIntent = PendingIntent
                .getBroadcast(getActivity(), r.nextInt(), intent, 0);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, abc, pendingIntent);
    }

    private void cancelAllAlarm(MatchObj match) {
        if (checkReminder.getIntValue("check15" + match.getmMatchId()) == Integer
                .valueOf(match.getmMatchId())) {
            checkReminder.putIntValue("check15" + match.getmMatchId(), 0);
        }
        if (checkReminder.getIntValue("check30" + match.getmMatchId()) == Integer
                .valueOf(match.getmMatchId())) {
            checkReminder.putIntValue("check30" + match.getmMatchId(), 0);
        }
        if (checkReminder.getIntValue("check60" + match.getmMatchId()) == Integer
                .valueOf(match.getmMatchId())) {
            checkReminder.putIntValue("check60" + match.getmMatchId(), 0);
        }
        if (checkReminder.getIntValue("checkOntime" + match.getmMatchId()) == Integer
                .valueOf(match.getmMatchId())) {
            checkReminder.putIntValue("checkOntime" + match.getmMatchId(), 0);
        }
    }

    private void showHideReminder() {
        if (checkReminder.getIntValue("check15" + MatchDetailNotPlayed.currentMatch.getmMatchId()) == Integer
                .valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId())) {
            mLblReminder15.setVisibility(View.VISIBLE);
        } else {
            mLblReminder15.setVisibility(View.GONE);
        }
        if (checkReminder.getIntValue("check30" + MatchDetailNotPlayed.currentMatch.getmMatchId()) == Integer
                .valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId())) {
            mLblReminder30.setVisibility(View.VISIBLE);
        } else {
            mLblReminder30.setVisibility(View.GONE);
        }
        if (checkReminder.getIntValue("check60" + MatchDetailNotPlayed.currentMatch.getmMatchId()) == Integer
                .valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId())) {
            mLblReminder60.setVisibility(View.VISIBLE);
        } else {
            mLblReminder60.setVisibility(View.GONE);
        }
        if (checkReminder.getIntValue("checkOntime" + MatchDetailNotPlayed.currentMatch.getmMatchId()) == Integer
                .valueOf(MatchDetailNotPlayed.currentMatch.getmMatchId())) {
            mLblReminderOntime.setVisibility(View.VISIBLE);
        } else {
            mLblReminderOntime.setVisibility(View.GONE);
        }
    }

    private void saveSettingToDatabase(AlarmMatch alarmMatch) {
        DatabaseUtility databaseUtility = new DatabaseUtility();
        databaseUtility.insertAlarmMatch(alarmMatch, getActivity());
        requestRefreshData();
//        mCallBack.changeDatabaseListener();


    }

    private void removeSettingFromDatabase(String matchId) {
        DatabaseUtility databaseUtility = new DatabaseUtility();
        databaseUtility.deleteAlarmMatch(matchId, getActivity());
        requestRefreshData();
//        mCallBack.changeDatabaseListener();
    }

    public void setUpdateDatabaseListener(UpdateDatabaseListener updateDatabaseListener) {
        this.mCallBack = updateDatabaseListener;
    }

    void requestRefreshData() {
        Intent intent = new Intent();
        intent.setAction(Constants.REFRESH);
        getActivity().sendBroadcast(intent);
    }
}
