package com.example.inhomecareapp.caregiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.inhomecareapp.R;
import com.google.android.material.tabs.TabLayout;

public class CaregiverLoginActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_login);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final CaregiverLoginAdapter adapter= new CaregiverLoginAdapter(getSupportFragmentManager(),
                this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }
}