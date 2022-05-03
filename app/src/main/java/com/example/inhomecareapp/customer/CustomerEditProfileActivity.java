package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_customer_profile) {
                    Intent intent = new Intent(CustomerEditProfileActivity.this, CustomerHome.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_customer_contract) {
                    Intent intent = new Intent(CustomerEditProfileActivity.this, CustomerAllContractsActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_customer_Logout) {
                    firebaseAuth.signOut();
                    Toast.makeText(CustomerEditProfileActivity.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
                }
                return false;
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
}