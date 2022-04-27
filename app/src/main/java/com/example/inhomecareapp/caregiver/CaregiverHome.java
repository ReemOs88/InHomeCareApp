package com.example.inhomecareapp.caregiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.inhomecareapp.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.auth.FirebaseAuth;

public class CaregiverHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_caregiver_home);
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        CustomerPostsAdapter customerPostsAdapter = new CustomerPostsAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(customerPostsAdapter);

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();
                if (id == R.id.profile) {
                  Intent intent = new Intent(CaregiverHome.this, CaregiverProfileActivity.class);
                    startActivity(intent);
                    return true;

                }
                else if(id== R.id.logout){
                    firebaseAuth.signOut();
                    Toast.makeText(CaregiverHome.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    return true;
                }
                return false;
            }


        });

    }




}