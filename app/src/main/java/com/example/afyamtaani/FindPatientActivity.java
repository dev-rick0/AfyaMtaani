package com.example.afyamtaani;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FindPatientActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private Button buttonGet;

    //Textview variables

    private TextView Gender;
    private TextView Age;
    private TextView FullName;
    private TextView ContactNumber;
    private TextView ReferenceContact;
    private TextView Profession;
    private TextView Email;
    private TextView Address;


    private ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_patient);

        editText = (EditText) findViewById(R.id.editTextId);
        buttonGet = (Button) findViewById(R.id.buttonGet);

        Gender = (TextView) findViewById(R.id.editTextPatientGender);
        Age = (TextView) findViewById(R.id.editTextAge);
        FullName = (TextView) findViewById(R.id.editTextFullName);
        ContactNumber = (TextView) findViewById(R.id.editTextContactNumber);
        ReferenceContact = (TextView) findViewById(R.id.editTextReferenceContact);
        Profession = (TextView) findViewById(R.id.editTextProfession);
        Email = (TextView) findViewById(R.id.editTextEmail);
        Address = (TextView) findViewById(R.id.editTextAddress);


        buttonGet.setOnClickListener(this);
    }

        private void getData() {
            String id = editText.getText().toString().trim();
            if (id.equals("")) {
                Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
                return;
            }
            loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

            String url = Config.DATA_URL+editText.getText().toString().trim();

            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    loading.dismiss();
                    showJSON(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(FindPatientActivity.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

        private void showJSON(String response){
            String gender="";
            String age="";
            String fullName = "";
            String contactNumber = "";
            String referenceContact = "";
            String profession = "";
            String email = "";
            String address = "";

            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
                JSONObject patientData = result.getJSONObject(0);
                gender = patientData.getString(Config.KEY_GENDER);
                age= patientData.getString(Config.KEY_AGE);
                fullName = patientData.getString(Config.KEY_FULLNAME);
                contactNumber =patientData.getString(Config.KEY_CONTACTNUMBER);
                referenceContact =patientData.getString(Config.KEY_REFERENCECONTACT);
                profession =patientData.getString(Config.KEY_PROFESSION);
                email =patientData.getString(Config.KEY_EMAIL);
                address =patientData.getString(Config.KEY_ADDRESS);


            } catch (JSONException j) {
                j.printStackTrace();
            }
            //textViewResult.setText("Name:\t"+name);
//            textViewResult1.setText("Name:\t"+name+"\nAddress:\t" +address+ "\nVice Chancellor:\t"+ vc+"\na:\t"+a+"\nb:\t"+b+"\nc:\t"+c+"\nd:\t"+d+"\ne:\t"+f+"\ng:\t"+g+"\nh:\t"+h+"\ni:\t"+i+"\nk:\t"+k);
            //textViewResult1.setText("Name:\t"+name+"\nAddress:\t" +address+ "\nVice Chancellor:\t"+ vc);
            Gender.setText(gender);
            Age.setText(age);
            FullName.setText(fullName);
            ContactNumber.setText(contactNumber);
            ReferenceContact.setText(referenceContact);
            Profession.setText(profession);
            Email.setText(email);
            Address.setText(address);

        }

        @Override
        public void onClick(View v) {
            getData();
        }
    }