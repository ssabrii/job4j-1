package io.tabooword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Analyze.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 4/6/2019
 */

public class Analyze {
    /**
     * Method unavailable.
     *
     * @param source source file
     * @param goal goal file
     */
    public final void unavailable(final String source, final String goal) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(goal))) {
            var line = "";
            var check = true;
            String[] result;
            while ((line = reader.readLine()) != null) {
                if (check && (line.startsWith("400")
                        || line.startsWith("500"))) {
                    result = line.split(" ");
                    writer.write(result[1].trim() + ";");
                    check = false;
                } else if (!check && (line.startsWith("200"))) {
                    result = line.split(" ");
                    writer.write(result[1].trim() + "\n");
                    check = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Missing file...");
        } catch (IOException e) {
            System.err.println("Mistake in-out");
        }
    }
}
