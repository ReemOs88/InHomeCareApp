package com.example.inhomecareapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.button.MaterialButton;

public class CaregiverRegisterTabFragment extends Fragment {
    EditText caregiverNameRegisterEt;
    EditText caregiverPassRegisterEt;
    EditText caregiverIdEt;
    EditText caregiverEmailRegisterEt;
    EditText caregiverPhoneRegisterEt;
    EditText caregiverAddressRegisterEt;
    MaterialButton caregiverRegisterBtn;
    RadioButton radioButton1;
    RadioButton radioButton2;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.caregiver_register_fragment,container,false);
        caregiverNameRegisterEt=root.findViewById(R.id.caregiverName_register);
        caregiverPassRegisterEt=root.findViewById(R.id.caregiverPass_register);
        caregiverIdEt=root.findViewById(R.id.caregiverID_register);
        caregiverEmailRegisterEt=root.findViewById(R.id.caregiverEmail_register);
        caregiverPhoneRegisterEt=root.findViewById(R.id.caregiverPhone_register);
        caregiverAddressRegisterEt=root.findViewById(R.id.caregiverAddress_register);
        radioButton1 = root.findViewById(R.id.radio_button_1);
        radioButton2 = root.findViewById(R.id.radio_button_2);
        caregiverRegisterBtn = root.findViewById(R.id.caregiverRegister_btn);
        caregiverRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),CustomerHome.class);
                startActivity(intent);
            }
        });
        return root;

    }
}
