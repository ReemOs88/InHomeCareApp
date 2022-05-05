package com.example.inhomecareapp.customer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.inhomecareapp.MapsActivity;
import com.example.inhomecareapp.R;
import com.example.inhomecareapp.databinding.ActivityCustomerEditProfileBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomerEditProfileActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    CustomerData customerData;

    ActivityCustomerEditProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        customerData = (CustomerData) getIntent().getSerializableExtra("customer");

        binding.customerEditNameEt.setText(customerData.getCustomerNameRegister());
        binding.customerEditPhoneEt.setText(customerData.getCustomerPhoneRegister());
        binding.contractAddressEt.setText(customerData.getCustomerAddressRegister());

        if (customerData.getGender().equals("male")) {
            binding.radioButtonCustomerMale.setChecked(true);
        } else {
            binding.radioButtonCustomerFemale.setChecked(true);
        }
        binding.contractAddressEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerEditProfileActivity.this, MapsActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    public void editProfile(View view) {
        String name = binding.customerEditNameEt.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, "name required", Toast.LENGTH_LONG).show();
            return;
        }

        String phone = binding.customerEditPhoneEt.getText().toString();

        if (phone.isEmpty()) {
            Toast.makeText(this, "phone required", Toast.LENGTH_LONG).show();
            return;
        }

        String address = binding.contractAddressEt.getText().toString();

        if (address.isEmpty()) {
            Toast.makeText(this, "address required", Toast.LENGTH_LONG).show();
            return;
        }

        String gender;

        if (binding.radioButtonCustomerMale.isChecked()) {
            gender = "male";
        } else {
            gender = "female";
        }

        customerData.setCustomerNameRegister(name);
        customerData.setCustomerPhoneRegister(phone);
        customerData.setCustomerAddressRegister(address);
        customerData.setGender(gender);

        FirebaseFirestore
                .getInstance()
                .collection("inHomeCustomers")
                .document(firebaseAuth.getUid())
                .set(customerData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CustomerEditProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String address = data.getStringExtra("address");
            binding.contractAddressEt.setText(address);
        }
    }

}