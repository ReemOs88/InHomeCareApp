package com.example.inhomecareapp.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.inhomecareapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class basicActivity extends AppCompatActivity {
    FrameLayout framelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        framelayout = findViewById(R.id.framelayout);
        setFragment(new home());
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        item.setChecked(true);
                        setFragment(new home());
                        break;
                    case R.id.menu:
                        setFragment(new menu());
                        item.setChecked(true);
                        break;

                    case R.id.contract:
                        item.setChecked(true);
                        break;
                    case R.id.message:
                        item.setChecked(true);
                        break;
                }
                return false;
            }


        });
        if (fab != null)
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(basicActivity.this,posts.class);
                    startActivity(i);

                }
            });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace( framelayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}