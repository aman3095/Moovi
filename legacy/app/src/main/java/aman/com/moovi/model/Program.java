package aman.com.moovi.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public abstract class Program implements Serializable{

    private int id;

    private String poster_path;

    private String backdrop_path;

    private String language;

    private String overview;

    private double popularity;

    private String status;

    private String title = "";

    private ArrayList<Genre> genres;

    private String genre = "";

    private int year = -1;

    private String name = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdrop_path = backdropPath;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getProgramType();

    public void setGenre(String genre) {
        String final_genre = "";
        for (Genre g : getGenres()) {
            final_genre += g.getName() + ", ";
        }
        if (final_genre.length() >= 2) {
            this.genre = final_genre.substring(0, final_genre.length() - 2);
        } else {
            this.genre = "";
        }
    }

    public String getGenre() {
        if (this.genre.equals("")) {
            setGenre("");
        }
        return this.genre;
    }

}
