package carte.start;


import carte.tracker.Input;
import carte.tracker.MenuTracker;
import carte.tracker.Tracker;

/**
 * * Carte.
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

