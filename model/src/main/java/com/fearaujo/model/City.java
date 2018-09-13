package com.fearaujo.model;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("country")
    private String country;
    @SerializedName("name")
    private String name;
    @SerializedName("_id")
    private Integer id;
    @SerializedName("coord")
    private Coord coord;

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Coord getCoord() {
        return coord;
    }
}


