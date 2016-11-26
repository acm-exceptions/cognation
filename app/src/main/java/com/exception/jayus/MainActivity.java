package com.exception.jayus;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.tl);
        mViewPager = (ViewPager) findViewById(R.id.vp);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));

        mTabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        mTabLayout.getTabAt(1).setIcon(R.drawable.icon_chat);
        mTabLayout.getTabAt(2).setIcon(R.drawable.icon_notifications);
        mTabLayout.getTabAt(3).setIcon(R.drawable.icon_profile);


        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.colorTabActive);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
//                tab.getIcon().clearColorFilter();
                int tabIconColor = ContextCompat.getColor(MainActivity.this, R.color.colorTabInactive);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }
        });
    }

    private class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                default:
                    return new ProfileFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}