package com.amanpreetsingh.moovi;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 2/3/2016.
 */
public class TVShow extends EntertainmentEntity {

    private String firstAiredDate;

    public TVShow(JSONObject data){
        setId(data.optInt(Constants.ID));
        setTitle(data.optString(Constants.NAME));
        setPosterPath(data.optString(Constants.POSTER_PATH));
        setBackdropPath(data.optString(Constants.BACKDROP_PATH));
        setOverview(data.optString(Constants.OVERVIEW));
        setFirstAiredDate(data.optString(Constants.FIRST_AIRED_DATE));
        setYear(Integer.parseInt(getFirstAiredDate().substring(0, getFirstAiredDate().indexOf("-"))));


        try {
            JSONArray genres = data.getJSONArray(Constants.GENRE_IDS);
            if (genres.length() != 0) {

                int genre_id = genres.getInt(0);
                String genre = MooviApplication.tvGenreMap.get(genre_id);
                if (TextUtils.isEmpty(genre)){
                    genre = MooviApplication.movieGenreMap.get(genre_id);
                }
                setGenre(genre);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getYearGenre(){
        return getYear() + " - " + getGenre();
    }

    public String getFirstAiredDate() {
        return firstAiredDate;
    }

    public void setFirstAiredDate(String firstAiredDate) {
        this.firstAiredDate = firstAiredDate;
    }
}
