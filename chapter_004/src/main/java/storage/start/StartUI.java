package storage.start;


import storage.tracker.Input;
import storage.tracker.MenuTracker;
import storage.tracker.Tracker;

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
     * Флаг выхода.
     */
    private boolean exit;

    /**
     * Конструтор.
     *
     * @param aInput   ввод данных.
     * @param aTracker хранилище заявок.
     */
    public StartUI(final Input aInput, final Tracker aTracker) {
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
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        int[] range = menu.fillMenu(menu.getActionsSize());
        do {
            menu.show();
            menu.select(input.ask("Введите пункт Carte: ", range));

        } while (!exit);
    }
}

