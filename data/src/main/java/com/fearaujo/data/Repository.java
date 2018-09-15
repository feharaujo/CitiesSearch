package com.fearaujo.data;

import com.fearaujo.model.City;

import java.util.List;

public interface Repository {

    boolean initRepository();

    List<City> searchCities(final String prefix);

}
