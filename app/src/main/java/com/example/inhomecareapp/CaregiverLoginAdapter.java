package com.example.inhomecareapp;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CaregiverLoginAdapter extends FragmentPagerAdapter {
    private final Context context;
    int totalTabs;

    public CaregiverLoginAdapter(FragmentManager fragmentManager, Context context, int totalTabs) {
        super(fragmentManager);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem (int position) {
        switch (position) {
            case 0:
                CaregiverLoginTabFragment loginTabFragment = new CaregiverLoginTabFragment();
                return loginTabFragment;
            case 1:
                CaregiverRegisterTabFragment registerTabFragment = new CaregiverRegisterTabFragment();
                return registerTabFragment;
            default:
                return null;
        }
    }
}
