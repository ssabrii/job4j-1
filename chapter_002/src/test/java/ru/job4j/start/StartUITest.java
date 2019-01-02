package ru.job4j.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * StartTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class StartUITest {
    private final Tracker tracker = new Tracker();
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private Input input;

    /**
     * метод запуска программы.
     */
    public void start() {
        new StartUI(input, tracker).init();
    }

    /**
     * метод отображения меню.
     *
     * @return возвращает отображение меню.
     */
    public String showCarte() {
        StringBuilder all = new StringBuilder()
                .append("-----------------------------------------------")
                .append(System.lineSeparator())
                .append("Carte.")
                .append(System.lineSeparator())
                .append("-----------------------------------------------")
                .append(System.lineSeparator())
                .append("0. Add new Item")
                .append(System.lineSeparator())
                .append("1. Show all items")
                .append(System.lineSeparator())
                .append("2. Edit item")
                .append(System.lineSeparator())
                .append("3. Delete item")
                .append(System.lineSeparator())
                .append("4. Find item by Id")
                .append(System.lineSeparator())
                .append("5. Find items by name")
                .append(System.lineSeparator())
                .append("6. Exit Program")
                .append(System.lineSeparator());
        return all.toString();

    }

    /**
     * метод отображает одиночную заявку из хранилища.
     *
     * @param index индекс заявки в хранилище.
     * @return возращает отображение заявки.
     */
    public String showOneItem(int index) {
        StringBuilder one = new StringBuilder();
        one.append("Заявка: id '");
        one.append(tracker.findAll()[index].getId());
        one.append("', name='");
        one.append(tracker.findAll()[index].getName());
        one.append("', description='");
        one.append(tracker.findAll()[index].getDescription());
        one.append("'");
        return one.toString();
    }

    /**
     * метод отображает все заявки в хранилище.
     *
     * @return возвращает отображение всех заявок из хранилища.
     */
    public String showAllItems() {
        Item[] items = tracker.findAll();
        StringBuilder all = new StringBuilder();
        all.append("[\\n");
        for (int i = 0; i < items.length; i++) {
            all.append("Заявка: id '");
            all.append(tracker.findAll()[i].getId());
            all.append("', name='");
            all.append(tracker.findAll()[i].getName());
            all.append("', description='");
            all.append(tracker.findAll()[i].getDescription());
            all.append("'");
            if (i != items.length - 1) {
                all.append(", ");
                all.append("\\n");
            }
            if (i == items.length - 1) {
                all.append("]");
                all.append(System.lineSeparator());
            }
        }
        return all.toString();
    }

    /**
     * метод заполняет хранилище перед тестом.
     */
    @Before
    public void addItemInStorage() {
        tracker.add(new Item("test1", "desc1"));
        tracker.add(new Item("test2", "desc2"));
        tracker.add(new Item("test3", "desc3"));
        tracker.add(new Item("test3", "desc4"));
    }

    /**
     * метод очищает хранилище после теста.
     */
    @After
    public void cleanItems() {
        Item[] items = tracker.findAll();
        for (Item item : items) {
            tracker.delete(item.getId());
        }
    }

    /**
     * метод тестирует отображение всех заявок из хранилища.
     */
    @Test
    public void whenShowsAllItemsInStorageAndPrint() {
        input = new StubInput(new String[]{"1", "6"});
        System.setOut(new PrintStream(out));
        start();
        // тест не проходит . контент одинаковый
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append(showAllItems())
                        .append(showCarte())
                        .toString()
                )
        );
        System.setOut(new PrintStream(stdout));
    }

    /**
     * метод тестирует положительное добавление заявки в хранилище.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameNameOK() {
        input = new StubInput(new String[]{"0", "test5", "desc", "6"});
        start();
        assertThat(tracker.findAll()[4].getName(), is("test5"));
    }


    /**
     * метод тестирует отображение положительное
     * добавления заявки в хранилище.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameNameAndPrintOK() {
        input = new StubInput(new String[]{"0", "test5", "desc", "6"});
        System.setOut(new PrintStream(out));
        start();
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("--------- Добавление новой заявки -----------")
                        .append(System.lineSeparator())
                        .append("----- Новая заявка с ID: ")
                        .append(tracker.findAll()[4].getId())
                        .append("----")
                        .append(System.lineSeparator())
                        .append(showCarte())
                        .toString()
                )
        );
        System.setOut(new PrintStream(stdout));
    }

    /**
     * * метод тестирует положительное обновление заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValueOK() {
        String id = tracker.findAll()[0].getId();
        input = new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "6"});
        start();
        assertThat(tracker.findById(id).getName(), is("test replace"));
    }

    /**
     * * метод тестирует отрицательное добавление заявки.
     */

    @Test
    public void whenUpdateThenTrackerHasUpdatedValueAndFall() {
        String id = "1234567890";
        input = new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "6"});
        start();
        assertNull(tracker.findById(id));
    }

    /**
     * * метод тестирует отображение отрицательного добавления заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValueAndPrintFall() {
        String id = "123456790";
        input = new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "6"});
        System.setOut(new PrintStream(out));
        start();
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка ID: ")
                        .append(id)
                        .append(" не обновлена.")
                        .append(System.lineSeparator())
                        .append(showCarte())
                        .toString()
                )
        );
        System.setOut(new PrintStream(stdout));
    }

    /**
     * * метод тестирует отображение положительного добавления заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValueAndPrintOK() {
        String id = tracker.findAll()[0].getId();
        input = new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "6"});
        System.setOut(new PrintStream(out));
        start();
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка ID: ")
                        .append(id)
                        .append(" обновлена.")
                        .append(System.lineSeparator())
                        .append(showCarte())
                        .toString()
                )
        );
        System.setOut(new PrintStream(stdout));
    }

    /**
     * * метод тестирует положительный поиск заявки.
     */
    @Test
    public void whenFindByID() {
        String id = tracker.findAll()[0].getId();
        input = new StubInput(new String[]{"4", id, "6"});
        start();
        assertThat(tracker.findAll()[0].getName(), is("test1"));
    }

    /**
     * * метод тестирует отрицательный поиск заявки.
     */

    @Test
    public void whenFindByIDFall() {
        String id = "1234567890";
        input = new StubInput(new String[]{"4", id, "6"});
        start();
        assertNull(tracker.findById(id));
    }

    /**
     * * метод тестирует отображение положительного поиска заявки.
     */
    @Test
    public void whenFindByIDAndPrintOK() {
        String id = tracker.findAll()[0].getId();
        input = new StubInput(new String[]{"4", id, "6"});
        System.setOut(new PrintStream(out));
        start();
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        // час убил времени не пойму откуда в оригинале берется пустая строка????
                        // тест не проходит. контент совпадает.
                        .append("\\n")
                        .append(showOneItem(0))
                        .append(System.lineSeparator())
                        .append(showCarte())
                        .toString()
                )
        );
        System.setOut(new PrintStream(stdout));
    }

    /**
     * метод тестирует отображение отрицательного поиска заявки.
     */
    @Test
    public void whenFindByIDAndPrintFall() {
        String id = "1234567890";
        input = new StubInput(new String[]{"4", id, "6"});
        System.setOut(new PrintStream(out));
        start();
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка не обнаружена. Уточните ID")
                        .append(System.lineSeparator())
                        .append(showCarte())
                        .toString()
                )
        );
        System.setOut(new PrintStream(stdout));
    }

    /**
     * метод тестирует положительный поиск по названию заявки.
     */
    @Test
    public void whenFindByName() {
        Item item1 = tracker.findAll()[2];
        Item item2 = tracker.findAll()[3];
        String name = item2.getName();
        Item[] expected = {item1, item2};
        input = new StubInput(new String[]{"5", name, "6"});
        start();
        assertThat(tracker.findByName(name), is(expected));
    }

    /**
     * метод тестирует отрицательный поиск по названию заявки.
     */
    @Test
    public void whenFindByNameFall() {
        String name = "test6";
        input = new StubInput(new String[]{"5", name, "6"});
        Item[] expected = {};
        start();
        assertThat(tracker.findByName(name), is(expected));
    }

    /**
     * метод тестирует положительный поиск по названию заявки.
     */
    @Test
    public void whenFindByNameAndPrintOK() {
        String name = "test3";
        input = new StubInput(new String[]{"5", name, "6"});
        System.setOut(new PrintStream(out));
        start();
        // тест не проходит. контент совпадает.
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("[")
                        .append("\\n")
                        .append(showOneItem(2))
                        .append(", \\n")
                        .append(showOneItem(3))
                        .append("]")
                        .append(System.lineSeparator())
                        .append(showCarte())
                        .toString()
                )
        );
        System.setOut(new PrintStream(stdout));
    }

    /**
     * метод тестирует отрицательный поиск по названию заявки.
     */
    @Test
    public void whenFindByNameAndPrintFall() {
        String name = "test6";
        input = new StubInput(new String[]{"5", name, "6"});
        System.setOut(new PrintStream(out));
        start();
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка не обнаружена. Уточните название.")
                        .append(System.lineSeparator())
                        .append(showCarte())
                        .toString()
                )
        );
        System.setOut(new PrintStream(stdout));
    }

    /**
     * метод тестирует положительное удаление по id заявки.
     */
    @Test
    public void whenDeleteItemFromItemsOK() {
        String id = tracker.findAll()[0].getId();
        input = new StubInput(new String[]{"3", id, "6"});
        start();
        assertThat(tracker.findAll()[0].getName(), is("test2"));
        assertThat(tracker.findAll().length, is(3));
        assertNull(tracker.findById(id));
    }

    /**
     * метод тестирует отрицательное удаление по id заявки.
     */
    @Test
    public void whenDeleteItemFromItemsFall() {
        String id = "1234567890";
        input = new StubInput(new String[]{"3", id, "6"});
        start();
        assertThat(tracker.findAll().length, is(4));
    }

    /**
     * метод тестирует отображение положительного удаления по id заявки.
     */
    @Test
    public void whenDeleteItemFromItemsAndPrintOK() {
        String id = tracker.findAll()[0].getId();
        input = new StubInput(new String[]{"3", id, "6"});
        System.setOut(new PrintStream(out));
        start();
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка ")
                        .append(id)
                        .append(" удалена.")
                        .append(System.lineSeparator())
                        .append(showCarte())
                        .toString()
                )
        );
        System.setOut(new PrintStream(stdout));
    }

    /**
     * метод тестирует отображение отрицательного удаления по id заявки.
     */
    @Test
    public void whenDeleteItemFromItemsAndPrintFall() {
        String id = "1234567890";
        input = new StubInput(new String[]{"3", id, "6"});
        System.setOut(new PrintStream(out));
        start();
        assertThat(new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка не удалена. Уточните ID заявки.")
                        .append(System.lineSeparator())
                        .append(showCarte())
                        .toString()
                )
        );
        System.setOut(new PrintStream(stdout));
    }
}