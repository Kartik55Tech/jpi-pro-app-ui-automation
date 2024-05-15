/*
 * This class contains constant values used across the application. 
   It provides various paths to resources,
 * test data, and app files. The class is marked as final to prevent inheritance, and the constructor is 
 * marked private to ensure that no instances of this class can be created, as it is intended to be used 
 * only for accessing static constants.
 */

package org.nibejpi.app.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

	
    // Project paths
    public static final String RESOURCES_FOLDER_PATH = System.getProperty("user.dir") + "/src/test/resources";
    public static final String MAIN_RESOURCES_FOLDER_PATH = System.getProperty("user.dir").replace("pro-app-automation","nibe-api-automation")+ "/src/main/resources";
    public static final String CONFIG_JSON_PATH = RESOURCES_FOLDER_PATH + "/config/config.json";
    public static final String CONFIG_PROPERTIES_PATH = RESOURCES_FOLDER_PATH + "/config/config.properties";
    public static final String API_CONFIG_PROPERTIES_PATH = MAIN_RESOURCES_FOLDER_PATH + "/config/api-config.properties";
    
    // Test data paths
    public static final String CONS_TEST_DATA = RESOURCES_FOLDER_PATH + "/testData/ConsTestData.xlsx";
    public static final String PRO_TEST_DATA = RESOURCES_FOLDER_PATH + "/testData/ProTestData.xlsx";

    // App paths
    public static final String CONS_ANDROID_APK_PATH = RESOURCES_FOLDER_PATH + "/app/consumerapp-prod-release.apk";
    public static final String PRO_ANDROID_APK_PATH = RESOURCES_FOLDER_PATH + "/app/professionalapp-prod-release.apk";
}
