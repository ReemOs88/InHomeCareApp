package com.example.inhomecareapp.caregiver;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.Transliterator;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class CaregiverRegisterTabFragment extends Fragment {
    EditText caregiverNameRegisterEt;
    EditText caregiverPassRegisterEt;
    EditText caregiverIdEt;
    EditText caregiverEmailRegisterEt;
    EditText caregiverPhoneRegisterEt;
    ImageView caregiverProfilePic;
    MaterialButton caregiverRegisterBtn;
    RadioGroup radioGroup;
    RadioButton radioButtonCaregiverMale;
    RadioButton radioButtonCaregiverFemale;
    private static final String TAG = "CaregiverRegisterFrag";
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    StorageReference storageReference=FirebaseStorage.getInstance().getReference();
    private String caregiverNameRegister, caregiverEmailRegister, caregiverPhoneRegister;
    Uri imageUri=null;
    String serviceSelected="";
    String selectedCategory="";
    String selectedAge="";

    String[] servicesTypes={"Hourly service","Stay in service"};
    AutoCompleteTextView autoCompleteServiceType;
    ArrayAdapter<String> adapterServiceTypeItems;

    String[] categories={"Children","Elderly","People with special needs"};
    AutoCompleteTextView autocompleteCategories;
    ArrayAdapter<String> adapterCategoriesItems;

    String[] ages={"0 - 3 months","3 months - 4 years" ,"10 - 20 years" ,"20 - 40 years" ,"40 - 80 years" };
    AutoCompleteTextView autoCompleteTAges;
    ArrayAdapter<String> adapterAgesItems;
    String gender="";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.caregiver_register_fragment, container, false);
        caregiverProfilePic = root.findViewById(R.id.caregiver_profile_Pic_register);
        caregiverNameRegisterEt = root.findViewById(R.id.caregiverName_register);
        caregiverPassRegisterEt = root.findViewById(R.id.caregiverPass_register);
        caregiverIdEt = root.findViewById(R.id.caregiverID_register);
        caregiverEmailRegisterEt = root.findViewById(R.id.caregiverEmail_register);
        caregiverPhoneRegisterEt = root.findViewById(R.id.caregiverPhone_register);
        caregiverRegisterBtn=root.findViewById(R.id.caregiverRegister_btn);
        radioGroup=root.findViewById(R.id.radioGroup_caregiver);
        radioButtonCaregiverMale = root.findViewById(R.id.radio_button_caregiver_male);
        radioButtonCaregiverFemale = root.findViewById(R.id.radio_button_caregiver_female);

        autoCompleteServiceType=root.findViewById(R.id.autoComplete_service_type);
        adapterServiceTypeItems= new ArrayAdapter<String>(requireContext(),R.layout.list_item_service_type,servicesTypes);
        autoCompleteServiceType.setAdapter(adapterServiceTypeItems);
        autoCompleteServiceType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //adapterServiceTypeItems.getItem(position);
                  serviceSelected=servicesTypes[position]; }
        });
        autocompleteCategories=root.findViewById(R.id.autoComplete_select_category);
        adapterCategoriesItems =new ArrayAdapter<String>(requireContext(),R.layout.list_item_select_category,categories);
        autocompleteCategories.setAdapter(adapterCategoriesItems);
        autocompleteCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               // adapterCategoriesItems.getItem(position);
                selectedCategory= categories[position]; }
        });
        autoCompleteTAges=root.findViewById(R.id.autoComplete_ages);
        adapterAgesItems= new ArrayAdapter<String>(requireContext(),R.layout.list_item_ages,ages);
        autoCompleteTAges.setAdapter(adapterAgesItems);
        autoCompleteTAges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               // adapterAgesItems.getItem(position);
                  selectedAge=ages[position]; }
        });



        caregiverProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(CaregiverRegisterTabFragment.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });

        caregiverRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {


                if(radioButtonCaregiverMale.isChecked()){
                       gender="male";
                } else if (radioButtonCaregiverFemale.isChecked()){
                       gender="female";
                }

                caregiverNameRegister = caregiverNameRegisterEt.getText().toString().trim();
                caregiverEmailRegister = caregiverEmailRegisterEt.getText().toString().trim();
                String caregiverPassRegister = caregiverPassRegisterEt.getText().toString().trim();
                caregiverPhoneRegister = caregiverPhoneRegisterEt.getText().toString().trim();

                if (caregiverNameRegister.isEmpty() || caregiverEmailRegister.isEmpty() || caregiverPassRegister.isEmpty() ||
                        caregiverPhoneRegister.isEmpty()|| gender.isEmpty()
                       ) {
                    Toast.makeText(requireContext(), "please fill all data", Toast.LENGTH_LONG).show();
                    return;
                }
                if(imageUri==null){
                    Toast.makeText(requireContext(), "Please select profile picture", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (caregiverPassRegister.length() < 6) {
                    Toast.makeText(requireContext(), "password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if( serviceSelected.isEmpty()||selectedCategory.isEmpty()||selectedAge.isEmpty()){
                    Toast.makeText(requireContext(), "please select items", Toast.LENGTH_SHORT).show();
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
             imageUri = data.getData();
            Log.i(TAG, "onActivityResult: " + imageUri);
            // Use Uri object instead of File to avoid storage permissions
            caregiverProfilePic.setImageURI(imageUri);
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
                            Toast.makeText(requireContext(), "Please verify your account", Toast.LENGTH_SHORT).show();
                            String caregiverUID= task.getResult().getUser().getUid();
                            uploadCaregiverProfilePic(caregiverUID);

                            sendVerificationEmail();
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: " + errorMessage);
                        }

                    }
                });
    }

    private void uploadCaregiverProfilePic(String caregiverUID) {
        storageReference.child("caregiverProfilePic").child(caregiverUID).putFile(imageUri)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(requireContext(), "Image uploaded", Toast.LENGTH_SHORT).show();
                            getCaregiverProfilePicUrl(caregiverUID);
                        }else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: " + errorMessage);
                        }
                    }
                });



    }

    private void getCaregiverProfilePicUrl(String caregiverUID) {
        storageReference.child("caregiverProfilePic").child(caregiverUID)
                .getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    String imageUrl=task.getResult().toString();
                    Log.i(TAG, "onComplete: "+imageUrl);
                    uploadUCaregiverData(imageUrl);

                }else {
                    String errorMessage = task.getException().getLocalizedMessage();
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onComplete: " + errorMessage);
                }
            }
        });
    }


    private void uploadUCaregiverData(String imageUrl) {
        CaregiverData caregiverData = new CaregiverData(imageUrl,caregiverNameRegister, caregiverEmailRegister,
                caregiverPhoneRegister,serviceSelected,selectedCategory,selectedAge,gender);

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
                            FirebaseAuth.getInstance().signOut();
                            requireActivity().finish();
                            Intent intent = new Intent(requireContext(), CaregiverLoginActivity.class);
                            startActivity(intent);
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: " + errorMessage);
                        }
                    }
                });


    }

   // private ArrayList<String> getCustomerList() {
     //   ArrayList<String> customers = new ArrayList<>();
       // customers.add("Hourly Services");
        //customers.add("Stay-in services");

        //return customers;
    //}

    //private ArrayList<String> getCategoryList() {
      //  ArrayList<String> customers = new ArrayList<>();
       // customers.add("Elderly");
        //customers.add("People with special needs");
        //customers.add("Children");

        //return customers;
    //}

    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {


                        }
                    }
                });
    }


}