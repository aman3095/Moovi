package com.amanpreetsingh.moovi;

/**
 * Created by Admin on 5/29/2016.
 */

public class LandingDetailObject extends DetailObject {

    private String title;
    private double rating;

    public LandingDetailObject(String title, double rating){
        setType(Constants.DetailViewTypes.LANDING_ITEM);
        this.title = title;
        this.rating = rating;
    }

    public String getLandingDetailTitle(){
        return title;
    }

    public double getRating() {
        return rating;
    }
}
