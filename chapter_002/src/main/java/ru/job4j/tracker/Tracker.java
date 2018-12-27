package ru.job4j.tracker;

import ru.job4j.models.Item;

import java.util.Arrays;

/**
 * Tracker.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];
    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализущий добавление заявки в хранилище.
     *
     * @param item новая заявка.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод реализущий замену заявки в хранилище.
     *
     * @param id   уникальный номер заявки.
     * @param item новая заявка для замены.
     * @return check статус выполнения метода.
     */
    public boolean replace(String id, Item item) {
        boolean check = false;
        for (int i = 0; i < items.length; i++) {
            if (id.equals(items[i].getId())) {
                items[i] = item;
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Метод реализущий удаление заявки из хранилища.
     *
     * @param id уникальный номер заявки.
     */
    public boolean delete(String id) {
        return false;
    }

    /**
     * Метод возвращающий все заявки из хранилище.
     *
     * @return Item[] массив заявок.
     */
    public Item[] findAll() {
        return Arrays.copyOfRange(items, 0, position);
    }

    /**
     * Метод реализущий поиск заявки в хранилище по полю name.
     *
     * @param key имя заявки.
     * @return item возвращает найденую заявку.
     */
    public Item findByName(String key) {
        Item item = null;
        for (Item item1 : items) {
            if (key.equals(item1.getName())) {
                item = item1;
                break;
            }
        }
        return item;
    }

    /**
     * Метод реализущий поиск заявки в хранилище по уникальному ключу.
     *
     * @param id уникальный ключ заявки.
     * @return item возвращает найденую заявку.
     */
    public Item findById(String id) {
        Item seeker = null;
        for (Item item : items) {
            if (id.equals(item.getId())) {
                seeker = item;
                break;
            }
        }
        return seeker;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Реализовать метод генерации.
        return String.valueOf(this.hashCode());
    }
}
