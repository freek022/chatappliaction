package com.fg.chatapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by fred on 6/29/2016.
 */
public class ContactsFragment extends Fragment {
    TextView txt1, txt2;
    private FloatingActionButton fab;



    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_layout, null);
        setHasOptionsMenu(true);
        txt1 = (TextView) view.findViewById(R.id.textView1);
        txt1.setText("Welcome to contact fragment");
        txt2 = (TextView) view.findViewById(R.id.link);
        txt2.getText();

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.menu_contact, menu);
    }
}
