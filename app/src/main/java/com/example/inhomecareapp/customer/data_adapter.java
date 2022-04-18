package com.example.inhomecareapp.customer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverData;

import java.util.List;

public class data_adapter extends RecyclerView.Adapter<data_adapter.MyViewHolder>{

    List<CaregiverData> mdata;
    private LayoutInflater inflater;
    Context mContext;
    contract_data contract_data;
    int check;





    public data_adapter(List<CaregiverData> mdata, Context mcontext,contract_data contract_data,int check) {
        this.mdata = mdata;
        this.mContext=mcontext;
        this.contract_data=  contract_data;
        this.check=check;

    }




    @NonNull
    @Override
    public data_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mContext);
        View view=view = inflater.inflate(R.layout.item_caregivert, parent, false);
        MyViewHolder holder = new MyViewHolder(view,mContext);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull data_adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(mdata.get(position).getCaregiverNameRegister());
        holder.phon.setText(mdata.get(position).getCaregiverPhoneRegister());
        holder.address.setText(mdata.get(position).getCaregiverAddressRegister());

    }




    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder   implements View.OnClickListener {
        TextView name,phon,address;

        private Context context;



        public MyViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.namm);
            itemView.setOnClickListener(this);
            phon = itemView.findViewById(R.id.phonnn);
            address = itemView.findViewById(R.id.addresssss);
        }
        @Override
        public void onClick(View v) {
            int position = getLayoutPosition(); // gets item position
            Intent i ;
            if(check==1){
                i = new Intent(mContext,stayincontact.class);

            }else{
                i = new Intent(mContext,hourlycontract.class);

            }
            String data="Name "+mdata.get(position).getCaregiverNameRegister()+" phon number: "+mdata.get(position).getCaregiverPhoneRegister()
                    +" Address: "+mdata.get(position).getCaregiverAddressRegister();
            contract_data.setCaregiver(data);
            i.putExtra("obj",contract_data);
            mContext.startActivity(i);



        }
    }





}
