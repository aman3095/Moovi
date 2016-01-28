package com.amanpreetsingh.moovi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amanpreetsingh.moovi.R;

import java.util.ArrayList;

/**
 * Created by Admin on 1/28/2016.
 */
public class GridAdapter extends BaseAdapter {

    ArrayList<String> moviesList;
    Context mContext;

    public GridAdapter(Context context, ArrayList<String> list){
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
        View gridItem;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            gridItem = inflater.inflate(R.layout.grid_item, null);
            TextView name = (TextView) gridItem.findViewById(R.id.title);
            name.setText(moviesList.get(position));
        }
        else {
            gridItem = (View) convertView;
        }

        return gridItem;
    }
}
