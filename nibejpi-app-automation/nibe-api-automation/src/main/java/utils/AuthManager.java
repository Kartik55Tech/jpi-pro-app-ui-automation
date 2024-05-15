package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import api.routes.Routes;

public class AuthManager {
    private static String accessToken;

    @BeforeClass
    public static void generateAccessToken() {
        try {
            // Base URL for the token endpoint
            RestAssured.baseURI = Routes.TOKEN_ENDPOINT;

            // Parameters for token request
            String clientId = ConfigurationManager.getClientId();
            String username = ConfigurationManager.getUsername();
            String password = ConfigurationManager.getPassword();
            String scope = ConfigurationManager.getScope();

            // Sending token request
            Response response = RestAssured.given()
                    .formParam("grant_type", "password")
                    .formParam("client_id", clientId)
                    .formParam("username", username)
                    .formParam("password", password)
                    .formParam("scope", scope)
                    .post();

            // Check if response is successful
            if (response.getStatusCode() != 200) {
                throw new RuntimeException("Error generating access token. Status code: " + response.getStatusCode());
            }

            // Extracting access token from response
            accessToken = response.jsonPath().getString("access_token");

            // Print response for debugging
            System.out.println("API Response code : " + response.getStatusCode());
            System.out.println("Token generated : " + response.getBody().asString());
        } catch (Exception e) {
            // Handle exception: Log error or throw exception
            e.printStackTrace();
            throw new RuntimeException("Error generating access token: " + e.getMessage());
        }
    }

    public static String getAccessToken() {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new RuntimeException("Access token is null or empty. Authentication failed.");
        }
        return accessToken;
    }
}
