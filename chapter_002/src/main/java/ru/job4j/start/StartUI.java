package ru.job4j.start;


import ru.job4j.tracker.Input;
import ru.job4j.tracker.MenuTracker;
import ru.job4j.tracker.ITracker;

/**
 * StartUI.
 *
 * @version 2.0
 * @since 0.1
 */
public class StartUI {
    /**
     * Интерфейс для получение данных от пользователя.
     */
    private final Input input;
    /**
     * Функциональное Хранилище заявок.
     */
    private final ITracker tracker;
    /**
     * Флаг выхода.
     */
    private boolean exit;

    /**
     * Конструтор.
     *
     * @param aInput   ввод данных.
     * @param aTracker хранилище заявок.
     */
    public StartUI(final Input aInput, final ITracker aTracker) {
        this.input = aInput;
        this.tracker = aTracker;
    }

    /**
     * метод устанавливающий флаг выхода.
     */
    public final void stop() {
        this.exit = true;
    }

    /**
     * Основой цикл программы.
     */
    public final void init() {
        var menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        int[] range = menu.fillMenu(menu.getActionsSize());
        do {
            menu.show();
            menu.select(input.ask("Введите пункт Carte: ", range));

        } while (!exit);
    }
}

