package com.amanpreetsingh.moovi.activities;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.amanpreetsingh.moovi.Constants;
import com.amanpreetsingh.moovi.HttpRequests;
import com.amanpreetsingh.moovi.MooviApplication;
import com.amanpreetsingh.moovi.OkClient;
import com.amanpreetsingh.moovi.R;
import com.amanpreetsingh.moovi.adapters.SlidingPagerAdapter;
import com.amanpreetsingh.moovi.slidingtabs.SlidingTabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;


public class HomeActivity extends ActionBarActivity {

    public final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupToolbar();
        setupSlidingTabs();
        initData();
    }

    public void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setupSlidingTabs(){
        SlidingPagerAdapter adapter = new SlidingPagerAdapter(this, getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.tab_pager);
        pager.setAdapter(adapter);

        SlidingTabLayout tabLayout = (SlidingTabLayout)findViewById(R.id.tab_layout);
        tabLayout.setDistributeEvenly(true);
        tabLayout.setViewPager(pager);
    }

    public void initData() {
        Request movieGenreListRequest = HttpRequests.getMovieGenreListRequest();
        try {
            OkClient.getInstance().executeAsync(movieGenreListRequest, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "Failed to fetch Movie Genre list.");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()){
                        Log.e(TAG, "Failed to fetch Movie Genre list. Response unsuccessful.");
                    } else {
                        try {
                            JSONArray genres = (new JSONObject(response.body().string())).getJSONArray(Constants.GENRES);
                            for (int i = 0; i < genres.length(); i++){
                                JSONObject json = genres.getJSONObject(i);
                                int id = json.getInt(Constants.ID);
                                String genre = json.getString(Constants.NAME);
                                MooviApplication.movieGenreMap.put(id, genre);
                            }
                        } catch (JSONException e) {
                            Log.e(TAG, "JSONException while fetching Movie Genre list.");
                            e.printStackTrace();
                        }


                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Request tvGenreListRequest = HttpRequests.getTVGenreListRequest();
        try {
            OkClient.getInstance().executeAsync(tvGenreListRequest, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "Failed to fetch TV Genre list.");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()){
                        Log.e(TAG, "Failed to fetch TV Genre list. Response unsuccessful.");
                    } else {
                        JSONArray genres = null;
                        try {
                            genres = (new JSONObject(response.body().string())).getJSONArray(Constants.GENRES);
                            for (int i = 0; i < genres.length(); i++){
                                JSONObject json = genres.getJSONObject(i);
                                int id = json.getInt(Constants.ID);
                                String genre = json.getString(Constants.NAME);
                                MooviApplication.tvGenreMap.put(id, genre);
                            }
                        } catch (JSONException e) {
                            Log.e(TAG, "JSONException while fetching TV Genre list.");
                            e.printStackTrace();
                        }

                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
