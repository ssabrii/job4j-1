package storage.tracker;

import storage.models.Item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * * Storage.
 * Tracker.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
@SuppressWarnings("Duplicates")
public class Tracker {
    /**
     * Динамический массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализущий добавление заявки в хранилище.
     *
     * @param item новая заявка.
     * @return возвращает добавленную заявку
     */
    public final Item add(final Item item) {
        Objects.requireNonNull(item, "must be not null");
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод реализущий замену заявки в хранилище.
     *
     * @param id   уникальный номер заявки.
     * @param item новая заявка для замены.
     * @return check статус выполнения метода.
     */
    public final boolean replace(final String id, final Item item) {
        boolean check = false;
        for (int index = 0; index < this.items.size(); index++) {
            String seek = this.items.get(index).getId();
            item.setId(id);
            if (id.equals(seek)) {
                this.items.set(index, item);
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
    public final boolean delete(final String id) {
        boolean check = false;
        Iterator<Item> it = this.items.iterator();
        while (it.hasNext()) {
            if (id.equals(it.next().getId())) {
                it.remove();
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * Метод возвращающий все заявки из хранилища.
     *
     * @return Item[] массив заявок.
     */
    public final List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод реализущий поиск заявки в хранилище по полю key.
     *
     * @param key имя заявки.
     * @return item возвращает массив найденных заявок.
     */
    public final List<Item> findByName(final String key) {
        List<Item> picker = new ArrayList<>();
        for (Item item : this.items) {
            if (key.equals(item.getName())) {
                picker.add(item);
            }
        }
        return picker;
    }

    /**
     * Метод реализущий поиск заявки в хранилище по уникальному ключу.
     *
     * @param id уникальный ключ заявки.
     * @return item возвращает найденую заявку.
     */
    public final Item findById(final String id) {
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
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Реализовать метод генерации.
        return String.valueOf(String.valueOf((Math.random())).hashCode());
    }
}
