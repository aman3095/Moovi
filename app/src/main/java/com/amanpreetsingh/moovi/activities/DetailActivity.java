package com.amanpreetsingh.moovi.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.amanpreetsingh.moovi.Constants;
import com.amanpreetsingh.moovi.HttpRequests;
import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.Utils;
import com.amanpreetsingh.moovi.adapters.DetailAdapter;
import com.squareup.picasso.Picasso;

public class DetailActivity extends ActionBarActivity {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupToolbar();
        setupView(getIntent());
    }

    public void setupToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
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

    public void setupView(Intent intent){

        String title = intent.getStringExtra(Constants.TITLE);
        int id = intent.getIntExtra(Constants.ID, -1);
        double rating = intent.getDoubleExtra(Constants.RATING, -1);
        String posterPath = intent.getStringExtra(Constants.POSTER_PATH);
        String overview = intent.getStringExtra(Constants.OVERVIEW);

        setBackgroundPicture(posterPath);

        ListView detailListView = (ListView) findViewById(R.id.detail_list);
        DetailAdapter detailAdapter = new DetailAdapter(this, Utils.getDetailObjectList(intent));
        detailListView.setAdapter(detailAdapter);
    }

    public void setBackgroundPicture(String posterPath){
        ImageView backgroundImage = (ImageView) findViewById(R.id.background_image);
        Picasso.with(this)
                .load(HttpRequests.getPosterURL(posterPath, Constants.PosterSizes.W500))
                .into(backgroundImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
