package com.softserveinc.edu.ita.utils;

import java.io.*;
import java.util.Properties;

public final class PropertyLoaderUtil {

    private PropertyLoaderUtil() {
    }

    public static String getProperty(String propertyName, String propertiesFile) throws IOException {

        final Properties property = new Properties();
        final File propertyFile = new File("src//resources//" + propertiesFile);
        FileInputStream fileInputStream = new FileInputStream(propertyFile);

        property.load(fileInputStream);

        return property.getProperty(propertyName);
    }

}
