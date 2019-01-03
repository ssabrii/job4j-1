package ru.job4j.tracker;

import ru.job4j.start.Input;

import java.util.Arrays;

/**
 * ShowItem.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class ShowItems implements UserAction {
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
    public ShowItems(int keyholder, String menu) {
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
        return 1;
    }

    /**
     * Override method shows all Items.
     *
     * @param input   объект типа Input.
     * @param tracker объект типа Tracker.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println(Arrays.toString(tracker.findAll()));
    }

    /**
     * Override method shows element menu.
     *
     * @return string element menu.
     */
    @Override
    public final String info() {
        return "1.Show all items";
    }
}
