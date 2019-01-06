package ru.job4j.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.MenuTracker;
import ru.job4j.tracker.Tracker;

/**
 * StartUI.
 *
 * @version 2.0
 * @since 0.1
 */
public class StartUI {
    /**
     * Диапазон номеров пунктов меню.
     */
    private final int[] range = {0, 1, 2, 3, 4, 5, 6};

    /**
     * Интерфейс для получение данных от пользователя.
     */
    private final Input input;
    /**
     * Функциональное Хранилище заявок.
     */
    private final Tracker tracker;
    /**
     * Переменная определяющая выход.
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
        do {
            menu.show();
            menu.select(input.ask("Введите пункт Carte: ", range));
        } while (!this.exit);
    }
}
