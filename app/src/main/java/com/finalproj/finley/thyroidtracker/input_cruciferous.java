package com.finalproj.finley.thyroidtracker;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.bullyboo.view.CircleSeekBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class input_cruciferous extends android.support.v4.app.Fragment {

//    private String title;
//    private int page;

    public static input_cruciferous newInstance() {
        input_cruciferous input_cruciferous = new input_cruciferous();
        Bundle args = new Bundle();

        return input_cruciferous;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("someInt", 0);
//        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input_cruciferous, container, false);

        final String FileName = "/Cruciferous.csv";
        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM");
        final String StringDate = sdf.format(new Date());

        final CircleSeekBar Input = (CircleSeekBar) view.findViewById(R.id.circleSeekBar);
        final TextView output = (TextView) view.findViewById(R.id.Readout);
        Input.setValue(0);
        Input.setOnValueChangedListener(new CircleSeekBar.OnValueChangedListener() {
            @Override
            public void onValueChanged(int i) {
                int v = Input.getValue();
                if (v <= 25) {
                    output.setText("No Cruciferous consumed");
                }
                else if ( v > 25 && v <=50)
                {
                    output.setText("Some Cruciferous consumed");
                }
                else if ( v > 50 && v <= 75)
                {
                    output.setText("Nearly Daily Allowance");
                }
                else if ( v > 75 )
                {
                    output.setText("Over Daily Allowance");
                }


            }
        });

        Button Submit = (Button) view.findViewById(R.id.button4);
        Submit.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                Toast.makeText(context, "Data Submitted for "+StringDate, Toast.LENGTH_SHORT).show();
                try {
                    CSVWriter writer = new CSVWriter(new FileWriter(context.getFilesDir().getPath().toString() + FileName, true), '\t');
                    String Enter = Input.getValue() +"#" + StringDate;
                    String[] entries = Enter.split("#");
                    writer.writeNext(entries);
                    writer.close();
                } catch(IOException ie) {
                    ie.printStackTrace();
                }
            }
        });

        return view;
    }
}
