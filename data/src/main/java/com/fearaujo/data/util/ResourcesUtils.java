package com.fearaujo.data.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

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
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            stream.close();
        }

        return writer.toString();
    }

}
