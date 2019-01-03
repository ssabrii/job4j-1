package ru.job4j.tracker;

import ru.job4j.models.Item;
import ru.job4j.start.Input;

/**
 * AddItem.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */

public class AddItem implements UserAction {
    /**
     * The hold key.
     */
    private int keyholder;
    /**
     * The string menu on the hold key.
     */
    private String menu;

    /**
     * Constructor.
     *
     * @param keyholder the hold key.
     * @param menu      the string menu on the hold key.
     */
    public AddItem(int keyholder, String menu) {
        this.keyholder = keyholder;
        this.menu = menu;
    }

    /**
     * Override method get the hold key.
     *
     * @return the hold key.
     */
    @Override
    public int key() {
        return 0;
    }

    /**
     * Override method adds new Item.
     *
     * @param input   объект типа Input.
     * @param tracker объект типа Tracker.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("--------- Добавление новой заявки -----------");
        String name = input.ask("Введите имя заявки: ");
        String desc = input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        if (tracker.add(item) != null) {
            System.out.println("Заявка не добавлена. Хранилище полное.");
        } else {
            System.out.println("----- Новая заявка с ID: " + item.getId() + "----");
        }
    }

    /**
     * Override method shows element menu.
     *
     * @return string element menu.
     */
    @Override
    public String info() {
        return "0.Add new Item.";
    }
}