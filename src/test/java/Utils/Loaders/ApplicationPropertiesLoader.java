package Utils.Loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationPropertiesLoader {
    private static Properties prop = new Properties();

    static {
        try (InputStream input = ApplicationPropertiesLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return prop.getProperty("URL");
    }

}
