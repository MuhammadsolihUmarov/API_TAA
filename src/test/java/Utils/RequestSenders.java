package Utils;

import Utils.Loaders.ApplicationPropertiesLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RequestSenders {

    static {
        RestAssured.filters(new AllureRestAssured());
        RestAssured.baseURI = ApplicationPropertiesLoader.getBaseUrl();
    }

    public static Response sendRequest(String method, String endpoint, Map<String, String> headers, Map<String, String> params, String body, int expectedStatusCode) {
        if (params == null) {
            params = new HashMap<>();
        }

        headers.put("device-id", "");
        System.out.println("YEAH");

        if (body != null)
        {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .headers(headers)
                    .body(body)
                    .log().ifValidationFails()
                    .when()
                    .request(method, endpoint)
                    .then()
                    .log().ifValidationFails()
                    .statusCode(expectedStatusCode)
                    .extract()
                    .response();

            assertNotNull(response);
            assertFalse(response.asString().isEmpty(), "Response body is empty!");

            return response;
        }
        else
        {
            Response response = given()
                    .contentType(ContentType.URLENC)
                    .headers(headers)
                    .formParams(params)
                    .log().ifValidationFails()
                    .when()
                    .request(method, endpoint)
                    .then()
                    .log().ifValidationFails()
                    .statusCode(expectedStatusCode)
                    .extract()
                    .response();

            assertNotNull(response);
            assertFalse(response.asString().isEmpty(), "Response body is empty!");

            return response;
        }
    }
}
