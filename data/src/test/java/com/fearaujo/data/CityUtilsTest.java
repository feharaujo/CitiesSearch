package com.fearaujo.data;

import com.fearaujo.data.util.CityUtils;
import com.fearaujo.model.City;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CityUtilsTest {

    private final String CITY_NAME = "Araras";
    private final String COUNTRY_SUFFIX = "BR";
    private final String FULL_FORMAT = "Araras, BR";

    @Test
    public void shouldReturnFullFormat() {
        City city = new City.Builder()
                .country(COUNTRY_SUFFIX)
                .name(CITY_NAME)
                .build();

        final String expected = FULL_FORMAT;
        assertEquals(expected, CityUtils.getFullFormat(city));
        assertEquals(expected, CityUtils.getFullFormat(city.getName(), city.getCountry()));
    }

    @Test
    public void shouldReturnJustCountry() {
        City city = new City.Builder()
                .country(COUNTRY_SUFFIX)
                .name(null)
                .build();

        final String expected = COUNTRY_SUFFIX;
        assertEquals(expected, CityUtils.getFullFormat(city));
        assertEquals(expected, CityUtils.getFullFormat(city.getName(), city.getCountry()));
    }

    @Test
    public void shouldReturnJustCity() {
        City city = new City.Builder()
                .country(null)
                .name(CITY_NAME)
                .build();

        final String expected = CITY_NAME;
        assertEquals(expected, CityUtils.getFullFormat(city));
        assertEquals(expected, CityUtils.getFullFormat(city.getName(), city.getCountry()));
    }

}
