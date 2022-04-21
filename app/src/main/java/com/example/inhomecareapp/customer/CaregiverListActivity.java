package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CustomerPostsAdapter;

public class CaregiverListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_list);
        CaregiversListAdapter caregiversListAdapter = new CaregiversListAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(caregiversListAdapter);
    }
}