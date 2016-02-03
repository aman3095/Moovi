package com.amanpreetsingh.moovi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amanpreetsingh.moovi.Constants;
import com.amanpreetsingh.moovi.HttpRequests;
import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.TVShow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 2/3/2016.
 */
public class TVGridAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<TVShow> mTvShows;

    public TVGridAdapter(Context context, ArrayList<TVShow> list){
        this.mContext = context;
        this.mTvShows = list;
    }

    @Override
    public int getCount() {
        return mTvShows.size();
    }

    @Override
    public Object getItem(int position) {
        return mTvShows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
            holder = new ViewHolder();

            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.name = (TextView) convertView.findViewById(R.id.title);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext)
                .load(HttpRequests.getPosterURL(mTvShows.get(position).getPosterPath(), Constants.PosterSizes.W342))
                .placeholder(R.drawable.image_placeholder)
                .into(holder.image);

        holder.name.setText(mTvShows.get(position).getTitle());

        return convertView;
    }

    private class ViewHolder{
        ImageView image;
        TextView name;
    }
}
