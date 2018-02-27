package com.finalproj.finley.thyroidtracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Created by Finley on 04/01/2018.
 */

public class Tab_b extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View Fragment = inflater.inflate(R.layout.tab_b, container, false);


        CalendarView simpleCalendarView = (CalendarView) Fragment.findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
            simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
            {
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
                {
                    Context context = getContext();
                    Toast toast = Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(context,InputActivity.class);
                    context.startActivity(intent);
                }
            });

        Button button = (Button)Fragment.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code here
            }
        });

        return Fragment;
    }



}
