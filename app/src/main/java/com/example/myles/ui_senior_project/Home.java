package com.example.myles.ui_senior_project;

import android.os.Bundle;
import android.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Home extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recyclerView =(RecyclerView) inflater.inflate(R.layout.fragment_home, container, false);

        layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }
}
