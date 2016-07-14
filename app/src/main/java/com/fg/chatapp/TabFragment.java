package com.fg.chatapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fred on 6/28/2016.
 */
public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;

    public TabFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // inflate tab_layout and setup Views
        View view = inflater.inflate(R.layout.tab_layout, null);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setUpViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        return view;
    }

    private void setUpViewPager(ViewPager viewPager){
        MyAdapter adapter = new MyAdapter(getChildFragmentManager());
        adapter.addFrag(new ChatsFragment());
        adapter.addFrag(new ContactsFragment());
        viewPager.setAdapter(adapter);
    }

    public class MyAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();

        public MyAdapter(FragmentManager fm){super(fm);}

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFrag(Fragment fragment){
            fragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0 :
                    return "CHATS";
                case 1 :
                    return "CONTACTS";
            }
            return null;
        }

    }
}
