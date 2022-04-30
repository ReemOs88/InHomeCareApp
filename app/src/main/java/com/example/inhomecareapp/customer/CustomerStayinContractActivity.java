package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.inhomecareapp.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

public class CustomerStayinContractActivity extends AppCompatActivity {
     TextInputEditText addressStayInContract, selectDateStayInContract;
     MaterialButton showContract;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_stayin_contract);
       // addressStayInContract=findViewById(R.id.address_stay_in_contract);
        selectDateStayInContract=findViewById(R.id.select_date_stay_in_contract);

        MaterialDatePicker<Pair<Long,Long>> dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                        .setTitleText("Select dates")
                        .setSelection(new Pair<>(MaterialDatePicker.thisMonthInUtcMilliseconds(),
                                MaterialDatePicker.todayInUtcMilliseconds()))
                        .build();

        selectDateStayInContract.setOnClickListener(view ->
                dateRangePicker.show(getSupportFragmentManager(), "DatePickerRange"));

        showContract=findViewById(R.id.show_contract_btn);
        showContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerStayinContractActivity.this, ContractsActivity.class);
                startActivity(intent);
            }
        });
        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_customer_profile) {
                    Intent intent = new Intent(CustomerStayinContractActivity.this, CustomerProfileActivity.class);
                    startActivity(intent);
                    return true;

                } else if(id == R.id.item_customer_home){
                    Intent intent = new Intent(CustomerStayinContractActivity.this, CustomerHome.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }



        });


    }
}