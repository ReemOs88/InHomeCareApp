package com.example.inhomecareapp.customer;

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

public class CustomerRegisterTabFragment extends Fragment {
    EditText customerNameRegisterEt;
    EditText customerPassRegisterEt;
    EditText customerEmailRegisterEt;
    EditText customerPhoneRegisterEt;
    EditText customerAddressRegisterEt;
    MaterialButton customerRegisterBtn;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    private static final String TAG = "CustomerRegisterTabFrag";
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private String customerNameRegister, customerEmailRegister, customerPhoneRegister, customerAddressRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.customer_register_fragment, container, false);

        customerNameRegisterEt = root.findViewById(R.id.customerName_register);
        customerPassRegisterEt = root.findViewById(R.id.customerPass_register);
        customerEmailRegisterEt = root.findViewById(R.id.customerEmail_register);
        customerPhoneRegisterEt = root.findViewById(R.id.customerPhone_register);
        customerAddressRegisterEt = root.findViewById(R.id.customerAddress_register);
        radioButtonMale = root.findViewById(R.id.radio_button_1);
        radioButtonFemale = root.findViewById(R.id.radio_button_2);
        customerRegisterBtn = root.findViewById(R.id.customerRegister_btn);

        customerRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerNameRegister = customerNameRegisterEt.getText().toString().trim();
                customerEmailRegister = customerEmailRegisterEt.getText().toString().trim();
                String customerPassRegister = customerPassRegisterEt.getText().toString().trim();
                customerPhoneRegister = customerPhoneRegisterEt.getText().toString().trim();
                customerAddressRegister = customerAddressRegisterEt.getText().toString().trim();

                if (customerNameRegister.isEmpty() || customerEmailRegister.isEmpty() || customerPassRegister.isEmpty() ||
                        customerPhoneRegister.isEmpty() || customerAddressRegister.isEmpty()) {
                    Toast.makeText(requireContext(), "please fill all data", Toast.LENGTH_LONG).show();
                    return;
                }
                if (customerPassRegister.length() < 6) {
                    Toast.makeText(requireContext(), "password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                createCustomerByEmail(customerEmailRegister, customerPassRegister);
            }

        });
        return root;

    }

    private void createCustomerByEmail(String customerEmailRegister, String customerPassRegister) {
        firebaseAuth.createUserWithEmailAndPassword(customerEmailRegister, customerPassRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "Account is created", Toast.LENGTH_SHORT).show();
                            uploadUCustomerData();
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: " + errorMessage);
                        }

                    }
                });
    }

    private void uploadUCustomerData() {
        CustomerData customerData = new CustomerData(customerNameRegister, customerEmailRegister,
                customerPhoneRegister, customerAddressRegister);

        firebaseFirestore
                .collection("inHomeCustomers")
                .document(FirebaseAuth.getInstance().getUid())
                .set(customerData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "User data is uploaded", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: User data uploaded");
                            Intent intent=new Intent(requireContext(), CustomerHome.class);
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






