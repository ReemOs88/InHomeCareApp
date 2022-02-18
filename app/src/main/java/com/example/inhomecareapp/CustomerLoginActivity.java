package com.example.inhomecareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class CustomerLoginActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final CustomerLoginAdapter adapter= new CustomerLoginAdapter(getSupportFragmentManager(),
                this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTranslationY(300);
        tabLayout.setAlpha(1);
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
    }
}