package com.amanpreetsingh.moovi.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amanpreetsingh.moovi.Constants;
import com.amanpreetsingh.moovi.IRequestListener;
import com.amanpreetsingh.moovi.Movie;
import com.amanpreetsingh.moovi.MovieManager;
import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.adapters.MovieGridAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Admin on 1/27/2016.
 */
public class MoviesFragment extends Fragment {

    MovieManager movieManager;
    GridView gridView;
    ProgressBar progressBar;

    public final String TAG = "MoviesFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_movies, container, false);

        gridView = (GridView) view.findViewById(R.id.grid_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        movieManager = new MovieManager(getActivity());

        populateGrid();

        return view;
    }

    public void populateGrid(){
        try {
            movieManager.fetchTopRatedMoviesList(new IRequestListener() {
                @Override
                public void onSuccess(Object data) {
                    Log.d(TAG, "onSuccess");
                    successHandling(data);
                }

                @Override
                public void onFailure() {
                    Log.d(TAG, "onFailure");
                    failureHandling();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void successHandling(Object data){
        if (data == null || !(data instanceof ArrayList) ){
            Log.d(TAG, "data is null in successHandling. Returning.");
            return;
        }
        ArrayList<Movie> list = (ArrayList<Movie>) data;
        gridView.setAdapter(new MovieGridAdapter(getActivity(), list));
        progressBar.setVisibility(View.GONE);
        gridView.setVisibility(View.VISIBLE);
    }

    public void failureHandling(){
        Handler handler = new Handler(getActivity().getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Failed to fetch movies!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
