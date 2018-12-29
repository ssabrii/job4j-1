package ru.job4j.start;

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
     * получение полей заявки по запросу.
     *
     * @param question запрос для пользователя.
     * @return возвращает последнее введённое поле заявки по запросу.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
