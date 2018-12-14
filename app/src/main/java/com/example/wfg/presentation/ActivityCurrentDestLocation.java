package com.example.wfg.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.wfg.presentation.HomePage;
import com.example.wfg.presentation.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class ActivityCurrentDestLocation extends AppCompatActivity {
    Context ctxCurrDes=ActivityCurrentDestLocation.this;
    EditText edtDistLoc,edtCurrentLoc;
    ImageView homeimg;
    Button btnShowPath;
    String sIds;
    Spinner spinDlocation;
    ArrayList<String> arrDlocation ;
    ArrayAdapter<String> AppDlocation;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser Fuser;
    String desl,srcl;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_current_dest_location);
        sIds=getIntent().getStringExtra("string");
        Log.e("sIds",""+sIds);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Fuser = firebaseAuth.getCurrentUser();
            }
        };
        edtCurrentLoc=(EditText)findViewById(R.id.edtCurrentLoc);
        edtDistLoc=(EditText)findViewById(R.id.edtDistLoc);
        homeimg=(ImageView)findViewById(R.id.homeimg);
        btnShowPath=(Button)findViewById(R.id.btnShowPath);
        spinDlocation=(Spinner)findViewById(R.id.spinDlocation);

        getUserLocation();
        arrDlocation=new ArrayList<>();
        arrDlocation.add("201");
        arrDlocation.add("202");
        arrDlocation.add("203");
        arrDlocation.add("204");
        arrDlocation.add("205");

        AppDlocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrDlocation);
        // Drop down layout style - list view with radio button
        AppDlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinDlocation.setAdapter(AppDlocation);

        spinDlocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                desl=""+item;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        homeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(ctxCurrDes, HomePage.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent3);
            }
        });
        btnShowPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent2 = new Intent(ctxCurrDes, ShowMapPathActivity.class);
                 intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                 intent2.putExtra("srcl",""+srcl);
                 intent2.putExtra("desl",""+desl);
                 startActivity(intent2);

            }
        });




    }
    public void  getUserLocation(){
        Query query = FirebaseDatabase.getInstance().getReference("locdata");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    try {

                        String id = snapshot.child("Id").getValue(String.class).toString();
                        String scl=(snapshot.child("cl").getValue(String.class).toString());

                        Log.e("sId", "" + id);
                        Log.e("scl",""+scl);


                        if (sIds.equals(id))
                        {
                            edtCurrentLoc.setText(scl);
                            srcl=""+scl;
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " ,""+ databaseError.getCode());
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent3 = new Intent(ctxCurrDes, ActivityQRCodeLayout.class);
        intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent3);
    }
}
