package ru.job4j.start;

import ru.job4j.models.Item;
import ru.job4j.tracker.Tracker;

/**
 * StartUI.
 *
 * @version 2.0
 * @since 0.1
 */
public class StartUI {
    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }

    public static void main(String[] args) {
        //  Input input = new StubInput(new String[]{"create stub Item"});
        Input input = new ConsoleInput();
        new StartUI(input).init();
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
