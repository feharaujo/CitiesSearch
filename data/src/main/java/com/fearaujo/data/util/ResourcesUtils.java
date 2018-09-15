package com.fearaujo.data.util;


import java.io.IOException;
import java.io.InputStream;

public class ResourcesUtils {


    /**
     * Load the JSON data file from resources
     *
     * @param aClass Context class
     * @return JSON data
     * @throws IOException Failed to load file
     */
    public static String loadDataFromResourcesFolder(Class aClass) throws IOException {
        InputStream stream = aClass.getClassLoader().getResourceAsStream("cities.json");
        byte[] b = new byte[stream.available()];
        stream.read(b);
        return new String(b);
    }

}
