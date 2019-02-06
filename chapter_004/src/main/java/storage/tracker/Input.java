package storage.tracker;

import storage.exception.MenuOutException;

import java.util.stream.IntStream;

/**
 * * Storage.
 * Input.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
@SuppressWarnings("Duplicates")
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
     * используется в двух источниках вывода.
     * ConsoleInput and StubInput.
     * перенёс в интерфейс, тк происходило дублирование кода.
     * в классах реализующих интерфейс.
     *
     * @param question запрос для пользователя.
     * @param range    диапзон пунктов мен.
     * @return возвращает введёный пользователем пункт меню.
     */
    default int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        final boolean exist = IntStream
                .range(0, range.length)
                .anyMatch(z -> z == key);
        if (!exist) {
            throw new MenuOutException("Out of range");
        }
        return key;
    }
}
