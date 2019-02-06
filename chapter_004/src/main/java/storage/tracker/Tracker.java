package storage.tracker;

import storage.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.IntStream;

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
        final OptionalInt first = IntStream.range(0, items.size())
                .filter(z -> id.equals(items.get(z).getId()))
                .takeWhile(Objects::nonNull)
                .peek(z -> {
                    item.setId(id);
                    this.items.set(z, item);
                })
                .findFirst();
        if (first.isPresent()) {
            check = true;
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
        return items.removeIf(z -> id.equals(z.getId()));
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
        return items.stream()
                .filter(z -> key.equals(z.getName()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    /**
     * Метод реализущий поиск заявки в хранилище по уникальному ключу.
     *
     * @param id уникальный ключ заявки.
     * @return item возвращает найденую заявку.
     */
    public final Item findById(final String id) {
        return items.stream()
                .filter(z -> id.equals(z.getId()))
                .takeWhile(Objects::nonNull)
                .findFirst().orElse(null);
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
