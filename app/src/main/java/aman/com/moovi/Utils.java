package aman.com.moovi;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public class Utils {

    public static String API_KEY = "c046f13f3bf575524a6dc79b7b8e5853";
    public static String MOVIE = "movie";
    public static String TV_SHOW = "tv";
    public static String BASE_URL = "http://api.themoviedb.org/3/";
    public static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    public static String PROGRAM_TYPE = "program_type";
    public static String RESULTS = "results";
    public static String SUMMARY = "SUMMARY";
    public static String PROGRAM_EXTRA = "PROGRAM_EXTRA";

    public static String getBackdropURL(String backdropPath, String size) {
        return IMAGE_BASE_URL + size + backdropPath;
    }

    public static class PosterSizes{
        public static final String W92 = "w92";
        public static final String W185 = "w185";
        public static final String W342 = "w342";
        public static final String W500 = "w500";
        public static final String ORIGINAL = "original";
    }

    public static class DetailViewTypes{
        public static final int LANDING_ITEM = 0;
        public static final int SUMMARY_ITEM = 1;
        public static final int HORIZONTAL_ADAPTER = 2;
    }
}
