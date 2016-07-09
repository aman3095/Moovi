package com.amanpreetsingh.moovi;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aman on 1/29/2016.
 */
public class Movie extends EntertainmentEntity {

    private String tagline;

    private int runtime;

    private String releaseDate;

    private double voteAverage;

    private int year;

    private String genre;

    public Movie(String data) throws JSONException {
        this(new JSONObject(data));
    }

    public Movie(JSONObject data){
        setId(data.optInt(Constants.ID));
        setTitle(data.optString(Constants.TITLE));
        setPosterPath(data.optString(Constants.POSTER_PATH));
        setBackdropPath(data.optString(Constants.BACKDROP_PATH));
        setVoteAverage(data.optDouble(Constants.RATING));
        setOverview(data.optString(Constants.OVERVIEW));
        setReleaseDate(data.optString(Constants.RELEASE_DATE));
        setYear(Integer.parseInt(getReleaseDate().substring(0, getReleaseDate().indexOf("-"))));

        try {
            JSONArray genres = data.getJSONArray(Constants.GENRE_IDS);
            if (genres.length() != 0) {
                int genre_id = genres.getInt(0);
                String genre = MooviApplication.movieGenreMap.get(genre_id);
                if (TextUtils.isEmpty(genre)){
                    genre = MooviApplication.tvGenreMap.get(genre_id);
                }
                setGenre(genre);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public String toString() {
        return getTitle();
    }

    public String getYearGenre(){
        return getYear() + " - " + getGenre();
    }
}
