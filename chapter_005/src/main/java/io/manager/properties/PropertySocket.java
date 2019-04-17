package io.manager.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * SocketProperty.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/20/2019
 */
public class PropertySocket implements Properties {
    @Override
    public final String port() {
        String property = getPropertyFromFile("port");
        return Objects.requireNonNullElse(property,
                "Refuse.Missing field port.");
    }

    @Override
    public final String ip() {
        String property = getPropertyFromFile("ip");
        return Objects.requireNonNullElse(property,
                "Refuse.Missing field ip.");
    }

    @Override
    public final String getRootCatalog() {
        return getUserDir() + "chapter_005/src/main/java/io/manager";
    }

    /**
     * Method gets data from property file.
     *
     * @param key key data in file
     * @return property for key
     */
    private String getPropertyFromFile(final String key) {
        final var props = "chapter_005/src/main/java/io"
                + "/manager/properties/app.properties";
        final var file = getUserDir() + props;
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
                        data = line.split("=")[1];
                    }
                }
            } catch (IOException e) {
                data = "Refuse.Mistake in-out property file.";
            }
        }
        return data;
    }

    /**
     * Method gets the path user dir for any OS.
     *
     * @return path
     */
    private String getUserDir() {
        final var sp = File.separator;
        var dir = System.getProperty("user.dir");
        if (!dir.endsWith(sp)) {
            dir += sp;
        }
        if (dir.contains("\\")) {
            dir = dir.replace('\\', '/');
        }
        return dir;
    }
}
