package com.example.inhomecareapp.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CustomerPostsAdapter;
import com.google.android.material.button.MaterialButton;

public class CaregiversListAdapter extends RecyclerView.Adapter<CaregiversListAdapter.CaregiversListViewHolder> {
    private Context context;
    public CaregiversListAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public CaregiversListAdapter.CaregiversListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CaregiversListViewHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.customer_posts, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CaregiversListAdapter.CaregiversListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }
    static class CaregiversListViewHolder extends RecyclerView.ViewHolder{

        TextView caregiverNameTv,caregiverGenderTv,caregiverSpecializeTv,caregiverServiceTypeTv,caregiverAgeTv;
        MaterialButton caregiverContractBtn;

        public CaregiversListViewHolder(@NonNull View itemView){
            super(itemView);

            caregiverNameTv=itemView.findViewById(R.id.caregiver_name);
            caregiverGenderTv=itemView.findViewById(R.id.caregiver_gender);
            caregiverServiceTypeTv=itemView.findViewById(R.id.caregiver_serviceType);
            caregiverSpecializeTv=itemView.findViewById(R.id.caregiver_specialize);
            caregiverAgeTv=itemView.findViewById(R.id.caregiver_Age);
            caregiverContractBtn=itemView.findViewById(R.id.contract_btn);

        }
    }
}
