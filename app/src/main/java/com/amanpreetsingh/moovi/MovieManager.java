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
 * Created by Admin on 1/29/2016.
 */
public class MovieManager {

    public final String TAG = "MovieManager";

    Context mContext;

    public MovieManager(Context context){
        mContext = context;
    }

    public void fetchTopRatedMoviesList(final IRequestListener listener) throws IOException {

        Request request = HttpRequests.getPopularMoviesRequest(1);
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
                            listener.onSuccess(getMoviesFromResults(json));

                        } catch (Exception e) {
                            listener.onFailure();
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public ArrayList<Movie> getMoviesFromResults(JSONObject data) {

        ArrayList<Movie> list = new ArrayList<>();

        if (data == null || !data.has(Constants.RESULTS)){
            Log.e(TAG, "Data to getMoviesFromResults is null. Returning.");
            return null;
        }

        JSONArray resultsArray = null;
        try {

            resultsArray = data.getJSONArray(Constants.RESULTS);
            JSONObject movieJson;
            for (int n = 0; n < resultsArray.length(); n++){
                movieJson = resultsArray.getJSONObject(n);
                Movie movie = new Movie(movieJson);
                list.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
