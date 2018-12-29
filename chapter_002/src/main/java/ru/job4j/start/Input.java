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
     * получение ответа на запрос.
     *
     * @param question запрос для пользователя.
     * @return возвращает ответ пользователя на запрос.
     */
    String ask(String question);
}
