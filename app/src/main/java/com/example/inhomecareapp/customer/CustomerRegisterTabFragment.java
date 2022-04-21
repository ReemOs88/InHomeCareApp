package com.example.inhomecareapp.customer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inhomecareapp.MapsActivity;
import com.example.inhomecareapp.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class CustomerRegisterTabFragment extends Fragment {
    ImageView caregiverProfilePicRegister;
    EditText customerNameRegisterEt;
    EditText customerPassRegisterEt;
    EditText customerEmailRegisterEt;
    EditText customerPhoneRegisterEt;
    EditText customerAddressRegisterEt;
    Button   customerMapButton;
    MaterialButton customerRegisterBtn;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    DatabaseReference databaseReference;
    private static final String TAG = "CustomerRegisterTabFrag";
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private String customerNameRegister, customerEmailRegister, customerPhoneRegister, customerAddressRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.customer_register_fragment, container, false);
        caregiverProfilePicRegister=root.findViewById(R.id.caregiver_profile_Pic_register);
//        caregiverProfilePicRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImagePicker.with(requireActivity())
//                        .crop()	    			//Crop image(Optional), Check Customization for more option
//                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//                        .start();
//            }
//        });
        customerNameRegisterEt = root.findViewById(R.id.customerName_register);
        customerPassRegisterEt = root.findViewById(R.id.customerPass_register);
        customerEmailRegisterEt = root.findViewById(R.id.customerEmail_register);
        customerPhoneRegisterEt = root.findViewById(R.id.customerPhone_register);
        customerAddressRegisterEt = root.findViewById(R.id.customerAddress_register);
        customerMapButton=root.findViewById(R.id.customer_map_button);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("customer").push();
        customerMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireContext(), MapsActivity.class);
                startActivity(intent);


            }
        });
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

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//            if (resultCode == Activity.RESULT_OK) {
//                //Image Uri will not be null for RESULT_OK
//                Uri uri = data.getData();
//
//                        // Use Uri object instead of File to avoid storage permissions
//                caregiverProfilePicRegister.setImageURI(uri);
//            } else if (resultCode == ImagePicker.RESULT_ERROR) {
//                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
//            }
//        }


    private void createCustomerByEmail(String customerEmailRegister, String customerPassRegister) {
        firebaseAuth.createUserWithEmailAndPassword(customerEmailRegister, customerPassRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "Account is created", Toast.LENGTH_SHORT).show();
                            uploadUCustomerData();
                            sendVerificationEmail();
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
                           Intent intent=new Intent(requireContext(), CustomerHome.class);
                            startActivity(intent);
                            Toast.makeText(requireContext(), "User data is uploaded", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: User data uploaded");
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: " + errorMessage);
                        }
                    }
                });


    }

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // email sent


                            // after email is sent just logout the user and finish this activity
                            FirebaseAuth.getInstance().signOut();

                        }
                    }
                });
    }

}






