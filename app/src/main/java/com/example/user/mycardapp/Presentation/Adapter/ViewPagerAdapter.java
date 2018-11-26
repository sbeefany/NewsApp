package com.example.user.mycardapp.Presentation.Adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragments = new ArrayList<>();

    public ViewPagerAdapter (FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem (int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount () {
        return fragments.size();
    }

    public void addFragment (Fragment fragment) {
        fragments.add(fragment);
    }
}