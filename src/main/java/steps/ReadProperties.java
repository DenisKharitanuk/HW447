package steps;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private static final Properties properties;


    static {
        properties = new Properties();
        try {
            properties.load(ReadProperties.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String login() {
        return properties.getProperty("login");
    }

    public static String password() {

        return properties.getProperty("password");
    }

    public static String baseUri(){
        return properties.getProperty("base_uri");
    }
    public static String path(){
        return properties.getProperty("path");
    }
}
