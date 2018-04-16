package com.finalproj.finley.thyroidtracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast toast = Toast.makeText(context, "Alarm worked.", Toast.LENGTH_LONG);
        toast.show();
        Log.d(TAG, "alarm received ");

        boolean flag = intent.getExtras().getBoolean("extra");

        Intent service_intent = new Intent(context, AlarmPlayer.class);

        service_intent.putExtra("extra", flag);

        context.startService(service_intent);
    }
}