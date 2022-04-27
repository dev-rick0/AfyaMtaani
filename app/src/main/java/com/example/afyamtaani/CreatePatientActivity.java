package com.example.afyamtaani;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreatePatientActivity extends AppCompatActivity {

    //creating variables for patient
    private EditText genderEdt, ageEdt, full_NameEdt, contact_NumberEdt, reference_ContactEdt, professionEdt, emailEdt, addressEdt;
    //creating variable for submit
    private Button submitPatientBtn;
    // creating a strings for storing our values from edittext fields.
    private String gender, age, full_Name, contact_Number, reference_Contact, profession, email, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);

        genderEdt = (EditText) findViewById(R.id.editTextPatientGender);
        ageEdt = (EditText) findViewById(R.id.editTextAge);
        full_NameEdt = (EditText) findViewById(R.id.editTextFullName);
        contact_NumberEdt = (EditText) findViewById(R.id.editTextContactNumber);
        reference_ContactEdt = (EditText) findViewById(R.id.editTextReferenceContact);
        professionEdt = (EditText) findViewById(R.id.editTextProfession);
        emailEdt = (EditText) findViewById(R.id.editTextEmail);
        addressEdt = (EditText) findViewById(R.id.editTextAddress);

        submitPatientBtn = findViewById(R.id.btn_create_patient_signup);

        submitPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting data from edittext fields.
                gender = genderEdt.getText().toString();
                age = ageEdt.getText().toString();
                full_Name = full_NameEdt.getText().toString();
                contact_Number = contact_NumberEdt.getText().toString();
                reference_Contact = reference_ContactEdt.getText().toString();
                profession = professionEdt.getText().toString();
                email = emailEdt.getText().toString();
                address = addressEdt.getText().toString();

                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(gender)) {
                    genderEdt.setError("Please enter Gender");
                } else if (TextUtils.isEmpty(age)) {
                    ageEdt.setError("Please enter Age");
                } else if (TextUtils.isEmpty(full_Name)) {
                    full_NameEdt.setError("Please enter FullName");
                } else if (TextUtils.isEmpty(contact_Number)) {
                    contact_NumberEdt.setError("Please enter Contact Number");
                } else if (TextUtils.isEmpty(reference_Contact)) {
                    reference_ContactEdt.setError("Please enter reference contact");
                } else if (TextUtils.isEmpty(profession)) {
                    professionEdt.setError("Please enter Profession");
                } else if (TextUtils.isEmpty(email)) {
                    emailEdt.setError("Please enter Email");
                } else if (TextUtils.isEmpty(address)) {
                    addressEdt.setError("Please enter Address");
                } else {
                    // calling method to add data to Firebase Firestore.
                    addDatatoDatabase(gender, age, full_Name, contact_Number, reference_Contact, profession, email, address);
                }


            }
        });


    }

    private void addDatatoDatabase(String gender, String age, String full_name, String contact_number, String reference_contact, String profession, String email, String address) {
        String url = "http://192.168.0.125/myapi/createPatient.php";
        RequestQueue queue = Volley.newRequestQueue(CreatePatientActivity.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(CreatePatientActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                genderEdt.setText("");
                ageEdt.setText("");
                full_NameEdt.setText("");
                contact_NumberEdt.setText("");
                reference_ContactEdt.setText("");
                professionEdt.setText("");
                emailEdt.setText("");
                addressEdt.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(CreatePatientActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("gender", gender);
                params.put("age", age);
                params.put("full_Name", full_Name);
                params.put("contact_Number", contact_Number);
                params.put("reference_Contact", reference_Contact);
                params.put("profession", profession);
                params.put("email", email);
                params.put("address", address);

                // at last we are returning our params.
                return params;
            }
        };
        queue.add(request);
    }
}
