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
     * получение полей заявки по запросу.
     *
     * @param question запрос для пользователя.
     * @return возвращает последнее введённое поле заявки по запросу.
     */
    String ask(String question);
}
