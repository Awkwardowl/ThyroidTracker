package com.finalproj.finley.thyroidtracker;
import android.content.Context;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Finley on 04/01/2018.
 */

public class Tab_a extends Fragment {

    GraphView graphView;
    LineGraphSeries<DataPoint> series;
    SimpleDateFormat sdf= new SimpleDateFormat("dd:MM");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View Fragment = inflater.inflate(R.layout.tab_a, container, false);

        graphView=(GraphView) Fragment.findViewById(R.id.graph);


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

// set manual Y bounds
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(100);

//        GraphView graph = (GraphView) Fragment.findViewById(R.id.graph);
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//                    new DataPoint(0, 1),
//                    new DataPoint(1, 5),
//                    new DataPoint(2, 3),
//                    new DataPoint(3, 2),
//                    new DataPoint(4, 6)
//            });
//            graph.addSeries(series);

        return Fragment;
    }

    private DataPoint[] getDataPoint() {
        Context context = getContext();
        String FileName="/Activity.csv";
        ArrayList<String[]> List = new ArrayList<>();

        try {
            CSVWriter writer = new CSVWriter(new FileWriter(context.getFilesDir().getPath().toString() + "/Activity.csv", false), '\t');
            for (int i=1; i<=30; i++)
            {
                String Enter = "";
                if (i<10)
                {
                    Enter = Math.ceil(Math.random()*100)+"#0" + i + ":" + "05";
                }
                else
                {
                    Enter = Math.ceil(Math.random()*100)+"#" + i + ":" + "05";
                }

                String[] entries = Enter.split("#");
                writer.writeNext(entries);
            }

            writer.close();
        } catch(IOException ie) {
            ie.printStackTrace();
        }

        try {

            CSVReader reader = new CSVReader(new FileReader(context.getFilesDir().getPath().toString() + FileName), '\t' ,'"',0);
            String[] nextline;

            while ((nextline = reader.readNext()) != null) {
                if (nextline != null) {
                    Log.d(TAG,nextline[0]);
                    Log.d(TAG, Arrays.toString(nextline)+"xxxxxxxx\n");
                    List.add(nextline);
                }

            }
        }catch(IOException ie) {
            ie.printStackTrace();
        }
        DataPoint[] dp = new DataPoint[List.size()];
                for(int i = 0; i<List.size();i++)
                {
                    try {
                        String[] Temp = List.get(i);
                        Date date = sdf.parse(Temp[1]);
//                    date = sdf.parse(Temp[1]);
                        DataPoint D = new DataPoint(date.getTime(),Double.parseDouble(Temp[0]));
                        dp[i] = D;
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }

                }
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
