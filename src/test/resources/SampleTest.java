package Tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class SampleTest {
    private static String authToken;
    @BeforeAll
    public static setup() {
        //Tests here
    }

    @Test
    public static LoginWithCorrectCredentials() {
        authToken = loginAndGetAccessToken();
    }
}