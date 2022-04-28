package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CaregiverHome;
import com.example.inhomecareapp.caregiver.CaregiverProfileActivity;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class CustomerHome extends AppCompatActivity {
     String[] servicesTypes={"Hourly service","Stay in service"};
     AutoCompleteTextView autoCompleteServiceType;
     ArrayAdapter<String> adapterServiceTypeItems;

    String[] categories={"Children","Elderly","People with special needs"};
    AutoCompleteTextView autocompleteCategories;
    ArrayAdapter<String> adapterCategoriesItems;

    String[] ages={"0 - 3 months","3 months - 4 years" ,"10 - 20 years" ,"20 - 40 years" ,"40 - 80 years" };
    AutoCompleteTextView autoCompleteTAges;
    ArrayAdapter<String> adapterAgesItems;

    FloatingActionButton addPostBtn;
    AlertDialog dialogAddPost;
    MaterialButton findCaregiverBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        findCaregiverBtn=findViewById(R.id.find_caregiverBtn);
        findCaregiverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CustomerHome.this, CaregiverListActivity.class);
                startActivity(intent);
            }
        });



        autoCompleteServiceType=findViewById(R.id.autoComplete_service_type);
        adapterServiceTypeItems= new ArrayAdapter<String>(this,R.layout.list_item_service_type,servicesTypes);
        autoCompleteServiceType.setAdapter(adapterServiceTypeItems);
        autoCompleteServiceType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                 adapterServiceTypeItems.getItem(position);

            }
        });

        autocompleteCategories=findViewById(R.id.autoComplete_select_category);
        adapterCategoriesItems =new ArrayAdapter<String>(this,R.layout.list_item_select_category,categories);
        autocompleteCategories.setAdapter(adapterCategoriesItems);
        autoCompleteServiceType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                adapterCategoriesItems.getItem(position);
            }
        });

        autoCompleteTAges=findViewById(R.id.autoComplete_ages);
        adapterAgesItems= new ArrayAdapter<String>(this,R.layout.list_item_ages,ages);
        autoCompleteTAges.setAdapter(adapterAgesItems);
        autoCompleteTAges.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                adapterAgesItems.getItem(position);

            }
        });
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Create new post");
        View view = getLayoutInflater().inflate(R.layout.dialog_add_post,null);
        TextInputEditText writePostEt;
        Button submitPostBtn;
        writePostEt=findViewById(R.id.write_post_et);
        submitPostBtn=findViewById(R.id.submit_post_btn);

        builder.setView(view);
        dialogAddPost=builder.create();
        addPostBtn=findViewById(R.id.add_post_btn);
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

                }
                return true;
            }



        });

    }
}



