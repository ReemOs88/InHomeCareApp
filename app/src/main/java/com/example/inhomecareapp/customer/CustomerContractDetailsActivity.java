package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverData;
import com.example.inhomecareapp.chat.ChatActivity;
import com.example.inhomecareapp.databinding.ActivityCaregiverContractsBinding;
import com.example.inhomecareapp.databinding.ActivityCustomerContractsBinding;

public class CustomerContractDetailsActivity extends AppCompatActivity {

    CaregiverData caregiverData;
    Contract contract;

    ActivityCustomerContractsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerContractsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        caregiverData = (CaregiverData) getIntent().getSerializableExtra("caregiver");
        contract = (Contract) getIntent().getSerializableExtra("contract");

        binding.caregiverNameList.setText(caregiverData.getCaregiverNameRegister());
        binding.caregiverGenderList.setText(caregiverData.getRate() + "");
        binding.caregiverRateTv.setText(caregiverData.getCaregiverNameRegister());
        binding.caregiverServiceTypeList.setText(caregiverData.getServiceSelected());
        binding.caregiverCategoryList.setText(caregiverData.getSelectedCategory());
        binding.caregiverAgeList.setText(caregiverData.getSelectedAge());

        binding.customerAddressContract.setText(contract.getCustomerAddress());
        binding.hourlyContractDate.setText(contract.getDateRange());
        binding.hourlyContractTime.setText(contract.getTimeRange());

        binding.hourlyContactBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("userId", caregiverData.getCaregiverId());
            intent.putExtra("username", caregiverData.getCaregiverNameRegister());
            intent.putExtra("type", "customer");
            startActivity(intent);
        });

        binding.rateContractBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, RateActivity.class);
            intent.putExtra("caregiverId", caregiverData.getCaregiverId());
            startActivity(intent);
            finish();
        });


    }

}
