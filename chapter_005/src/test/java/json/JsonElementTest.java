package json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class JsonElementTest {
    final JsonElement json = new JsonElement();
    JSONObject object = this.json.createJsonObject();


    @SuppressWarnings("unchecked")
    public void createJsonObjectAndArrayBefore() {
        final JSONObject alex = this.json.createJsonObject();
        alex.put("firstName", "Alex");
        alex.put("lastName", "Alex");
        final JSONObject mick = this.json.createJsonObject();
        mick.put("firstName", "Mick");
        mick.put("lastName", "Mick");
        final JSONObject raw = this.json.createJsonObject();
        raw.put("firstName", "Raw");
        raw.put("lastName", "Raw");
        final JSONArray list = this.json.createJsonArray();
        list.add(alex);
        list.add(mick);
        list.add(raw);
        this.object.put("boss", alex);
        this.object.put("manager", mick);
        this.object.put("worker", raw);
    }

    @SuppressWarnings("unchecked")
    public void createJsonObjectAndArrayBeforeMap() {
        this.object.put("firstName", "Alex");
        this.object.put("lastName", "Alex");
        this.object.put("age", 25);
//****
        Map map = new LinkedHashMap(4);
        map.put("streetAddress", "21 2nd Street");
        map.put("city", "New York");
        map.put("state", "NY");
        map.put("postalCode", 10021);
//****
        this.object.put("address", map);
//****
        JSONArray ja = new JSONArray();
//****
        map = new LinkedHashMap(2);
        map.put("type", "home");
        map.put("number", "212 555-1234");
//****
        ja.add(map);
//****
        map = new LinkedHashMap(2);
        map.put("type", "fax");
        map.put("number", "212 555-1234");
//****
        ja.add(map);
//****
        this.object.put("phoneNumbers", ja);
        this.json.writeJsonToFile(this.object, "test.json");
    }


    @Test
    public void whenCreateJsonArrayToString() {
        this.createJsonObjectAndArrayBefore();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        System.out.println(this.object.toJSONString());
        assertThat(bos.toString(), is(new StringBuilder()
                .append("{\"boss\":{\"firstName\":\"Alex\",\"lastName\":\"Alex\"},")
                .append("\"manager\":{\"firstName\":\"Mick\",\"lastName\":\"Mick\"},")
                .append("\"worker\":{\"firstName\":\"Raw\",\"lastName\":\"Raw\"}}")
                .append(System.lineSeparator())
                .toString()));
        System.setOut(System.out);
    }

    @Test
    public void whenCreateJsonArrayToFile() throws IOException {
        this.createJsonObjectAndArrayBefore();
        final var fileName = "test.json";
        this.json.writeJsonToFile(this.object, fileName);
        final var path = System.getProperty("java.io.tmpdir") + "/" + fileName;
        FileReader reader = new FileReader(path);
        int tmp;
        StringBuilder sb = new StringBuilder();
        while ((tmp = reader.read()) != -1) {
            sb.append((char) tmp);
        }
        assertThat(sb.toString(), is(new StringBuilder()
                .append("{\"boss\":{\"firstName\":\"Alex\",\"lastName\":\"Alex\"},")
                .append("\"manager\":{\"firstName\":\"Mick\",\"lastName\":\"Mick\"},")
                .append("\"worker\":{\"firstName\":\"Raw\",\"lastName\":\"Raw\"}}")
                .toString()));
    }

    @Test
    public void whenCreateJsonArrayToStringInJava7() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        this.createJsonObjectAndArrayBeforeMap();
        this.object = this.json.readJsonFile("test.json");
        final var resultName = Objects.requireNonNull(this.object).get("firstName");
        assertThat(resultName, is("Alex"));
        final var resultLastName = Objects.requireNonNull(this.object).get("lastName");
        assertThat(resultLastName, is("Alex"));
        final var resultAge = Objects.requireNonNull(this.object).get("age");
        assertThat(resultAge, is(25L));
        final var addresses = ((Map) this.object.get("address"));
        StringBuilder address = new StringBuilder();
        for (final Object o : addresses.entrySet()) {
            final Map.Entry pair = (Map.Entry) o;
            address.append(pair.getKey())
                    .append(" : ")
                    .append(pair.getValue())
                    .append(System.lineSeparator());
        }
        StringBuilder number = new StringBuilder();
        final JSONArray numbers = (JSONArray) this.object.get("phoneNumbers");
        for (final Object obj : numbers) {
            for (final Object next : ((Map) obj).entrySet()) {
                Map.Entry pair = (Map.Entry) next;
                number.append(pair.getKey())
                        .append(" : ")
                        .append(pair.getValue())
                        .append(System.lineSeparator());
            }
        }
        System.out.println(address.toString());
        System.out.println(number.toString());
        assertThat(bos.toString(), is(new StringBuilder()
                .append("streetAddress : 21 2nd Street")
                .append(System.lineSeparator())
                .append("city : New York")
                .append(System.lineSeparator())
                .append("postalCode : 10021")
                .append(System.lineSeparator())
                .append("state : NY")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("number : 212 555-1234")
                .append(System.lineSeparator())
                .append("type : home")
                .append(System.lineSeparator())
                .append("number : 212 555-1234")
                .append(System.lineSeparator())
                .append("type : fax")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .toString()));

        System.setOut(System.out);
    }

}
