package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverData;
import com.example.inhomecareapp.chat.ChatActivity;
import com.example.inhomecareapp.databinding.ActivityCustomerContractsBinding;
import com.squareup.picasso.Picasso;

public class CustomerContractDetailsActivity extends AppCompatActivity {

    CaregiverData caregiverData;
    Contract contract;

    ActivityCustomerContractsBinding binding;
    boolean isCaregiver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerContractsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        isCaregiver = sharedPreferences.getString("userType", "").equals("caregiver");
        if (isCaregiver) {
            binding.rateContractBtn.setVisibility(View.GONE);
        }

        caregiverData = (CaregiverData) getIntent().getSerializableExtra("caregiver");
        contract = (Contract) getIntent().getSerializableExtra("contract");

        Picasso.get().load(caregiverData.getImageUrl()).into(binding.caregiverListProfilePicImageView);

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
            if (isCaregiver) {
                intent.putExtra("userId", contract.getCustomerId());
                intent.putExtra("username", contract.getCustomerName());
                intent.putExtra("type", "caregiver");
            } else {
                intent.putExtra("userId", caregiverData.getCaregiverId());
                intent.putExtra("username", caregiverData.getCaregiverNameRegister());
                intent.putExtra("type", "customer");
            }

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
