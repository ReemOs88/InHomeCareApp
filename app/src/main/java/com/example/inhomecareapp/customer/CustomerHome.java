package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverData;
import com.example.inhomecareapp.caregiver.CaregiverHome;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CustomerHome extends AppCompatActivity {
    private static final String TAG = "CustomerHome";

    String[] servicesTypes = {"Hourly service", "Stay in service"};
    AutoCompleteTextView autoCompleteServiceType;
    ArrayAdapter<String> adapterServiceTypeItems;

    String[] categories = {"Children", "Elderly", "People with special needs"};
    AutoCompleteTextView autocompleteCategories;
    ArrayAdapter<String> adapterCategoriesItems;

    String[] ages = {"0 - 3 months", "3 months - 4 years", "10 - 20 years", "20 - 40 years", "40 - 80 years"};
    AutoCompleteTextView autoCompleteTAges;
    ArrayAdapter<String> adapterAgesItems;

    FloatingActionButton addPostBtn;
    AlertDialog dialogAddPost;
    MaterialButton findCaregiverBtn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    String serviceType = "";
    String category = "";
    String age = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        findCaregiverBtn = findViewById(R.id.find_caregiverBtn);
        findCaregiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findCareGivers();
            }
        });


        autoCompleteServiceType = findViewById(R.id.autoComplete_service_type);
        adapterServiceTypeItems = new ArrayAdapter<String>(this, R.layout.list_item_service_type, servicesTypes);
        autoCompleteServiceType.setAdapter(adapterServiceTypeItems);
        autoCompleteServiceType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                serviceType = servicesTypes[position];

            }
        });

        autocompleteCategories = findViewById(R.id.autoComplete_select_category);
        adapterCategoriesItems = new ArrayAdapter<String>(this, R.layout.list_item_select_category, categories);
        autocompleteCategories.setAdapter(adapterCategoriesItems);
        autocompleteCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                category = categories[position];
            }
        });

        autoCompleteTAges = findViewById(R.id.autoComplete_ages);
        adapterAgesItems = new ArrayAdapter<String>(this, R.layout.list_item_ages, ages);
        autoCompleteTAges.setAdapter(adapterAgesItems);
        autoCompleteTAges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                age = ages[position];

            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create new post");
        View view = getLayoutInflater().inflate(R.layout.dialog_add_post, null);
        TextInputEditText writePostEt;
        Button submitPostBtn;
        writePostEt = view.findViewById(R.id.write_post_et);
        submitPostBtn = view.findViewById(R.id.submit_post_btn);

        submitPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = writePostEt.getText().toString();

                if (content.isEmpty()) {
                    Toast.makeText(CustomerHome.this, "content required", Toast.LENGTH_LONG).show();
                    return;
                }

                CustomerPost customerPost = new CustomerPost(
                        FirebaseAuth.getInstance().getUid(),
                        String.valueOf(System.currentTimeMillis()),
                        content
                );

                FirebaseFirestore firestore = FirebaseFirestore.getInstance();

                firestore.collection("posts")
                        .document(customerPost.getPostId())
                        .set(customerPost).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        writePostEt.setText("");
                        Toast.makeText(CustomerHome.this, "Post added", Toast.LENGTH_SHORT).show();
                        dialogAddPost.dismiss();
                    }
                });

            }
        });

        builder.setView(view);
        dialogAddPost = builder.create();
        addPostBtn = findViewById(R.id.add_post_btn);
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddPost.show();
            }
        });

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_customer_profile) {
                    Intent intent = new Intent(CustomerHome.this, CustomerProfileActivity.class);
                    startActivity(intent);
                    return true;

                }
                if (id == R.id.item_customer_contract) {
                    Intent intent = new Intent(CustomerHome.this, CustomerContractsActivity.class);
                    startActivity(intent);
                    return true;
                }
                if (id == R.id.item_customer_Logout) {
                    firebaseAuth.signOut();
                    Toast.makeText(CustomerHome.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
                }
                return false;
            }


        });

    }

    private void findCareGivers() {
        if (serviceType.isEmpty()) {
            Toast.makeText(this, "Select type of service", Toast.LENGTH_SHORT).show();
            return;
        }

        if (category.isEmpty()) {
            Toast.makeText(this, "Select category", Toast.LENGTH_SHORT).show();
            return;
        }

        if (age.isEmpty()) {
            Toast.makeText(this, "Select age", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseFirestore
                .getInstance()
                .collection("inHomeCaregivers")
                .whereEqualTo("serviceSelected", serviceType)
                .whereEqualTo("selectedCategory", category)
                .whereEqualTo("selectedAge", age)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot query) {
                        ArrayList<CaregiverData> caregivers = new ArrayList<>();

                        for (DocumentSnapshot document : query.getDocuments()) {
                            CaregiverData caregiverData = document.toObject(CaregiverData.class);
                            caregivers.add(caregiverData);
                        }

                        Log.i(TAG, "onSuccess: " + caregivers.size());

                        Intent intent = new Intent(CustomerHome.this, StayInCaregiverListActivity.class);
                        intent.putExtra("caregivers", caregivers);
                        startActivity(intent);
                    }
                });


    }
}



