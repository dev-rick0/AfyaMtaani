
package com.example.afyamtaani;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditProfileDoctorActivity extends AppCompatActivity {

    EditText et_name,et_email,et_phone,et_ic,et_clinic,et_title;
    TextView tv_name_patient;
    Button btn_update, btn_upload_doctor;
    ImageView img_profile_doctor;
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirestore;
    FirebaseUser mFirebaseUser;
    String userID;
    StorageReference mStorageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_doctor);
        et_name=findViewById(R.id.et_doctor_name_editprofile);
        et_email=findViewById(R.id.et_doctor_email_editprofile);
        et_phone=findViewById(R.id.et_doctor_phone_editprofile);
        et_ic=findViewById(R.id.et_doctor_icno_editprofile);
        et_clinic=findViewById(R.id.et_clinic_doctor_editprofile);
        et_title=findViewById(R.id.et_doctor_title_editprofile);
        btn_update=findViewById(R.id.btn_update_editprofile_doctor);

        btn_upload_doctor=findViewById(R.id.btn_upload_image_doctor);
        img_profile_doctor=findViewById(R.id.profile_image_doctor);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirestore=FirebaseFirestore.getInstance();
        mStorageReference= FirebaseStorage.getInstance().getReference();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        et_ic.addTextChangedListener(new TextWatcher() {
            int keyDel;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                et_ic.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {

                        if (keyCode == KeyEvent.KEYCODE_DEL)
                            keyDel = 1;
                        return false;
                    }
                });

                if (keyDel == 0) {
                    int len = et_ic.getText().length();
                    if(len == 6) {
                        et_ic.setText(et_ic.getText() + "-");
                        et_ic.setSelection(et_ic.getText().length());
                    }
                    else if(len == 9){
                        et_ic.setText(et_ic.getText() + "-");
                        et_ic.setSelection(et_ic.getText().length());
                    }
                } else {
                    keyDel = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });

        et_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        StorageReference profileRef=mStorageReference.child("users/"+mFirebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(img_profile_doctor);

            }
        });

        userID=mFirebaseAuth.getCurrentUser().getUid();

        final DocumentReference documentReference=mFirestore.collection("DoctorProfile").document(userID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                et_name.setText(value.getString("DoctorName"));
                et_email.setText(value.getString("Email"));
                et_ic.setText(value.getString("ICNo"));
                et_phone.setText(value.getString("PhoneNo"));
                et_clinic.setText(value.getString("Clinic"));
                et_title.setText(value.getString("Title"));


            }
        });
        btn_upload_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_ic.length()<14 ){
                    Toast.makeText(EditProfileDoctorActivity.this,"Please enter a valid IC number!",Toast.LENGTH_SHORT).show();
                    et_ic.requestFocus();
                    return;
                }

                else if (et_ic.length()>14 ) {
                    Toast.makeText(EditProfileDoctorActivity.this,"Please enter a valid IC number!",Toast.LENGTH_SHORT).show();
                    et_ic.requestFocus();
                    return;
                }


                else if (et_phone.length()>11 ) {
                    Toast.makeText(EditProfileDoctorActivity.this,"Please enter a valid phone number!",Toast.LENGTH_SHORT).show();
                    et_phone.requestFocus();
                    return;
                }

                else if (et_phone.length()<10) {
                    Toast.makeText(EditProfileDoctorActivity.this,"Please enter a valid phone number!",Toast.LENGTH_SHORT).show();
                    et_phone.requestFocus();
                    return;
                }

                else if(et_name.getText().toString().isEmpty() || et_email.getText().toString().isEmpty() || et_phone.getText().toString().isEmpty() || et_ic.getText().toString().isEmpty() || et_title.getText().toString().isEmpty() || et_clinic.getText().toString().isEmpty()){
                    Toast.makeText(EditProfileDoctorActivity.this,"One or more fields are empty!",Toast.LENGTH_SHORT).show();
                    return;
                }

                final String email=et_email.getText().toString();

                mFirebaseUser.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef=mFirestore.collection("DoctorProfile").document(mFirebaseUser.getUid());
                        Map<String, Object> edited= new HashMap<>();
                        edited.put("Email",email);
                        edited.put("DoctorName",et_name.getText().toString());
                        edited.put("PhoneNo",et_phone.getText().toString());
                        edited.put("ICNo",et_ic.getText().toString());
                        edited.put("Clinic",et_clinic.getText().toString());
                        edited.put("Title",et_title.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(EditProfileDoctorActivity.this,"Profile is updated",Toast.LENGTH_SHORT).show();
                            }
                        });
//                        Toast.makeText(EditProfilePatientActivity.this,"Email is changed",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(EditProfileDoctorActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri ImageUri=data.getData();

                uploadImageToFirebase(ImageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri ImageUri) {
        final StorageReference fileRef= mStorageReference.child("users/"+mFirebaseAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(img_profile_doctor);

                    }
                });
                Toast.makeText(EditProfileDoctorActivity.this,"Image Uploaded Successfully",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfileDoctorActivity.this,"Image Upload Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
