package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.inhomecareapp.databinding.ActivityCustomerAllContractsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CustomerAllContractsActivity extends AppCompatActivity {

    ActivityCustomerAllContractsBinding binding;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerAllContractsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getAllContracts();
    }

    private void getAllContracts() {
        firestore.collection("contracts")
                .whereEqualTo("customerId", auth.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot query) {
                        List<Contract> contractList = new ArrayList<>();

                        for (QueryDocumentSnapshot snapshot : query) {
                            Contract contract = snapshot.toObject(Contract.class);
                            contractList.add(contract);
                        }

                        AllContractsAdapter adapter = new AllContractsAdapter(contractList);
                        binding.rvContracts.setAdapter(adapter);
                    }
                });
    }

}