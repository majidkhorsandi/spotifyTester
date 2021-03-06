package Helpers;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Properties;

/**
 * Created by majid on 26/10/15.
 */
public class PropertiesHandler {

    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final String RESOURCE_PATH_PROPERTY_NAME = "resource_path";
    private static final String EMPTY_STRING = "";

    private static Properties props;
    private static InputStream inputProperty = null;

    private PropertiesHandler() {}

    /**
     * Finds a property values in the specified config file given a property key
     * @param propertyKey
     * @return property value
     */
    public static String getPropertyValue(String propertyKey) {
        try {
            loadPropertyFile();
            if (props.getProperty(propertyKey) != null) {
                return props.getProperty(propertyKey);
            } else {
                throw new InvalidParameterException("Invalid property key");
            }
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
            return EMPTY_STRING;
        }
        finally {
            closePropertyStream();
        }
    }

    private static Properties loadPropertyFile() throws IOException {
        inputProperty = PropertiesHandler.class.getClassLoader().getSystemResourceAsStream(CONFIG_FILE_NAME);
        if (inputProperty == null) {
            throw new InvalidParameterException("Can not find the configuratoin file");
        }
        props = new Properties();
        props.load(inputProperty);
        return props;
    }

    private static void closePropertyStream () {
        if (inputProperty != null) {
            try {
                inputProperty.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the full path to a pattern file given the pattern file name
     * @param fileName
     * @return Full path to pattern file
     */
    public static String getPatternFile (String fileName) {
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(getPropertyValue(RESOURCE_PATH_PROPERTY_NAME));
        pathBuilder.append(fileName);
        return pathBuilder.toString();
    }

}