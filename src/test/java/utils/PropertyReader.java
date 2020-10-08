package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    static Properties properties = null;
    static BufferedReader reader = null;
    private final static String propertyFilePath = "src/test/resources/env/application.properties";

    static {
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getPropValue(String propName) {
        return properties.getProperty(propName);
    }
}
