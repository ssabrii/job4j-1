package ru.job4j.tracker;

import ru.job4j.start.Input;

/**
 * DeleteItem.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class DeleteItem implements UserAction {
    /**
     * The string menu on the hold key.
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
    public DeleteItem(int keyholder, String menu) {
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
        return 3;
    }

    /**
     * Override method delete Item.
     *
     * @param input   объект типа Input.
     * @param tracker объект типа Tracker.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите ID удаляемой заявки: ");
        if (!tracker.delete(id)) {
            System.out.println("Заявка не удалена. Уточните ID заявки.");
        } else {
            System.out.println("Заявка " + id + " удалена.");
        }
    }

    /**
     * Override method shows element menu.
     *
     * @return string element menu.
     */
    @Override
    public final String info() {
        return "3.Delete item";
    }
}
