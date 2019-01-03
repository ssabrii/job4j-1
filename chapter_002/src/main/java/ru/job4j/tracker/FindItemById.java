package ru.job4j.tracker;

import ru.job4j.models.Item;
import ru.job4j.start.Input;

/**
 * FindItemById.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class FindItemById implements UserAction {
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
    public FindItemById(int keyholder, String menu) {
        this.keyholder = keyholder;
        this.menu = menu;
    }

    /**
     * Override method get the hold key.
     *
     * @return the hold key.
     */

    @Override
    public final int key() {
        return 4;
    }

    /**
     * Override method finds Item by ID.
     *
     * @param input   объект типа Input.
     * @param tracker объект типа Tracker.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Поиск, введите ID заявки: ");
        Item byId = tracker.findById(id);
        if (byId == null) {
            System.out.println("Заявка не обнаружена. Уточните ID");
        } else {
            System.out.println(byId);
        }
    }

    /**
     * Override method shows element menu.
     *
     * @return string element menu.
     */
    @Override
    public final String info() {

        return "4.Find item by Id";
    }
}
