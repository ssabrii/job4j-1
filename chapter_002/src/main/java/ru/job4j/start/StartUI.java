package ru.job4j.start;

import ru.job4j.models.Item;
import ru.job4j.tracker.Tracker;

import java.util.Arrays;

/**
 * StartUI.
 *
 * @version 2.0
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для отображения всех заявок.
     */
    private static final String ALL = "1";
    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки.
     */
    private static final String DEL = "3";
    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final String FINDID = "4";
    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final String FINDNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Интерфейс для получение данных от пользователя.
     */
    private final Input input;
    /**
     * Функциональное Хранилище заявок.
     */
    private final Tracker tracker;

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
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт Carte: ");
            switch (answer) {
                case ADD:
                    this.createItem();
                    break;
                case ALL:
                    this.findAllItems();
                    break;
                case EDIT:
                    this.updateItemById();
                    break;
                case DEL:
                    this.deleteItem();
                    break;
                case FINDID:
                    this.findByID();
                    break;
                case FINDNAME:
                    this.findByName();
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    System.out.println("Введите корректный пункт Carte");
                    break;
            }

        }
    }

    /**
     * Метод заменяет заявки в хранилище.
     */
    private void updateItemById() {
        String id = input.ask("Введите ID обновляемой заявки: ");
        Item[] items = this.tracker.findAll();
        for (int i = 0; i < items.length; i++) {
            if (id.equals(items[i].getId())) {
                System.out.println("------------ Обновление заявки + " + items[i].getId() + "--------------");
                String name = this.input.ask("Введите имя заявки: ");
                String desc = this.input.ask("Введите описание заявки: ");
                Item item = new Item(name, desc);
                if (this.tracker.replace(id, item)) {
                    System.out.println("Заявка ID: " + items[i].getId() + " обновлена.");
                    break;
                } else {
                    System.out.println("Заявка ID: " + items[i].getId() + " не обновлена.");
                    break;
                }
            } else if (i == items.length - 1) {
                System.out.println("Заявка ID: " + items[i].getId() + " Отказано. Уточните ID заявки.");
            }
        }
    }

    /**
     * Метод ищет заявку из хранилища по имени.
     */
    private void findByName() {
        String name = input.ask("Введите название требуемой заявки: ");
        System.out.println(Arrays.toString(tracker.findByName(name)));
    }

    /**
     * Метод ищет заявку из хранилища по ID.
     */
    private void findByID() {
        String id = input.ask("Введите ID требуемой заявки: ");
        System.out.println(tracker.findById(id));
    }

    /**
     * Метод отображает все заявки из хранилища.
     */
    private void findAllItems() {
        System.out.println(Arrays.toString(this.tracker.findAll()));
    }

    /**
     * Метод удаляет заявку из хранилища.
     */
    private void deleteItem() {
        String id = input.ask("Введите ID удаляемой заявки: ");
        if (tracker.delete(id)) {
            System.out.println("Заявка " + id + " удалена.");
        } else {
            System.out.println("Заявка не удалена. Уточните ID заявки.");
        }
    }

    /**
     * Метод реализует добавление новой заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с ID: " + item.getId() + "-----------");
    }

    private void showMenu() {
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Carte.");
        System.out.println("-----------------------------------------------");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Запуск программы.
     *
     * @param args simbols.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}