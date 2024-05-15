package services;

import io.restassured.response.Response;
import model.FAQitem;
import utils.AuthManager;

import static io.restassured.RestAssured.given;

import api.routes.Routes;

public class FAQ {
    public static FAQitem[] getFaq(String acceptLanguage , String brandName) {
        try {
            String accessToken = AuthManager.getAccessToken(); // Retrieve access token
            
            // Fetch FAQ response from API
            Response response =
                given()
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Accept-Language", acceptLanguage)
                    .queryParam("brand",brandName )
                    .queryParam("tag", "pro")
                .when()
                    .get(Routes.FAQ_ENDPOINT)
                .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response();

            // Print statement indicating successful response generation 
            System.out.println(String.format("FAQ API response for %s Language %s brand generated successfully. Starting validations on app...", acceptLanguage, brandName));

            // Parse response to FAQ items
            FAQitem[] faqItems = response.as(FAQitem[].class);
            return faqItems;
        } catch (Exception e) {
            // Log error and return null or throw custom exception
            System.err.println("Error fetching FAQs: " + e.getMessage());
            return null;
        }
    }
}
