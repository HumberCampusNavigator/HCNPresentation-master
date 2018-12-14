package com.example.wfg.presentation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wfg.presentation.HomePage;
import com.example.wfg.presentation.R;

import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;



public class ActivityQRCodeLayout  extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {
    private static final String TAG = ActivityQRCodeLayout.class.getSimpleName();
    Context ctxActivityQrCode=ActivityQRCodeLayout.this;
    private ImageView homeimg,imgCam;
    private BarcodeReader barcodeReader;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser Fuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_qr_code_scan_layout);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Fuser = firebaseAuth.getCurrentUser();
            }
        };
        // getting barcode instance
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);
        homeimg = (ImageView)findViewById(R.id.homeimg);
        imgCam = (ImageView)findViewById(R.id.imgCam);


        homeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ctxActivityQrCode, HomePage.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
            }
        });


        imgCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ctxActivityQrCode, ActivityCurrentDestLocation.class);
                startActivity(intent2);
            }
        });

    }

    @Override
    public void onScanned(final Barcode barcode) {
        Log.e(TAG, "onScanned: " + barcode.displayValue);
        barcodeReader.playBeep();

        runOnUiThread(new Runnable() {
            @SuppressLint("WrongConstant")
            @Override
            public void run() {
                String sBarcode;
                sBarcode=""+ barcode.displayValue;
                Log.e("sBar",""+sBarcode);
                Toast.makeText(getApplicationContext(), "Barcode: " + sBarcode, Toast.LENGTH_LONG).show();

                getUserLocation(sBarcode);
            }
        });
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {
        Log.e(TAG, "onScannedMultiple: " + barcodes.size());

        String codes = "";
        for (Barcode barcode : barcodes) {
            codes += barcode.displayValue + ", ";
        }

        final String finalCodes = codes;
        runOnUiThread(new Runnable() {
            @SuppressLint("WrongConstant")
            @Override
            public void run() {
                Toast.makeText(ctxActivityQrCode, "Barcodes: " + finalCodes, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onCameraPermissionDenied() {

        Toast.makeText(ctxActivityQrCode, "Camera permission denied!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void  getUserLocation(final String barcode){
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
                        if(barcode!=null)
                        {
                            if(barcode.equals(id))
                            {
                                Intent intent2 = new Intent(ctxActivityQrCode, ActivityCurrentDestLocation.class);
                                intent2.putExtra("string",""+id);
                                ctxActivityQrCode.startActivity(intent2);
                            }

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

}
