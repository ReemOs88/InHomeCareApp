package com.example.inhomecareapp.customer;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverData;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class CaregiversListAdapter extends RecyclerView.Adapter<CaregiversListAdapter.StayInCaregiversListViewHolder> {
    ArrayList<CaregiverData> caregivers;
    public CaregiversListAdapter(ArrayList<CaregiverData> caregivers) {
        this.caregivers = caregivers;
    }
    @NonNull
    @Override
    public StayInCaregiversListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StayInCaregiversListViewHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.caregivers, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull StayInCaregiversListViewHolder holder, int position) {
        CaregiverData caregiverData = caregivers.get(position);
        holder.caregiverNameTv.setText(caregiverData.getCaregiverNameRegister());
        holder.caregiverGenderTv.setText(caregiverData.getGender());
        holder.caregiverServiceTypeTv.setText(caregiverData.getServiceSelected());
        holder.caregiverSpecializeTv.setText(caregiverData.getSelectedCategory());
        holder.caregiverAgeTv.setText(caregiverData.getSelectedAge());
        Picasso.get().load(caregiverData.getImageUrl()).into(holder.profileImage);
        holder.caregiverStayInContractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CustomerContractActivity.class);
                intent.putExtra("caregiver", caregiverData);
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return caregivers.size();
    }
    public class StayInCaregiversListViewHolder extends RecyclerView.ViewHolder {
        TextView caregiverNameTv, caregiverGenderTv, caregiverServiceTypeTv, caregiverSpecializeTv, caregiverAgeTv;
        MaterialButton caregiverStayInContractBtn;
        ImageView profileImage;
        public StayInCaregiversListViewHolder(@NonNull View itemView) {
            super(itemView);
            caregiverNameTv = itemView.findViewById(R.id.caregiver_name_list);
            caregiverGenderTv = itemView.findViewById(R.id.caregiver_gender_list);
            caregiverServiceTypeTv = itemView.findViewById(R.id.caregiver_serviceType_list);
            caregiverSpecializeTv = itemView.findViewById(R.id.caregiver_category_list);
            caregiverAgeTv = itemView.findViewById(R.id.caregiver_Age_list);
            caregiverStayInContractBtn = itemView.findViewById(R.id.stay_in_contract_btn);
            profileImage = itemView.findViewById(R.id.caregiver_list_profilePic_imageView);

        }
    }
}
