package com.fearaujo.model;

public class Coord {

    private final Double lon;
    private final Double lat;

    private Coord(Builder builder) {
        this.lon = builder.lon;
        this.lat = builder.lat;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }

    public static class Builder {
        private Double lon;
        private Double lat;

        public Builder lon(Double lon) {
            this.lon = lon;
            return this;
        }

        public Builder lat(Double lat) {
            this.lat = lat;
            return this;
        }

        public Coord build() {
            return new Coord(this);
        }
    }
}
