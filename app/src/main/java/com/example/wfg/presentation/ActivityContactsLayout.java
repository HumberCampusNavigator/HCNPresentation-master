package com.example.wfg.presentation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.wfg.presentation.HomePage;
import com.example.wfg.presentation.R;
import com.example.wfg.presentation.callSecurity;

import java.util.ArrayList;



public class ActivityContactsLayout extends AppCompatActivity {
    Spinner spinAppTech,spinBusinessSchool,spinMediaSchool,spinInformationDesk,spinEmergency;
    Context ctxContacts=ActivityContactsLayout.this;
    public ImageView homeimg,callimg;
    public String item;
    Context ctxCallSelected=ActivityContactsLayout.this;

    ArrayList<String> arrAppTech ;
    ArrayList<String> arrBusinessSchool;
    ArrayList<String> arrMediaSchool ;
    ArrayList<String> arrInformationDesk ;
    ArrayList<String> arrEmergency ;

    ArrayAdapter<String> AppTech;
    ArrayAdapter<String> BusinessSchool;
    ArrayAdapter<String> MediaSchool;
    ArrayAdapter<String> InformationDesk;
    ArrayAdapter<String> Emergency;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contact_layout);
        spinAppTech = (Spinner) findViewById(R.id.spinAppTech);
        spinBusinessSchool = (Spinner) findViewById(R.id.spinBusinessSchool);
        spinMediaSchool = (Spinner) findViewById(R.id.spinMediaSchool);
        spinInformationDesk = (Spinner) findViewById(R.id.spinInformationDesk);
        spinEmergency = (Spinner) findViewById(R.id.spinEmergency);
        homeimg = (ImageView) findViewById(R.id.homeimg);
        callimg = (ImageView) findViewById(R.id.callimg);



        arrAppTech = new ArrayList<String>();
        arrBusinessSchool = new ArrayList<String>();
        arrMediaSchool = new ArrayList<String>();
        arrInformationDesk = new ArrayList<String>();
        arrEmergency = new ArrayList<String>();

        arrAppTech.add("Applied Technologies");
        arrBusinessSchool.add("Business School");
        arrMediaSchool.add("Media School");
        arrInformationDesk.add("Information Desk");
        arrEmergency.add("Emergency");

        //1.
        String[] appTechSpinner = new String[] {"Applied Technology","+14166756622,,5561"};
        AppTech = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, appTechSpinner);
        // Drop down layout style - list view with radio button
        AppTech.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinAppTech.setAdapter(AppTech);

        //2.
        String[] bussSpinner = new String[] {"Bussiness School","+14166756623,,5562"};
        BusinessSchool = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bussSpinner);
        // Drop down layout style - list view with radio button
        BusinessSchool.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinBusinessSchool.setAdapter(BusinessSchool);


        //3.
        String[] mediaSpinner = new String[] {"Media Studies","+14166756624,,5563"};
        MediaSchool = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mediaSpinner);
        // Drop down layout style - list view with radio button
        MediaSchool.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinMediaSchool.setAdapter(MediaSchool);


        //4.
        String[] infoSpinner = new String[] {"Information Desk","+14166756625,,5564"};
        InformationDesk = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, infoSpinner);
        // Drop down layout style - list view with radio button
        InformationDesk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinInformationDesk.setAdapter(InformationDesk);


        //5.
        String[] emerSpinner = new String[] {"Cleaning Department","+14166756626,,5565"};
        Emergency = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, emerSpinner);
        // Drop down layout style - list view with radio button
        Emergency.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinEmergency.setAdapter(Emergency);


        spinAppTech.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                item = adapterView.getItemAtPosition(position).toString();
                Log.e("item",""+item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinBusinessSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                item = adapterView.getItemAtPosition(position).toString();
                Log.e("item",""+item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinMediaSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                item = adapterView.getItemAtPosition(position).toString();
                Log.e("item",""+item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinInformationDesk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                item = adapterView.getItemAtPosition(position).toString();
                Log.e("item",""+item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinEmergency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                item = adapterView.getItemAtPosition(position).toString();
                Log.e("item",""+item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        callimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+item));
                if (ActivityCompat.checkSelfPermission(ctxCallSelected, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                startActivity(callIntent);
            }
        });

        homeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(ctxContacts, HomePage.class);
                startActivity(intent3);
            }
        });

    }
}
