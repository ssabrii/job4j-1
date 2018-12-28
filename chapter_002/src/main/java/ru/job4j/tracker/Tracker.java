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
     * @return возвращает добавленную заявку
     */
    public Item add(Item item) {
        if (position < this.items.length - 1) {
            item.setId(this.generateId());
            this.items[this.position++] = item;
        }
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
        // position исправил. но перебора всего массива нигде нет, кроме findByName(),
        // тк везде используется оператор break.
        for (int i = 0; i < position; i++) {
            if (id.equals(this.items[i].getId())) {
                //зачем устанавливать, если id перезапишется из item?
                item.setId(id);
                this.items[i] = item;
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
     * @return возвращает статус выполнения метода.
     */
    public boolean delete(String id) {
        boolean check = false;
        for (int i = 0; i < position; i++) {
            if (id.equals(this.items[i].getId())) {
                System.arraycopy(this.items, i + 1, this.items, i, this.position);
                position--;
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Метод возвращающий все заявки из хранилище.
     *
     * @return Item[] массив заявок.
     */
    public Item[] findAll() {
        return Arrays.copyOfRange(this.items, 0, this.position);
    }

    /**
     * Метод реализущий поиск заявки в хранилище по полю key.
     *
     * @param key имя заявки.
     * @return item возвращает массив найденных заявок.
     */
    public Item[] findByName(String key) {
        Item[] seeker = new Item[100];
        int count = 0;
        for (int i = 0; i < position; i++) {
            if (key.equals(items[i].getName())) {
                seeker[count++] = items[i];
            }
        }
        seeker = Arrays.copyOf(seeker, count);
        return seeker;
    }

    /**
     * Метод реализущий поиск заявки в хранилище по уникальному ключу.
     *
     * @param id уникальный ключ заявки.
     * @return item возвращает найденую заявку.
     */
    public Item findById(String id) {
        Item seeker = null;
        for (Item item : this.items) {
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
        return String.valueOf(String.valueOf((Math.random())).hashCode());
    }
}
