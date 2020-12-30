package com.example.cs496_proj1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {
    private int tabCount;

    public TabPagerAdapter(FragmentActivity fa, int count) {
        super(fa);
        tabCount = count;
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                ContactFragment fr1 = new ContactFragment();
                return fr1;
            case 1:
                GalleryFragment fr2 = new GalleryFragment();
                return fr2;
            case 2:
                BlankFragment fr3 = new BlankFragment();
                return fr3;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return tabCount;
    }
}
