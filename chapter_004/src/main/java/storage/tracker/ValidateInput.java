package storage.tracker;

import storage.exception.MenuOutException;

/**
 * ValidateConsoleInput.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class ValidateInput implements Input {
    /**
     * Интерфейс ввода.
     */
    private final Input input;

    /**
     * Конструктор.
     *
     * @param input интерфейс ввода.
     */
    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Запрос на действия пользователя.
     *
     * @param question запрос для пользователя.
     * @return ответ пользователя.
     */
    @Override
    public String ask(String question) {
        return input.ask(question);
    }

    /**
     * Method gets clean answer from users.
     *
     * @param question запрос для пользователя.
     * @param range    диапазон пунктов меню.
     * @return key menu.
     */
    @Override
    public int ask(final String question, final int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Введите корректные данные.");
            } catch (MenuOutException moe) {
                System.out.println("Выберите корректный пункт меню.");
            }
        } while (invalid);
        return value;
    }
}
