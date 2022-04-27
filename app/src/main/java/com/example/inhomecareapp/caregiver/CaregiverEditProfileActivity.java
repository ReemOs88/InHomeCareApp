package com.example.inhomecareapp.caregiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.inhomecareapp.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;

public class CaregiverEditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_edit_profile);
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(CaregiverEditProfileActivity.this, CaregiverHome.class);
                    startActivity(intent);
                    return true;
                }
                if(id== R.id.logout){
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
}