package com.finalproj.finley.thyroidtracker;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
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

        if (!this.isRunning && startId == 1) //Alarm not running and should be playing
        {
            media_song = MediaPlayer.create(this, R.raw.kids); //Creates a media player
            media_song.start(); //Starts the media player
            this.isRunning = true;
            startId = 0;

            //Creates the notification manager and sets the context to allow the user to be notified regarding what to take.
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent intent_Tab_c = new Intent(this.getApplicationContext(), Tab_c.class);
            PendingIntent pendingIntent_main_actvity = PendingIntent.getActivity(this, 0, intent_Tab_c,0);

            Calendar date = Calendar.getInstance();
            int day = date.get(Calendar.DAY_OF_YEAR) ;

            String daily=null;
            String alt=null;
            String alternate=null;

            try {
                Context context = getApplicationContext();

                CSVReader reader = new CSVReader(new FileReader(context.getFilesDir().getPath().toString() + "/LastAlarm.csv"), '\t', '"', 0);
                String[] nextline;



                while ((nextline = reader.readNext()) != null) {
                    if (nextline != null)
                    {
                            daily = nextline[2];
                            alt = nextline[1];
                            alternate = nextline[3];
                    }

                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }

            if(alternate.equals("true")) //For each alternate day
            {
                if (day%2==0) //for each even calendar day
                {
                    Notification AlarmPopup = new Notification.Builder(this)
                            .setContentTitle("Take your Medication")
                            .setContentText(daily) //Normal Notification
                            .setContentIntent(pendingIntent_main_actvity)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.thyroidlogo)
                            .build();

                    notificationManager.notify(0, AlarmPopup);
                } else { //every odd day
                    Notification AlarmPopup = new Notification.Builder(this)
                            .setContentTitle("Take your Medication")
                            .setContentText(alt)    //Alt notification
                            .setContentIntent(pendingIntent_main_actvity)
                            .setAutoCancel(true)
                            .setSmallIcon(R.drawable.thyroidlogo)
                            .build();

                    notificationManager.notify(0, AlarmPopup);
                }
            }
            else //For every day
            {
                Notification AlarmPopup = new Notification.Builder(this)
                        .setContentTitle("Take your Medication")
                        .setContentText(daily) //Normal Notification
                        .setContentIntent(pendingIntent_main_actvity)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.thyroidlogo)
                        .build();

                notificationManager.notify(0, AlarmPopup);
            }

        }
        else if (this.isRunning && startId == 0) //Is running and stop pressed
        {
            media_song.stop();
            media_song.reset();
            this.isRunning = false;
            startId = 0;
        }
        else if (!this.isRunning && startId == 0) //Not running and stop pressed.
        {
            this.isRunning = false;
            startId = 0;
        }
        else if (this.isRunning && startId == 1)  //Is runining and set pressed.
        {
            this.isRunning = true;
            startId = 1;

        } else {

        }

//        media_song = MediaPlayer.create(this, R.raw.kids);
//        media_song.start();
        return START_NOT_STICKY;
    }
}
