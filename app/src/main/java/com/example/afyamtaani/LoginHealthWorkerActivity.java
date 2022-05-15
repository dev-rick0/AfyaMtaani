package com.example.afyamtaani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginHealthWorkerActivity extends AppCompatActivity {
        Button loginbutton;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_health_worker);

            loginbutton = (Button) findViewById(R.id.healthWorker);
            loginbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginDoctor();
                }
            });
        }

        private void loginDoctor() {
            Intent intent = new Intent(this,SignupAsPatientActivity.class);
            startActivity(intent);
        }

}