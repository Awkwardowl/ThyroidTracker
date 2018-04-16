package com.finalproj.finley.thyroidtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.TimePickerDialog;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

/**
 * Created by Finley on 04/01/2018.
 */

public class Tab_c extends Fragment {
    int hour;
    int minute;
    AlarmManager alarmManager;
    PendingIntent alarmIntent;
    TimePickerDialog timePickerDialog;
    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    SimpleDateFormat sdf= new SimpleDateFormat("dd:MM");
    LineGraphSeries<DataPoint> TSH ;
    LineGraphSeries<DataPoint> T4 ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View Fragment = inflater.inflate(R.layout.tab_c, container, false);
        final Context context;
        context = getContext();



        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(context, Alarm_Receiver.class);


        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        final Button button = (Button) Fragment.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
        public void onClick(final View v) {

                timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Log.d(TAG,selectedHour + ":" + selectedMinute);
                        hour = selectedHour;
                        minute = selectedMinute;
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);

                        intent.putExtra("extra", true);

                        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                        String date = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
                        String date2 = String.valueOf(calendar.get(Calendar.MINUTE));

                        Log.d(TAG, date + ":" + date2);

                        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,alarmIntent);


//                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),1000 * 60 * 20, alarmIntent);
//                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                    }



                }, hour, minute, true);
                timePickerDialog.setTitle("Select Alarm Time:");
                timePickerDialog.show();


              }
        });

        final Button button2 = (Button) Fragment.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(final View v) {
                alarmManager.cancel(alarmIntent);

                intent.putExtra("extra", false);

                getContext().sendBroadcast(intent);
            }


        });

        final Spinner spinner = (Spinner) Fragment.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.MedicationTypes, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        graphView=(GraphView) Fragment.findViewById(R.id.graph2);


        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX)
                {
                    return sdf.format(new Date((long) value));
                } else
                    return super.formatLabel(value, isValueX);
            }
        });

        series=new LineGraphSeries<>(getDataPoint());
        graphView.addSeries(series);


        final EditText editText = (EditText) Fragment.findViewById(R.id.editText);
        final Button button3 = (Button) Fragment.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(final View v) {
                String Type = String.valueOf(spinner.getSelectedItem());
                Double data = Double.valueOf(editText.getText().toString());
                Log.d("D",Type);
                if (Type.equals("T4"))
                {

                }
                else if (Type.equals("TSH"))
                {

                }
            }
        });


        return Fragment;
    }

    private DataPoint[] getDataPoint() {
        DataPoint[] dp=new DataPoint[]{
                new DataPoint(new Date().getTime(), 1.3),
                new DataPoint(new Date().getTime()+1, 3.4),
                new DataPoint(new Date().getTime()+2, 2.7)

        };
        return dp;
    }
}
