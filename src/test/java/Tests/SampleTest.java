package Tests;

import Utils.Loaders.JSONPropertiesLoader;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static Utils.RequestSenders.sendRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SampleTest {
    public static String method = JSONPropertiesLoader.getMethod("login");
    public static String loginUrl = JSONPropertiesLoader.getUrl("login");
    public static Map<String, String> params = JSONPropertiesLoader.getParams("login");
    public static Map<String, String> headers = new HashMap<>();

    @BeforeAll
    static void setup() {
        //Tests here
    }

    @Test
    void loginWithCorrectCredentials() {
        Response authResponse = sendRequest(method, loginUrl, headers, params, null, 200);
        assertNotNull(authResponse, "authToken should not be null");
    }
}
