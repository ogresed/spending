package dbConnectWindow;

import java.util.Properties;

public class DataWrapper {
    private Properties properties;
    private String URL;

    public DataWrapper(Properties properties, String string) {
        this.properties = properties;
        this.URL = string;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getURL() {
        return URL;
    }
}
