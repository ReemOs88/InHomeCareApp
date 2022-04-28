package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.inhomecareapp.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

public class CustomerHourlyContractActivity extends AppCompatActivity {
   TextInputEditText selectDateEt;
   TextInputEditText contractAddressEt;
   TextInputEditText enterTimeEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_hourly_contract);
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

        selectDateEt=findViewById(R.id.select_date_hourly_contract);
        selectDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.show(getSupportFragmentManager(),"Tag");

            }
        });
        enterTimeEt=findViewById(R.id.select_time_et);
        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_customer_profile) {
                    Intent intent = new Intent(CustomerHourlyContractActivity.this, CustomerProfileActivity.class);
                    startActivity(intent);
                    return true;

                } else if(id == R.id.item_customer_home){
                    Intent intent = new Intent(CustomerHourlyContractActivity.this, CustomerHome.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }



        });

    }
}