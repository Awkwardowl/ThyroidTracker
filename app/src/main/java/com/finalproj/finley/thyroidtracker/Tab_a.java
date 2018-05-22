package com.finalproj.finley.thyroidtracker;
import android.content.Context;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

/**
 * Created by Finley on 04/01/2018.
 */

public class Tab_a extends Fragment {

    Boolean FirstView = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !FirstView) {
            graphView.removeAllSeries();

            series=new LineGraphSeries<>(getDataPoint(spinner.getSelectedItem().toString()));
            series.setColor(getResources().getColor(android.R.color.holo_blue_dark));
            graphView.addSeries(series);

            series2=new LineGraphSeries<>(getDataPoint(spinner2.getSelectedItem().toString()));
            series2.setColor(getResources().getColor(android.R.color.holo_green_dark));
            graphView.addSeries(series2);

            series3=new LineGraphSeries<>(getDataPoint(spinner3.getSelectedItem().toString()));
            series3.setColor(getResources().getColor(android.R.color.holo_red_dark));
            graphView.addSeries(series3);
        }
    }

    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series2;
    LineGraphSeries<DataPoint> series3;
    Date d1;
    Date d2;
    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;


    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM");
    SimpleDateFormat sdfo= new SimpleDateFormat("dd/MM");



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View Fragment = inflater.inflate(R.layout.tab_a, container, false);



        final Context context;
        context = getContext();

        //- TimeUnit.DAYS.toMillis(30)

        String[] ResourceNames = getResources().getStringArray(R.array.Resulttypes);
        for (String s: ResourceNames){
            try {
                String FileName="/" + s+".csv";
                Log.d(TAG, "xxxxxxxxxxxxxx"+FileName);
                CSVWriter writer = new CSVWriter(new FileWriter(context.getFilesDir().getPath().toString() + FileName, false), '\t');
                for (int i=1; i<=90; i++)
                {
                    int Temp = i + 20;
                    String Enter = "";
                    if (i<10)
                    {
                        Enter = Math.ceil(20+Math.random()*50)+",0" + Temp + "/" + "02";
                    }
                    else
                    {
                        Enter = Math.ceil(20+Math.random()*50)+"," + Temp + "/" + "02";
                    }

                    String[] entries = Enter.split(",");
                    writer.writeNext(entries);
                }

                writer.close();
            } catch(IOException ie) {
                ie.printStackTrace();
            }
        }





        spinner = (Spinner) Fragment.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.Resulttypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner2 = (Spinner) Fragment.findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(context, R.array.Resulttypes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner) Fragment.findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(context, R.array.Resulttypes, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinner.setSelection(0);
        spinner2.setSelection(1);
        spinner3.setSelection(2);



        final CheckBox checkBox1 = (CheckBox) Fragment.findViewById(R.id.checkBox);
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkBox1.isChecked()){
                    spinner.setEnabled(true);
                    graphView.addSeries(series);

                }else{
                    spinner.setEnabled(false);
                    graphView.removeSeries(series);
                }
            }
        });

        final CheckBox checkBox2 = (CheckBox) Fragment.findViewById(R.id.checkBox2);
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkBox2.isChecked()){
                    spinner2.setEnabled(true);
                    graphView.addSeries(series2);
                }else{
                    spinner2.setEnabled(false);
                    graphView.removeSeries(series2);
                }
            }
        });

        final CheckBox checkBox3 = (CheckBox) Fragment.findViewById(R.id.checkBox3);
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkBox3.isChecked()){
                    spinner3.setEnabled(true);
                    graphView.addSeries(series3);
                }else{
                    spinner3.setEnabled(false);
                    graphView.removeSeries(series3);
                }
            }
        });

        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                graphView.removeSeries(series);
                if (spinner != null && spinner.getSelectedItem()!= null ){
                    Log.d(TAG, "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+spinner.getSelectedItem().toString());
                    series=new LineGraphSeries<>(getDataPoint(spinner.getSelectedItem().toString()));
                    series.setColor(getResources().getColor(android.R.color.holo_blue_dark));
                    graphView.addSeries(series);

                } else {
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                graphView.removeSeries(series2);
                if (spinner2 != null && spinner2.getSelectedItem()!= null ){
                    series2 = new LineGraphSeries<>(getDataPoint(spinner2.getSelectedItem().toString()));
                    series2.setColor(getResources().getColor(android.R.color.holo_green_dark));
                    graphView.addSeries(series2);
                } else {

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                graphView.removeSeries(series3);
                if (spinner3 != null && spinner3.getSelectedItem()!= null ){
                    series3 = new LineGraphSeries<>(getDataPoint(spinner3.getSelectedItem().toString()));
                    series3.setColor(getResources().getColor(android.R.color.holo_red_dark));
                    graphView.addSeries(series3);
                } else {

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        graphView=(GraphView) Fragment.findViewById(R.id.graph);


        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX)
                {
                    return sdfo.format(new Date((long) value));
                } else
                    return super.formatLabel(value, isValueX);
            }
        });

//        series=new LineGraphSeries<>(getDataPoint());
//        graphView.addSeries(series);

// set manual Y bounds
//        graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        graphView.getGridLabelRenderer().setNumHorizontalLabels(5);

        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(100);

        graphView.getViewport().setXAxisBoundsManual(true);
        d1 = new Date();
        graphView.getViewport().setMinX(d1.getTime()- TimeUnit.DAYS.toMillis(30));
        graphView.getViewport().setMaxX(d1.getTime());

//        graphView.getViewport().setScrollable(true);
        graphView.getViewport().setScalable(true);

//        graphView.refreshDrawableState();
        FirstView = false;
        return Fragment;
    }

    private DataPoint[] getDataPoint(String File) {
        Context context = getContext();
        String FileName="/" + File+".csv";
        Log.d(TAG, "xxxxxxxxxxxxxx"+FileName);
        ArrayList<String[]> List = new ArrayList<>();

        try {

            CSVReader reader = new CSVReader(new FileReader(context.getFilesDir().getPath().toString() + FileName), '\t' ,'"',0);
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {
                if (nextline != null) {
//                    Log.d(TAG,nextline[0]);
                    Log.d(TAG, Arrays.toString(nextline)+"xxxxxxxx\n");
                    List.add(nextline);
                }

            }
        }catch(IOException ie) {
            ie.printStackTrace();
        }
        DataPoint[] dp = new DataPoint[List.size()];


        for(int i = 0; i<List.size();i++)
//        {
            try {
                String[] Temp = List.get(i);
                Date date = sdf.parse(Temp[1]);
//                    date = sdf.parse(Temp[1]);
                DataPoint D = new DataPoint(date.getTime(),Double.parseDouble(Temp[0]));
                dp[i] = D;
//                if (i==0)
//                {
//                    d1 = date;
//                }
//                if (i==29)
//                {
//                    d2 = date;
//                }
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
//
//        }
//                new DataPoint(new Date().getTime(), 1.3),
//                new DataPoint(new Date().getTime()+86400000, 3.4),
//                new DataPoint(new Date().getTime()+(2*86400000), 2.7),
//                new DataPoint(new Date().getTime()+(3*86400000), 2.7),
//                new DataPoint(new Date().getTime()+(4*86400000), 2.7),
//                new DataPoint(new Date().getTime()+(5*86400000), 2.7),
//                new DataPoint(new Date().getTime()+(6*86400000), 2.7)
        return dp;
    }


}