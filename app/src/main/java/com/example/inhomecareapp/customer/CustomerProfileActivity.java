package com.example.inhomecareapp.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomerProfileActivity extends AppCompatActivity {
    TextView customerName, customerPhone, customerAddress, customerGender;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CustomerData customerData;
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        customerName = findViewById(R.id.customer_name_profile_activity);
        customerPhone = findViewById(R.id.customer_phone_profile_activity);
        customerAddress = findViewById(R.id.customer_address_profile_activity);
        customerGender = findViewById(R.id.customer_gender_profile_activity);

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_customer_profile) {
                    Intent intent = new Intent(CustomerProfileActivity.this, CustomerHome.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_customer_contract) {
                    Intent intent = new Intent(CustomerProfileActivity.this, CustomerAllContractsActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_customer_Logout) {
                    firebaseAuth.signOut();
                    Toast.makeText(CustomerProfileActivity.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
                }
                return false;
            }


        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCustomerData();
    }

    private void getCustomerData() {
        String uid = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore.collection("inHomeCustomers").document(uid)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    customerData = task.getResult().toObject(CustomerData.class);
                    Log.i(TAG, "onComplete: " + customerData.toString());
                    updateUi(customerData);
                } else {
                    String errorMessage = task.getException().getLocalizedMessage();
                    Toast.makeText(CustomerProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onComplete: " + errorMessage);
                }
            }
        });

    }

    private void updateUi(CustomerData customerData) {
        customerName.setText(customerData.getCustomerNameRegister());
        customerPhone.setText(customerData.getCustomerPhoneRegister());
        customerAddress.setText(customerData.getCustomerAddressRegister());
        customerGender.setText(customerData.getGender());
    }

    public void editProfile(View view) {
        Intent intent = new Intent(this, CustomerEditProfileActivity.class);
        intent.putExtra("customer", customerData);
        startActivity(intent);
    }

}
