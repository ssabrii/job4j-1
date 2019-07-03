package json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * JsonElement.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/3/2019
 */
public class JsonElement {
    /**
     * Method create json array.
     *
     * @return json array
     */
    public final JSONArray createJsonArray() {
        return new JSONArray();
    }

    /**
     * Method create json object.
     * @return json object
     */
    public final JSONObject createJsonObject() {
        return new JSONObject();
    }

    /**
     * Method write data to Json file.
     *
     * @param objects  json object
     * @param fileName file name
     */
    public final void writeJsonToFile(final JSONObject objects,
                                      final String fileName) {
        File path = new File(System.getProperty("java.io.tmpdir"));
        try (FileWriter file = new FileWriter(path + "/" + fileName)) {
            file.write(objects.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method read Json file from tmp dir.
     *
     * @param fileName file name
     * @return the data from Json file
     */
    public final JSONObject readJsonFile(final String fileName) {
        File path = new File(
                System.getProperty("java.io.tmpdir") + "/" + fileName);
        try {
            return (JSONObject) new JSONParser().parse(new FileReader(path));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

