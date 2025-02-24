package com.TGMetadataService.Helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream stream = new FileInputStream("config.properties");
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration file", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
