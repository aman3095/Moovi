package aman.com.moovi.model;

import aman.com.moovi.Utils;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public class TVShow extends Program {

    private String programType = Utils.TV_SHOW;

    private String first_air_date;

    @Override
    public String getTitle() {
        if (super.getTitle().equals("")) {
            return getName();
        }
        return super.getTitle();
    }

    public String getYearGenre(){
        return getYear() + " - " + getGenres();
    }

    public String getFirstAiredDate() {
        return first_air_date;
    }

    public void setFirstAiredDate(String firstAiredDate) {
        this.first_air_date = firstAiredDate;
    }

    public String getProgramType() {
        return programType;
    }
}
