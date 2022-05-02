package com.example.inhomecareapp.caregiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.customer.CustomerEditProfileActivity;
import com.example.inhomecareapp.databinding.ActivityCaregiverEditProfileBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CaregiverEditProfileActivity extends AppCompatActivity {
    CaregiverData caregiverData;

    ActivityCaregiverEditProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCaregiverEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);

        caregiverData = (CaregiverData) getIntent().getSerializableExtra("caregiver");

        binding.caregiverEditNameEt.setText(caregiverData.getCaregiverNameRegister());
        binding.caregiverEditPhoneEt.setText(caregiverData.getCaregiverPhoneRegister());

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(CaregiverEditProfileActivity.this, CaregiverHome.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.logout) {
                    firebaseAuth.signOut();
                    finish();
                    return true;
                }
                if (id == R.id.profile) {
                    Intent intent = new Intent(CaregiverEditProfileActivity.this, CaregiverProfileActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }


        });

    }

    public void editProfile(View view) {
        String name = binding.caregiverEditNameEt.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, "name required", Toast.LENGTH_LONG).show();
            return;
        }

        String phone = binding.caregiverEditPhoneEt.getText().toString();

        if (phone.isEmpty()) {
            Toast.makeText(this, "phone required", Toast.LENGTH_LONG).show();
            return;
        }

        caregiverData.setCaregiverNameRegister(name);
        caregiverData.setCaregiverPhoneRegister(phone);

        FirebaseFirestore
                .getInstance()
                .collection("inHomeCaregivers")
                .document(FirebaseAuth.getInstance().getUid())
                .set(caregiverData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CaregiverEditProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}