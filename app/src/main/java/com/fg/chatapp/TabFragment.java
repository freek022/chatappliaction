package com.fg.chatapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fred on 6/28/2016.
 */
public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2;

    // initialize the tab icons for the tabLayout
    private int[] tabIcons = {
            R.drawable.ic_chat_white_48dp,
            R.drawable.ic_contacts_white_48dp
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // inflate tab_layout and setup Views
        View view = inflater.inflate(R.layout.tab_layout, null);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        // set an adapter for the view pager
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        // initiate the runnable
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                ////
                setUpIcons();

            }
        });
        return view;
    }


    // method that set icons for the tabs
    public void setUpIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

        try {
            // set the initial color of the tabs. chats icon being home and selected is to parseColor("#f39c12")
            // and contacts icon is set to parseColor("#ffffff")
            tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#f39c12"), PorterDuff.Mode.SRC_IN);
            tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
        }catch (Exception e){

        }
        // change colors upon selecting the icon
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#f39c12"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        // return fragment with respect to position

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0 :
                    return new ChatsFragment();
                case 1 :
                    return new ContactsFragment();

            }
            return  null;
        }

        @Override
        public int getCount() {
            return  int_items;
        }

        // this method returns the title of the tab according to the position
        @Override
        public CharSequence getPageTitle(int position){
            /**
            switch (position){
                case 0 :
                    return "Chats";
                case 1 :
                    return "Contacts";
            }
            */
            return null;
        }
    }
}
