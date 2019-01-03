package ru.job4j.tracker;

import ru.job4j.models.Item;
import ru.job4j.start.Input;

/**
 * UpdateItem.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class UpdateItem implements UserAction {
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
    public UpdateItem(int keyholder, String menu) {
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
        return 2;
    }

    /**
     * Override method updates Item.
     *
     * @param input   объект типа Input.
     * @param tracker объект типа Tracker.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID обновляемой заявки: ");
        String name = input.ask("Введите имя заявки: ");
        String desc = input.ask("Введите описание заявки: ");
        if (!tracker.replace(id, new Item(name, desc))) {
            System.out.println("Заявка ID: " + id + "  не обновлена.");
        } else {
            System.out.println("Заявка ID: " + id + "  обновлена.");
        }
    }

    /**
     * Override method shows element menu.
     *
     * @return string element menu.
     */
    @Override
    public String info() {
        return "2.Edit item";
    }
}
