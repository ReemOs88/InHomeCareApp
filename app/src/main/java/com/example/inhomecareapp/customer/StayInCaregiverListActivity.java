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

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_customer_profile) {
                    Intent intent = new Intent(StayInCaregiverListActivity.this, CustomerProfileActivity.class);
                    startActivity(intent);
                    return true;

                } else if(id == R.id.item_customer_home){
                    Intent intent = new Intent(StayInCaregiverListActivity.this, CustomerHome.class);
                    startActivity(intent);
                    return true;
                } if (id == R.id.item_customer_contract) {
                    Intent intent = new Intent(StayInCaregiverListActivity.this, CustomerAllContractsActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_customer_Logout) {
                    firebaseAuth.signOut();
                    Toast.makeText(StayInCaregiverListActivity.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
                }
                return false;
            }



        });
    }
}