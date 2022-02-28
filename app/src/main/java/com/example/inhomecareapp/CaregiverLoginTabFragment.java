package com.example.inhomecareapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class CaregiverLoginTabFragment extends Fragment {
    EditText caregiverNameET;
    EditText caregiverPassET;
    TextView caregiverForgetPass;
    MaterialButton caregiverLoginBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.caregiver_login_fragment,container,false);
        caregiverNameET=root.findViewById(R.id.caregiverName_login);
        caregiverPassET=root.findViewById(R.id.caregiverPass_login);
        caregiverForgetPass=root.findViewById(R.id.caregiverPass_login);
        caregiverLoginBtn=root.findViewById(R.id.caregiver_loin_btn);
        caregiverLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),CustomerHome.class);
                startActivity(intent);
            }
        });
        return root;



    }

}
