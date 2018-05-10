package com.finalproj.finley.thyroidtracker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

public class AlarmPlayer extends Service{

    MediaPlayer media_song;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Called",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");




        boolean flag = intent.getExtras().getBoolean("extra");

        if (flag==true){
            startId = 1;
        }
        else if (flag == false)
        {
            startId = 0;
        }
        else
        {
            startId = 0;
        }

        if (!this.isRunning && startId == 1) {
            media_song = MediaPlayer.create(this, R.raw.kids);
            media_song.start();
            this.isRunning = true;
            startId = 0;

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent intent_Tab_c = new Intent(this.getApplicationContext(), Tab_c.class);
            PendingIntent pendingIntent_main_actvity = PendingIntent.getActivity(this, 0, intent_Tab_c,0);

            Calendar date = Calendar.getInstance();
            int day = date.get(Calendar.DAY_OF_YEAR) ;
            Log.d(TAG, String.valueOf(day));
            if (day%2==0)
            {
                Notification AlarmPopup = new Notification.Builder(this).setContentTitle("Take your Medication").setContentText("125mcg Levothyroxine").setContentIntent(pendingIntent_main_actvity).setAutoCancel(true).setSmallIcon(R.mipmap.ic_launcher).build();
                notificationManager.notify(0, AlarmPopup);
            } else {
                Notification AlarmPopup = new Notification.Builder(this).setContentTitle("Take your Medication").setContentText("100mcg Levothyroxine").setContentIntent(pendingIntent_main_actvity).setAutoCancel(true).setSmallIcon(R.mipmap.ic_launcher).build();
                notificationManager.notify(0, AlarmPopup);
            }



        } else if (this.isRunning && startId == 0) {

            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            startId = 0;


        } else if (!this.isRunning && startId == 0) {

            this.isRunning = false;
            startId = 0;


        } else if (this.isRunning && startId == 1) {


            this.isRunning = true;
            startId = 1;

        } else {

        }

//        media_song = MediaPlayer.create(this, R.raw.kids);
//        media_song.start();
        return START_NOT_STICKY;
    }
}