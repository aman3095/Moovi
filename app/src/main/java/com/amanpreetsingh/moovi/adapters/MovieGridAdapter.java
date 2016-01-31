package com.amanpreetsingh.moovi.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amanpreetsingh.moovi.Constants;
import com.amanpreetsingh.moovi.HttpRequests;
import com.amanpreetsingh.moovi.Movie;
import com.amanpreetsingh.moovi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Admin on 1/28/2016.
 */
public class MovieGridAdapter extends BaseAdapter {

    ArrayList<Movie> moviesList;
    Context mContext;

    public MovieGridAdapter(Context context, ArrayList<Movie> list){
        mContext = context;
        moviesList = list;
    }

    @Override
    public int getCount() {
        return moviesList.size();
    }

    @Override
    public Object getItem(int position) {
        return moviesList.get(position);
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
            holder.name = (TextView) convertView.findViewById(R.id.title);
            holder.image = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(moviesList.get(position).getTitle());

        Picasso.with(mContext)
                .load(HttpRequests.getPosterURL(moviesList.get(position).getPosterPath(), Constants.PosterSizes.W342))
                .placeholder(R.drawable.image_placeholder)
                .into(holder.image);

        Log.d("Aman", "height = "+holder.image.getMeasuredHeight());

        return convertView;
    }

    public class ViewHolder{
        TextView name;
        ImageView image;
    }
}
