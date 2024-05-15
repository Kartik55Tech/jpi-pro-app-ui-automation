package org.nibejpi.app.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.nibejpi.app.constant.Constants;
import org.nibejpi.app.enumeration.ConfigProperties;
import org.nibejpi.app.exception.PropertyFileUsageException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyUtils {

    private static final Properties property = new Properties();

    private static void loadProperties(String filePath) {
        try (var input = new FileInputStream(filePath)) {
            property.load(input);
        } catch (IOException e) {
            throw new PropertyFileUsageException("IOException occurred while loading Property file in the specified path");
        }
    }

    public static String getPropertyValue(ConfigProperties key) {
        String filePath = null;
        switch (key) {
            case CONFIG_PROPERTIES_PATH:
                filePath = Constants.CONFIG_PROPERTIES_PATH;
                break;
            case API_CONFIG_PROPERTIES_PATH:
                filePath = Constants.API_CONFIG_PROPERTIES_PATH;
                break;
            // Add more cases for other property file paths if needed
		default:
			break;
        }

        if (Objects.isNull(filePath)) {
            throw new PropertyFileUsageException("Invalid property file path");
        }

        loadProperties(filePath); // Call loadProperties method before accessing properties

        if (Objects.isNull(property.getProperty(key.name().toLowerCase()))) {
            throw new PropertyFileUsageException("Property name - " + key + " is not found. Please check the property file: " + filePath);
        }
        return property.getProperty(key.name().toLowerCase());
    }
}
