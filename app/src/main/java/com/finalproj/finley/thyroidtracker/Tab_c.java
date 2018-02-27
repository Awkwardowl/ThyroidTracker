package com.finalproj.finley.thyroidtracker;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Finley on 04/01/2018.
 */

public class Tab_c extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View Fragment = inflater.inflate(R.layout.tab_c, container, false);
        GraphView graph2 = (GraphView) Fragment.findViewById(R.id.graph2);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 4),
                new DataPoint(3, 3),
                new DataPoint(4, 2)
        });
        graph2.addSeries(series);
        return Fragment;
    }
}
