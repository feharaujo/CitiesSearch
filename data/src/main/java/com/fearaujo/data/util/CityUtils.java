package com.fearaujo.data.util;

import com.fearaujo.model.City;

public class CityUtils {

    /**
     * Get the following format name:
     * <p>
     * City name + country
     * <p>
     * Ex: Toronto, CA
     *
     * @param city City object
     * @return City name with the country
     */
    public static String getFullFormat(City city) {
        if (city == null) return null;

        return getFullFormat(city.getName(), city.getCountry());
    }

    /**
     * Get the following format name:
     * <p>
     * City name + country
     * <p>
     * Ex: Toronto, CA
     *
     * @param city    City name
     * @param country Country name
     * @return City name with the country
     */
    public static String getFullFormat(String city, String country) {
        final StringBuilder stringBuilder = new StringBuilder();
        if (city != null) {
            stringBuilder.append(city);
        }

        if (city != null && country != null) {
            stringBuilder.append(", ");
        }

        if (country != null) {
            stringBuilder.append(country);
        }

        return stringBuilder.toString();
    }

}
