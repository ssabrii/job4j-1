package db.tracker;

import org.junit.Test;
import ru.job4j.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TrackerSQLTest {

    public final Connection init() {
        try (InputStream is = TrackerSQL.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            Properties config = new Properties();
            config.load(Objects.requireNonNull(is));
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenItemAddItem() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            sql.add(new Item("5", "TEST", "Test"));
            final Item[] result = sql.findByName("TEST");
            sql.delete("5");
            assertThat(Arrays.toString(result), is(new StringBuilder()
                    .append("[")
                    .append("\n")
                    .append("Заявка: id =\'")
                    .append(Objects.requireNonNull(result)[0].getId())
                    .append("\', name='TEST', description='Test'")
                    .append("]")
                    .toString()));
        }
    }

    @Test
    public void whenItemsReplace() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            sql.add(new Item("1", "TEST", "Test"));
            final var result = sql.replace("1", new Item("NEW", "New"));
            final var name = Objects.requireNonNull(sql.findById("1")).getName();
            assertThat(name, is("NEW"));
            assertTrue(result);
        }

    }

    @Test
    public void whenItemsFindAll() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            sql.add(new Item("1", "ONE", "One"));
            sql.add(new Item("2", "TWO", "Two"));
            sql.add(new Item("3", "THREE", "Three"));
            sql.add(new Item("4", "FOUR", "Four"));
            final var all = sql.findAll();
            Arrays.sort(all, Comparator.comparing(Item::getId));
            assertThat(Arrays.toString(all), is(new StringBuilder()
                    .append("[")
                    .append("\n")
                    .append("Заявка: id ='1', name='ONE', description='One', ")
                    .append("\n")
                    .append("Заявка: id ='2', name='TWO', description='Two', ")
                    .append("\n")
                    .append("Заявка: id ='3', name='THREE', description='Three', ")
                    .append("\n")
                    .append("Заявка: id ='4', name='FOUR', description='Four'")
                    .append("]")
                    .toString()));
        }
    }

    @Test
    public void whenItemsFindAllEmpty() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            assertEquals(0, sql.findAll().length);
        }
    }

    @Test
    public void whenItemFindByName() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            sql.add(new Item("1", "ONE", "One"));
            sql.add(new Item("2", "ONE", "Test"));
            final var all = sql.findByName("ONE");
            assertThat(Arrays.toString(all), is(new StringBuilder()
                    .append("[")
                    .append("\n")
                    .append("Заявка: id ='1', name='ONE', description='One', ")
                    .append("\n")
                    .append("Заявка: id ='2', name='ONE', description='Test'")
                    .append("]")
                    .toString()));
        }
    }

    @Test
    public void whenItemFindByNameFall() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            sql.add(new Item("1", "ONE", "One"));
            final Item[] items = sql.findByName("SIX");
            assertThat(items.length, is(0));
        }
    }

    @Test
    public void whenItemFindById() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            sql.add(new Item("1", "ONE", "One"));
            final var item = sql.findById("1");
            assertThat(Objects.requireNonNull(item).toString(), is(new StringBuilder()
                    .append("\n")
                    .append("Заявка: id ='1', name='ONE', description='One'")
                    .toString()));
        }
    }

    @Test
    public void whenItemFindByIdFall() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            sql.add(new Item("1", "ONE", "One"));
            assertNull(sql.findById("50"));
        }
    }

    @Test
    public void whenItemDeleteById() throws SQLException {
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            sql.add(new Item("1", "ONE", "One"));
            sql.add(new Item("2", "TWO", "Two"));
            sql.add(new Item("3", "THREE", "Three"));
            sql.add(new Item("4", "FOUR", "Four"));
            final var id = sql.findAll()[0].getId();
            assertNotNull(id);
            assertTrue(sql.delete(id));
            assertNull(sql.findById(id));
        }
    }

    @Test
    public void whenItemDeleteByIdFall() throws SQLException {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        try (TrackerSQL sql = new TrackerSQL(ConnectionRollBack.create(this.init()))) {
            sql.add(new Item("1", "ONE", "One"));
            sql.add(new Item("2", "TWO", "Two"));
            sql.add(new Item("3", "THREE", "Three"));
            sql.add(new Item("4", "FOUR", "Four"));
            final var id = "50";
            final var result = sql.delete(id);
            assertFalse(result);
            assertThat(bos.toString(), is(new StringBuilder()
                    .append("ID = ")
                    .append(id)
                    .append(" not exist.")
                    .append(System.lineSeparator())
                    .toString()));
            System.setOut(new PrintStream(System.out));
        }
    }
}
