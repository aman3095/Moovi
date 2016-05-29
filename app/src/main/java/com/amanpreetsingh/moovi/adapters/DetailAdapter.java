package com.amanpreetsingh.moovi.adapters;

import android.content.Context;
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

public class DetailAdapter extends BaseAdapter{

    private final String TAG = "DetailAdapter";

    private Context mContext;
    private ArrayList<DetailObject> detailObjects;

    public DetailAdapter(Context context, ArrayList<DetailObject> detailObjects){
        this.mContext = context;
        this.detailObjects = detailObjects;
    }

    @Override
    public int getCount() {
        return detailObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return detailObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return Constants.DETAIL_VIEW_TYPES;
    }

    @Override
    public int getItemViewType(int position) {
        return detailObjects.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);

        switch(type) {
            case Constants.DetailViewTypes.LANDING_ITEM:
                LandingViewHolder landingViewHolder;
                View landingView = convertView;

                if (landingView == null){
                    landingViewHolder = new LandingViewHolder();

                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    landingView = inflater.inflate(R.layout.detail_list_landing_item, parent, false);

                    landingViewHolder.title = (TextView) landingView.findViewById(R.id.title);
                    landingViewHolder.rating = (TextView) landingView.findViewById(R.id.rating);

                    landingView.setTag(landingViewHolder);
                    landingView.setMinimumHeight(parent.getMeasuredHeight());
                } else {
                    landingViewHolder = (LandingViewHolder) landingView.getTag();
                }

                LandingDetailObject landingObject = (LandingDetailObject) detailObjects.get(position);

                landingViewHolder.title.setText(landingObject.getLandingDetailTitle());
                if (landingObject.getRating() == -1.0){
                    landingViewHolder.rating.setVisibility(View.GONE);
                } else {
                    landingViewHolder.rating.setText("" + landingObject.getRating());
                }

                return landingView;

            case Constants.DetailViewTypes.SUMMARY_ITEM:
                SummaryViewHolder summaryViewHolder;
                View summaryView = convertView;

                if (summaryView == null){
                    summaryViewHolder = new SummaryViewHolder();

                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    summaryView = inflater.inflate(R.layout.detail_list_summary_item, parent, false);

                    summaryViewHolder.viewTitle = (TextView) summaryView.findViewById(R.id.title);
                    summaryViewHolder.summaryText = (TextView) summaryView.findViewById(R.id.summary);
                    summaryView.setTag(summaryViewHolder);
                } else {
                    summaryViewHolder = (SummaryViewHolder) summaryView.getTag();
                }

                SummaryDetailObject summaryObject = (SummaryDetailObject) detailObjects.get(position);

                summaryViewHolder.viewTitle.setText(R.string.summary_title);
                summaryViewHolder.summaryText.setText(summaryObject.getSummaryText());

                return summaryView;
            default:
                Log.d(TAG, "View type " + type +" does not exist.");
                return new View(mContext);
        }
    }

    class LandingViewHolder{
        public TextView title;
        public TextView rating;
    }

    class SummaryViewHolder{
        public TextView viewTitle;
        public TextView summaryText;
    }
}
