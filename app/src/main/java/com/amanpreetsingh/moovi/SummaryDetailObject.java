package com.amanpreetsingh.moovi;

/**
 * Created by Aman on 5/29/2016.
 */

public class SummaryDetailObject extends DetailObject {

    private String summaryText;

    public SummaryDetailObject(String text){
        setType(Constants.DetailViewTypes.SUMMARY_ITEM);
        this.summaryText = text;
    }

    public String getSummaryText() {
        return summaryText;
    }
}
