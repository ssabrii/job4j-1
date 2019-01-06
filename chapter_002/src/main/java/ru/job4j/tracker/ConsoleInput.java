package ru.job4j.tracker;

import ru.job4j.exception.MenuOutException;

import java.util.Scanner;

/**
 * ConsoleInput.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class ConsoleInput implements Input {
    /**
     * получение данных от пользователя с консоли.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * получение ответа на запрос.
     *
     * @param question запрос для пользователя.
     * @return возвращает ответ пользователя на запрос.
     */
    @Override
    public String ask(final String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Получение ключа на запрос.
     *
     * @param question запрос для пользователя.
     * @param range    диапзон пунктов мен.
     * @return пункт меню или исключение.
     */
    @Override
    public int ask(final String question, final int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of range");
        }
    }
}
