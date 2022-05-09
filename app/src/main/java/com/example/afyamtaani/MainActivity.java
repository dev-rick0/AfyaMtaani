package com.example.afyamtaani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    Button hwbutton;
    Button mwbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hwbutton = (Button) findViewById(R.id.btn_login_as_doctor);
        mwbutton = (Button) findViewById(R.id.btn_login_as_patient);
        hwbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDoctor();
            }
        });

        mwbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDoctor();
            }
        });
    }

    private void loginDoctor() {
        Intent intent = new Intent(this,LoginDoctorActivity.class);
        startActivity(intent);
    }

    private void LoginDoctor() {
        Intent intent = new Intent(this,LoginDoctorActivity.class);
        startActivity(intent);

    }
    /* Called when user taps the login as Health worker button */

}
