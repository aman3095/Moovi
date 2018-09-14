package aman.com.moovi.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import aman.com.moovi.R;
import aman.com.moovi.Utils;
import aman.com.moovi.model.Program;
import aman.com.moovi.ui.adapter.DetailAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.background_image) ImageView mBackgroundImage;
    @BindView(R.id.detail_list) RecyclerView mDetailList;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    DetailPresenter mDetailPresenter = new DetailPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setupToolbar();

        Program program = (Program) getIntent().getSerializableExtra(Utils.PROGRAM_EXTRA);

        mDetailPresenter.attachView(this);
        mDetailPresenter.loadBackgroundImage(program);
        mDetailPresenter.loadProgramDetail(program);
    }

    public void setupToolbar(){
        setSupportActionBar(mToolbar);
        mToolbar.setBackgroundColor(Color.TRANSPARENT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("");
    }

    @Override
    public void showBackgroundImage(String posterPath) {
        Glide.with(this)
                .load(Utils.getBackdropURL(posterPath, Utils.PosterSizes.W500))
                .apply(new RequestOptions()
                        .fitCenter()
                )
                .into(mBackgroundImage);
    }

    @Override
    public void showProgramDetails(Program program) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mDetailList.setLayoutManager(layoutManager);
        mDetailList.setAdapter(new DetailAdapter(this, mDetailPresenter.getDetailObjects(program)));

        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, DetailActivity.class);
    }
}
