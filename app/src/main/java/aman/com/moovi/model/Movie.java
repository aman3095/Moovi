package aman.com.moovi.model;

import aman.com.moovi.Utils;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public class Movie extends Program {

    private String programType = Utils.MOVIE;

    private String tagline;

    private int runtime;

    private String release_date;

    private double vote_average;

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
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(double voteAverage) {
        this.vote_average = voteAverage;
    }

    public String getProgramType() {
        return programType;
    }

}
