package com.example.inhomecareapp.customer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CustomerPostsAdapter;
import com.google.android.material.button.MaterialButton;

public class CaregiversListAdapter extends RecyclerView.Adapter<CaregiversListAdapter.CaregiversListViewHolder> {
    private Context context;

    public CaregiversListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CaregiversListAdapter.CaregiversListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CaregiversListViewHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.caregivers, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CaregiversListAdapter.CaregiversListViewHolder holder, int position) {
      holder.caregiverStayinContractBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(view.getContext(),CustomerStayinContractActivity.class);
              view.getContext().startActivity(intent);

          }
      });
      holder.caregiverHourlyContract.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(view.getContext(),CustomerHourlyContractActivity.class);
              view.getContext().startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    static class CaregiversListViewHolder extends RecyclerView.ViewHolder {


        TextView caregiverNameTv, caregiverGenderTv, caregiverSpecializeTv, caregiverServiceTypeTv, caregiverAgeTv;
        MaterialButton caregiverStayinContractBtn,caregiverHourlyContract;

        public CaregiversListViewHolder(@NonNull View itemView) {
            super(itemView);

            caregiverNameTv = itemView.findViewById(R.id.caregiver_name_list);
            caregiverGenderTv = itemView.findViewById(R.id.caregiver_gender_list);
            caregiverServiceTypeTv = itemView.findViewById(R.id.caregiver_serviceType_list);
            caregiverSpecializeTv = itemView.findViewById(R.id.caregiver_category_list);
            caregiverAgeTv = itemView.findViewById(R.id.caregiver_Age_list);
            caregiverStayinContractBtn = itemView.findViewById(R.id.stay_in_contract_btn);
            caregiverHourlyContract=itemView.findViewById(R.id.hourly_contract_btn);

        }
    }
}

