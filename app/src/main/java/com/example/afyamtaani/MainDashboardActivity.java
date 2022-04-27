package com.example.afyamtaani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainDashboardActivity extends AppCompatActivity {

    Button teleconsult;
    String number = "254740839121";

    Button createPatient;

    Button FindPatient;

    Button StartVisit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_dashboard);

        createPatient = (Button) findViewById(R.id.btncreatePatient);
        createPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPatient();
            }
        });


        teleconsult = (Button) findViewById(R.id.btnteleconsultation);
        teleconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone="+number;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        FindPatient = (Button)findViewById(R.id.findPatient);
        FindPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findPatient();
            }
        });


        StartVisit = (Button) findViewById(R.id.startVisit);
        StartVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startVisit();
            }
        });



    }

    private void startVisit() {
        Intent intent = new Intent(this, FullVisit.class);
        startActivity(intent);
    }

    private void createPatient() {
        Intent intent = new Intent(this,CreatePatientActivity.class);
        startActivity(intent);
    }

    private void findPatient(){
            Intent intent = new Intent(this, FindPatientActivity.class);
            startActivity(intent);
    }
}