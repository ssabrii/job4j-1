package ru.job4j.singletons;


import ru.job4j.models.Item;

/**
 * TrackerSingletonLazy.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public final class TrackerSingletonInner {
    /**
     * Method get static final link to object TSI.
     *
     * @return static link.
     */
    public static TrackerSingletonInner getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Constructor.
     */
    private TrackerSingletonInner() {
    }

    /**
     * Example method.
     *
     * @param model model.
     * @return model.
     */
    @SuppressWarnings("unused")
    public Item add(final Item model) {
        return model;
    }

    /**
     * Inner class Holder.
     * create static final link to object TSI.
     *
     * @author Maxim Vanny.
     * @version 2.0
     * @since 0.1
     */
    private static final class Holder {
        /**
         * static final link to object TSI.
         */
        public static final TrackerSingletonInner INSTANCE =
                new TrackerSingletonInner();
    }

    /**
     * Start program.
     *
     * @param args string.
     */
    public static void main(final String[] args) {
        var tracker = TrackerSingletonInner.getInstance();
    }
}
