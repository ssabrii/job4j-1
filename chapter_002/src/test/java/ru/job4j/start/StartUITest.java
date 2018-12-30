package ru.job4j.start;

import org.junit.Test;
import ru.job4j.models.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenDeleteItemFromItems() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test1", "desc1"));
        Item item2 = tracker.add(new Item("test2", "desc2"));
        Item item3 = tracker.add(new Item("test3", "desc3"));
        String id = item1.getId();
        Input input = new StubInput(new String[]{"3", id, "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test2"));
        assertThat(tracker.findAll().length, is(2));
    }

    @Test
    public void whenDeleteItemFromItemsFall() {
        Tracker tracker = new Tracker();
        String id = "1234567890";
        Input input = new StubInput(new String[]{"0", "test1", "desc1", "0", "test2", "desc2", "0", "test3", "desc3", "3", id, "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(3));
    }

    @Test
    public void whenFindByID() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test1", "desc1"));
        Item item2 = tracker.add(new Item("test2", "desc2"));
        Item item3 = tracker.add(new Item("test3", "desc3"));
        String id = item1.getId();
        Input input = new StubInput(new String[]{"4", id, "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0], is(item1));
    }
    @Test
    public void whenFindByIDFall() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test1", "desc1"));
        Item item2 = tracker.add(new Item("test2", "desc2"));
        Item item3 = tracker.add(new Item("test3", "desc3"));
        String id = "1234567890";
        Input input = new StubInput(new String[]{"4", id, "6"});
        new StartUI(input, tracker).init();
    //    assertThat(tracker.findById(id), is(NullPointerException));
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker();
        Item item11 = tracker.add(new Item("test1", "desc1"));
        Item item22 = tracker.add(new Item("test2", "desc2"));
        Item item33 = tracker.add(new Item("test3", "desc3"));
        Item item34 = tracker.add(new Item("test3", "desc4"));
        String name = item33.getName();
        Input input = new StubInput(new String[]{"4", name, "6"});
        Item[] expected = {item33, item34};
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName(name), is(expected));
    }
    @Test
    public void whenFindByNameFall() {
        Tracker tracker = new Tracker();
        Item item11 = tracker.add(new Item("test1", "desc1"));
        Item item22 = tracker.add(new Item("test2", "desc2"));
        Item item33 = tracker.add(new Item("test3", "desc3"));
        Item item34 = tracker.add(new Item("test3", "desc4"));
        String name = "test5";
        Input input = new StubInput(new String[]{"4", name, "6"});
        Item[] expected = {};
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName(name), is(expected));
    }

}