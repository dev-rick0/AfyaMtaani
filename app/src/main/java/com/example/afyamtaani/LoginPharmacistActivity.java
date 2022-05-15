package com.example.afyamtaani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPharmacistActivity extends AppCompatActivity {
        Button loginbutton;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_pharmacist);

            loginbutton = (Button) findViewById(R.id.btn_login_pharmacist);
            loginbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginDoctor();
                }
            });
        }

        private void loginDoctor() {
            Intent intent = new Intent(this, PharmacistDashboardActivity.class);
            startActivity(intent);
        }
    }
