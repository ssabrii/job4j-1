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
     * Константа меню для добавления новой заявки.
     */
    private static final int ADD = 0;
    /**
     * Константа меню для отображения всех заявок.
     */
    private static final int ALL = 1;
    /**
     * Константа меню для редактирования заявки.
     */
    private static final int EDIT = 2;
    /**
     * Константа меню для удаления заявки.
     */
    private static final int DEL = 3;
    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final int FINDID = 4;
    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final int FINDNAME = 5;
    /**
     * Константа для выхода из цикла.
     */
    private static final int EXIT = 6;
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
     * @param ui сылка на основной класс программы.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem());
        this.actions.add(new ShowItems());
        this.actions.add(new UpdateItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(new FindItemsByName());
        this.actions.add(new ExitProgram(ui));
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

    public class AddItem implements UserAction {
        /**
         * Override method get the hold key.
         *
         * @return the hold key.
         */
        @Override
        public final int key() {
            return ADD;
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

        /**
         * Override method shows element menu.
         *
         * @return string element menu.
         */
        @Override
        public final String info() {
            return "0.Add new item";
        }
    }

    /**
     * Inner class ShowItem.
     */
    public class ShowItems implements UserAction {
        /**
         * Override method get the hold key.
         *
         * @return the hold key.
         */
        @Override
        public final int key() {
            return ALL;
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

        /**
         * Override method shows element menu.
         *
         * @return string element menu.
         */
        @Override
        public final String info() {
            return "1.Show all items";
        }
    }

    /**
     * Inner class UpdateItem.
     */
    public class UpdateItem implements UserAction {
        /**
         * Override method get the hold key.
         *
         * @return the hold key.
         */
        @Override
        public final int key() {
            return EDIT;
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

        /**
         * Override method shows element menu.
         *
         * @return string element menu.
         */
        @Override
        public final String info() {
            return "2.Edit item";
        }
    }

    /**
     * Inner class DeleteItem.
     */
    public class DeleteItem implements UserAction {
        /**
         * Override method get the hold key.
         *
         * @return the hold key.
         */
        @Override
        public final int key() {
            return DEL;
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

        /**
         * Override method shows element menu.
         *
         * @return string element menu.
         */
        @Override
        public final String info() {
            return "3.Delete item";
        }
    }


    /**
     * Inner class FindItemById.
     */
    public class FindItemById implements UserAction {
        /**
         * Override method get the hold key.
         *
         * @return the hold key.
         */
        @Override
        public final int key() {
            return FINDID;
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

        /**
         * Override method shows element menu.
         *
         * @return string element menu.
         */
        @Override
        public final String info() {

            return "4.Find item by Id";
        }
    }

    /**
     * Inner class FindItemByName.
     */
    public class FindItemsByName implements UserAction {
        /**
         * Override method get the hold key.
         *
         * @return the hold key.
         */
        @Override
        public final int key() {
            return FINDNAME;
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

        /**
         * Override method shows element menu.
         *
         * @return string element menu.
         */
        @Override
        public final String info() {
            return "5.Find items by name";
        }
    }

    /**
     * Inner class ExitProgram.
     */
    public class ExitProgram implements UserAction {
        /**
         * link on StartUI.
         */
        private final StartUI ui;

        /**
         * Contstructor.
         *
         * @param ui link on StartUI
         */
        public ExitProgram(StartUI ui) {
            this.ui = ui;
        }

        /**
         * Override method get the hold key.
         *
         * @return the hold key.
         */
        @Override
        public final int key() {
            return EXIT;
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

        /**
         * Override method shows element menu.
         *
         * @return string element menu.
         */
        @Override
        public final String info() {
            return "6.Exit Program";
        }
    }

}
