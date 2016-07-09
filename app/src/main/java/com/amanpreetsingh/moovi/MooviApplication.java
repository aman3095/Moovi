package com.amanpreetsingh.moovi;

import android.app.Application;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by amanpreetsingh on 09/07/16.
 */
public class MooviApplication extends Application {

    public static HashMap<Integer,String> movieGenreMap;

    public static HashMap<Integer,String> tvGenreMap;

    @Override
    public void onCreate() {
        super.onCreate();
        initImportantAppComponents();
    }

    public void initImportantAppComponents(){
        movieGenreMap = new HashMap<>();
        tvGenreMap = new HashMap<>();
    }
}
