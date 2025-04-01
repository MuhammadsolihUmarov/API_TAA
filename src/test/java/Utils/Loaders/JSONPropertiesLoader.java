package Utils.Loaders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONPropertiesLoader {
    private static JsonNode config;

    static {
        try (InputStream inputStream = JSONPropertiesLoader.class.getClassLoader().getResourceAsStream("endpoints.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Failed to find endpoints.json");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            config = objectMapper.readTree(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load endpoint configuration", e);
        }
    }

    public static String getUrl(String key) {
        return config.path(key).path("url").asText();
    }

    public static String getMethod(String key) {
        return config.path(key).path("method").asText();
    }

    public static Map<String, String> getBody(String key) {
        return getMap(config.path(key).path("body"));
    }

    public static Map<String, String> getParams(String key) {
        return getMap(config.path(key).path("params"));
    }

    public static String getBodyJson(String key) {
        JsonNode bodyNode = config.path(key).path("body");
        return bodyNode.toString();
    }

    private static Map<String, String> getMap(JsonNode node) {
        Map<String, String> map = new HashMap<>();
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            map.put(field.getKey(), field.getValue().asText());
        }
        return map;
    }

}
