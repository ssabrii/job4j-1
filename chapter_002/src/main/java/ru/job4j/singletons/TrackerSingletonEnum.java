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
    public Item add(Item model) {
        return model;
    }

    /**
     * Start program.
     * @param args string.
     */
    public static void main(String[] args) {
        TrackerSingletonEnum tracker = TrackerSingletonEnum.INSTANCE;
    }
}
