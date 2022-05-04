package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;

public class HourlyCaregiversActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_caregivers);
        HourlyCaregiversAdapter hourlyCaregiversAdapter= new HourlyCaregiversAdapter();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(hourlyCaregiversAdapter);
   ;
    }
}