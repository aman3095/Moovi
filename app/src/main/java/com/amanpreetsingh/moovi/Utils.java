package com.amanpreetsingh.moovi;

import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Aman on 1/29/2016.
 */
public class Utils {

    public static final String KEY = "c046f13f3bf575524a6dc79b7b8e5853";

    public static ArrayList<DetailObject> getDetailObjectList(Intent intent){
        ArrayList<DetailObject> list = new ArrayList<>();
        list.add(new LandingDetailObject(intent.getStringExtra(Constants.TITLE), intent.getDoubleExtra(Constants.RATING, -1)));
        list.add(new SummaryDetailObject(intent.getStringExtra(Constants.OVERVIEW)));

        return list;
    }
}
