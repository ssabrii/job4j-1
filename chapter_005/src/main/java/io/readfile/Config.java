package io.readfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Config.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 0.1
 */
public class Config {
    /**
     * field path.
     */
    private final String path;
    /**
     * field values.
     */
    private final Map<String, String> values = new HashMap<>();

    /**
     * Constructor.
     *
     * @param aPath path
     */
    public Config(final String aPath) {
        this.path = aPath;
    }

    /**
     * Method load.
     */
    public final void load() {
        this.values.clear();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(this.path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().startsWith("##")) {
                    if (!line.equals("")) {
                        String[] target = line.split("=");
                        this.values.put(target[0], target[1]);
                    }
                } else {
                    this.values.put(line, "");
                }
            }
        } catch (Exception e) {
            System.err.println("(" + this.path + ")" + " Path not found...");
        }
    }

    /**
     * Method value.
     *
     * @param key key
     * @return value
     */
    public final String value(final String key) {
        if (this.values.containsKey(key)) {
            return this.values.get(key);
        } else {
            throw new UnsupportedOperationException("Missing key...");
        }
    }

    @Override
    public final String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(
                new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * Method main.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        System.out.println(new Config("config/app.properties"));
    }
}
