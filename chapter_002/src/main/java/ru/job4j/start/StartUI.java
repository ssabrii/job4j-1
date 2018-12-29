package ru.job4j.start;

import ru.job4j.models.Item;
import ru.job4j.tracker.Tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }

    public static void main(String[] args) {
        new StartUI(
                new StubInput(new String[]{"create stub Item"})
        ).init();
    }

    public void init() {
        String name = input.ask("Please, enter item's name:");
        Tracker tracker = new Tracker();
        tracker.add(new Item(name, "description1"));

        for (Item item : tracker.findAll()) {
            System.out.println(item.getName());
        }
    }
}
