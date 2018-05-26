package com.enghack2018.Model;


import java.io.Serializable;

public class PlateDO extends DataObject implements Serializable {

    private String imageUrl;
    private String type;
    private String dollar;
    private String name;
    private String avgReviews;

    public PlateDO(String imageUrl, String type, String dollar, String description, String avgReviews){
        this.imageUrl = imageUrl;
        this.type = type;
        this.dollar = dollar;
        this.name = description;
        this.avgReviews = avgReviews;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }

    public String getDollar() {
        return dollar;
    }

    public String getName() {
        return name;
    }

    public String getAvgReviews() {
        return avgReviews;
    }
}
