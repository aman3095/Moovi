package com.amanpreetsingh.moovi.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.TVManager;
import com.amanpreetsingh.moovi.TVShow;
import com.amanpreetsingh.moovi.activities.DetailActivity;
import com.amanpreetsingh.moovi.adapters.TVGridAdapter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Aman on 1/27/2016.
 */
public class TVFragment extends Fragment implements GridView.OnItemClickListener {

    GridView mGridView;
    TVManager mTvManager;
    ProgressBar mProgessBar;
    ArrayList<TVShow> mTvShowsList;

    public static final String TAG = "TVFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_tv, container, false);

        mGridView = (GridView) view.findViewById(R.id.grid_view);
        mGridView.setOnItemClickListener(this);
        mProgessBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mTvManager = new TVManager(getActivity());

        populateGrid();

        return view;
    }

    public void populateGrid(){
        try {
            mTvManager.fetchPopularTvShows(new IRequestListener() {
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

        mTvShowsList = (ArrayList<TVShow>) data;
        mGridView.setAdapter(new TVGridAdapter(getActivity(), mTvShowsList));
        mProgessBar.setVisibility(View.GONE);
        mGridView.setVisibility(View.VISIBLE);
    }

    public void failureHandling(){
        Handler handler = new Handler(getActivity().getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "Failed to fetch TV Shows!", Toast.LENGTH_SHORT).show();
                mProgessBar.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mTvShowsList == null || mTvShowsList.isEmpty()){
            return;
        }

        TVShow tvShow = mTvShowsList.get(position);

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constants.ID, tvShow.getId());
        intent.putExtra(Constants.TYPE, Constants.TYPE_TV);
        intent.putExtra(Constants.TITLE, tvShow.getTitle());
        intent.putExtra(Constants.POSTER_PATH, tvShow.getPosterPath());

        startActivity(intent);
    }
}
