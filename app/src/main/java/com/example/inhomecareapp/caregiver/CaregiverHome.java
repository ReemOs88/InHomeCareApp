package com.example.inhomecareapp.caregiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.inhomecareapp.R;
import com.google.android.material.bottomappbar.BottomAppBar;

public class CaregiverHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_caregiver_home);
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
                return false;
            }


        });

        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id=item.getItemId();
                if(id== R.id.home){
                    Intent intent = new Intent(CaregiverHome.this, CaregiverHome.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }


}