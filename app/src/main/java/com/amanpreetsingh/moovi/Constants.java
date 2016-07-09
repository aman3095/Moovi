package com.amanpreetsingh.moovi;

/**
 * Created by Admin on 1/29/2016.
 */
public class Constants {

    public static final String RESULTS = "results";

    public static final String POSTER_PATH = "poster_path";

    public static final String OVERVIEW = "overview";

    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String BACKDROP_PATH = "backdrop_path";

    public static final String RATING = "vote_average";

    public static final String TYPE = "type";

    public static final String RELEASE_DATE = "release_date";

    public static final int TYPE_MOVIE = 0;

    public static final int TYPE_TV = 1;

    public static final String NAME = "name";

    public static final int DETAIL_VIEW_TYPES = 3;

    public static final String SHARED_PREFS = "moovi_shared_prefs";

    public static final String GENRES = "genres";

    public static final String GENRE_IDS = "genre_ids";

    public static final String MOVIE_GENRE_LIST = "movie_genre_list";

    public static final String TV_GENRE_LIST = "tv_genre_list";

    public static final String FIRST_AIRED_DATE = "first_air_date";

    public static class DetailViewTypes{
        public static final int LANDING_ITEM = 0;
        public static final int SUMMARY_ITEM = 1;
        public static final int HORIZONTAL_ADAPTER = 2;
    }

    public static class PosterSizes{
        public static final String W92 = "w92";
        public static final String W185 = "w185";
        public static final String W342 = "w342";
        public static final String W500 = "w500";
        public static final String ORIGINAL = "original";
    }
}
