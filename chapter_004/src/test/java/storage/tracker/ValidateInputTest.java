package storage.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.start.StartUI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * * StorageTest
 * ValidateInputTest.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */

public class ValidateInputTest {
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private final PrintStream out = System.out;
    private final String ls = System.lineSeparator();
    private final Consumer<String> output = new Consumer<>() {
        PrintStream stdout = new PrintStream(bos);

        @Override
        public void accept(final String s) {
            stdout.println(s);
        }

        @Override
        public String toString() {
            return new String(bos.toByteArray());
        }
    };
    private Input input;

    @Before
    public void setBos() {
        System.setOut(new PrintStream(this.bos));
    }

    @After
    public void setOut() {
        System.setOut(this.out);
    }

    /**
     * метод отображения меню.
     *
     * @return возвращает отображение меню.
     */
    public String showCarte() {
        StringBuilder all = new StringBuilder()
                .append("-----------------------------------------------")
                .append(ls)
                .append("Carte.")
                .append(ls)
                .append("-----------------------------------------------")
                .append(ls)
                .append("0.Add new item")
                .append(ls)
                .append("1.Show all items")
                .append(ls)
                .append("2.Edit item")
                .append(ls)
                .append("3.Delete item")
                .append(ls)
                .append("4.Find item by Id")
                .append(ls)
                .append("5.Find items by name")
                .append(ls)
                .append("6.Exit Program")
                .append(ls);
        return all.toString();
    }

    @Test
    public void whenInvalidInputNFE() {
        input = new ValidateInput(new StubInput(new String[]{"no", "6"}));
        new StartUI(input, new Tracker(), output).init();
        assertThat(this.output.toString(), is(new StringBuilder()
                        .append(showCarte())
                        .append("Введите корректные данные.")
                        .append(ls)
                        .toString()
                )
        );
    }

    @Test
    public void whenInvalidInputOUT() {
        input = new ValidateInput(new StubInput(new String[]{"9", "6"}));
        new StartUI(input, new Tracker(), output).init();
        assertThat(this.output.toString(), is(new StringBuilder()
                        .append(showCarte())
                        .append("Выберите корректный пункт меню.")
                        .append(ls)
                        .toString()
                )
        );
    }

    @Test
    public void whenValidInputOUT() {
        input = new ValidateInput(new StubInput(new String[]{"1", "6"}));
        new StartUI(input, new Tracker(), output).init();
        assertThat(this.output.toString(), is(new StringBuilder()
                        .append(showCarte())
                        .append(showCarte())
                        .toString()
                )
        );
    }
}
