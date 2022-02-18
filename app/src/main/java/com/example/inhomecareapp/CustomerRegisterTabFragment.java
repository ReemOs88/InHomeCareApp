package com.example.inhomecareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerRegisterTabFragment extends Fragment {
    EditText customerNameRegisterEt;
    EditText customerPassRegisterEt;
    EditText customerEmailRegisterEt;
    EditText customerPhoneRegisterEt;
    EditText customerAddressRegisterEt;
    MaterialButton customerRegisterBtn;
    RadioButton radioButton1;
    RadioButton radioButton2;

    //FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.customer_register_fragment, container, false);

        customerNameRegisterEt = root.findViewById(R.id.customerName_register);
        customerPassRegisterEt = root.findViewById(R.id.customerPass_register);
        customerEmailRegisterEt = root.findViewById(R.id.customerEmail_register);
        customerPhoneRegisterEt = root.findViewById(R.id.customerPhone_register);
        customerAddressRegisterEt = root.findViewById(R.id.customerAddress_register);
        radioButton1 = root.findViewById(R.id.radio_button_1);
        radioButton2 = root.findViewById(R.id.radio_button_2);
        customerRegisterBtn = root.findViewById(R.id.customerRegister_btn);

        customerRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),CustomerHome.class);
                startActivity(intent);
            }
        });
        return root;

    }
}






