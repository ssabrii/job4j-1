package ru.job4j.tracker;

/**
 * BaseAction
 *
 * @author Maxim Vannny.
 * @version 2.0
 * @since 0.1
 */
public abstract class BaseAction implements UserAction {
    /**
     * The key of menu.
     */
    private final int key;
    /**
     * The name of menu.
     */
    private final String name;

    /**
     * Constructor.
     *
     * @param key  the of menu.
     * @param name the name of menu.
     */
    protected BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * Method get the key menu.
     *
     * @return the key of menu.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Method get string the key and name of menu.
     *
     * @return string the key and name of menu.
     */
    @Override
    public String info() {
        return String.format("%s.%s", this.key, this.name);
    }
}
