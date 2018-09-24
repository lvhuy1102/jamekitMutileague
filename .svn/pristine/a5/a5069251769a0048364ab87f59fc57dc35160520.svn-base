package com.hcpt.multileagues.services;

import java.io.IOException;

import com.hcpt.multileagues.configs.FruitySharedPreferences;
import com.hcpt.multileagues.configs.GlobalValue;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.WindowManager;

public class AlarmMatchService extends BroadcastReceiver {

    private MediaPlayer mMediaPlayer = null;
    private String mTitleReminder = "", mMatchId = "", mType = "";
    private boolean isReminder = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mTitleReminder = bundle.getString("titleReminder");
            mMatchId = bundle.getString("matchId");
            mType = bundle.getString("type");
            Log.e("TAG", "onReceive: ");
        }

        if (GlobalValue.prefs == null) {
            GlobalValue.prefs = new FruitySharedPreferences(context);
        }

        // If == 0, reminder will be canceled.
        if (GlobalValue.prefs.getIntValue(mType + mMatchId) > 0) {
            final Vibrator vibrator = (Vibrator) context
                    .getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(GlobalValue.vibrateAlarm * 1000);

            mMediaPlayer = new MediaPlayer();
            if (!mMediaPlayer.isPlaying()) {
                try {
                    AssetFileDescriptor asset = context.getAssets().openFd(
                            GlobalValue.alarmFileName);
                    mMediaPlayer.setDataSource(asset.getFileDescriptor(),
                            asset.getStartOffset(), asset.getLength());
                    asset.close();

                    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();

                    isReminder = true;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Reminder");
                builder.setMessage(mTitleReminder);

                builder.setPositiveButton("Stop",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                if (isReminder) {
                                    mMediaPlayer.stop();
                                    mMediaPlayer.release();
                                    mMediaPlayer = null;
                                    vibrator.cancel();

                                    System.runFinalizersOnExit(true);
                                    isReminder = false;
                                } else {
                                    mMediaPlayer.release();
                                    mMediaPlayer = null;
                                    vibrator.cancel();
                                    System.runFinalizersOnExit(true);
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.getWindow().setType(
                        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (isReminder) {
                            mMediaPlayer.stop();
                            mMediaPlayer.release();
                            mMediaPlayer = null;
                            vibrator.cancel();
                            System.runFinalizersOnExit(true);
                            isReminder = false;
                        }
                    }
                });
            }
        }
    }

}
