package storage.tracker;

import storage.models.Item;

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
     * Количество элементов массива.
     */
    private final int index = 100;
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[this.index];
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
        Item seeker = null;
        if (position < this.items.length - 1) {
            item.setId(this.generateId());
            this.items[this.position++] = item;
            seeker = item;
        }
        return seeker;
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
        for (int i = 0; i < this.position; i++) {
            if (id.equals(this.items[i].getId())) {
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
        for (int i = 0; i < this.position; i++) {
            if (id.equals(this.items[i].getId())) {
                System.arraycopy(this.items, i + 1, this.items, i, this.position);
                this.position--;
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
        Item[] seeker = new Item[this.index];
        int count = 0;
        for (int i = 0; i < this.position; i++) {
            if (key.equals(this.items[i].getName())) {
                seeker[count++] = this.items[i];
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
        for (int i = 0; i < this.position; i++) {
            if (id.equals(this.items[i].getId())) {
                seeker = this.items[i];
                break;
            }
        }
        return seeker;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Реализовать метод генерации.
        return String.valueOf(String.valueOf((Math.random())).hashCode());
    }
}
