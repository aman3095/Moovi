package com.amanpreetsingh.moovi.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.adapters.GridAdapter;

import java.util.ArrayList;

/**
 * Created by Admin on 1/27/2016.
 */
public class MoviesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_movies, container, false);

        ArrayList<String> moviesList = new ArrayList<>();
        moviesList.add("The Shawshank Redemption");
        moviesList.add("DEF");
        moviesList.add("GHI");
        moviesList.add("JKL");
        moviesList.add("MNO");
        moviesList.add("PQR");

        GridView gridView = (GridView) view.findViewById(R.id.grid_view);
        gridView.setAdapter(new GridAdapter(getActivity(), moviesList));

        return view;
    }

}
