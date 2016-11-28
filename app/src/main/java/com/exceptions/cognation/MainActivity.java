package com.exceptions.cognation;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.Arrays;

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
//        mTabLayout.getTabAt(2).setIcon(R.drawable.icon_notifications);
        mTabLayout.getTabAt(2).setIcon(R.drawable.icon_profile);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        AutoCompleteTextView mAutoCompleteTextView = (AutoCompleteTextView) menu.findItem(R.id.action_search).getActionView();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        mAutoCompleteTextView.setMinWidth(displayMetrics.widthPixels);

        String[] items = {"ABC", "ABB", "ABD", "ACB", "ACD", "BCD", "BDC"};

        mAutoCompleteTextView.setAdapter(new ArrayAdapter(MainActivity.this, R.layout.search_custom_listitem, Arrays.asList(items)));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_search:
                return true;
            case R.id.action_notification:
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NewsfeedFragment();
                case 1:
                    return new ChatFragment();
                default:
                    return new ProfileFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    private class CustomArrayAdapter extends ArrayAdapter {

        private Context context;
        private String[] items;

        public CustomArrayAdapter(Context context, String[] items) {
            super(context, android.R.layout.simple_list_item_1);
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = LayoutInflater.from(context).inflate(R.layout.search_custom_listitem, null);
            ((TextView) v.findViewById(R.id.search_custom_tv)).setText(items[i]);
            return v;
        }
    }
}