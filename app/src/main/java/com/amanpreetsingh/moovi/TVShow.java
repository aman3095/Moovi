package com.amanpreetsingh.moovi;

import org.json.JSONObject;

/**
 * Created by Admin on 2/3/2016.
 */
public class TVShow extends EntertainmentEntity {

    public TVShow(JSONObject data){
        setId(data.optInt(Constants.ID));
        setTitle(data.optString(Constants.NAME));
        setPosterPath(data.optString(Constants.POSTER_PATH));
    }

}
