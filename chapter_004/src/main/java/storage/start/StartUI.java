package storage.start;


import storage.tracker.ConsoleInput;
import storage.tracker.Input;
import storage.tracker.MenuTracker;
import storage.tracker.Tracker;
import storage.tracker.ValidateInput;

import java.util.function.Consumer;

/**
 * * Storage.
 * StartUI.
 *
 * @version 4.0
 * @since 0.1
 */
@SuppressWarnings("Duplicates")
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
     * Consumer вывод данных.
     */
    private final Consumer<String> output;
    /**
     * Флаг выхода.
     */
    private boolean exit;

    /**
     * Конструтор.
     *
     * @param aInput   ввод данных.
     * @param aTracker хранилище заявок.
     * @param aOutput  способ вывода
     */
    public StartUI(final Input aInput,
                   final Tracker aTracker,
                   final Consumer<String> aOutput) {
        this.input = aInput;
        this.tracker = aTracker;
        this.output = aOutput;
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
        MenuTracker menu = new MenuTracker(
                this.input, this.tracker, this.output);
        menu.fillActions(this);
        int[] range = menu.fillMenu(menu.getActionsSize());
        do {
            menu.show();
            menu.select(input.ask("Введите пункт Carte: ", range));

        } while (!exit);
    }

    /**
     * Точка входа в программу.
     *
     * @param args массив аргументов.
     */
    public static void main(final String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}

