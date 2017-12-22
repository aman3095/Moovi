package aman.com.moovi.model;

import aman.com.moovi.Utils;

/**
 * Created by amanpreetsingh on 12/22/17.
 */

public class SummaryDetailObject extends DetailObject {

    private String mSummaryText;
    private String mReleaseDate;
    private String mGenres;

    public SummaryDetailObject(String text, String mReleaseDate, String mGenres){
        setType(Utils.DetailViewTypes.SUMMARY_ITEM);
        this.mSummaryText = text;
        this.mReleaseDate = mReleaseDate;
        this.mGenres = mGenres;
    }

    public String getSummaryText() {
        return mSummaryText;
    }

    public void setSummaryText(String mSummaryText) {
        this.mSummaryText = mSummaryText;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getGenres() {
        return mGenres;
    }

    public void setGenres(String mGenres) {
        this.mGenres = mGenres;
    }
}
