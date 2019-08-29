package carte.tracker;

import carte.models.Item;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * * CarteTest.
 * TrackerTest
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
@Ignore
public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription1", 1L);
        Item item2 = new Item("test2", "testDescription2", 12L);
        tracker.add(item1);
        tracker.add(item2);
        assertThat(tracker.findAll().get(0), is(item1));
        assertThat(tracker.findAll().get(1), is(item2));
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
        assertNull(tracker.findById(first.getId()));
    }

    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1", "testDescription", 1L);
        Item second = new Item("test2", "testDescription2", 12L);
        Item thirst = new Item("test3", "testDescription3", 123L);
        Item fourth = new Item("test3", "testDescription4", 1234L);
        Item fifth = new Item("test3", "testDescription5", 12345L);
        tracker.add(first);
        tracker.add(second);
        tracker.add(thirst);
        tracker.add(fourth);
        tracker.add(fifth);
        List<Item> expected = new ArrayList<>(Arrays.asList(thirst, fourth, fifth));
        List<Item> result = tracker.findByName("test3");
        assertThat(result, is(expected));
    }

    @Test
    public void whenFindAll() {
        Tracker tracker = new Tracker();
        Item one = new Item("test1", "description1", 1L);
        Item second = new Item("test2", "description2", 12L);
        Item third = new Item("test3", "description3", 123L);
        List<Item> expected = new ArrayList<>(Arrays.asList(one, second, third));
        tracker.add(one);
        tracker.add(second);
        tracker.add(third);
        List<Item> result = tracker.findAll();
        assertThat(result, is(expected));
    }
}