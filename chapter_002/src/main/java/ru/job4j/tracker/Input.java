package ru.job4j.tracker;

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
    /**
     * получение ответа на запрос.
     *
     * @param question запрос для пользователя.
     * @param range диапзон пунктов мен.
     * @return возвращает введёный пользователем пункт меню.
     */
    int ask(String question, int[] range);
}
