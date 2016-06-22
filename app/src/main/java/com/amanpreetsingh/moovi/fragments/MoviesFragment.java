package com.amanpreetsingh.moovi.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amanpreetsingh.moovi.Constants;
import com.amanpreetsingh.moovi.IRequestListener;
import com.amanpreetsingh.moovi.Movie;
import com.amanpreetsingh.moovi.MovieManager;
import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.RecyclerViewClickListener;
import com.amanpreetsingh.moovi.activities.DetailActivity;
import com.amanpreetsingh.moovi.adapters.MovieListAdapter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Admin on 1/27/2016.
 */
public class MoviesFragment extends Fragment implements RecyclerViewClickListener {

    MovieManager movieManager;
    RecyclerView mMoviesRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ProgressBar progressBar;
    ArrayList<Movie> moviesList;

    public final String TAG = "MoviesFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_movies, container, false);

        mMoviesRecyclerView = (RecyclerView) view.findViewById(R.id.movies_list);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        movieManager = new MovieManager(getActivity());

        populateMoviesList();

        return view;
    }

    public void populateMoviesList(){
        try {
            movieManager.fetchPopularMoviesList(new IRequestListener() {
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
        moviesList = (ArrayList<Movie>) data;
        mLayoutManager = new LinearLayoutManager(getActivity());
        mMoviesRecyclerView.setLayoutManager(mLayoutManager);
        mMoviesRecyclerView.setAdapter(new MovieListAdapter(getActivity(), moviesList, this));
        progressBar.setVisibility(View.GONE);
        mMoviesRecyclerView.setVisibility(View.VISIBLE);
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

    @Override
    public void onItemClicked(int position) {
        if (moviesList == null || moviesList.isEmpty()){
            return;
        }

        Movie movie = moviesList.get(position);

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constants.ID, movie.getId());
        intent.putExtra(Constants.TYPE, Constants.TYPE_MOVIE);
        intent.putExtra(Constants.TITLE, movie.getTitle());
        intent.putExtra(Constants.POSTER_PATH, movie.getPosterPath());
        intent.putExtra(Constants.RATING, movie.getVoteAverage());
        intent.putExtra(Constants.OVERVIEW, movie.getOverview());

        startActivity(intent);
    }
}
