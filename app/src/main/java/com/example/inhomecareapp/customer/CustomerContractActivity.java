package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.inhomecareapp.MapsActivity;
import com.example.inhomecareapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

public class CustomerContractActivity extends AppCompatActivity {
   TextInputEditText selectDateEt;
   TextInputEditText contractAddressEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_contract);
       // contractAddressEt=findViewById(R.id.contract_address_et);
       // contractAddressEt.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View view) {
             //   Intent intent=new Intent(CustomerContractActivity.this, MapsActivity.class);
                //startActivity(intent);
            //}
        //});
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