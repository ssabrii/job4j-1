package db.tracker;

import org.junit.Test;
import ru.job4j.models.Item;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TrackerSQLTest {
    private final TrackerSQL sql = new TrackerSQL();
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();


    private void beforeSetUP() {
        this.sql.init();
        this.sql.add(new Item("1", "ONE", "One"));
        this.sql.add(new Item("2", "TWO", "Two"));
        this.sql.add(new Item("3", "THREE", "Three"));
        this.sql.add(new Item("4", "FOUR", "Four"));
        System.setOut(new PrintStream(this.bos));
    }


    private void afterSetUp() {
        this.sql.delete("1");
        this.sql.delete("2");
        this.sql.delete("3");
        this.sql.delete("4");
        this.sql.close();
        System.setOut(new PrintStream(System.out));
    }

    @Test
    public void checkConnection() {
        assertThat(this.sql.init(), is(true));

    }

    @Test
    public void whenItemAddItem() {
        beforeSetUP();
        this.sql.add(new Item("5", "TEST", "Test"));
        final Item[] result = this.sql.findByName("TEST");
        this.sql.delete("5");
        assertThat(Arrays.toString(result), is(new StringBuilder()
                .append("[")
                .append("\n")
                .append("Заявка: id =\'")
                .append(Objects.requireNonNull(result)[0].getId())
                .append("\', name='TEST', description='Test'")
                .append("]")
                .toString()));
        afterSetUp();
    }

    @Test
    public void whenItemsReplace() {
        beforeSetUP();
        final var result = this.sql.replace("1", new Item("TEST", "Test"));
        final var name = Objects.requireNonNull(this.sql.findById("1")).getName();
        assertThat(name, is("TEST"));
        assertTrue(result);
        afterSetUp();

    }

    @Test
    public void whenItemsFindAll() {
        beforeSetUP();
        final var all = this.sql.findAll();
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
        afterSetUp();
    }

    @Test
    public void whenItemsFindAllEmpty() {
        this.sql.init();
        assertEquals(0, this.sql.findAll().length);
    }

    @Test
    public void whenItemFindByName() {
        beforeSetUP();
        final var all = this.sql.findByName("ONE");
        assertThat(Arrays.toString(all), is(new StringBuilder()
                .append("[")
                .append("\n")
                .append("Заявка: id ='1', name='ONE', description='One'")
                .append("]")
                .toString()));
        afterSetUp();
    }

    @Test
    public void whenItemFindByNameFall() {
        beforeSetUP();
        final Item[] items = this.sql.findByName("SIX");
        assertThat(items.length, is(0));
        afterSetUp();
    }

    @Test
    public void whenItemFindById() {
        beforeSetUP();
        final var item = this.sql.findById("1");
        assertThat(Objects.requireNonNull(item).toString(), is(new StringBuilder()
                .append("\n")
                .append("Заявка: id ='1', name='ONE', description='One'")
                .toString()));
        afterSetUp();
    }

    @Test
    public void whenItemFindByIdFall() {
        beforeSetUP();
        assertNull(this.sql.findById("50"));
        afterSetUp();
    }

    @Test
    public void whenItemDeleteById() {
        beforeSetUP();
        final var id = this.sql.findAll()[3].getId();
        assertNotNull(id);
        assertTrue(this.sql.delete(id));
        assertNull(this.sql.findById(id));
        this.sql.delete("1");
        this.sql.delete("2");
        this.sql.delete("3");
        this.sql.close();
        System.setOut(new PrintStream(System.out));
    }

    @Test
    public void whenItemDeleteByIdFall() {
        beforeSetUP();
        final var id = "50";
        final var result = this.sql.delete(id);
        assertFalse(result);
        assertThat(bos.toString(), is(new StringBuilder()
                .append("ID = ")
                .append(id)
                .append(" not exist.")
                .append(System.lineSeparator())
                .toString()));
        afterSetUp();
    }

}
