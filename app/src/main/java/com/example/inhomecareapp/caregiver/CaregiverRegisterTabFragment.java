package com.example.inhomecareapp.caregiver;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.inhomecareapp.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class CaregiverRegisterTabFragment extends Fragment {
    EditText caregiverNameRegisterEt;
    EditText caregiverPassRegisterEt;
    EditText caregiverIdEt;
    EditText caregiverEmailRegisterEt;
    EditText caregiverPhoneRegisterEt;
    ImageView imageViewPic;
    MaterialButton caregiverRegisterBtn;
    RadioButton radioButton1;
    RadioButton radioButton2;
    private static final String TAG = "CaregiverRegisterFrag";
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private String caregiverNameRegister, caregiverEmailRegister, caregiverPhoneRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.caregiver_register_fragment, container, false);
        imageViewPic = root.findViewById(R.id.caregiver_profile_Pic_register);
        caregiverNameRegisterEt = root.findViewById(R.id.caregiverName_register);
        caregiverPassRegisterEt = root.findViewById(R.id.caregiverPass_register);
        caregiverIdEt = root.findViewById(R.id.caregiverID_register);
        caregiverEmailRegisterEt = root.findViewById(R.id.caregiverEmail_register);
        caregiverPhoneRegisterEt = root.findViewById(R.id.caregiverPhone_register);
        radioButton1 = root.findViewById(R.id.radio_button_1);
        radioButton2 = root.findViewById(R.id.radio_button_2);
        caregiverRegisterBtn = root.findViewById(R.id.caregiverRegister_btn);

        imageViewPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(CaregiverRegisterTabFragment.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        caregiverRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caregiverNameRegister = caregiverNameRegisterEt.getText().toString().trim();
                caregiverEmailRegister = caregiverEmailRegisterEt.getText().toString().trim();
                String caregiverPassRegister = caregiverPassRegisterEt.getText().toString().trim();
                caregiverPhoneRegister = caregiverPhoneRegisterEt.getText().toString().trim();


                if (caregiverNameRegister.isEmpty() || caregiverEmailRegister.isEmpty() || caregiverPassRegister.isEmpty() ||
                        caregiverPhoneRegister.isEmpty()) {
                    Toast.makeText(requireContext(), "please fill all data", Toast.LENGTH_LONG).show();
                    return;
                }
                if (caregiverPassRegister.length() < 6) {
                    Toast.makeText(requireContext(), "password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                createCaregiverByEmail(caregiverEmailRegister, caregiverPassRegister);
            }

        });
        return root;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: ");
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri uri = data.getData();
            Log.i(TAG, "onActivityResult: " + uri);
            // Use Uri object instead of File to avoid storage permissions
            imageViewPic.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    private void createCaregiverByEmail(String caregiverEmailRegister, String caregiverPassRegister) {
        firebaseAuth.createUserWithEmailAndPassword(caregiverEmailRegister, caregiverPassRegister)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(requireContext(), "Account is created", Toast.LENGTH_SHORT).show();
                            uploadUCaregiverData();
                            sendVerificationEmail();
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: " + errorMessage);
                        }

                    }
                });
    }


    private void uploadUCaregiverData() {
        CaregiverData caregiverData = new CaregiverData(caregiverNameRegister, caregiverEmailRegister,
                caregiverPhoneRegister);

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
                            Intent intent = new Intent(requireContext(), CaregiverHome.class);
                            startActivity(intent);
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: " + errorMessage);
                        }
                    }
                });


    }

    private ArrayList<String> getCustomerList() {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("Hourly Services");
        customers.add("Stay-in services");

        return customers;
    }

    private ArrayList<String> getCcategoryList() {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("Elderly");
        customers.add("People with special needs");
        customers.add("Children");

        return customers;
    }


    private void sendVerificationEmail() {
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