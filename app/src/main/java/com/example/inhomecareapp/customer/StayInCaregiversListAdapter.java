package com.example.inhomecareapp.customer;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inhomecareapp.R;
import com.google.android.material.button.MaterialButton;

public class StayInCaregiversListAdapter extends RecyclerView.Adapter<StayInCaregiversListAdapter.StayInCaregiversListViewHolder> {

    @NonNull
    @Override
    public StayInCaregiversListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StayInCaregiversListViewHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.stayin_caregivers, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StayInCaregiversListViewHolder holder, int position) {
        holder.caregiverStayInContractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),CustomerStayinContractActivity.class);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class StayInCaregiversListViewHolder extends RecyclerView.ViewHolder {
        TextView caregiverNameTv,caregiverGenderTv,caregiverServiceTypeTv,caregiverSpecializeTv, caregiverAgeTv;
        MaterialButton caregiverStayInContractBtn;
        public StayInCaregiversListViewHolder(@NonNull View itemView) {
            super(itemView);

            caregiverNameTv = itemView.findViewById(R.id.caregiver_name_list);
            caregiverGenderTv = itemView.findViewById(R.id.caregiver_gender_list);
            caregiverServiceTypeTv = itemView.findViewById(R.id.caregiver_serviceType_list);
            caregiverSpecializeTv = itemView.findViewById(R.id.caregiver_category_list);
            caregiverAgeTv = itemView.findViewById(R.id.caregiver_Age_list);
            caregiverStayInContractBtn = itemView.findViewById(R.id.stay_in_contract_btn);

        }
    }
}
