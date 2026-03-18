package com.perfdog.automation.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration class for tests
 * Responsible for loading environment properties before the test suite runs
 */
@Slf4j
public class TestRunner {

    private static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static final Properties PROPERTIES = new Properties();

    @Getter
    private static String baseurl;

    @Getter
    private static String apikey;

    /**
     * Initializes the environment variables
     * This method runs once before the entire test suite execution
     */
    @BeforeSuite
    public void setupEnviroment() {
        loadProperties();
        baseurl = PROPERTIES.getProperty("url.base");
        apikey = PROPERTIES.getProperty("apikey");
    }

    /**
     * Loads the properties file from the specified path into memory.
     */
    private void loadProperties() {
        try {
            FileInputStream inputStream = new FileInputStream(PROPERTIES_FILE);
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
