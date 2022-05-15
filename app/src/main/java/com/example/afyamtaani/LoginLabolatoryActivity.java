package com.example.afyamtaani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginLabolatoryActivity extends AppCompatActivity {

        Button loginbutton;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_labolatory);

            loginbutton = (Button) findViewById(R.id.btn_login_labolatory);
            loginbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginDoctor();
                }
            });
        }

        private void loginDoctor() {
            Intent intent = new Intent(this, LabolatoryDashboardActivity.class);
            startActivity(intent);
        }
    }