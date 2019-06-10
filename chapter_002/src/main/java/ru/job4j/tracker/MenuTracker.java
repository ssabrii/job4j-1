package ru.job4j.tracker;

import ru.job4j.models.Item;
import ru.job4j.start.StartUI;

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
    private final ITracker tracker;
    /**
     * Сылка на список типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();
    /**
     * ADD.
     */
    public static final int ADD = 0;
    /**
     * SHOW.
     */
    public static final int SHOW = 1;
    /**
     * UPDATE.
     */
    public static final int UPD = 2;
    /**
     * DELETE.
     */
    public static final int DEL = 3;
    /**
     * FIND BY ID.
     */
    public static final int FIND_ID = 4;
    /**
     * FIND BY NAME.
     */
    public static final int FIND_BY_NAME = 5;
    /**
     * EXIT.
     */
    public static final int EXIT = 6;


    /**
     * Конструктор.
     *
     * @param aInput   объект типа Input
     * @param aTracker объект типа Tracker
     */
    public MenuTracker(final Input aInput, final ITracker aTracker) {
        this.input = aInput;
        this.tracker = aTracker;
    }

    /**
     * Метод для получения размера списка меню.
     *
     * @return размер списка
     */
    public final int getActionsSize() {
        return this.actions.size();
    }

    /**
     * Метод заполняет список.
     *
     * @param ui сылка на основной класс программы.
     */
    @SuppressWarnings("Duplicates")
    public final void fillActions(final StartUI ui) {
        this.actions = List.of(
                new AddItem(ADD, "Add new item"),
                new ShowItems(SHOW, "Show all items"),
                new UpdateItem(UPD, "Edit item"),
                new DeleteItem(DEL, "Delete item"),
                new FindItemById(FIND_ID, "Find item by Id"),
                new FindItemsByName(FIND_BY_NAME, "Find items by name"),
                new ExitProgram(EXIT, "Exit Program", ui));
    }

    /**
     * Метод опеределяет количество элементов меню.
     *
     * @param length количество элементов меню.
     * @return установленное количество элементов меню.
     */
    public final int[] fillMenu(final int length) {
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
    public final void select(final int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public final void show() {
        System.out.println("-----------------------------------------------");
        System.out.println("Carte.");
        System.out.println("-----------------------------------------------");
        for (var action : this.actions) {
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
        public AddItem(final int key, final String name) {
            super(key, name);
        }

        /**
         * Override method adds new Item.
         *
         * @param aInput   объект типа Input.
         * @param aTracker объект типа Tracker.
         */
        @Override
        public final void execute(final Input aInput, final ITracker aTracker) {
            System.out.println("--------- Добавление новой заявки -----------");
            var name = aInput.ask("Введите имя заявки: ");
            var desc = aInput.ask("Введите описание заявки: ");
            var item = new Item(name, desc);
            if (aTracker.add(item) == null) {
                System.out.println("Заявка не добавлена. Хранилище полное.");
            } else {
                System.out.println("----- Новая заявка с ID: "
                        + item.getId() + "----");
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
        public ShowItems(final int key, final String name) {
            super(key, name);
        }

        /**
         * Override method shows all Items.
         *
         * @param aInput   объект типа aInput.
         * @param aTracker объект типа Tracker.
         */
        @Override
        public final void execute(final Input aInput, final ITracker aTracker) {
            System.out.println(Arrays.toString(aTracker.findAll()));
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
        public UpdateItem(final int key, final String name) {
            super(key, name);
        }

        /**
         * Override method updates Item.
         *
         * @param aInput   объект типа aInput.
         * @param aTracker объект типа Tracker.
         */
        @Override
        public final void execute(final Input aInput, final ITracker aTracker) {
            var id = aInput.ask("Введите ID обновляемой заявки: ");
            var name = aInput.ask("Введите имя заявки: ");
            var desc = aInput.ask("Введите описание заявки: ");
            if (!aTracker.replace(id, new Item(name, desc))) {
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
        public DeleteItem(final int key, final String name) {
            super(key, name);
        }

        /**
         * Override method delete Item.
         *
         * @param aInput   объект типа aInput.
         * @param aTracker объект типа Tracker.
         */
        @Override
        public final void execute(final Input aInput, final ITracker aTracker) {
            var id = aInput.ask("Введите ID удаляемой заявки: ");
            if (!aTracker.delete(id)) {
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
        public FindItemById(final int key, final String name) {
            super(key, name);
        }

        /**
         * Override method finds Item by ID.
         *
         * @param aInput   объект типа aInput.
         * @param aTracker объект типа Tracker.
         */
        @Override
        public final void execute(final Input aInput, final ITracker aTracker) {
            var id = aInput.ask("Поиск, введите ID заявки: ");
            var byId = aTracker.findById(id);
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
        public FindItemsByName(final int key, final String name) {
            super(key, name);
        }

        /**
         * Override method find Item by name.
         *
         * @param aInput   объект типа Input.
         * @param aTracker объект типа Tracker.
         */
        @Override
        public final void execute(final Input aInput, final ITracker aTracker) {
            var name = aInput.ask("Поиск, введите название заявки:");
            Item[] byNames = aTracker.findByName(name);
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
         * @param aUi  the link to StartUI.
         */
        public ExitProgram(final int key, final String name,
                           final StartUI aUi) {
            super(key, name);
            this.ui = aUi;
        }

        /**
         * Override method to close program.
         *
         * @param aInput   объект типа Input.
         * @param aTracker объект типа Tracker.
         */
        @Override
        public final void execute(final Input aInput,
                                  final ITracker aTracker) {
            this.ui.stop();
        }

    }
}
