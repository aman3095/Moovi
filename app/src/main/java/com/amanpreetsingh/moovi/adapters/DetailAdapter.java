package com.amanpreetsingh.moovi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.amanpreetsingh.moovi.Constants;
import com.amanpreetsingh.moovi.DetailObject;
import com.amanpreetsingh.moovi.LandingDetailObject;
import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.SummaryDetailObject;

import java.util.ArrayList;

/**
 * Created by Aman on 5/29/2016.
 */

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "DetailAdapter";

    private Context mContext;
    private ArrayList<DetailObject> detailObjects;

    public DetailAdapter(Context context, ArrayList<DetailObject> detailObjects){
        this.mContext = context;
        this.detailObjects = detailObjects;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return detailObjects.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        switch (viewType) {
            case Constants.DetailViewTypes.LANDING_ITEM:

                View landingView = inflater.inflate(R.layout.detail_list_landing_item, parent, false);
                LandingViewHolder landingHolder = new LandingViewHolder(landingView);
                landingView.setMinimumHeight(parent.getMeasuredHeight());
                return landingHolder;

            case Constants.DetailViewTypes.SUMMARY_ITEM:

                View summaryView = inflater.inflate(R.layout.detail_list_summary_item, parent, false);
                SummaryViewHolder summaryHolder = new SummaryViewHolder(summaryView);

                return summaryHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Constants.DetailViewTypes.LANDING_ITEM:
                LandingDetailObject landingObject = (LandingDetailObject) detailObjects.get(position);
                LandingViewHolder landingViewHolder = (LandingViewHolder) holder;

                landingViewHolder.title.setText(landingObject.getLandingDetailTitle());
                if (landingObject.getRating() == -1.0){
                    landingViewHolder.rating.setVisibility(View.GONE);
                } else {
                    landingViewHolder.rating.setText("" + landingObject.getRating());
                }
                break;

            case Constants.DetailViewTypes.SUMMARY_ITEM:
                SummaryDetailObject summaryObject = (SummaryDetailObject) detailObjects.get(position);
                SummaryViewHolder summaryViewHolder = (SummaryViewHolder) holder;

                summaryViewHolder.viewTitle.setText(R.string.summary_title);
                summaryViewHolder.summaryText.setText(summaryObject.getSummaryText());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return detailObjects.get(position).getType();
    }

    class LandingViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView rating;

        public LandingViewHolder(View item) {
            super(item);
            title = (TextView) item.findViewById(R.id.title);
            rating = (TextView) item.findViewById(R.id.rating);
        }
    }

    class SummaryViewHolder extends RecyclerView.ViewHolder{
        public TextView viewTitle;
        public TextView summaryText;

        public SummaryViewHolder(View item) {
            super(item);
            viewTitle = (TextView) item.findViewById(R.id.title);
            summaryText = (TextView) item.findViewById(R.id.summary);
        }
    }
}
