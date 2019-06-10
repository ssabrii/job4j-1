package ru.job4j.tracker;

import ru.job4j.models.Item;

/**
 * ITracker.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/10/2019
 */
public interface ITracker {
    /**
     * Method add item.
     *
     * @param item item
     * @return add item
     */
    Item add(Item item);

    /**
     * Method replace item.
     *
     * @param id   id
     * @param item item
     * @return boolean result
     */
    boolean replace(String id, Item item);

    /**
     * Method delete item.
     *
     * @param id id
     * @return boolean result
     */
    boolean delete(String id);

    /**
     * Method find all items.
     *
     * @return all items
     */
    Item[] findAll();

    /**
     * Method find item by name.
     *
     * @param key key
     * @return item
     */
    Item[] findByName(String key);

    /**
     * Method find item by id.
     *
     * @param id id
     * @return id
     */
    Item findById(String id);
}
