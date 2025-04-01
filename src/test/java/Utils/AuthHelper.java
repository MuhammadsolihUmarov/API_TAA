package Utils;

import Utils.Loaders.JSONPropertiesLoader;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static Utils.RequestSenders.sendRequest;

public class AuthHelper {
    public static String accessToken;
    public static String refreshToken;
    public static String method = JSONPropertiesLoader.getMethod("login");
    public static String loginUrl = JSONPropertiesLoader.getUrl("login");
    public static Map<String, String> params = JSONPropertiesLoader.getParams("login");
    public static Map<String, String> headers = new HashMap<>();


    public static String loginAndGetAccessToken() {
        if (accessToken != null) {
            return accessToken;
        }

        Response loginResponse = sendRequest(method, loginUrl, headers, params, null, 200);
        accessToken = loginResponse.path("data.access_token");
        return accessToken;
    }

    public static String loginAndGetRefreshToken() {
        Response loginResponse = sendRequest(JSONPropertiesLoader.getMethod("refreshToken"), JSONPropertiesLoader.getUrl("refreshToken"), headers, null, null,  200);

        refreshToken = loginResponse.path("data.refresh_token");
        return refreshToken;
    }
}
