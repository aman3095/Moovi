package com.amanpreetsingh.moovi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amanpreetsingh.moovi.Constants;
import com.amanpreetsingh.moovi.HttpRequests;
import com.amanpreetsingh.moovi.Movie;
import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.RecyclerViewClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by amanpreetsingh on 23/06/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    Context mContext;
    ArrayList<Movie> moviesList;
    RecyclerViewClickListener mClickListener;

    public MovieListAdapter (Context context, ArrayList<Movie> list, RecyclerViewClickListener listener){
        mContext = context;
        moviesList = list;
        mClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v, mClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mMovieName.setText(moviesList.get(position).getTitle());
        Picasso.with(mContext)
                .load(HttpRequests.getBackdropURL(moviesList.get(position).getBackdropPath(), Constants.PosterSizes.W342))
                .placeholder(R.drawable.image_placeholder)
                .into(holder.mMovieImage);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerViewClickListener mClickListener;
        ImageView mMovieImage;
        TextView mMovieName;

        public ViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            mClickListener = listener;
            view.setOnClickListener(this);
            mMovieImage = (ImageView) view.findViewById(R.id.background_image);
            mMovieName = (TextView) view.findViewById(R.id.name);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClicked(getLayoutPosition());
        }
    }

}
