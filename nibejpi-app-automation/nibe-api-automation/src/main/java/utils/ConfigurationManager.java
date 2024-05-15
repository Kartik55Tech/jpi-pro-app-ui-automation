package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.nibejpi.app.constant.Constants;

public class ConfigurationManager {
    public static Properties properties;

    // Directly specify the file path here
    //public static final String API_CONFIG_PROPERTIES_PATH = "D:/jpi-nibe-pro-app-ui-automation/nibejpi-app-automation/nibe-api-automation/src/main/resources/config/api-config.properties";

    static {
        try {
            FileInputStream fileInputStream = new FileInputStream(Constants.API_CONFIG_PROPERTIES_PATH);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getClientId() {
        return properties.getProperty("client_id");
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getScope() {
        return properties.getProperty("scope");
    }
}
