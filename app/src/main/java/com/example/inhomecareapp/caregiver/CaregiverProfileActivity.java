package com.example.inhomecareapp.caregiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class CaregiverProfileActivity extends AppCompatActivity {
    ImageView caregiverPicProfileActivity;
    TextView caregiverNameProfileActivity,
            caregiverPhoneProfileActivity,
            caregiverGenderProfileActivity,
            caregiverSpecializeProfileActivity,
            caregiverTypeOfService,
            caregiverSpecialist,
            caregiverAge;
    MaterialButton editCaregiverProfileActivity;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private static final String TAG = "TAG";
    CaregiverData caregiverData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_caregiver_profile);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        caregiverPicProfileActivity = findViewById(R.id.caregiver_pic_profile_activity);
        caregiverNameProfileActivity = findViewById(R.id.caregiver_name_profile_activity);
        caregiverPhoneProfileActivity = findViewById(R.id.caregiver_phone_profile_activity);
        caregiverGenderProfileActivity = findViewById(R.id.caregiver_gender_profile_activity);
        editCaregiverProfileActivity = findViewById(R.id.edit_caregiver_profile_activity);
        caregiverTypeOfService = findViewById(R.id.caregiver_typOfService_profile_activity);
        caregiverSpecialist = findViewById(R.id.caregiver_specialize_profile_activity);
        caregiverAge = findViewById(R.id.caregiver_ages_profile_activity);
        editCaregiverProfileActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaregiverProfileActivity.this, CaregiverEditProfileActivity.class);
                intent.putExtra("caregiver", caregiverData);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCaregiverData();
    }

    private void getCaregiverData() {
        String uid = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("inHomeCaregivers").document(uid)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    caregiverData = task.getResult().toObject(CaregiverData.class);
                    Log.i(TAG, "onComplete: " + caregiverData.toString());
                    updateUi(caregiverData);
                } else {
                    String errorMessage = task.getException().getLocalizedMessage();
                    Toast.makeText(CaregiverProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onComplete: " + errorMessage);
                }
            }
        });

    }

    private void updateUi(CaregiverData caregiverData) {
        caregiverNameProfileActivity.setText(caregiverData.getCaregiverNameRegister());
        caregiverPhoneProfileActivity.setText(caregiverData.getCaregiverPhoneRegister());
        caregiverGenderProfileActivity.setText(caregiverData.getGender());
        caregiverTypeOfService.setText(caregiverData.getServiceSelected());
        caregiverSpecialist.setText(caregiverData.getSelectedCategory());
        caregiverAge.setText(caregiverData.getSelectedAge());
        Picasso.get().load(caregiverData.getImageUrl()).placeholder(R.drawable.profile)
                .into(caregiverPicProfileActivity);

    }
}