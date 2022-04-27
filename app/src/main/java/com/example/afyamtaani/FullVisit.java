package com.example.afyamtaani;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class FullVisit extends AppCompatActivity {

    //private EditText editText;
    private Button buttonPOST;

    //Edittext Variables
    private TextView patientIdEdt;
    private TextView allergiesEdt;
    private TextView heightEdt;
    private TextView weightEdt;
    private TextView temperatureEdt;
    private TextView pulseEdt;
    private TextView bloodPressureEdt;
    private TextView respirationEdt;
    private TextView symptomsEdt;
    private TextView diagnosisEdt;
    private TextView prescriptionEdt;

    //private ProgressDialog loading;

    //strings for storing the variables
    private String patientId, allergies, height, weight, temperature, pulse, bloodPressure, respiration, symptoms, diagnosis, prescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_visit);

        //editText = (EditText) findViewById(R.id.editTextId);
        patientIdEdt = (EditText) findViewById(R.id.editPatientId);
        allergiesEdt = (EditText)findViewById(R.id.editTextALlergies);
        heightEdt = (EditText)findViewById(R.id.editTextHeight);
        weightEdt = (EditText)findViewById(R.id.editTextWeight);
        temperatureEdt = (EditText)findViewById(R.id.editTextTemperature);
        pulseEdt = (EditText)findViewById(R.id.editTextPulse);
        bloodPressureEdt = (EditText)findViewById(R.id.editTextBloodPressure);
        respirationEdt = (EditText)findViewById(R.id.editTextRespiration);
        symptomsEdt = (EditText)findViewById(R.id.editTextSymptoms);
        diagnosisEdt = (EditText)findViewById(R.id.editTextDiagnosis);
        prescriptionEdt = (EditText)findViewById(R.id.editTextPrescription);

        buttonPOST = (Button) findViewById(R.id.buttonGet);
        buttonPOST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting data from edit text fields
                patientId = patientIdEdt.getText().toString();
                allergies = allergiesEdt.getText().toString();
                height = heightEdt.getText().toString();
                weight = weightEdt.getText().toString();
                temperature = temperatureEdt.getText().toString();
                pulse = pulseEdt.getText().toString();
                bloodPressure = bloodPressureEdt.getText().toString();
                respiration = respirationEdt.getText().toString();
                symptoms = symptomsEdt.getText().toString();
                diagnosis = diagnosisEdt.getText().toString();
                prescription = prescriptionEdt.getText().toString();


                //Validating text fields are not empty

                if (TextUtils.isEmpty(patientId)){
                    patientIdEdt.setError("Please enter Patient ID");
                } else  if (TextUtils.isEmpty(height)) {
                    heightEdt.setError("Please enter Height");
                } else  if (TextUtils.isEmpty(weight)){
                    weightEdt.setError("Please enter Weight");
                } else  if (TextUtils.isEmpty(temperature)){
                    temperatureEdt.setError("Please enter Temperature");
                } else  if (TextUtils.isEmpty(pulse)){
                    pulseEdt.setError("Please enter Pulse");
                } else  if (TextUtils.isEmpty(bloodPressure)){
                    bloodPressureEdt.setError("Please enter Blood Pressure");
                } else  if (TextUtils.isEmpty(respiration)){
                    respirationEdt.setError("Please enter respiration");
                } else  if (TextUtils.isEmpty(symptoms)){
                    symptomsEdt.setError("Please enter Symptoms ");
                } else  if (TextUtils.isEmpty(diagnosis)){
                    diagnosisEdt.setError("Please enter Diagnosis");
                } else  if (TextUtils.isEmpty(prescription)){
                    prescriptionEdt.setError("Please enter Prescription");
                } else {
                    //calling method to ad the data to the database
                    addPatient( patientId,allergies, height, weight, temperature, pulse, bloodPressure, respiration, symptoms, diagnosis, prescription);
                }




            }
        });

    }

    private void addPatient(String patientId, String allergies, String height, String weight, String temperature, String pulse, String bloodPressure, String respiration, String symptoms, String diagnosis, String prescription) {
    String url = "http://192.168.0.125/myapi/startVisit.php";

        RequestQueue queue = Volley.newRequestQueue(FullVisit.this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS"+ response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(FullVisit.this,jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                //setting data to empty
                patientIdEdt.setText("");
                allergiesEdt.setText("");
                heightEdt.setText("");
                weightEdt.setText("");
                temperatureEdt.setText("");
                pulseEdt.setText("");
                bloodPressureEdt.setText("");
                respirationEdt.setText("");
                symptomsEdt.setText("");
                diagnosisEdt.setText("");
                prescriptionEdt.setText("");
            }
        }, new com.android.volley.Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                //handling the errors
                Toast.makeText(FullVisit.this, "Failed to get Response = "+error , Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public String getBodyContentType(){
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                //passing key and value paitrs to our pareameter
                params.put("patient_Id", patientId);
                params.put("allergies",allergies);
                params.put("height",height);
                params.put("weight",weight);
                params.put("temperature",temperature);
                params.put("pulse",pulse);
                params.put("blood_Pressure",bloodPressure);
                params.put("respiration",respiration);
                params.put("symptoms",symptoms);
                params.put("diagnosis",diagnosis);
                params.put("prescription",prescription);

                return params;
            }

        };

        queue.add(request);
    }

}