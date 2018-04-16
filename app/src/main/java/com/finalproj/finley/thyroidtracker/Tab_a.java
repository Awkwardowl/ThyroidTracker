package com.finalproj.finley.thyroidtracker;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        graphView.getViewport().setMaxY(5);

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
        DataPoint[] dp=new DataPoint[]{
                new DataPoint(new Date().getTime(), 1.3),
                new DataPoint(new Date().getTime()+86400000, 3.4),
                new DataPoint(new Date().getTime()+(2*86400000), 2.7),
                new DataPoint(new Date().getTime()+(3*86400000), 2.7),
                new DataPoint(new Date().getTime()+(4*86400000), 2.7),
                new DataPoint(new Date().getTime()+(5*86400000), 2.7),
                new DataPoint(new Date().getTime()+(6*86400000), 2.7)

                };
        return dp;
    }
}
