package com.example.afyamtaani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    public boolean isSpinnerTouched = false;
//    Button hwbutton;
//    Button mwbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        hwbutton = (Button) findViewById(R.id.btn_login_as_doctor);
//        mwbutton = (Button) findViewById(R.id.btn_login_as_patient);
//        hwbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginDoctor();
//            }
//        });
//
//        mwbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginDoctor();
//            }
//        });
//    }
//
//    private void loginDoctor() {
//        Intent intent = new Intent(this,LoginDoctorActivity.class);
//        startActivity(intent);
//    }
//
//    private void LoginDoctor() {
//        Intent intent = new Intent(this,LoginDoctorActivity.class);
//        startActivity(intent);
//
//    }
//    /* Called when user taps the login as Health worker button */
//
//}


        Intent intent = new Intent(this, LoginHealthWorkerActivity.class);
        Intent intent1 = new Intent(this, LoginLabolatoryActivity.class);
        Intent intent2 = new Intent(this, LoginPharmacistActivity.class);
        Intent intent3 = new Intent(this, LoginDoctorActivity.class);
        // get Spinner reference
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
//        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List countries = new ArrayList();
        countries.add("Healthworker");
        countries.add("Labolatory");
        countries.add("Pharmacist");
        countries.add("Specialist");

        // Creating array adapter for spinner
        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, countries);

        // Drop down style will be listview with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        // attaching data adapter to spinner

        spinner.setAdapter(dataAdapter);
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinnerTouched = true;
                return false;
            }
        });



spinner.setOnItemSelectedListener(new OnItemSelectedListener(){


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemSelected(AdapterView parent, View view, int position, long id) {
        // getting selected item
        String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item in toast

            switch (item) {
                default:
                    Toast.makeText(view.getContext(), "Successfully Deleted Data ", Toast.LENGTH_LONG).show();

                case "Healthworker":
                    startActivity(intent);
                    break;
                case "Labolatory":
                    startActivity(intent1);
                    break;
                case "Pharmacist":
                    startActivity(intent2);
                    break;
                case "Doctor":
                    startActivity(intent3);

            }
        }
    });
    }
}
