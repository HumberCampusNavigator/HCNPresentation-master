package com.example.wfg.presentation;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ReadingsList extends ArrayAdapter<sensorReadings>{

    private Activity context;
    private List<sensorReadings> readingsList;

    public ReadingsList(Activity context, List<sensorReadings> readingsList){
        super(context, R.layout.sensordisplay, readingsList);
        this.context = context;
        this.readingsList = readingsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflator = context.getLayoutInflater();
        View listViewItem = inflator.inflate(R.layout.sensordisplay, null, true);

        TextView textView2 = listViewItem.findViewById(R.id.textView2);
        TextView textView3 = listViewItem.findViewById(R.id.textView3);

        sensorReadings records = readingsList.get(position);

        textView2.setText(records.getReadingTime());
        textView3.setText(records.getReadingValue());

        return listViewItem;

    }
}
