package storage.tracker;

import storage.exception.MenuOutException;

/**
 * * Storage.
 * ValidateConsoleInput.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
@SuppressWarnings("Duplicates")
public class ValidateInput implements Input {
    /**
     * Интерфейс ввода.
     */
    private final Input input;

    /**
     * Конструктор.
     *
     * @param aInput интерфейс ввода.
     */
    public ValidateInput(final Input aInput) {
        this.input = aInput;
    }

    /**
     * Запрос на действия пользователя.
     *
     * @param question запрос для пользователя.
     * @return ответ пользователя.
     */
    @Override
    public final String ask(final String question) {
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
    public final int ask(final String question, final int[] range) {
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
