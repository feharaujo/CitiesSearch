package com.fearaujo.model;

import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName("lon")
    private Double lon;
    @SerializedName("lat")
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }
}
