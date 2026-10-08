package com.practice.saucedemo.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class ConfigReader {
    private static final Path CONFIG_FILE = Path.of("configuration.properties");
    private static final Properties PROPERTIES = loadProperties();

    private ConfigReader() {
    }

    public static String getProperty(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing configuration property: " + key);
        }
        return value.trim();
    }

    public static int getIntProperty(String key) {
        try {
            return Integer.parseInt(getProperty(key));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Configuration property must be an integer: " + key,
                    exception);
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (var stream = Files.newInputStream(CONFIG_FILE)) {
            properties.load(stream);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not load configuration file: "
                    + CONFIG_FILE.toAbsolutePath(),
                    exception);
        }
        return properties;
    }
}
