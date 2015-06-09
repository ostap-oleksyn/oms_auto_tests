package com.softserveinc.edu.ita.utils;

import java.io.*;
import java.util.Properties;
//TODO remove Util
public final class PropertyLoaderUtil {

    private PropertyLoaderUtil() {
    }

    public static String getProperty(String propertyName, String propertiesFile) throws IOException {

        final Properties property = new Properties();
        //TODO move path out to a variable
        final File propertyFile = new File("src//resources//" + propertiesFile);
        FileInputStream fileInputStream = new FileInputStream(propertyFile);

        property.load(fileInputStream);

        return property.getProperty(propertyName);
    }

}
