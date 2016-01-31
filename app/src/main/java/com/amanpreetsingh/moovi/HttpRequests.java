package com.amanpreetsingh.moovi;

import okhttp3.Request;

/**
 * Created by Aman on 1/29/2016.
 */
public class HttpRequests {

    public static final String API_KEY = "api_key";

    public static final String API_SUFFIX = "http://api.themoviedb.org/3/";

    public static final String BASE_URL = "http://image.tmdb.org/t/p/";

    public static final String MOVIE = "movie/";

    public static final String QUESTION_MARK = "?";

    public static final String EQUALS = "=";

    public static final String TOP_RATED = "top_rated";

    public static final String PAGE = "page";

    public static final String AMPERSAND = "&";

    public static String getMovieURL(int id){
        return API_SUFFIX + MOVIE + id + QUESTION_MARK + API_KEY + EQUALS + Utils.KEY;
    }

    public static String getTopRatedMoviesURL(){
        return API_SUFFIX + MOVIE + TOP_RATED + QUESTION_MARK + API_KEY + EQUALS + Utils.KEY;
    }

    public static String getTopRatedMoviesURL(int pages){
        return API_SUFFIX + MOVIE + TOP_RATED + QUESTION_MARK + PAGE + EQUALS + pages + AMPERSAND + API_KEY + EQUALS + Utils.KEY;
    }

    public static String getPosterURL(String posterPath, String size){
        return BASE_URL + size + posterPath;
    }

    public static Request getRequestFromURL(String url){
        return new Request.Builder()
                .url(url)
                .build();
    }

    public static Request getMovieRequest(int id){
        return getRequestFromURL(getMovieURL(id));
    }

    public static Request getTopRateMoviesRequest(){
        return getRequestFromURL(getTopRatedMoviesURL());
    }

    public static Request getTopRateMoviesRequest(int pages){
        return getRequestFromURL(getTopRatedMoviesURL(pages));
    }
}
