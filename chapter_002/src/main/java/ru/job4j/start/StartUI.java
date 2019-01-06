package ru.job4j.start;

import ru.job4j.tracker.*;

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
    private final Tracker tracker;
    /**
     * Флаг выхода.
     */
    private boolean exit;

    /**
     * Конструтор.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
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
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        int[] range = menu.fillMenu(menu.getActionsSize());
        do {
            menu.show();
            menu.select(input.ask("Введите пункт Carte: ", range));

        } while (!exit);
    }
}