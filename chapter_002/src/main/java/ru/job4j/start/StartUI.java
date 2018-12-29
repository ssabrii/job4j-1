package ru.job4j.start;

import ru.job4j.models.Item;
import ru.job4j.tracker.Tracker;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput();
        String name = input.ask("Please, enter item's name:");
        Tracker tracker = new Tracker();
        tracker.add(new Item(name, "description1"));

        for (Item item : tracker.findAll()) {
            System.out.println(item.getName());
        }
    }

}
