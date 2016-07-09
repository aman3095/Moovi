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
import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.RecyclerViewClickListener;
import com.amanpreetsingh.moovi.TVShow;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by amanpreetsingh on 23/06/16.
 */
public class TVListAdapter extends RecyclerView.Adapter<TVListAdapter.ViewHolder> {

    Context mContext;
    RecyclerViewClickListener mClickListener;
    ArrayList<TVShow> tvList;

    public TVListAdapter (Context context, ArrayList<TVShow> list, RecyclerViewClickListener listener){
        mContext = context;
        tvList = list;
        mClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v, mClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTVShowName.setText(tvList.get(position).getTitle());
        holder.mYearGenre.setText(tvList.get(position).getYearGenre());
        Picasso.with(mContext)
                .load(HttpRequests.getBackdropURL(tvList.get(position).getBackdropPath(), Constants.PosterSizes.W342))
                .placeholder(R.drawable.image_placeholder)
                .into(holder.mTVShowBackdrop);

    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mTVShowBackdrop;
        TextView mTVShowName;
        TextView mYearGenre;
        RecyclerViewClickListener mClickListener;

        public ViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            view.setOnClickListener(this);
            mClickListener = listener;
            mTVShowBackdrop = (ImageView) view.findViewById(R.id.background_image);
            mTVShowName = (TextView) view.findViewById(R.id.name);
            mYearGenre = (TextView) view.findViewById(R.id.year_genre);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClicked(getLayoutPosition());
        }
    }

}
