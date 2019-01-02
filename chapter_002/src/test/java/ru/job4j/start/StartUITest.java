package ru.job4j.start;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final Tracker tracker = new Tracker();
    private Input input;

    public void start() {
        new StartUI(input, tracker).init();
    }

    @Before
    public void addItemInStorage() {
        tracker.add(new Item("test1", "desc1"));
        tracker.add(new Item("test2", "desc2"));
        tracker.add(new Item("test3", "desc3"));
        tracker.add(new Item("test3", "desc4"));
    }

    @After
    public void cleanItems() {
        Item[] items = tracker.findAll();
        for (Item item : items) {
            tracker.delete(item.getId());
        }
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        input = new StubInput(new String[]{"0", "test5", "desc", "6"});
        start();
        assertThat(tracker.findAll()[4].getName(), is("test5"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        String id = tracker.findAll()[0].getId();
        input = new StubInput(new String[]{"2", id, "test replace", "заменили заявку", "6"});
        start();
        assertThat(tracker.findById(id).getName(), is("test replace"));
    }

    @Test
    public void whenFindByID() {
        String id = tracker.findAll()[0].getId();
        input = new StubInput(new String[]{"4", id, "1", "6"});
        start();
        assertThat(tracker.findAll()[0].getName(), is("test1"));
    }

    @Test
    public void whenFindByIDFall() {
        String id = "1234567890";
        input = new StubInput(new String[]{"4", id, "6"});
        start();
        assertNull(tracker.findById(id));
    }

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

    @Test
    public void whenFindByNameFall() {
        String name = "test6";
        input = new StubInput(new String[]{"4", name, "6"});
        Item[] expected = {};
        start();
        assertThat(tracker.findByName(name), is(expected));
    }

    @Test
    public void whenDeleteItemFromItems() {
        String id = tracker.findAll()[0].getId();
        input = new StubInput(new String[]{"3", id, "6"});
        start();
        assertThat(tracker.findAll()[0].getName(), is("test2"));
        assertThat(tracker.findAll().length, is(3));
        assertNull(tracker.findById(id));
    }

    @Test
    public void whenDeleteItemFromItemsFall() {
        String id = "1234567890";
        input = new StubInput(new String[]{"3", id, "6"});
        start();
        assertThat(tracker.findAll().length, is(4));
    }
}