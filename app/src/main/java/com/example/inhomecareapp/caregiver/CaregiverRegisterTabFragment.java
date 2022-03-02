package com.example.inhomecareapp.caregiver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.inhomecareapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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
    private static final String TAG = "CaregiverRegisterFrag";
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private String caregiverNameRegister, caregiverEmailRegister, caregiverPhoneRegister, caregiverAddressRegister;
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
                caregiverNameRegister = caregiverNameRegisterEt.getText().toString().trim();
                caregiverEmailRegister = caregiverEmailRegisterEt.getText().toString().trim();
                String caregiverPassRegister = caregiverPassRegisterEt.getText().toString().trim();
                caregiverPhoneRegister = caregiverPhoneRegisterEt.getText().toString().trim();
                caregiverAddressRegister = caregiverAddressRegisterEt.getText().toString().trim();

                if (caregiverNameRegister.isEmpty() || caregiverEmailRegister.isEmpty() || caregiverPassRegister.isEmpty() ||
                        caregiverPhoneRegister.isEmpty() || caregiverAddressRegister.isEmpty()) {
                    Toast.makeText(requireContext(), "please fill all data", Toast.LENGTH_LONG).show();
                    return;
                }
                if (caregiverPassRegister.length() < 6)
                {
                    Toast.makeText(requireContext(), "password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                createCaregiverByEmail(caregiverEmailRegister, caregiverPassRegister);
            }

        });
        return root;

    }

    private void createCaregiverByEmail(String caregiverEmailRegister, String caregiverPassRegister) {
        firebaseAuth.createUserWithEmailAndPassword(caregiverEmailRegister, caregiverPassRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "Account is created", Toast.LENGTH_SHORT).show();
                            uploadUCaregiverData();
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG,"onComplete: "+errorMessage);
                        }

                    }
                });
    }

    private void uploadUCaregiverData() {
        CaregiverData caregiverData = new CaregiverData(caregiverNameRegister, caregiverEmailRegister,
                caregiverPhoneRegister, caregiverAddressRegister);

        firebaseFirestore
                .collection("inHomeCaregivers")
                .document(FirebaseAuth.getInstance().getUid())
                .set(caregiverData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "User data is uploaded", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: User data uploaded");
                            Intent intent=new Intent(requireContext(), CaregiverHome.class);
                            startActivity(intent);
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: " + errorMessage);
                        }
                    }
                });


    }

}