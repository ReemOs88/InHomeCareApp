package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverEditProfileActivity;
import com.example.inhomecareapp.caregiver.CaregiverHome;
import com.example.inhomecareapp.caregiver.CaregiverProfileActivity;
import com.google.android.material.bottomappbar.BottomAppBar;

public class CustomerEditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_profile);

    }
}