package com.example.afyamtaani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SVMainDashoard extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svmain_dashoard);

//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
//        bottomNavigationView.setSelectedItemId(R.id.person);
    }

//    VitalsFragment vitalsFragment = new VitalsFragment();
//    DiagnosisFragment diagnosisFragment = new DiagnosisFragment();
//    PrescriptionFragment prescriptionFragment = new PrescriptionFragment();
//
//
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.person:
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, vitalsFragment).commit();
//                return true;
//
//            case R.id.home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, diagnosisFragment).commit();
//                return true;
//
//            case R.id.settings:
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, prescriptionFragment).commit();
//                return true;
//        }
//        return false;
//    }
}