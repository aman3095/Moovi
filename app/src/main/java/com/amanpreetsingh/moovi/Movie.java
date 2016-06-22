package com.amanpreetsingh.moovi;

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
}
