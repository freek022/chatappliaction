package com.fg.chatapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by fred on 6/29/2016.
 */
public class ChatsFragment extends Fragment {
    TextView txt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.chats_layout, null);

        txt = (TextView) view.findViewById(R.id.textView);
        txt.setText("Welcome to chat app");
        return view;

    }
}
