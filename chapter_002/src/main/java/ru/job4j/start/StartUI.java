package ru.job4j.start;

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
    public void stop() {
        this.exit = true;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        //   List<Integer> range = new ArrayList<>();
        menu.fillActions(this);
      /*  for (int i = 0; i < menu.getActionsSize(); i++) {
            range.add(i);
        }*/
        do {
            menu.show();
            menu.select(Integer.parseInt(input.ask("Введите пункт Carte: "/*, range*/)));
        } while (!this.exit);
    }
}
