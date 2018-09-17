package com.fearaujo.data;

import com.fearaujo.data.util.CityUtils;
import com.fearaujo.data.util.ResourcesUtils;
import com.fearaujo.model.City;
import com.fearaujo.model.Coord;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;


public class RepositoryImpl implements Repository {

    private TreeSet<City> mCitiesTree;

    private JsonDeserializer<City> deserializer = (json, typeOfT, context) -> {
        final JsonObject jsonObject = json.getAsJsonObject();

        final String cityName = jsonObject.get("name").getAsString();
        final String countryPrefix = jsonObject.get("country").getAsString();

        final JsonObject coordJson = jsonObject.getAsJsonObject("coord");
        final Double lat = coordJson.get("lat").getAsDouble();
        final Double lon = coordJson.get("lon").getAsDouble();
        final Coord coord = new Coord.Builder().lon(lon).lat(lat).build();

        final String fullname = CityUtils.getFullFormat(cityName, countryPrefix);
        final Integer id = jsonObject.get("_id").getAsInt();

        return new City.Builder()
                .fullname(fullname)
                .name(cityName)
                .country(countryPrefix)
                .id(id)
                .coord(coord)
                .build();
    };

    /**
     * Loads the data from the JSON file into the tree
     *
     * @return Success loaded or not
     */
    @Override
    public boolean initRepository() {
        if (mCitiesTree != null) return true;

        try {
            String json = getJsonData();
            final GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(City.class, deserializer);
            mCitiesTree = gsonBuilder.create().fromJson(json, new TypeToken<TreeSet<City>>() {
            }.getType());

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Search cities by prefix
     *
     * @param prefix User input
     * @return List of cities found
     */
    @Override
    public List<City> searchCities(String prefix) {
        if (prefix == null) return null;

        City begin = new City.Builder().fullname(prefix).build();
        City end = new City.Builder().fullname(prefix + Character.MAX_VALUE).build();

        SortedSet<City> resultSet = mCitiesTree.subSet(begin, end);
        return new ArrayList<>(resultSet);
    }

    private String getJsonData() throws IOException {
        return ResourcesUtils.loadDataFromResourcesFolder(this.getClass());
    }


}
