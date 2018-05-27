package com.finalproj.finley.thyroidtracker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.app.TimePickerDialog;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.Manifest.permission_group.CALENDAR;
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
    LineGraphSeries<DataPoint> series2;
    LineGraphSeries<DataPoint> series3;
    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View Fragment = inflater.inflate(R.layout.tab_c, container, false);
        final Context context;
        context = getContext();

        final Switch toggle = (Switch) Fragment.findViewById(R.id.switch1);
        final TextView textView10 = (TextView)  Fragment.findViewById(R.id.textView10);
        final TextView textView2 = (TextView)  Fragment.findViewById(R.id.textView2);
        final EditText editText3 = (EditText) Fragment.findViewById(R.id.editText3);
        final EditText editText2 = (EditText) Fragment.findViewById(R.id.editText2);
        final TextView Output = (TextView) Fragment.findViewById(R.id.CurrentAlarm);

        textView10.setEnabled(false);
        editText3.setEnabled(false);

        String[] ResourceNames = getResources().getStringArray(R.array.LabsResultsTypes);
        for (String s: ResourceNames)
        {
            try
            {
                String FileName="/"+s+".csv";
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE, -91);
                Date TempDate;
                String DateString;

                CSVWriter writer = new CSVWriter(new FileWriter(context.getFilesDir().getPath().toString() + FileName, false), '\t');
                for (int i=1; i<=3; i++)
                {
                    cal.add(Calendar.DATE, 30);
                    TempDate = cal.getTime();
                    DateString = sdf.format(TempDate);
                    String Enter = "";
                    Enter = Math.ceil(20+Math.random()*50)+","+ DateString;
                    String[] entries = Enter.split(",");
                    writer.writeNext(entries);
                }
                writer.close();
            } catch(IOException ie) {
                ie.printStackTrace();
            }
        }

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

                        if (calendar.getTimeInMillis() < System.currentTimeMillis())
                        {
                            calendar.add(Calendar.DATE, 1);
                        }

                        String date = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
                        String date2 = String.valueOf(calendar.get(Calendar.MINUTE));

                        Log.d(TAG, date + ":" + date2);

                        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,alarmIntent);

                        Context context = getContext();
                        Toast.makeText(context, "Alarm set for: " + date + ":" + date2, Toast.LENGTH_SHORT).show();



                                try
                                {
                                    CSVWriter writer = new CSVWriter(new FileWriter(context.getFilesDir().getPath().toString() + "/LastAlarm.csv", false), '\t');
                                    String Enter = "";
                                    Enter = date+":"+date2+","+editText3.getText()+","+editText2.getText()+","+toggle.isChecked();
                                    String[] entries = Enter.split(",");
                                    writer.writeNext(entries);
                                    writer.close();
                                }
                                catch(IOException ie)
                                {
                                    ie.printStackTrace();
                                }



                        try {

                            CSVReader reader = new CSVReader(new FileReader(context.getFilesDir().getPath().toString() + "/LastAlarm.csv"), '\t' ,'"',0);
                            String[] nextline;

                            while ((nextline = reader.readNext()) != null) {
                                if (nextline != null) {
                                    if(nextline[3].equals("false"))
                                    {
                                        Output.setText("Alarm set for " + nextline[0]+".\n" +"Daily: "+nextline[2]);
                                    }
                                    else
                                    {
                                        Output.setText("Alarm set for " + nextline[0]+".\n" +"Alternating: "+nextline[2]+"/"+nextline[1]);
                                    }

                                }

                            }
                        }catch(IOException ie) {
                            ie.printStackTrace();
                        }

                    }



                }, hour, minute, true);
                timePickerDialog.setTitle("Select Alarm Time:");
                timePickerDialog.show();


              }
        });

        File f = new File("/LastAlarm.csv");
        if(f.exists() && !f.isDirectory()) {
            try {

                CSVReader reader = new CSVReader(new FileReader(context.getFilesDir().getPath().toString() + "/LastAlarm.csv"), '\t', '"', 0);
                String[] nextline;

                while ((nextline = reader.readNext()) != null) {
                    if (nextline != null) {
                        if (nextline[3].equals("false"))
                        {
                            Output.setText("Alarm set for " + nextline[0]+".\n" +"Daily: "+nextline[2]);
                        }
                        else
                        {
                            Output.setText("Alarm set for " + nextline[0]+".\n" +"Alternating: "+nextline[2]+"/"+nextline[1]);
                        }

                    }

                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }

        final Button button2 = (Button) Fragment.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(final View v) {
                alarmManager.cancel(alarmIntent);

                intent.putExtra("extra", false);

                getContext().sendBroadcast(intent);
            }


        });

        final Spinner spinner = (Spinner) Fragment.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.LabsResultsTypes, android.R.layout.simple_spinner_item);

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

        series=new LineGraphSeries<>(getDataPoint("T4"));
        series2=new LineGraphSeries<>(getDataPoint("TSH"));
        series3=new LineGraphSeries<>(getDataPoint("T3"));
        graphView.addSeries(series);
        graphView.addSeries(series2);
        graphView.addSeries(series3);
        series.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        series.setColor(getResources().getColor(android.R.color.holo_green_dark));
        series.setColor(getResources().getColor(android.R.color.holo_red_dark));


        final EditText editText = (EditText) Fragment.findViewById(R.id.editText);
        final Button button3 = (Button) Fragment.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(final View v) {

                SimpleDateFormat sdf= new SimpleDateFormat("dd/MM");
                final String StringDate = sdf.format(new Date());

                String Type = String.valueOf(spinner.getSelectedItem());
                Double data = Double.valueOf(editText.getText().toString());

                Log.d("D",Type);

                Context context = getContext();
                String[] LastDateLine;
                String LastDate=null;
                List<String[]> File = null;
                final String FileName = "/" + Type + ".csv";
                    try
                    {
                        CSVReader reader = new CSVReader(new FileReader(context.getFilesDir().getPath().toString() + FileName), '\t', '"', 0);
                        File = reader.readAll();
                        LastDateLine =File.get(File.size()-1);
                        LastDate = LastDateLine[1];
                    } catch (IOException ie) {
                        ie.printStackTrace();
                    }



                    if (!(LastDate.equals(StringDate)))
                    {
                        try {
                            CSVWriter writer = new CSVWriter(new FileWriter(context.getFilesDir().getPath().toString() + FileName, true), '\t');
                            String Enter = editText.getText() + "," + StringDate;
                            String[] entries = Enter.split(",");
                            writer.writeNext(entries);
                            Toast.makeText(context, Type+" data Submitted for " + StringDate, Toast.LENGTH_SHORT).show();
                            writer.close();
                        } catch (IOException ie) {
                            ie.printStackTrace();
                        }
                    }
                    else
                    {
                        try{
                            CSVWriter writer = new CSVWriter(new FileWriter(context.getFilesDir().getPath().toString() + FileName, false), '\t');
                            File.remove(File.size()-1);
                            writer.writeAll(File);
                            writer.close();
                            CSVWriter writer2 = new CSVWriter(new FileWriter(context.getFilesDir().getPath().toString() + FileName, true), '\t');
                            String Enter = editText.getText() + "," + StringDate;
                            String[] entries = Enter.split(",");
                            writer2.writeNext(entries);
                            writer2.close();
                            Toast.makeText(context, Type+" data Resubmitted for " + StringDate, Toast.LENGTH_SHORT).show();
                        } catch (IOException ie) {
                            ie.printStackTrace();
                        }
                    }

                graphView.removeAllSeries();

                series=new LineGraphSeries<>(getDataPoint("T4"));
                series.setColor(getResources().getColor(android.R.color.holo_blue_dark));
                graphView.addSeries(series);

                series2=new LineGraphSeries<>(getDataPoint("TSH"));
                series2.setColor(getResources().getColor(android.R.color.holo_green_dark));
                graphView.addSeries(series2);

                series3=new LineGraphSeries<>(getDataPoint("T3"));
                series3.setColor(getResources().getColor(android.R.color.holo_red_dark));
                graphView.addSeries(series3);

            }
        });




        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (toggle.isChecked())
                {
                    textView10.setEnabled(true);
                    editText3.setEnabled(true);
                }
                else
                {
                    textView10.setEnabled(false);
                    editText3.setEnabled(false);
                }
            }
        });



        return Fragment;
    }

    private DataPoint[] getDataPoint(String Type) {
        Context context = getContext();
        String FileName="/" + Type+".csv";
        ArrayList<String[]> List = new ArrayList<>();

        try {

            CSVReader reader = new CSVReader(new FileReader(context.getFilesDir().getPath().toString() + FileName), '\t' ,'"',0);
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {
                if (nextline != null) {
                    Log.d(TAG, Arrays.toString(nextline)+"xxxxxxxx\n");
                    List.add(nextline);
                }

            }
        }catch(IOException ie) {
            ie.printStackTrace();
        }
        DataPoint[] dp = new DataPoint[List.size()];


        for(int i = 0; i<List.size();i++)
            try {
                String[] Temp = List.get(i);
                Date date = sdf.parse(Temp[1]);
                DataPoint D = new DataPoint(date.getTime(),Double.parseDouble(Temp[0]));
                dp[i] = D;
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        return dp;
    }
}
