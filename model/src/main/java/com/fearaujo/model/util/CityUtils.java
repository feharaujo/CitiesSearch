package com.fearaujo.model.util;

import com.fearaujo.model.City;

public class CityUtils {

    public static String getFullFormat(City city) {
        if (city == null) return null;

        return getFullFormat(city.getName(), city.getCountry());
    }

    public static String getFullFormat(String city, String country) {
        final StringBuilder stringBuilder = new StringBuilder();
        if (city != null) {
            stringBuilder.append(city);
        }

        if(city != null && country != null) {
            stringBuilder.append(", ");
        }

        if (country != null) {
            stringBuilder.append(country);
        }

        return stringBuilder.toString();
    }

}
