package com.example.inhomecareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.inhomecareapp.caregiver.CaregiverLoginActivity;
import com.example.inhomecareapp.customer.CustomerLoginActivity;

public class MainActivity extends AppCompatActivity {
      Button customerBtn,caregiverBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerBtn=findViewById(R.id.customer);

        customerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
            }
        });
        caregiverBtn=findViewById(R.id.caregiver);

        caregiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, CaregiverLoginActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
            }
        });

    }




}