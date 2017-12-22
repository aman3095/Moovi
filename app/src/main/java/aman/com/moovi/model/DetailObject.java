package aman.com.moovi.model;

/**
 * Created by amanpreetsingh on 12/22/17.
 */

public class DetailObject {
    /**
     * See {@link aman.com.moovi.Utils.DetailViewTypes}
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
