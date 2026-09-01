package com.orangehrm.utils;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    
    static{
        try{
            properties= new Properties();
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (Exception e) {
           throw new RuntimeException("Failed to load config.properties file: " + e.getMessage());
        }
    }
    public static String getAppUrl() {
        return properties.getProperty("app.url");
    }
    
    public static String getBrowser() {
        return properties.getProperty("browser");
    }
    
    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }
    
    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout"));
    }
    
    public static String getUsername() {
        return properties.getProperty("test.username");
    }
    
    public static String getPassword() {
        return properties.getProperty("test.password");
    }
    
    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }
}
