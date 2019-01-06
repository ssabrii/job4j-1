package ru.job4j.tracker;

import ru.job4j.exception.MenuOutException;

/**
 * ValidateConsoleInput.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class ValidateConsoleInput extends ConsoleInput {
    /**
     * Method gets clean answer from users.
     *
     * @param question запрос для пользователя.
     * @param range   диапзон пунктов мен.
     * @return key menu.
     */
    @Override
    public int ask(final String question, final int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
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
