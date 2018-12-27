package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TrackerTest
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 1L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 1L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 12L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemFromItems() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 1L);
        Item second = new Item("test2", "testDescription2", 12L);
        Item thirst = new Item("test3", "testDescription3", 123L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(thirst);
        tracker.delete(first.getId());
        assertThat(tracker.findById(thirst.getId()).getName(), is("test3"));
    }

    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 1L);
        Item second = new Item("test2", "testDescription2", 12L);
        Item thirst = new Item("test3", "testDescription3", 123L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(thirst);
        Item result = tracker.findByName("test2");
        assertThat(result.getName(), is("test2"));
    }

    @Test
    public void whenFindAll() {
        Tracker tracker = new Tracker();
        Item one = new Item("test1", "description1", 1L);
        Item second = new Item("test2", "description2", 12L);
        Item third = new Item("test3", "description3", 123L);
        Item[] expected = {one, second, third};
        tracker.add(one);
        tracker.add(second);
        tracker.add(third);
        Item[] result = tracker.findAll();
        assertThat(result, is(expected));
    }
}