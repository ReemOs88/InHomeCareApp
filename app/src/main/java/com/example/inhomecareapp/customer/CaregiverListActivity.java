package com.example.inhomecareapp.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.caregiver.CustomerPostsAdapter;
import com.google.android.material.bottomappbar.BottomAppBar;

public class CaregiverListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_list);
        CaregiversListAdapter caregiversListAdapter = new CaregiversListAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(caregiversListAdapter);

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.item_customer_profile) {
                    Intent intent = new Intent(CaregiverListActivity.this, CustomerProfileActivity.class);
                    startActivity(intent);
                    return true;

                } else if(id == R.id.item_customer_home){
                    Intent intent = new Intent(CaregiverListActivity.this, CustomerHome.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }



        });
    }
}