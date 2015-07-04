package com.softserveinc.edu.ita.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Class with util methods for working with properties.
 */
public final class PropertyLoader {
    private static final String RESOURCES_PATH = "src//resources//";
    private static final String CONFIG_PROPERTY_FILE = "config.properties";

    private PropertyLoader() {
    }

    /**
     * Returns a property from specified property file
     * @param propertyName - property name
     * @param propertiesFile - propery file
     * @throws IOException
     */
    public static String getProperty(final String propertyName, final String propertiesFile) throws IOException {
        return getPropertyGeneric(propertyName, RESOURCES_PATH + propertiesFile);
    }

    /**
     * Returns a property from default property file
     * @param propertyName - property name
     * @throws IOException
     */
    public static String getProperty(final String propertyName) throws IOException {
        return getPropertyGeneric(propertyName, RESOURCES_PATH + CONFIG_PROPERTY_FILE);
    }

    private static String getPropertyGeneric(final String propertyName, final String fileName) throws IOException {
        final Properties property = new Properties();
        final File propertyFile = new File(fileName);

        final FileInputStream fileInputStream = new FileInputStream(propertyFile);
        property.load(fileInputStream);

        return property.getProperty(propertyName);
    }

}