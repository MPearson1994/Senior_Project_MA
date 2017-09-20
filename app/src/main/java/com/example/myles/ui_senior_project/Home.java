package com.example.myles.ui_senior_project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


public class Home extends Fragment {
    GridView gridView;

    private String[] values = {
            "Closet",
            "Weather",
            "Calendar",
            "Camera",
            "Favorites",
            "Settings"
    };

    // references to our images
    private int[] images = {
            R.drawable.closet,
            R.drawable.weather,
            R.drawable.calendar,
            R.drawable.camera,
            R.drawable.favorites,
            R.drawable.settings
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = (GridView) rootView.findViewById(R.id.GridMenu_Home);
        GridAdapter gridAdapter =  new GridAdapter(this.getActivity(), values, images);
    gridView.setAdapter(gridAdapter);
        return rootView;
    }






}
