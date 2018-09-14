package aman.com.moovi.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import aman.com.moovi.R;
import aman.com.moovi.Utils;
import aman.com.moovi.model.Program;
import aman.com.moovi.ui.RecyclerViewClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public class ProgramListAdapter<T extends Program> extends RecyclerView.Adapter<ProgramListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<T> mProgramList;
    private RecyclerViewClickListener mClickListener;

    public ProgramListAdapter(Context context, ArrayList<T> programList, RecyclerViewClickListener listener) {
        mContext = context;
        mProgramList = programList;
        mClickListener = listener;
    }

    @Override
    public ProgramListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v, mClickListener);
    }

    @Override
    public void onBindViewHolder(ProgramListAdapter.ViewHolder holder, int position) {
        Program program = mProgramList.get(position);

        holder.mProgramName.setText(program.getTitle());
//        holder.mProgramName.setText(Utils.getTitleYearSpan(movie.getTitle(), movie.getYear()));
//        holder.mProgramRating.setText("" + movie.getVoteAverage());

        Glide.with(mContext)
                .load(Utils.getBackdropURL(program.getBackdropPath(), Utils.PosterSizes.W342))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.image_placeholder)
                        .fitCenter()
                )
                .into(holder.mProgramImage);

    }

    @Override
    public int getItemCount() {
        return mProgramList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerViewClickListener mClickListener;
        @BindView(R.id.background_image) ImageView mProgramImage;
        @BindView(R.id.name) TextView mProgramName;
        @BindView(R.id.rating) TextView mProgramRating;

        public ViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            mClickListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClicked(ProgramListAdapter.this.mProgramList.get(getLayoutPosition()));
        }
    }
}
