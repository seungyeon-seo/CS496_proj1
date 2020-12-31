package com.example.cs496_proj1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cs496_proj1.Gallery.GalleryFragment;

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
            default:
                return ContactFragment.newInstance();
            case 1:
                return GalleryFragment.newInstance();
            case 2:
                return BlankFragment.newInstance();
        }

    }

    @Override
    public int getItemCount() {
        return tabCount;
    }
}
