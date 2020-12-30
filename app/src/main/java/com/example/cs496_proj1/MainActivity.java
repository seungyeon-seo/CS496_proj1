package com.example.cs496_proj1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    TabPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TabLayout Initialization
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        // ViewPager Initialization
        viewPager = (ViewPager2) findViewById(R.id.pager);
        TabPagerAdapter fgAdapter = new TabPagerAdapter(this, 3);
        viewPager.setAdapter(fgAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                default:
                    tab.setText("Contacts");
                    break;
                case 1:
                    tab.setText("Gallery");
                    break;
                case 2:
                    tab.setText("Temp");
                    break;
            }
            viewPager.setCurrentItem(0);
        }).attach();

        // TabSelectedListener Initialization
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


    }

}


