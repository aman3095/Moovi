package aman.com.moovi.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import aman.com.moovi.MooviApplication;
import aman.com.moovi.R;
import aman.com.moovi.Utils;
import aman.com.moovi.model.DetailObject;
import aman.com.moovi.model.LandingDetailObject;
import aman.com.moovi.model.SummaryDetailObject;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by amanpreetsingh on 12/22/17.
 */

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<DetailObject> mDetailObjects;

    public DetailAdapter(Context context, ArrayList<DetailObject> detailObjects) {
        mContext = context;
        mDetailObjects = detailObjects;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        switch (viewType) {
            case Utils.DetailViewTypes.LANDING_ITEM:
                View landingView = inflater.inflate(R.layout.detail_list_landing_item, parent, false);
                LandingViewHolder landingHolder = new LandingViewHolder(landingView);
                landingView.setMinimumHeight(parent.getMeasuredHeight());
                return landingHolder;

            case Utils.DetailViewTypes.SUMMARY_ITEM:
                View summaryView = inflater.inflate(R.layout.detail_list_summary_item, parent, false);
                SummaryViewHolder summaryHolder = new SummaryViewHolder(summaryView);

                return summaryHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Utils.DetailViewTypes.LANDING_ITEM:
                LandingDetailObject landingObject = (LandingDetailObject) mDetailObjects.get(position);
                LandingViewHolder landingViewHolder = (LandingViewHolder) holder;

                landingViewHolder.mTitle.setText(landingObject.getLandingDetailTitle());

                if (landingObject.getRating() == -1.0){
                    landingViewHolder.mRatingLayout.setVisibility(View.GONE);
                } else {
                    landingViewHolder.mRating.setText("" + landingObject.getRating());
                }
                break;

            case Utils.DetailViewTypes.SUMMARY_ITEM:
                SummaryDetailObject summaryObject = (SummaryDetailObject) mDetailObjects.get(position);
                SummaryViewHolder summaryViewHolder = (SummaryViewHolder) holder;

                summaryViewHolder.mTitle.setText(Utils.SUMMARY);
                summaryViewHolder.mSummaryText.setText(summaryObject.getSummaryText());
                summaryViewHolder.mReleaseDate.setText("Released : " + summaryObject.getReleaseDate());
                summaryViewHolder.mGenres.setText(summaryObject.getGenres());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDetailObjects.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDetailObjects.get(position).getType();
    }

    class LandingViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.title) TextView mTitle;
        @BindView(R.id.rating) TextView mRating;
        @BindView(R.id.rating_layout) LinearLayout mRatingLayout;

        public LandingViewHolder(View item) {
            super(item);
            ButterKnife.bind(this, item);
        }
    }

    class SummaryViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title) TextView mTitle;
        @BindView(R.id.summary) TextView mSummaryText;
        @BindView(R.id.release_date) TextView mReleaseDate;
        @BindView(R.id.genres) TextView mGenres;

        public SummaryViewHolder(View item) {
            super(item);
            ButterKnife.bind(this, item);
        }
    }

}
