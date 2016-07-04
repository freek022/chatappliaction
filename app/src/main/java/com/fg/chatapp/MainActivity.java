package com.fg.chatapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;


    //////
    private Toolbar toolbar; // declare the Toolbar object



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup the Drawerlayout and NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navDrawerList);
        View headerLayout = mNavigationView.inflateHeaderView(R.layout.nav_header);
        ImageView imageHeaderPhoto = (ImageView) headerLayout.findViewById(R.id.profileImage);
        imageHeaderPhoto.setPressed(true);


        /////

        // attach the layout to the toolbar
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // set the toolbar as an ActionBar


        /////


        // inflate the very fragment, here we are inflating the TabFragment
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();

        // set click events on the Navigation View items
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // mDrawerLayout.closeDrawers();
                /////
                int id = menuItem.getItemId();

                if (id == R.id.navItemSignin) {
                    // handle the sent action
                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    ft.replace(R.id.containerView, new SigninFragment()).commit();
                }

                if (id == R.id.navItemChats) {
                    FragmentTransaction xft = mFragmentManager.beginTransaction();
                    xft.replace(R.id.containerView, new TabFragment()).commit();
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // set the drawer toggle of the toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }



    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
