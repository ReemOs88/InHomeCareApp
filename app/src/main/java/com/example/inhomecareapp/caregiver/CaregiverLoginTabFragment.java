package com.example.inhomecareapp.caregiver;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.inhomecareapp.ForgetPasswordActivity;
import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverHome;
import com.example.inhomecareapp.customer.CustomerHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CaregiverLoginTabFragment extends Fragment {
    EditText caregiverEmailET;
    EditText caregiverPassET;
    TextView caregiverForgetPass;
    MaterialButton caregiverLoginBtn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private static final String TAG = "CaregiverRegisterFrag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.caregiver_login_fragment, container, false);
        caregiverEmailET = root.findViewById(R.id.caregiverEmail_login);
        caregiverPassET = root.findViewById(R.id.caregiverPass_login);
        caregiverForgetPass = root.findViewById(R.id.forget_pass_tv);
        caregiverLoginBtn = root.findViewById(R.id.caregiver_loin_btn);

        caregiverForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(requireContext(), ForgetPasswordActivity.class));
            }
        });
        caregiverLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caregiverEmail= caregiverEmailET.getText().toString();
                String caregiverPass= caregiverPassET.getText().toString();
                if (caregiverEmail.isEmpty() || caregiverPass.isEmpty()) {
                    Toast.makeText(requireContext(), "please fill all data", Toast.LENGTH_LONG).show();
                    return;
                }
                if(caregiverPass.length()<6) {
                    Toast.makeText(requireContext(), "password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(caregiverEmail,caregiverPass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                 if (task.getResult().getUser().isEmailVerified()){
                 FirebaseFirestore.getInstance().collection("inHomeCaregivers")
                  .document(task.getResult().getUser().getUid()).get()
                  .addOnSuccessListener(documentSnapshot -> {
                  if (documentSnapshot.exists()) {
                  Intent intent=new Intent(requireContext(), CaregiverHome.class);
                   startActivity(intent);
                    getActivity().finish();
                      } else {
                    Toast.makeText(requireContext(), "Not caregiver", Toast.LENGTH_SHORT).show();
                    }
                    });

                      } else{
                     Toast.makeText(requireContext(), "Please verify your email!", Toast.LENGTH_SHORT).show();
                      }
                    }else {
                    String errorMessage=task.getException().getLocalizedMessage();
                   Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onComplete: "+errorMessage);
                   }
                            }
                        });
                         }
                       });
                       return root; }}
