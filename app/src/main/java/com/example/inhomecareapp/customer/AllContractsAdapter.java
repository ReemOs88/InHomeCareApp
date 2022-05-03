package com.example.inhomecareapp.customer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inhomecareapp.caregiver.CaregiverData;
import com.example.inhomecareapp.databinding.ItemContractsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AllContractsAdapter extends RecyclerView.Adapter<AllContractsAdapter.Holder> {

    List<Contract> contractList;

    public AllContractsAdapter(List<Contract> contractList) {
        this.contractList = contractList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(ItemContractsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Contract contract = contractList.get(position);

        holder.binding.tvContractNumber.setText("Contract number : " + (position + 1));
        holder.binding.tvContractDate.setText("Date : " + contract.getDateRange());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCaregiverData(contract, view.getContext());
            }
        });
    }

    private void getCaregiverData(Contract contract, Context context) {
        FirebaseFirestore.getInstance()
                .collection("inHomeCaregivers")
                .document(contract.getCaregiverId())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        CaregiverData caregiverData = documentSnapshot.toObject(CaregiverData.class);

                        Intent intent = new Intent(context, CustomerContractDetailsActivity.class);
                        intent.putExtra("caregiver", caregiverData);
                        intent.putExtra("contract", contract);
                        context.startActivity(intent);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return contractList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemContractsBinding binding;

        public Holder(@NonNull ItemContractsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
