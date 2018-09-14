package com.fearaujo.model;

import com.google.gson.annotations.SerializedName;

import static com.fearaujo.model.util.CityUtils.getFullFormat;

public class City implements Comparable<City> {

    @SerializedName("country")
    private final String country;
    @SerializedName("name")
    private final String name;
    @SerializedName("_id")
    private final Integer id;
    @SerializedName("coord")
    private final Coord coord;

    private City(Builder builder) {
        this.country = builder.country;
        this.name = builder.name;
        this.id = builder.id;
        this.coord = builder.coord;
    }

    public static Builder builder() {
        return new Builder();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;
        String c1 = getFullFormat(getCountry(), city.getCountry());
        String c2 = getFullFormat(city.getName(), city.getCountry());

        return c1.equals(c2);
    }

    @Override
    public int hashCode() {
        return getFullFormat(getName(), getCountry()).hashCode();
    }

    @Override
    public int compareTo(City city) {
        return getFullFormat(city).compareToIgnoreCase(getFullFormat(this));
    }

    public static class Builder {
        private String country;
        private String name;
        private Integer id;
        private Coord coord;

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder coord(Coord coord) {
            this.coord = coord;
            return this;
        }

        public City build() {
            return new City(this);
        }
    }
}


