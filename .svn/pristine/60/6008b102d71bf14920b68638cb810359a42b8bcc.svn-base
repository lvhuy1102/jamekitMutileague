package com.hcpt.multileagues.services;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.hcpt.multileagues.R;
import com.hcpt.multileagues.configs.Constants;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String title = intent.getStringExtra(Constants.KEY_TITLE_ALARM);
        String msg = intent.getStringExtra(Constants.KEY_MESSAE_ALARM);
        Log.d("ALARM", msg);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(context);
        notificationCompat.setContentTitle(title);
        notificationCompat.setVibrate(new long[]{1000, 1000});
        notificationCompat.setSmallIcon(R.drawable.ic_launcher);
        notificationCompat.setSound(defaultSoundUri);
        notificationCompat.setStyle(new NotificationCompat.BigTextStyle().bigText(msg));
        notificationCompat.setContentText(msg);
        notificationCompat.setAutoCancel(true);

        notificationManager.notify(0 /*id*/, notificationCompat.build());


    }
}
