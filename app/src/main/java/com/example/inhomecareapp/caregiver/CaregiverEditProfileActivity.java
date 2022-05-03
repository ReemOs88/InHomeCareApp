package com.example.inhomecareapp.caregiver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.customer.CustomerEditProfileActivity;
import com.example.inhomecareapp.databinding.ActivityCaregiverEditProfileBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class CaregiverEditProfileActivity extends AppCompatActivity {
    private static final String TAG = "CaregiverEditProfileAct";

    CaregiverData caregiverData;
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    ActivityCaregiverEditProfileBinding binding;
    Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCaregiverEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);

        caregiverData = (CaregiverData) getIntent().getSerializableExtra("caregiver");

        binding.caregiverEditNameEt.setText(caregiverData.getCaregiverNameRegister());
        binding.caregiverEditPhoneEt.setText(caregiverData.getCaregiverPhoneRegister());

        Picasso.get().load(caregiverData.getImageUrl()).placeholder(R.drawable.profile)
                .into(binding.caregiverPicProfileActivity);

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home) {
                    Intent intent = new Intent(CaregiverEditProfileActivity.this, CaregiverHome.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.logout) {
                    firebaseAuth.signOut();
                    finish();
                    return true;
                }
                if (id == R.id.profile) {
                    Intent intent = new Intent(CaregiverEditProfileActivity.this, CaregiverProfileActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }


        });

        binding.caregiverPicProfileActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(CaregiverEditProfileActivity.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            imageUri = data.getData();
            // Use Uri object instead of File to avoid storage permissions
            binding.caregiverPicProfileActivity.setImageURI(imageUri);

            uploadCaregiverProfilePic(FirebaseAuth.getInstance().getUid());

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(CaregiverEditProfileActivity.this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CaregiverEditProfileActivity.this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }


    private void uploadCaregiverProfilePic(String caregiverUID) {
        storageReference.child("caregiverProfilePic").child(caregiverUID).putFile(imageUri)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CaregiverEditProfileActivity.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                            getCaregiverProfilePicUrl(caregiverUID);
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(CaregiverEditProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
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
                if (task.isSuccessful()) {
                    String imageUrl = task.getResult().toString();
                    caregiverData.setImageUrl(imageUrl);
                    Log.i(TAG, "onComplete: " + imageUrl);
                    uploadUCaregiverData(imageUrl);

                } else {
                    String errorMessage = task.getException().getLocalizedMessage();
                    Toast.makeText(CaregiverEditProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onComplete: " + errorMessage);
                }
            }
        });
    }


    private void uploadUCaregiverData(String imageUrl) {
        Map<String, Object> map = new HashMap<>();
        map.put("imageUrl", imageUrl);

        FirebaseFirestore.getInstance()
                .collection("inHomeCaregivers")
                .document(FirebaseAuth.getInstance().getUid())
                .update(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CaregiverEditProfileActivity.this, "Image updated", Toast.LENGTH_SHORT).show();
                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(CaregiverEditProfileActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: " + errorMessage);
                        }
                    }
                });


    }


    public void editProfile(View view) {
        String name = binding.caregiverEditNameEt.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, "name required", Toast.LENGTH_LONG).show();
            return;
        }

        String phone = binding.caregiverEditPhoneEt.getText().toString();

        if (phone.isEmpty()) {
            Toast.makeText(this, "phone required", Toast.LENGTH_LONG).show();
            return;
        }

        caregiverData.setCaregiverNameRegister(name);
        caregiverData.setCaregiverPhoneRegister(phone);

        FirebaseFirestore
                .getInstance()
                .collection("inHomeCaregivers")
                .document(FirebaseAuth.getInstance().getUid())
                .set(caregiverData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CaregiverEditProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}