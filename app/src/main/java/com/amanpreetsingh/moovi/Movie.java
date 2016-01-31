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

    public Movie(String data) throws JSONException {
        this(new JSONObject(data));
    }

    public Movie(JSONObject data){
        setPosterPath(data.optString(Constants.POSTER_PATH));
        setTitle(data.optString(Constants.TITLE));
        setId(data.optInt(Constants.ID));
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

    @Override
    public String toString() {
        return getTitle();
    }
}
