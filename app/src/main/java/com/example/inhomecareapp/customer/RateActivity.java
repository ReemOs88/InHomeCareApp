package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inhomecareapp.caregiver.CaregiverData;
import com.example.inhomecareapp.databinding.ActivityRateBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RateActivity extends AppCompatActivity {

    ActivityRateBinding binding;
    String caregiverId;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        caregiverId = getIntent().getStringExtra("caregiverId");

    }

    public void rateCaregiver(View view) {
        double rating = binding.rating.getRating();

        firestore.collection("inHomeCaregivers")
                .document(caregiverId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        CaregiverData caregiverData = documentSnapshot.toObject(CaregiverData.class);

                        double newRate = (caregiverData.getRate() + rating) / 2;

                        caregiverData.setRate(newRate);

                        firestore.collection("inHomeCaregivers")
                                .document(caregiverId)
                                .set(caregiverData);

                        Toast.makeText(RateActivity.this, "Caregiver rated", Toast.LENGTH_SHORT).show();

                        finish();
                    }
                });

    }

}
