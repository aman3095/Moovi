package com.amanpreetsingh.moovi;

/**
 * Created by Admin on 5/29/2016.
 */

public class DetailObject {

    /**
     * See {@link Constants.DetailViewTypes}
     */
    private int type;

    private String detailTitle;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }
}
