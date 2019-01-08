package ru.job4j.singletons;

import ru.job4j.models.Item;

/**
 * TrackerSingletonLazy.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public final class TackerSingletonLazy {
    /**
     * Create TrackSingletonLazy link.
     */
    private static TackerSingletonLazy instance;

    /**
     * Method get static link to object TSL.
     *
     * @return static link.
     */
    public static TackerSingletonLazy getInstance() {
        if (instance == null) {
            instance = new TackerSingletonLazy();
        }
        return instance;
    }

    /**
     * Constructor.
     */
    private TackerSingletonLazy() {
    }

    /**
     * Example method.
     *
     * @param model model.
     * @return model.
     */
    public Item add(final Item model) {
        return model;
    }

    /**
     * Start program.
     *
     * @param args string.
     */
    public static void main(final String[] args) {
        TackerSingletonLazy tracker = TackerSingletonLazy.getInstance();
    }
}
