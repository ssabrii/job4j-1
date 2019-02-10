package ru.job4j.singletons;

import ru.job4j.models.Item;

/**
 * TrackerSingletonEnum.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public enum TrackerSingletonEnum {
    /**
     * field-constant.
     */
    INSTANCE;

    /**
     * Example method.
     * @param model model.
     * @return model.
     */
    @SuppressWarnings("unused")
    public Item add(final Item model) {
        return model;
    }

    /**
     * Start program.
     * @param args string.
     */
    public static void main(final String[] args) {
        var tracker = TrackerSingletonEnum.INSTANCE;
    }
}
