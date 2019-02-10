package ru.job4j.tracker;

import java.util.Scanner;

/**
 * ConsoleInput.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
@SuppressWarnings("unused")
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
    public final String ask(final String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
