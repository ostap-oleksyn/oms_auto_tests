package com.softserveinc.edu.ita.utils;

import java.io.*;
import java.util.Properties;

public final class PropertyLoaderUtil {

    private PropertyLoaderUtil() {
    }

    public static String getProperty(String propertyName, String propertiesFile) {

        final Properties property = new Properties();
        final File propertyFile = new File("src//resources//" + propertiesFile);
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(propertyFile);
            property.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Property file not found");
            e.printStackTrace();
        }

        return property.getProperty(propertyName);
    }

}
