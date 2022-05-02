package com.example.inhomecareapp.caregiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.inhomecareapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class CaregiverContractsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_contracts);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        BottomNavigationView bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(CaregiverContractsActivity.this, CaregiverHome.class);
                    startActivity(intent);

                }
                if (id == R.id.logout) {
                    firebaseAuth.signOut();
                    finish();

                }
                if (id == R.id.profile) {
                    Intent intent = new Intent(CaregiverContractsActivity.this, CaregiverProfileActivity.class);
                    startActivity(intent);


                }
            }
        });

    }
}