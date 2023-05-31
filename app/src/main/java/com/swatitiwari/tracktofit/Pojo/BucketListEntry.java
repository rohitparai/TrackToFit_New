package com.swatitiwari.tracktofit.Pojo;

import androidx.annotation.DrawableRes;



public class BucketListEntry {
    public String heading;
    public String description;
    public int image;
    public float rating;


    public BucketListEntry(String heading, String description, @DrawableRes int image, float rating){
        this.heading=heading;
        this.description=description;
        this.image=image;
        this.rating=rating;
    }
}
