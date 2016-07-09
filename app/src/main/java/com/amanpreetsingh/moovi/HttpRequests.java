package com.amanpreetsingh.moovi;

import okhttp3.Request;

/**
 * Created by Aman on 1/29/2016.
 */
public class HttpRequests {

    public static final String API_KEY = "api_key";

    public static final String API_SUFFIX = "http://api.themoviedb.org/3/";

    public static final String BASE_URL = "http://image.tmdb.org/t/p/";

    public static final String SLASH = "/";

    public static final String MOVIE = "movie";

    public static final String QUESTION_MARK = "?";

    public static final String EQUALS = "=";

    public static final String TOP_RATED = "top_rated";

    public static final String PAGE = "page";

    public static final String AMPERSAND = "&";

    public static final String DISCOVER = "discover/";

    public static final String SORT_BY = "sort_by";

    public static final String POPULARITY = "popularity.desc";

    public static final String TV = "tv";

    public static final String GENRE = "genre/";

    public static final String LIST = "list";

    public static String getMovieURL(int id){
        return API_SUFFIX + MOVIE + SLASH + id + addFirstParameter(API_KEY, Utils.KEY);
    }

    public static String getPopularMoviesURL(){
        return API_SUFFIX + DISCOVER + MOVIE + addFirstParameter(SORT_BY, POPULARITY) + addParameter(API_KEY, Utils.KEY);
    }

    public static String getPopularMoviesURL(int pageNo){
        return API_SUFFIX + DISCOVER + MOVIE + addFirstParameter(SORT_BY, POPULARITY) + addParameter(PAGE, "" + pageNo) + addParameter(API_KEY, Utils.KEY);
    }

    public static String getPosterURL(String posterPath, String size){
        return BASE_URL + size + posterPath;
    }

    public static String getBackdropURL(String backgroundPath, String size){
        return BASE_URL + size + backgroundPath;
    }

    public static String getPopularTvShowsURL(){
        return API_SUFFIX + DISCOVER + TV + addFirstParameter(SORT_BY, POPULARITY) + addParameter(API_KEY, Utils.KEY);
    }

    public static String getPopularTvShowsURL(int pageNo){
        return API_SUFFIX + DISCOVER + TV + addFirstParameter(SORT_BY, POPULARITY) + addParameter(PAGE, "" + pageNo) + addParameter(API_KEY, Utils.KEY);
    }

    public static String getMovieGenreListURL(){
        return API_SUFFIX + GENRE + MOVIE + SLASH + LIST + addFirstParameter(API_KEY, Utils.KEY);
    }

    public static String getTVGenreListURL(){
        return API_SUFFIX + GENRE + TV + SLASH + LIST + addFirstParameter(API_KEY, Utils.KEY);
    }

    public static Request getRequestFromURL(String url){
        return new Request.Builder()
                .url(url)
                .build();
    }

    public static Request getMovieRequest(int id){
        return getRequestFromURL(getMovieURL(id));
    }

    public static Request getPopularMoviesRequest(){
        return getRequestFromURL(getPopularMoviesURL());
    }

    public static Request getPopularMoviesRequest(int pages){
        return getRequestFromURL(getPopularMoviesURL(pages));
    }

    public static Request getPopularTvShowsRequest(){
        return getPopularTvShowsRequest(1);
    }

    public static Request getPopularTvShowsRequest(int pageNo){
        return getRequestFromURL(getPopularTvShowsURL(pageNo));
    }

    public static Request getMovieGenreListRequest(){
        return getRequestFromURL(getMovieGenreListURL());
    }

    public static Request getTVGenreListRequest(){
        return getRequestFromURL(getTVGenreListURL());
    }

    public static String addParameter(String param, String value){
        return AMPERSAND + param + EQUALS + value;
    }

    public static String addFirstParameter(String param, String value){
        return QUESTION_MARK + param + EQUALS + value;
    }
}
