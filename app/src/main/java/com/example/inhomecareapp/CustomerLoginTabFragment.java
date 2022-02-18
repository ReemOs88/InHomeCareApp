package com.example.inhomecareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class CustomerLoginTabFragment extends Fragment {
    EditText customerNameEt;
    EditText customerPassEt;
    TextView customerForgetPass;
    MaterialButton customerLoginBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.customer_login_fragment,container,false);

        customerNameEt= root.findViewById(R.id.customerName_login);
        customerPassEt=root.findViewById(R.id.customerPass_login);
        customerForgetPass=root.findViewById(R.id.customerPass_login);
        customerLoginBtn=root.findViewById(R.id.customer_loin_btn);
        customerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CustomerHome.class);
                startActivity(intent);
            }
        });
        return root;



    }

}
