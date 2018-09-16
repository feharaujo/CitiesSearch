package com.fearaujo.model;

public class City implements Comparable<City> {

    private final String country;
    private final String name;
    private final Integer id;
    private final Coord coord;
    private final String fullName;

    private City(Builder builder) {
        this.country = builder.country;
        this.name = builder.name;
        this.id = builder.id;
        this.coord = builder.coord;
        this.fullName = builder.fullname;
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

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        //return Objects.equals(getFullName(), city.getFullName());
        return getFullName().equals(city.getFullName());
    }

    @Override
    public int hashCode() {
        return getFullName().hashCode();
    }

    @Override
    public int compareTo(City city) {
        return fullName.compareTo(city.getFullName());
    }

    public static class Builder {
        private String country;
        private String name;
        private Integer id;
        private Coord coord;
        private String fullname;

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

        public Builder fullname(String fullname) {
            this.fullname = fullname;
            return this;
        }

        public City build() {
            return new City(this);
        }
    }
}


