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

public class HourlyCaregiversAdapter extends RecyclerView.Adapter<HourlyCaregiversAdapter.HourlyCaregiversViewHolder> {
    @NonNull
    @Override
    public HourlyCaregiversViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HourlyCaregiversViewHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.hourly_caregivers, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull HourlyCaregiversViewHolder holder, int position) {
          holder.hourlyContractBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent=new Intent(view.getContext(),CustomerHourlyContractActivity.class);
                  view.getContext().startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class HourlyCaregiversViewHolder extends RecyclerView.ViewHolder {
        TextView hourlyCaregiverName,hourlyCaregiverGender,hourlyCaregiverServiceType
                ,hourlyCaregiverSpecialize,hourlyCaregiverCategory;
        MaterialButton hourlyContractBtn;
        public HourlyCaregiversViewHolder(@NonNull View itemView){
            super(itemView);
            hourlyCaregiverName=itemView.findViewById(R.id.hourly_caregiver_name);
            hourlyCaregiverGender=itemView.findViewById(R.id.hourly_caregiver_gender);
            hourlyCaregiverServiceType=itemView.findViewById(R.id.hourly_caregiver_serviceType);
            hourlyCaregiverSpecialize=itemView.findViewById(R.id.hourly_caregiver_category);
            hourlyCaregiverCategory=itemView.findViewById(R.id.hourly_caregiver_category);
        }
    }
}
