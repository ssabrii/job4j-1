package ru.job4j.example;

/**
 * LoadOrder.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class LoadOrder {
    /**
     * конечное статическое поле.
     */
    private static final String STATIC_FINAL_FIELD = echo("static final field");
    /**
     * статическое поле.
     */
    private static String staticfield = echo("static field");


    //private static final LoadOrder INSTANCE = new LoadOrder("static inner field");
    //статический блок.
    static {
        echo("static block");
    }

    /**
     * конечное поле класса.
     */
    private final String name = echo("final field");
    /**
     * поле класса.
     */
    private String surname = echo("field");

    //не статической блок класса.
    {
        echo("not-static block");
    }

    /**
     * Конструктор.
     *
     * @param msg текст.
     */
    public LoadOrder(final String msg) {
        echo("constructor " + msg);
    }

    /**
     * Метод выводить текст.
     *
     * @param text text.
     * @return text.
     */
    public static String echo(final String text) {
        System.out.println(text);
        return text;
    }

    /**
     * Start program.
     *
     * @param args string.
     */
    public static void main(final String[] args) {
     //   LoadOrder order = new LoadOrder("main");
    }

    /**
     * inner static final class Holder.
     *
     * @author Maxim Vanny.
     * @version 2.0
     * @since 0.1
     */


    private static final class Holder {
        /**
         * Create object LoadOrder.
         */
        private static final LoadOrder INSTANCE = new LoadOrder("static inner field");
    }
}
