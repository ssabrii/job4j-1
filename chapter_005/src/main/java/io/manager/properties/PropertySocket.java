package io.manager.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * SocketProperty.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/20/2019
 */
public class PropertySocket implements Properties {
    /**
     * field path property file.
     */
    private final String property;

    /**
     * Constructor.
     *
     * @param path path to property file
     */
    public PropertySocket(final String path) {
        // real path "config/manager.properties"
        final Root root = new Root();
        this.property = root.getUserDir() + path;
    }

    @Override
    public final String port() {
        var answer = getPropertyFromFile("port");
        if (answer.equals("") || answer.equals("abuse parameter.")) {
            answer = "Refuse.Missing field port.";
        }
        return answer;
    }

    @Override
    public final String ip() {
        var answer = getPropertyFromFile("ip");
        if (answer.equals("") || answer.equals("abuse parameter.")) {
            answer = "Refuse.Missing field ip.";
        }
        return answer;
    }

    /**
     * Method gets the the path file of properties.
     *
     * @return the path file of properties
     */
    private String getPropertyCatalog() {
        return this.property;
    }

    /**
     * Method gets data from property file.
     *
     * @param key key data in file
     * @return property for key
     */
    public final String getPropertyFromFile(final String key) {
        final var file = this.getPropertyCatalog();
        String data = "";
        File prop = new File(file);
        if (!prop.exists() || prop.length() == 0) {
            data = "Refuse.Missing property file.";
        } else {
            try (BufferedReader reader = new BufferedReader(
                    new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("=") && line.contains(key)) {
                        if (line.split("=").length >= 2) {
                            data = line.split("=")[1];
                            break;
                        } else {
                            data = "abuse parameter.";
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                data = "Refuse.Mistake in-out property file.";
            }
        }
        return data;
    }
}
