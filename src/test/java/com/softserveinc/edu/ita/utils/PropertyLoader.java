package com.softserveinc.edu.ita.utils;

import java.io.*;
import java.util.Properties;

public final class PropertyLoader {
    private static final String RESOURCES_PATH = "src//resources//";
    private static final String CONFIG_PROPERTY_FILE = "config.properties";

    private PropertyLoader() {
    }

    public static String getProperty(String propertyName, String propertiesFile) throws IOException {
        return getPropertyGeneric(propertyName, RESOURCES_PATH + propertiesFile);
    }

    public static String getProperty(String propertyName) throws IOException {
        return getPropertyGeneric(propertyName, RESOURCES_PATH + CONFIG_PROPERTY_FILE);
    }

    private static String getPropertyGeneric(String propertyName, String fileName) throws IOException {
        final Properties property = new Properties();
        final File propertyFile = new File(fileName);

        final FileInputStream fileInputStream = new FileInputStream(propertyFile);
        property.load(fileInputStream);

        return property.getProperty(propertyName);
    }
}