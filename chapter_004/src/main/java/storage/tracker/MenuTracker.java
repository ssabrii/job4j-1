package storage.tracker;

import storage.models.Item;
import storage.start.StartUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MenuTracker.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class MenuTracker {
    /**
     * Ссылка на объект ввода.
     */
    private final Input input;
    /**
     * Сылка на объект хранилища.
     */
    private final Tracker tracker;
    /**
     * Сылка на список типа UserAction.
     */
    private final List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения размера списка меню.
     *
     * @return размер списка
     */
    public int getActionsSize() {
        return this.actions.size();
    }

    /**
     * Метод заполняет список.
     *
     * @param ui сылка на основной класс программы.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem(0, "Add new item"));
        this.actions.add(new ShowItems(1, "Show all items"));
        this.actions.add(new UpdateItem(2, "Edit item"));
        this.actions.add(new DeleteItem(3, "Delete item"));
        this.actions.add(new FindItemById(4, "Find item by Id"));
        this.actions.add(new FindItemsByName(5, "Find items by name"));
        this.actions.add(new ExitProgram(6, "Exit Program", ui));
    }

    /**
     * Метод опеределяет количество элементов меню.
     *
     * @param length количество элементов меню.
     * @return установленное количество элементов меню.
     */
    public int[] fillMenu(int length) {
        int[] menu = new int[length];
        for (int index = 0; index < this.getActionsSize(); index++) {
            menu[index] = index;
        }
        return menu;
    }

    /**
     * Метод в зависимости от указанного ключа,
     * выполняет соответствующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        System.out.println("-----------------------------------------------");
        System.out.println("Carte.");
        System.out.println("-----------------------------------------------");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Inner class AddItem.
     */

    public class AddItem extends BaseAction {
        /**
         * Constructor.
         *
         * @param key  the key of menu.
         * @param name the name of menu
         */
        public AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Override method adds new Item.
         *
         * @param input   объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("--------- Добавление новой заявки -----------");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            Item item = new Item(name, desc);
            if (tracker.add(item) == null) {
                System.out.println("Заявка не добавлена. Хранилище полное.");
            } else {
                System.out.println("----- Новая заявка с ID: " + item.getId() + "----");
            }
        }
    }

    /**
     * Inner class ShowItem.
     */
    public class ShowItems extends BaseAction {
        /**
         * Constructor.
         *
         * @param key  the key of menu.
         * @param name the name of menu
         */
        public ShowItems(int key, String name) {
            super(key, name);
        }

        /**
         * Override method shows all Items.
         *
         * @param input   объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {

            System.out.println(Arrays.toString(tracker.findAll()));
        }
    }

    /**
     * Inner class UpdateItem.
     */
    public class UpdateItem extends BaseAction {
        /**
         * Constructor.
         *
         * @param key  the key of menu.
         * @param name the name of menu
         */
        public UpdateItem(int key, String name) {
            super(key, name);
        }

        /**
         * Override method updates Item.
         *
         * @param input   объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID обновляемой заявки: ");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            if (!tracker.replace(id, new Item(name, desc))) {
                System.out.println("Заявка ID: " + id + " не обновлена.");
            } else {
                System.out.println("Заявка ID: " + id + " обновлена.");
            }
        }
    }

    /**
     * Inner class DeleteItem.
     */
    public class DeleteItem extends BaseAction {
        /**
         * Constructor.
         *
         * @param key  the key of menu.
         * @param name the name of menu
         */
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        /**
         * Override method delete Item.
         *
         * @param input   объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите ID удаляемой заявки: ");
            if (!tracker.delete(id)) {
                System.out.println("Заявка не удалена. Уточните ID заявки.");
            } else {
                System.out.println("Заявка " + id + " удалена.");
            }
        }
    }

    /**
     * Inner class FindItemById.
     */
    public class FindItemById extends BaseAction {
        /**
         * Constructor.
         *
         * @param key  the key of menu.
         * @param name the name of menu
         */
        public FindItemById(int key, String name) {
            super(key, name);
        }

        /**
         * Override method finds Item by ID.
         *
         * @param input   объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Поиск, введите ID заявки: ");
            Item byId = tracker.findById(id);
            if (byId == null) {
                System.out.println("Заявка не обнаружена. Уточните ID");
            } else {
                System.out.println(byId);
            }
        }
    }

    /**
     * Inner class FindItemByName.
     */
    public class FindItemsByName extends BaseAction {
        /**
         * Constructor.
         *
         * @param key  the key of menu.
         * @param name the name of menu
         */
        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        /**
         * Override method find Item by name.
         *
         * @param input   объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Поиск, введите название заявки:");
            Item[] byNames = tracker.findByName(name);
            if (byNames.length == 0) {
                System.out.println("Заявка не обнаружена. Уточните название.");
            } else {
                System.out.println(Arrays.toString(byNames));
            }
        }
    }

    /**
     * Inner class ExitProgram.
     */
    public class ExitProgram extends BaseAction {
        /**
         * link on StartUI.
         */
        private final StartUI ui;

        /**
         * Constructor.
         *
         * @param key  the key of menu.
         * @param name the name of menu.
         * @param  ui the link to StartUI.
         */
        public ExitProgram(int key, String name, StartUI ui) {
            super(key, name);
            this.ui = ui;
        }

        /**
         * Override method to close program.
         *
         * @param input   объект типа Input.
         * @param tracker объект типа Tracker.
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            this.ui.stop();
        }

    }
}
