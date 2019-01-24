package storage.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.models.Item;
import storage.tracker.Input;
import storage.tracker.StubInput;
import storage.tracker.Tracker;
import storage.tracker.ValidateInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * * StorageTest.
 * StartTest.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class StartUITest {
    private final Tracker tracker = new Tracker();
    private Input input;
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private final PrintStream out = System.out;
    private final Consumer<String> output = new Consumer<String>() {
        //установил вывод в buffer-bos.
        PrintStream stdout = new PrintStream(bos);

        @Override
        // я добавил третий параметр в конструкторы StartUI и MenuTracker
        // это сылка на интерфейс потока Вывода.
        // добавил сылку на этот интерфейс в метод start в этом файле.
        // в MenuTracker, ShowItems, execute() добавил output.accept()
        // изменений в коде больше не делал.
        // **************************
        // accept(String s) именно в тестах. в этом файле.
        // не пойму где и как этот метод проявляется?
        // что такое 'String s'(в методе accept) и откуда берутся значения для неё?
        // я этот метод в коде не вижу. как его использовать?
        // все как то запутано, а лекция не обьсняет.
        // мы использовали раньше буффер. он заменял нам вывод в консоль.
        // а теперь мы поменяли  буффер опять на вывод в консоль?
        public void accept(final String s) {
            //откуда 's' берёт реальные данные?
            // не пойму как до кучи всё работает в тестах
            stdout.println(s);
        }
    };
    private final String ls = System.lineSeparator();

    /**
     * метод запуска программы.
     */
    public void start() {
        new StartUI(input, tracker, output).init();
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

    /**
     * метод заполняет хранилище перед тестом.
     */
    @Before
    public void addItemInStorage() {
        tracker.add(new Item("test1", "desc1"));
        tracker.add(new Item("test2", "desc2"));
        tracker.add(new Item("test3", "desc3"));
        tracker.add(new Item("test3", "desc4"));
        System.setOut(new PrintStream(bos));
    }

    /**
     * метод очищает хранилище после теста.
     */
    @After
    public void cleanItems() {
        Collections.fill(tracker.findAll(), null);
        System.setOut(new PrintStream(out));
    }

    /**
     * метод тестирует отображение всех заявок из хранилища.
     */
    @Test
    public void whenShowsAllItemsInStorageAndPrint() {
        input = new ValidateInput(new StubInput(new String[]{"1", "6"}));
        start();
        String expected = new StringBuilder()
                .append("Name: ")
                .append(tracker.findAll().get(0).getName())
                .append("| Desc: ")
                .append(tracker.findAll().get(0).getDescription())
                .append("| Id: ")
                .append(tracker.findAll().get(0).getId())
                .append(ls)
                .append("Name: ")
                .append(tracker.findAll().get(1).getName())
                .append("| Desc: ")
                .append(tracker.findAll().get(1).getDescription())
                .append("| Id: ")
                .append(tracker.findAll().get(1).getId())
                .append(ls)
                .append("Name: ")
                .append(tracker.findAll().get(2).getName())
                .append("| Desc: ")
                .append(tracker.findAll().get(2).getDescription())
                .append("| Id: ")
                .append(tracker.findAll().get(2).getId())
                .append(ls)
                .append("Name: ")
                .append(tracker.findAll().get(3).getName())
                .append("| Desc: ")
                .append(tracker.findAll().get(3).getDescription())
                .append("| Id: ")
                .append(tracker.findAll().get(3).getId())
                .append(ls)
                .toString();

        //ничего не пойму . output это ссылка на интерфейс.
        //она ничего не возвращает. как она может быть result в assertThat???
        // делаю по лекции но ничего не выходит.
        // не понятно как получить данные с консоли?
        assertThat(this.output.toString(), is(expected));
    }

    /**
     * метод тестирует положительное добавление заявки в хранилище.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameNameOK() {
        input = new ValidateInput(new StubInput(new String[]{"0", "test5", "desc", "6"}));
        start();
        assertThat(tracker.findAll().get(4).getName(), is("test5"));
    }

    /**
     * метод тестирует отрицательное добавление заявки в хранилище.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameNameFall() {
        for (int i = 0; i < 100; i++) {
            tracker.add(new Item("testX", "descX"));
        }
        Item item = new Item("test5", "desc");
        item.setId("0123456789");
        String name = item.getName();
        String description = item.getDescription();
        input = new ValidateInput(new StubInput(new String[]{"0", name, description, "6"}));
        start();
        assertNull(tracker.findById(item.getId()));
    }

    /**
     * метод тестирует отображение
     * положительного добавления заявки в хранилище.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameNamePrintOK() {
        input = new ValidateInput(new StubInput(new String[]{"0", "test5", "desc", "6"}));
        start();
        assertThat(new String(bos.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("--------- Добавление новой заявки -----------")
                        .append(ls)
                        .append("----- Новая заявка ID: ")
                        .append(tracker.findAll().get(4).getId())
                        .append(ls)
                        .append(showCarte())
                        .toString()
                )
        );
    }

    /**
     * метод тестирует отображение положительного
     * добавления заявки в хранилище.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameNameAndPrintOK() {
        input = new ValidateInput(new StubInput(new String[]{"0", "test5", "desc", "6"}));
        start();
        assertThat(new String(bos.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("--------- Добавление новой заявки -----------")
                        .append(ls)
                        .append("----- Новая заявка ID: ")
                        .append(tracker.findAll().get(4).getId())
                        .append(ls)
                        .append(showCarte())
                        .toString()
                )
        );
    }

    /**
     * * метод тестирует положительное обновление заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValueOK() {
        System.out.println(tracker.findAll());
        String id = tracker.findAll().get(0).getId();
        input = new ValidateInput(new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "6"}));
        start();
        String expected = tracker.findById(id).getName();
        assertThat(expected, is("test replace"));
    }

    /**
     * * метод тестирует отрицательное добавление заявки.
     */

    @Test
    public void whenUpdateThenTrackerHasUpdatedValueAndFall() {
        String id = "12345y7890";
        input = new ValidateInput(new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "6"}));
        start();
        assertNull(tracker.findById(id));
    }

    /**
     * * метод тестирует отображение отрицательного добавления заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValueAndPrintFall() {
        String id = "12345y790";
        input = new ValidateInput(new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "6"}));
        start();
        assertThat(new String(bos.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка ID: ")
                        .append(id)
                        .append(" не обновлена.")
                        .append(ls)
                        .append(showCarte())
                        .toString()
                )
        );
    }

    /**
     * * метод тестирует отображение положительного добавления заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValueAndPrintOK() {
        String id = tracker.findAll().get(0).getId();
        input = new ValidateInput(new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "6"}));
        start();
        assertThat(new String(bos.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка ID: ")
                        .append(id)
                        .append(" обновлена.")
                        .append(ls)
                        .append(showCarte())
                        .toString()
                )
        );
    }

    /**
     * * метод тестирует положительный поиск заявки.
     */
    @Test
    public void whenFindByID() {
        String id = tracker.findAll().get(0).getId();
        input = new ValidateInput(new StubInput(new String[]{"4", id, "6"}));
        start();
        assertThat(tracker.findAll().get(0).getName(), is("test1"));
    }

    /**
     * * метод тестирует отрицательный поиск заявки.
     */
    @Test
    public void whenFindByIDFall() {
        String id = "1234567890";
        input = new ValidateInput(new StubInput(new String[]{"4", id, "6"}));
        start();
        assertNull(tracker.findById(id));
    }

    /**
     * * метод тестирует отображение положительного поиска заявки.
     */
    @Test
    public void whenFindByIDAndPrintOK() {
        String id = tracker.findAll().get(0).getId();
        input = new ValidateInput(new StubInput(new String[]{"4", id, "6"}));
        start();
        assertThat(new String(bos.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append(tracker.findAll().get(0))
                        .append(ls)
                        .append(showCarte())
                        .toString()
                )
        );
    }

    /**
     * метод тестирует отображение отрицательного поиска заявки.
     */
    @Test
    public void whenFindByIDAndPrintFall() {
        String id = "1234567890";
        input = new ValidateInput(new StubInput(new String[]{"4", id, "6"}));
        start();
        assertThat(new String(bos.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка не обнаружена. Уточните ID")
                        .append(ls)
                        .append(showCarte())
                        .toString()
                )
        );
    }

    /**
     * метод тестирует положительный поиск по названию заявки.
     */
    @Test
    public void whenFindByName() {
        Item item1 = tracker.findAll().get(2);
        Item item2 = tracker.findAll().get(3);
        String name = item2.getName();
        List<Item> expected = new ArrayList<>(Arrays.asList(item1, item2));
        input = new ValidateInput(new StubInput(new String[]{"5", name, "6"}));
        start();
        assertThat(tracker.findByName(name), is(expected));
    }

    /**
     * метод тестирует отрицательный поиск по названию заявки.
     */
    @Test
    public void whenFindByNameFall() {
        String name = "test6";
        input = new ValidateInput(new StubInput(new String[]{"5", name, "6"}));
        List<Item> expected = new ArrayList<>();
        start();
        assertThat(tracker.findByName(name), is(expected));
    }

    /**
     * метод тестирует положительный поиск по названию заявки.
     */
    @Test
    public void whenFindByNameAndPrintOK() {
        String name = "test3";
        input = new ValidateInput(new StubInput(new String[]{"5", name, "6"}));
        start();
        assertThat(new String(bos.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("[")
                        .append(tracker.findAll().get(2))
                        .append(", ")
                        .append(tracker.findAll().get(3))
                        .append("]")
                        .append(ls)
                        .append(showCarte())
                        .toString()
                )
        );
    }

    /**
     * метод тестирует отрицательный поиск по названию заявки.
     */
    @Test
    public void whenFindByNameAndPrintFall() {
        String name = "test6";
        input = new ValidateInput(new StubInput(new String[]{"5", name, "6"}));
        start();
        assertThat(new String(bos.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка не обнаружена. Уточните название.")
                        .append(ls)
                        .append(showCarte())
                        .toString()
                )
        );
    }

    /**
     * метод тестирует положительное удаление по id заявки.
     */
    @Test
    public void whenDeleteItemFromItemsOK() {
        String id = tracker.findAll().get(0).getId();
        input = new ValidateInput(new StubInput(new String[]{"3", id, "6"}));
        start();
        assertThat(tracker.findAll().get(0).getName(), is("test2"));
        assertThat(tracker.findAll().size(), is(3));
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
        assertThat(tracker.findAll().size(), is(4));
    }

    /**
     * метод тестирует отображение положительного удаления по id заявки.
     */
    @Test
    public void whenDeleteItemFromItemsAndPrintOK() {
        String id = tracker.findAll().get(0).getId();
        input = new ValidateInput(new StubInput(new String[]{"3", id, "6"}));
        start();
        assertThat(new String(bos.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка ")
                        .append(id)
                        .append(" удалена.")
                        .append(ls)
                        .append(showCarte())
                        .toString()
                )
        );
    }

    /**
     * метод тестирует отображение отрицательного удаления по id заявки.
     */
    @Test
    public void whenDeleteItemFromItemsAndPrintFall() {
        String id = "1234567890";
        input = new ValidateInput(new StubInput(new String[]{"3", id, "6"}));
        start();
        assertThat(new String(bos.toByteArray()),
                is(new StringBuilder()
                        .append(showCarte())
                        .append("Заявка не удалена. Уточните ID заявки.")
                        .append(ls)
                        .append(showCarte())
                        .toString()
                )
        );
    }
}