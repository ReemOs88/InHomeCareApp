package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverData;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class StayInCaregiverListActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    ArrayList<CaregiverData> caregivers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stayin_caregiver_list);

        caregivers = (ArrayList<CaregiverData>) getIntent().getSerializableExtra("caregivers");

        StayInCaregiversListAdapter caregiversListAdapter = new StayInCaregiversListAdapter(caregivers);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(caregiversListAdapter);


    }
}