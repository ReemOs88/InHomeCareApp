package com.example.inhomecareapp.caregiver;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inhomecareapp.customer.AllContractsAdapter;
import com.example.inhomecareapp.customer.Contract;
import com.example.inhomecareapp.databinding.ActivityCustomerAllContractsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CaregiverAllContractsActivity extends AppCompatActivity {

    ActivityCustomerAllContractsBinding binding;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerAllContractsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllContracts();
    }

    private void getAllContracts() {
        firestore.collection("contracts")
                .whereEqualTo("caregiverId", auth.getUid())
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
