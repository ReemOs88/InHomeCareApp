package com.example.inhomecareapp.customer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inhomecareapp.MapsActivity;
import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerStayInContractActivity extends AppCompatActivity {
    TextInputLayout textInputLayoutTime;
    TextInputEditText etSelectTime;
    TextView addressStayInContract, selectDateStayInContract;
    MaterialButton showContract;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    CaregiverData caregiverData;

    String address = "";
    String date = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_stayin_contract);

        textInputLayoutTime = findViewById(R.id.time_layout);
        addressStayInContract = findViewById(R.id.address_stay_in_contract);
        selectDateStayInContract = findViewById(R.id.select_date_stay_in_contract);
        etSelectTime = findViewById(R.id.select_time_contract);

        caregiverData = (CaregiverData) getIntent().getSerializableExtra("caregiver");

        if (caregiverData.getServiceSelected().equals("Stay in service")) {
            textInputLayoutTime.setVisibility(View.GONE);
        } else {
            textInputLayoutTime.setVisibility(View.VISIBLE);
        }

        addressStayInContract.setOnClickListener(view -> {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivityForResult(intent, 1);
        });

        MaterialDatePicker<Pair<Long, Long>> dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                        .setTitleText("Select dates")
                        .setSelection(new Pair<>(MaterialDatePicker.thisMonthInUtcMilliseconds(),
                                MaterialDatePicker.todayInUtcMilliseconds()))
                        .build();


        selectDateStayInContract.setOnClickListener(view ->
                dateRangePicker.show(getSupportFragmentManager(), "DatePickerRange"));

        dateRangePicker.addOnPositiveButtonClickListener(selection -> {
            Long startDate = selection.first;
            Long endDate = selection.second;

            Date date = new Date(startDate);
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
            String formattedStartDate = df.format(date);

            Date date2 = new Date(endDate);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
            String formattedEndDate = df2.format(date2);

            this.date = formattedStartDate + " : " + formattedEndDate;
            selectDateStayInContract.setText(this.date);
        });

        showContract = findViewById(R.id.show_contract_btn);
        showContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContract();
            }
        });
        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_customer_profile) {
                    Intent intent = new Intent(CustomerStayInContractActivity.this, CustomerProfileActivity.class);
                    startActivity(intent);
                    return true;

                } else if (id == R.id.item_customer_home) {
                    Intent intent = new Intent(CustomerStayInContractActivity.this, CustomerHome.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_customer_contract) {
                    Intent intent = new Intent(CustomerStayInContractActivity.this, CustomerAllContractsActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_customer_Logout) {
                    firebaseAuth.signOut();
                    Toast.makeText(CustomerStayInContractActivity.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
                }
                return false;
            }


        });

    }

    private void openContract() {
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String customerName = sharedPreferences.getString("customerName", "");

//        String address = addressStayInContract.getText().toString();

        if (address.isEmpty()) {
            Toast.makeText(this, "address required", Toast.LENGTH_LONG).show();
            return;
        }

//        String date = selectDateStayInContract.getText().toString();

        if (date.isEmpty()) {
            Toast.makeText(this, "date required", Toast.LENGTH_LONG).show();
            return;
        }

        Contract contract = new Contract(
                String.valueOf(System.currentTimeMillis()),
                FirebaseAuth.getInstance().getUid(),
                caregiverData.getCaregiverId(),
                customerName,
                address,
                date,
                etSelectTime.getText().toString()
        );

        FirebaseFirestore
                .getInstance()
                .collection("contracts")
                .document(contract.getContractId())
                .set(contract)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Intent intent = new Intent(CustomerStayInContractActivity.this, CustomerContractDetailsActivity.class);
                        intent.putExtra("caregiver", caregiverData);
                        intent.putExtra("contract", contract);
                        startActivity(intent);
                    }

                });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String address = data.getStringExtra("address");
            addressStayInContract.setText(address);
            this.address = address;
        }

    }

}
