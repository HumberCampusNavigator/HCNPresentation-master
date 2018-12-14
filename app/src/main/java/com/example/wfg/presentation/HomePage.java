package com.example.wfg.presentation;

import android.support.v7.app.AppCompatActivity;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

import com.example.wfg.presentation.ActivityContactsLayout;
import com.example.wfg.presentation.ActivityQRCodeLayout;

public class HomePage extends AppCompatActivity {
    private Button button,phoneBook;
    private Button button1,CallSecurity,sensorbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, ActivityQRCodeLayout.class);
                startActivity(intent);
            }
        });

        CallSecurity = (Button) findViewById(R.id.CallSecurity);
        CallSecurity.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, callSecurity.class);
                startActivity(intent);
            }
        });
        button1 = (Button)findViewById(R.id.quit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });

        phoneBook = (Button)findViewById(R.id.phoneBook);
        phoneBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(HomePage.this, ActivityContactsLayout.class);
                startActivity(intent3);
            }
        });

        sensorbutton = (Button) findViewById(R.id.sensor);
        sensorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, SensorInput.class);
                startActivity(intent);
            }
        });




    }
}
