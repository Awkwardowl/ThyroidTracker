package com.finalproj.finley.thyroidtracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Finley on 04/01/2018.
 */

public class Tab_b extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View Fragment = inflater.inflate(R.layout.tab_b, container, false);
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        Context context = getContext();
        Toast toast = Toast.makeText(context,date +","+ dayOfWeek,Toast.LENGTH_LONG);
        toast.show();

        Calendar now = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("dd");

        String[] days = new String[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta );
        for (int i = 0; i < 7; i++)
        {
            days[i] = format.format(now.getTime());
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        System.out.println(Arrays.toString(days));


                final TextView Sunday = (TextView) Fragment.findViewById(R.id.Sunday);
                Sunday.setText(days[6]);
                Sunday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

                final TextView Monday = (TextView) Fragment.findViewById(R.id.Monday);
                Monday.setText(days[0]);
                Monday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

                final TextView Tuesday = (TextView) Fragment.findViewById(R.id.Tuesday);
                Tuesday.setText(days[1]);
                Tuesday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

                final TextView Wednesday = (TextView) Fragment.findViewById(R.id.Wednesday);
                Wednesday.setText(days[2]);
                Wednesday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

                final TextView Thursday = (TextView) Fragment.findViewById(R.id.Thursday);
                Thursday.setText(days[3]);
                Thursday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

                final TextView Friday = (TextView) Fragment.findViewById(R.id.Friday);
                Friday.setText(days[4]);
                Friday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

                final TextView Saturday = (TextView) Fragment.findViewById(R.id.Saturday);
                Saturday.setText(days[5]);
                Saturday.setBackgroundColor(getResources().getColor(R.color.LightGreen));

        switch (dayOfWeek) {
            case 1:
                Sunday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 2:
                Monday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 3:
                Tuesday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 4:
                Wednesday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 5:
                Thursday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 6:
                Friday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
            case 7:
                Saturday.setBackgroundColor(getResources().getColor(R.color.HighlightGreen));
                break;
        }

//        CalendarView simpleCalendarView = (CalendarView) Fragment.findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
//            simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
//            {
//                @Override
//                public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth)
//                {
//                    Context context = getContext();
//                    Toast toast = Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT);
//                    toast.show();
//                    Intent intent = new Intent(context,InputActivity.class);
//                    context.startActivity(intent);
//                }
//            });
//
//        button button = (button)Fragment.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Code here
//            }
//        });

        return Fragment;
    }



}
