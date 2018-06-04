package com.finalproj.finley.thyroidtracker;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

/**
 * Created by Finley on 04/01/2018.
 */

public class Tab_a extends Fragment {

    Boolean FirstView = true;

    Boolean Check1 = true;
    Boolean Check2 = true;
    Boolean Check3 = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !FirstView) {
            graphView.removeAllSeries();
            if(Check1){
                series=new LineGraphSeries<>(getDataPoint(spinner.getSelectedItem().toString()));
                series.setColor(getResources().getColor(android.R.color.holo_blue_dark));
                graphView.addSeries(series);

            }
            if(Check2){

                series2=new LineGraphSeries<>(getDataPoint(spinner2.getSelectedItem().toString()));
                series2.setColor(getResources().getColor(android.R.color.holo_green_dark));
                graphView.addSeries(series2);
            }
            if(Check3)
            {

                series3=new LineGraphSeries<>(getDataPoint(spinner3.getSelectedItem().toString()));
                series3.setColor(getResources().getColor(android.R.color.holo_red_dark));
                graphView.addSeries(series3);
            }
            graphView.getViewport().scrollToEnd();
        }
    }

    GraphView graphView;

    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series2;
    LineGraphSeries<DataPoint> series3;

    Date d1;

    Spinner spinner;
    Spinner spinner2;
    Spinner spinner3;

    SimpleDateFormat sdf= new SimpleDateFormat("dd/MM");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View Fragment = inflater.inflate(R.layout.tab_a, container, false);

        final Context context;
        context = getContext();

        if(FirstView == true) {
            String[] ResourceNames = getResources().getStringArray(R.array.Resulttypes);
            for (String s : ResourceNames) {
                try {

                    if (s.equals("Loss of libido")) {
                        s = "LossOfLibido";
                    }
                    if (s.equals("Pins and Needles")) {
                        s = "PinsAndNeedles";
                    }

                    String FileName = "/" + s + ".csv";
                    String DateString;

                    Date TempDate;

                    Calendar cal = Calendar.getInstance();

                    cal.setTime(new Date());
                    cal.add(Calendar.DATE, -91);

                    Double LastVal = Math.ceil(20 + Math.random() * 50);

                    CSVWriter writer = new CSVWriter(new FileWriter(context.getFilesDir().getPath().toString() + FileName, false), '\t');

                    for (int i = 1; i <= 90; i++) {
                        cal.add(Calendar.DATE, 1);
                        TempDate = cal.getTime();
                        DateString = sdf.format(TempDate);
                        String Enter = "";
                        LastVal = LastVal + (Math.ceil(Math.random() * 20) - 10);

                        if (LastVal > 99) {
                            LastVal = LastVal - 11;
                        }

                        if (LastVal < 1) {
                            LastVal = LastVal + 11;
                        }

                        Enter = LastVal + "," + DateString;
                        String[] entries = Enter.split(",");
                        writer.writeNext(entries);
                    }
                    writer.close();
                } catch (IOException ie) {
                    ie.printStackTrace();
                }
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

        final ImageView imageview1 = (ImageView) Fragment.findViewById(R.id.imageView2);
        final ImageView imageview2 = (ImageView) Fragment.findViewById(R.id.imageView3);
        final ImageView imageview3 = (ImageView) Fragment.findViewById(R.id.imageView4);

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
                    imageview1.setEnabled(true);
                    graphView.addSeries(series);
                    Check1 = true;

                }else{
                    imageview1.setEnabled(false);
                    spinner.setEnabled(false);
                    graphView.removeSeries(series);
                    Check1 = false;
                }
            }
        });

        final CheckBox checkBox2 = (CheckBox) Fragment.findViewById(R.id.checkBox2);
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkBox2.isChecked()){
                    imageview2.setEnabled(true);
                    spinner2.setEnabled(true);
                    graphView.addSeries(series2);
                    Check2 = true;
                }else{
                    imageview2.setEnabled(false);
                    spinner2.setEnabled(false);
                    graphView.removeSeries(series2);
                    Check2 = false;
                }
            }
        });

        final CheckBox checkBox3 = (CheckBox) Fragment.findViewById(R.id.checkBox3);
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(checkBox3.isChecked()){
                    imageview3.setEnabled(true);
                    spinner3.setEnabled(true);
                    graphView.addSeries(series3);
                    Check3 = true;
                }else{
                    imageview3.setEnabled(false);
                    spinner3.setEnabled(false);
                    graphView.removeSeries(series3);
                    Check3 = false;
                }
            }
        });

        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                graphView.removeSeries(series); //Removes the current series if it exists
                if (spinner != null && spinner.getSelectedItem()!= null ) //Ensures spinner has something selected
                {
                    series=new LineGraphSeries<>(getDataPoint(spinner.getSelectedItem().toString())); //Gets the selected csvs data points
                    series.setColor(getResources().getColor(android.R.color.holo_blue_dark)); //Sets the colour of the series
                    graphView.addSeries(series); //Adds the series to the graph

                } else {
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
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
                    return sdf.format(new Date((long) value));
                } else
                    return super.formatLabel(value, isValueX);
            }
        });

        graphView.getGridLabelRenderer().setNumHorizontalLabels(3);

        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(100);
        graphView.getViewport().scrollToEnd();
        graphView.getViewport().setXAxisBoundsManual(true);

        d1 = new Date();

        graphView.getViewport().setMinX(d1.getTime()- TimeUnit.DAYS.toMillis(30));
        graphView.getViewport().setMaxX(d1.getTime());
        graphView.getViewport().setScalable(true);

        FirstView = false;

        Button Rec = (Button) Fragment.findViewById(R.id.REC);
        Rec.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                String output = "\n"; //Initialises the string as a line return character
                String[] ResourceNames2 = getResources().getStringArray(R.array.Resulttypes); //gets an array of all possible strings for the input
                for (String s: ResourceNames2) //Iterates over the list of strings
                {
                    try
                    {
                        //Corrects the input strings to be the usable strings
                        if (s.equals("Loss of libido"))
                        {
                            s = "LossOfLibido";
                        }
                        if (s.equals("Pins and Needles"))
                        {
                            s = "PinsAndNeedles";
                        }
                        String FileName = "/" + s + ".csv";//Turns string into file path

                        //Creates the file reader used to get the data.
                        CSVReader reader = new CSVReader(new FileReader(context.getFilesDir().getPath().toString() + FileName), '\t', '"', 0);
                        String[] nextline;
                        int linecount=0;//Initialises the line reader as 0.
                        boolean outputted = false;
                        int Count = 0;
                        int NumLines = 0;

                        try {

                            //Opens the reader using the filepath
                            CSVReader reader2 = new CSVReader(new FileReader(context.getFilesDir().getPath().toString() + FileName), '\t' ,'"',0);
                            //Gets a single line of the csv and iterates until the line run out
                            while ((nextline = reader.readNext()) != null) {
                                if (nextline != null) {
                                    NumLines++;
                                }
                            }
                        }catch(IOException ie) {
                            ie.printStackTrace();
                        }

                        while ((nextline = reader.readNext()) != null) //Iterates over each line until the lines run out
                        {
                            if (nextline != null&&linecount>=NumLines-20) //For every line which isn't the last line and is in the last 20 days.
                            {
                                switch(s){ //Case statement passed the string from the array of possible strings
                                    case "Activity": //For this string
                                        if(Double.parseDouble(nextline[0])<40) //if the value is less than 40
                                        {

                                            Count++; //Count increases
                                            if(Count>=8) //If count is greater than 8
                                            {
                                                if (outputted==false) //If it hasn't already outputted.
                                                {
                                                    output = output + "Consider increasing the amount of exercise you do.\n\n"; //Adds a string to the output string
                                                    outputted = true; //Flags as already outputted.
                                                }
                                            }
                                        }
                                        break;
                                    case "Brainfog":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {
                                                if (outputted==false)
                                                {
                                                    output = output + "Consider visiting the doctor and discussing your brainfog.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Brittlenails":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {
                                                if (outputted==false)                                                {
                                                    output = output + "Consider visiting the doctor and discussing the brittleness of your hair/nails.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Cold":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)                                                {
                                                    output = output + "Consider visiting the doctor and discussing your increased sensitivity to cold.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Constipation":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)                                                {
                                                    output = output + "Consider visiting the doctor and discussing your bowel difficulties.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Cramps":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)                                                {
                                                    output = output + "Consider visiting the doctor and discussing your cramps.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Cruciferous":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)                                                {
                                                    output = output + "Consider reducing the amount of cruciferous vegetable consumption.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Depression":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)                                                {
                                                    output = output + "Consider visiting the doctor and discussing your depression with them.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Energy":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)                                                {
                                                    output = output + "Consider visiting the doctor and discussing your lack of energy.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Iodine":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)                                                {
                                                    output = output + "Consider reducing your iodine consumption.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Loss of libido":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)                                                {
                                                    output = output + "Consider visiting the doctor and discussing your loss of libido.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Pins and Needles":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)                                                {
                                                    output = output + "Consider visiting the doctor and discussing your pins and needles.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Sleep":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)                                                {
                                                    output = output + "Consider visiting the doctor and discussing your inability to sleep.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Soya":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {
                                                if (outputted==false)                                                {
                                                    output = output + "Consider reducing your soya consumption.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Thirst":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)
                                                {
                                                    output = output + "Consider visiting the doctor and discussing your increased thirst.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Tiredness":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)
                                                {
                                                    output = output + "Consider visiting the doctor and discussing your lethargy/fatigue.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                    case "Weakness":
                                        if(Double.parseDouble(nextline[0])>60)
                                        {

                                            Count++;
                                            if(Count>=12)
                                            {

                                                if (outputted==false)
                                                {
                                                    output = output + "Consider visiting the doctor and discussing your muscle weakness.\n\n";
                                                    outputted = true;
                                                }
                                            }
                                        }
                                        break;
                                }
                            }
                            linecount++;
                        }

                    } catch (IOException ie) {
                        ie.printStackTrace();
                    }
                }
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("Recommendations");
                alertDialog.setMessage(output);
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

        return Fragment;
    }

    private DataPoint[] getDataPoint(String File) {
        Context context = getContext();

        //Converts the passed string into the filename for the two exceptions.
        if (File.equals("Loss of libido"))
        {
            File = "LossOfLibido";
        }
        if (File.equals("Pins and Needles"))
        {
            File = "PinsAndNeedles";
        }

        //Constructs the file path from the string
        String FileName="/" + File+".csv";
        ArrayList<String[]> List = new ArrayList<>();

        try {

            //Opens the reader using the filepath
            CSVReader reader = new CSVReader(new FileReader(context.getFilesDir().getPath().toString() + FileName), '\t' ,'"',0);
            String[] nextline;

            //Gets a single line of the csv and iterates until the line run out
            while ((nextline = reader.readNext()) != null) {
                if (nextline != null) {
                    List.add(nextline); //Adds a line to the arraylist
                }

            }
        }catch(IOException ie) {
            ie.printStackTrace();
        }
        DataPoint[] dp = new DataPoint[List.size()]; //Creates a appropriately sized array of data points


        for(int i = 0; i<List.size();i++)
            try {
                String[] Temp = List.get(i); //Gets a single line
                Date date = sdf.parse(Temp[1]); //Parses the string into date format. "DD/mm"
                DataPoint D = new DataPoint(date.getTime(),Double.parseDouble(Temp[0])); //Creates a new data point using the parsed date and the data from the line.
                dp[i] = D; //Adds the point ot the array of points
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        return dp; //Returns the array of datapoints
    }


}