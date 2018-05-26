package com.finalproj.finley.thyroidtracker;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ru.bullyboo.view.CircleSeekBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class input_cold extends android.support.v4.app.Fragment {

//    private String title;
//    private int page;

    public static input_cold newInstance() {
        input_cold input_cold = new input_cold();
        Bundle args = new Bundle();

        return input_cold;
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
        View view = inflater.inflate(R.layout.fragment_input_cold, container, false);

        final String FileName = "/Cold.csv";
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
                    output.setText("No cold sensitivity");
                }
                else if ( v > 25 && v <=50)
                {
                    output.setText("Slight cold sensitivity");
                }
                else if ( v > 50 && v <= 75)
                {
                    output.setText("Moderate cold sensitivity");
                }
                else if ( v > 75 )
                {
                    output.setText("Extreme cold sensitivity");
                }


            }
        });

        Button Help = (Button) view.findViewById(R.id.help);
        Help.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Cold sensitivity!");
                alertDialog.setMessage("The feeling of cold when you shouldn't be is a major indicator of hypothyroidism. Such as feeling cold on a sunny day.");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        Button Submit = (Button) view.findViewById(R.id.button4);
        Submit.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Context context = getContext();
                String[] LastDateLine;
                String LastDate=null;
                List<String[]> File = null;

                try {
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
                        String Enter = Input.getValue() + "," + StringDate;
                        String[] entries = Enter.split(",");
                        writer.writeNext(entries);
                        Toast.makeText(context, "Data Submitted for " + StringDate, Toast.LENGTH_SHORT).show();
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
                        String Enter = Input.getValue() + "," + StringDate;
                        String[] entries = Enter.split(",");
                        writer2.writeNext(entries);
                        writer2.close();
                        Toast.makeText(context, "Data Resubmitted for " + StringDate, Toast.LENGTH_SHORT).show();
                    } catch (IOException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        });

        return view;
    }
}
