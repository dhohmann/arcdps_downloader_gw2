package com.gw2.arcdpsdownloadergw2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * Configuration class with all settings.
 */
public class Configuration {
    private static final String DEFAULT_VALUES_PATH = "/config.defaults.properties";
    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final String FALLBACK_CONFIG_FOLDER_NAME = "arcdpsdownloadergw2";

    private static final String PROPERTY_DIRECTX_VERSION = "directx.version";

    private final Properties properties;

    public Configuration() {
        this.properties = load();
    }

    public static File getConfigFile() {
        URL codeSource = Configuration.class.getProtectionDomain().getCodeSource().getLocation();
        File folder;
        try {
            // When executed with Maven points to target/classes
            // When executed in JAR, points to folder with JAR
            folder = new File(codeSource.toURI()).getParentFile();
        } catch (URISyntaxException e) {
            // Create a folder in user home as fallback
            File userHome = new File(System.getProperty("user.home"));
            folder = new File(userHome, FALLBACK_CONFIG_FOLDER_NAME);
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        return new File(folder, CONFIG_FILE_NAME);
    }

    public Properties load() {
        Properties defaults = loadDefaults();

        File configurationFile = getConfigFile();
        Properties properties = new Properties(defaults);
        if (configurationFile.exists()) {
            try {
                InputStream input = new FileInputStream(configurationFile);
                properties.load(input);
                input.close();
            } catch (IOException ex) {
                System.out.println("Could not load configuration file: " + ex.getMessage());
            }
        }
        return properties;
    }

    private Properties loadDefaults() {
        Properties defaults = new Properties();
        try {
            InputStream inputStream = Configuration.class.getResourceAsStream(DEFAULT_VALUES_PATH);
            if (inputStream != null) {
                defaults.load(inputStream);
            }
            inputStream.close();
        } catch (IOException ex) {
            System.out.println("Could not load default configuration: " + ex.getMessage());
        }
        return defaults;
    }

    public void save() {
        try {
            FileOutputStream out = new FileOutputStream(getConfigFile());
            properties.store(out, "");
            out.close();
        } catch (IOException ex) {
            System.out.println("Could not save configuration: " + ex.getMessage());
        }
    }

    public int getDirectXVersion() {
        return Integer.parseInt(properties.getProperty(PROPERTY_DIRECTX_VERSION));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(PROPERTY_DIRECTX_VERSION).append(": ").append(properties.getProperty(PROPERTY_DIRECTX_VERSION));
        return result.toString();
    }
}
