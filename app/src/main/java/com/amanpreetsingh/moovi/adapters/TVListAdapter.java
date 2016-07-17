package com.amanpreetsingh.moovi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.text.style.RelativeSizeSpan;
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
import com.amanpreetsingh.moovi.TVShow;
import com.squareup.picasso.Picasso;

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

        holder.mTVShowName.setText(getTVNameYearSpan(tvList.get(position)));
        holder.rating.setText(tvList.get(position).getYearGenre());
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
        TextView rating;
        RecyclerViewClickListener mClickListener;

        public ViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            view.setOnClickListener(this);
            mClickListener = listener;
            mTVShowBackdrop = (ImageView) view.findViewById(R.id.background_image);
            mTVShowName = (TextView) view.findViewById(R.id.name);
            rating = (TextView) view.findViewById(R.id.rating);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClicked(getLayoutPosition());
        }
    }

    public Spannable getTVNameYearSpan (TVShow tvShow){
        String str = tvShow.getTitle() + " (" + tvShow.getYear() + ")";
        Spannable span = new SpannableString(str);
        int spanStartIndex = tvShow.getTitle().length();
        int spanEndIndex = str.length();
        span.setSpan(new RelativeSizeSpan(0.55f), spanStartIndex, spanEndIndex, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        span.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), spanStartIndex, spanEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return span;
    }

}
