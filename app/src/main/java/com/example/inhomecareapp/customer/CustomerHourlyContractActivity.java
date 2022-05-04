package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerHourlyContractActivity extends AppCompatActivity {
   TextInputEditText selectDateEt;
   TextInputEditText contractAddressEt;
   TextInputEditText enterTimeEt;
   MaterialButton showContract;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_hourly_contract);
        MaterialDatePicker datePicker=MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date").setSelection(MaterialDatePicker.thisMonthInUtcMilliseconds()).build();

        selectDateEt=findViewById(R.id.select_date_hourly_contract);
        selectDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getSupportFragmentManager(),"Tag");

            }
        });
        enterTimeEt=findViewById(R.id.select_time_et);
        showContract=findViewById(R.id.show_contract_btn);
        showContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(CustomerHourlyContractActivity.this, CustomerContractDetailsActivity.class);
//                startActivity(intent);
            }
        });

    }
}