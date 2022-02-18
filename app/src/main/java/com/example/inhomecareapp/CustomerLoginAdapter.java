package com.example.inhomecareapp;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CustomerLoginAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public CustomerLoginAdapter(FragmentManager fragmentManager, Context context, int totalTabs) {
        super(fragmentManager);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CustomerLoginTabFragment loginTabFragment = new CustomerLoginTabFragment();
                return loginTabFragment;
            case 1:
                CustomerRegisterTabFragment registerTabFragment = new CustomerRegisterTabFragment();
                return registerTabFragment;
            default:
                return null;
        }
    }
}