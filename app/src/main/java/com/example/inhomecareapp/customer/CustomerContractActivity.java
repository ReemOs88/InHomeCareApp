package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.inhomecareapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

public class CustomerContractActivity extends AppCompatActivity {
   TextInputEditText selectDateEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_contract);
        MaterialDatePicker datePicker=MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date").setSelection(MaterialDatePicker.thisMonthInUtcMilliseconds()).build();


        selectDateEt=findViewById(R.id.select_date_et);
        selectDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getSupportFragmentManager(),"Tag");

            }
        });
    }
}