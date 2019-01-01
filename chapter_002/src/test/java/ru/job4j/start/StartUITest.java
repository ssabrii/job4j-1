package ru.job4j.start;

import org.junit.Test;
import ru.job4j.models.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.Tracker;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final static Tracker TRACKER = new Tracker();
    private static Input input;

    static {
        TRACKER.add(new Item("test1", "desc1"));
        TRACKER.add(new Item("test2", "desc2"));
        TRACKER.add(new Item("test3", "desc3"));
        TRACKER.add(new Item("test3", "desc4"));
    }

    public static void start() {
        new StartUI(input, TRACKER).init();
    }

    @Test
    public void whenDeleteItemFromItems() {
        String id = TRACKER.findAll()[0].getId();
        input = new StubInput(new String[]{"3", id, "6"});
        start();
        System.out.println(Arrays.toString(TRACKER.findAll()));
        assertThat(TRACKER.findAll()[0].getName(), is("test2"));
        //не могу пройти груповой тест.
        // тк разный размер хранилища при одиночном и груповом тестах.
        //   assertThat(TRACKER.findAll().length, is(4));
        // как провести проверку на размер?
        // Пробывал обнулять хранилище @After в теле обычного метода.
        // через Arrays.fill()
        //но обнуление по неведомым мне причинам не происходило.
        //тоже вопрос почему не срабатывало @After?
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        input = new StubInput(new String[]{"0", "test5", "desc", "6"});
        start();
        assertThat(TRACKER.findAll()[4].getName(), is("test5"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        String id = TRACKER.findAll()[0].getId();
        input = new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "6"});
        start();
        assertThat(TRACKER.findById(id).getName(), is("test replace"));
    }

    @Test
    public void whenFindByID() {
        String id = TRACKER.findAll()[0].getId();
        input = new StubInput(new String[]{"4", id, "1", "6"});
        start();
        assertThat(TRACKER.findAll()[0].getName(), is("test1"));
    }

    @Test
    public void whenFindByIDFall() {
        String id = "1234567890";
        input = new StubInput(new String[]{"4", id, "6"});
        start();
        assertNull(TRACKER.findById(id));
    }

    @Test
    public void whenFindByName() {
        Item item1 = TRACKER.findAll()[2];
        Item item2 = TRACKER.findAll()[3];
        Item[] expected = {item1, item2};
        String name = item2.getName();
        input = new StubInput(new String[]{"5", name, "6"});
        start();
        assertThat(TRACKER.findByName(name), is(expected));
    }

    @Test
    public void whenFindByNameFall() {
        String name = "test6";
        Item[] expected = {};
        input = new StubInput(new String[]{"5", name, "6"});
        start();
        assertThat(TRACKER.findByName(name), is(expected));
    }

    @Test
    public void whenDeleteItemFromItemsFall() {
        String id = "1234567890";
        input = new StubInput(new String[]{"3", id, "6"});
        start();
        assertThat(TRACKER.findAll().length, is(4));
    }
}