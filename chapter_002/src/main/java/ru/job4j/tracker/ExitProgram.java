package ru.job4j.tracker;

import ru.job4j.start.Input;

/**
 * ExitProgram.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class ExitProgram implements UserAction {
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
    public ExitProgram(int keyholder, String menu) {
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
        return 6;
    }

    /**
     * Override method offers to close program.
     *
     * @param input   объект типа Input.
     * @param tracker объект типа Tracker.
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("Для выхода из программы нажмите \"y\"");
    }

    /**
     * Override method shows element menu.
     *
     * @return string element menu.
     */
    @Override
    public String info() {
        return "6.Exit Program.";
    }
}
