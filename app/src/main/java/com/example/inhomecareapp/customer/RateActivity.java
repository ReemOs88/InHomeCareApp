package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.inhomecareapp.databinding.ActivityRateBinding;

public class RateActivity extends AppCompatActivity {

    ActivityRateBinding binding;
    String caregiverId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        caregiverId = getIntent().getStringExtra("caregiverId");

    }

}
