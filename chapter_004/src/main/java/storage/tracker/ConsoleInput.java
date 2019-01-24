package storage.tracker;

import java.util.Scanner;

/**
 * * Storage.
 * ConsoleInput.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class ConsoleInput implements Input {
    /**
     * получение данных от пользователя с консоли.
     */
    private final Scanner scanner = new Scanner(System.in);

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
