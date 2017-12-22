package aman.com.moovi.model;

import android.text.Spannable;

import aman.com.moovi.Utils;

/**
 * Created by amanpreetsingh on 12/22/17.
 */

public class LandingDetailObject extends DetailObject {

    private Spannable mTitle;
    private double mRating;

    public LandingDetailObject(Spannable title, double rating){
        setType(Utils.DetailViewTypes.LANDING_ITEM);
        this.mTitle = title;
        this.mRating = rating;
    }

    public Spannable getLandingDetailTitle(){
        return mTitle;
    }

    public double getRating() {
        return mRating;
    }
}
