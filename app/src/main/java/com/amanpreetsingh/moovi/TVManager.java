package com.amanpreetsingh.moovi;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Admin on 2/3/2016.
 */
public class TVManager {

    Context mContext;

    public static final String TAG = "TVManager";

    public TVManager(Context context){
        mContext = context;
    }

    public void fetchPopularTvShows(final IRequestListener listener) throws IOException {
        Request request = HttpRequests.getPopularTvShowsRequest();
        OkClient.getInstance().executeAsync(request, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){
                    listener.onFailure();
                    return;
                }

                final String jsonString = response.body().string();
                Handler handler = new Handler(mContext.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(jsonString);
                            listener.onSuccess(getTvShowsFromResults(json));

                        } catch (Exception e) {
                            listener.onFailure();
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public ArrayList<TVShow> getTvShowsFromResults(JSONObject data){
        ArrayList<TVShow> list = new ArrayList<>();

        if (data == null || !data.has(Constants.RESULTS)){
            Log.e(TAG, "Data to getTvShowsFromResults is null. Returning.");
            return null;
        }

        JSONArray resultsArray = null;
        try {

            resultsArray = data.getJSONArray(Constants.RESULTS);
            JSONObject tvJson;
            for (int n = 0; n < resultsArray.length(); n++){
                tvJson = resultsArray.getJSONObject(n);
                TVShow tvShow = new TVShow(tvJson);
                list.add(tvShow);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
