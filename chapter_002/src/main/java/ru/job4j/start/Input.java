package ru.job4j.start;
/**
 * Input.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public interface Input {
    /**
     * получение данных от пользователя с консоли по запросу.
     */
    String ask(String question);
}
