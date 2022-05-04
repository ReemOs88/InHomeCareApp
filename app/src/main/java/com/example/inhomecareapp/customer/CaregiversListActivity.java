package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverData;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class CaregiversListActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    ArrayList<CaregiverData> caregivers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregivers_list);

        caregivers = (ArrayList<CaregiverData>) getIntent().getSerializableExtra("caregivers");

        CaregiversListAdapter caregiversListAdapter = new CaregiversListAdapter(caregivers);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(caregiversListAdapter);


    }
}