package com.example.wfg.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.wfg.presentation.ReadingsList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SensorInput extends AppCompatActivity {
    EditText editTextTime1;
    EditText editTextValue1;
    Button buttonAddReading1;

    DatabaseReference databaseReadings;

    ListView listviewSensor;

    List<sensorReadings> readingsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_input);

        Thread t = new Thread(){

            @Override
            public void run(){
                try{
                    while(!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.editTextTime);
                                long editTextTime = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
                                String dateString = sdf.format(editTextTime);
                                tdate.setText(dateString);
                            }
                        });
                    }

                }catch(InterruptedException e){

                }
            }
        };
        t.start();

        databaseReadings = FirebaseDatabase.getInstance().getReference("Accelerometer");

        editTextTime1 = findViewById(R.id.editTextTime);
        editTextValue1 = findViewById(R.id.editTextValue);
        buttonAddReading1 = findViewById(R.id.buttonAddReading);

        listviewSensor = findViewById(R.id.listviewSensor);

        readingsList = new ArrayList<>();

        buttonAddReading1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReading();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReadings.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                readingsList.clear();

                for(DataSnapshot readingSnapshot : dataSnapshot.getChildren()){
                    sensorReadings sensorRecords = readingSnapshot.getValue(sensorReadings.class);

                    readingsList.add(sensorRecords);
                }

                ReadingsList adapter = new ReadingsList(SensorInput.this, readingsList);
                listviewSensor.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addReading(){

        String time = editTextTime1.getText().toString();

        String value = editTextValue1.getText().toString();

        String id = databaseReadings.push().getKey();

        sensorReadings sensorrecording = new sensorReadings(id, time, value);

        databaseReadings.child(id).setValue(sensorrecording);

        Toast.makeText(this,"Reading Recorded", Toast.LENGTH_LONG).show();
    }
}
