package com.example.inhomecareapp.customer;

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

import com.example.inhomecareapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLoginTabFragment extends Fragment {
    EditText customerEmailEt;
    EditText customerPassEt;
    TextView customerForgetPass;
    MaterialButton customerLoginBtn;
    FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    private static final String TAG = "CustomerRegisterTabFrag";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root=(ViewGroup) inflater.inflate(R.layout.customer_login_fragment,container,false);

        customerEmailEt= root.findViewById(R.id.customerEmail_login);
        customerPassEt=root.findViewById(R.id.customerPass_login);
        customerForgetPass=root.findViewById(R.id.customerPass_login);
        customerLoginBtn=root.findViewById(R.id.customer_loin_btn);
        customerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerEmail= customerEmailEt.getText().toString();
                String customerPass= customerPassEt.getText().toString();
                if (customerEmail.isEmpty() || customerPass.isEmpty()) {
                    Toast.makeText(requireContext(), "please fill all data", Toast.LENGTH_LONG).show();
                    return;
                }
                if(customerPass.length()<6) {
                    Toast.makeText(requireContext(), "password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(customerEmail,customerPass)
                             .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                     if(task.isSuccessful()){
                                         if (task.getResult().getUser().isEmailVerified()){
                                             Intent intent=new Intent(requireContext(), home.class);
                                             startActivity(intent);
                                             getActivity().finish();
                                         }
                                         else {
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
        return root;



    }

}
