package com.gw2.arcdpsdownloadergw2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static final String DEFAULT_VALUES_PATH = "/config.defaults.properties";
    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final String CONFIG_FOLDER_NAME = "arcdpsdownloadergw2";

    private static final String PROPERTY_DIRECTX_VERSION = "directx.version";

    private static Configuration instance;

    private final Properties properties;

    private Configuration(Properties properties) {
        this.properties = properties;
    }

    public static File getConfigFile() {
        File userHome = new File(System.getProperty("user.home"));
        File folder = new File(userHome, CONFIG_FOLDER_NAME);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return new File(folder, CONFIG_FILE_NAME);
    }

    public static Configuration load() throws IOException {
        if (instance == null) {
            Properties defaults = loadDefaults();

            File configurationFile = getConfigFile();
            Properties properties = new Properties(defaults);
            if (configurationFile.exists()) {
                InputStream input = new FileInputStream(configurationFile);
                properties.load(input);
                input.close();
            }
            instance = new Configuration(properties);
        }
        return instance;
    }

    public static Configuration get() {
        if (instance == null) {
            try {
                return load();
            } catch (IOException e) {
                System.out.println("Error while loading configuration file: " + e.toString());
            }
        }
        return instance;
    }

    private static Properties loadDefaults() throws IOException {
        Properties defaults = new Properties();
        InputStream inputStream = Configuration.class.getResourceAsStream(DEFAULT_VALUES_PATH);
        if (inputStream != null) {
            defaults.load(inputStream);
        }
        inputStream.close();
        return defaults;
    }

    public static void save(Configuration configuration) throws IOException {
        FileOutputStream out = new FileOutputStream(getConfigFile());
        configuration.properties.store(out, "");
        out.close();
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
