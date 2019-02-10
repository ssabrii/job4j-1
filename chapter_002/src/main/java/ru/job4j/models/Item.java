package ru.job4j.models;

/**
 * Item.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class Item {
    /**
     * уникальный ключ заявки.
     */
    private String id;
    /**
     * имя заявки.
     */
    private final String name;
    /**
     * описание заявки.
     */
    private final String description;
    /**
     * создание заявки.
     */
    private long create;

    /**
     * Конструктор.
     *
     * @param aName        название заявки.
     * @param sDescription описание заявки.
     * @param aCreate      создание заявки.
     */
    public Item(final String aName,
                final String sDescription,
                final long aCreate) {
        this.name = aName;
        this.description = sDescription;
        this.create = aCreate;
    }

    /**
     * констуртор заявки.
     *
     * @param aName        название заявки.
     * @param aDescription описание заявки.
     */
    public Item(final String aName, final String aDescription) {
        this.name = aName;
        this.description = aDescription;
    }

    /**
     * получение имени заявки.
     *
     * @return возвращает имя заявки.
     */
    public final String getName() {
        return this.name;
    }

    /**
     * получение описания заявки.
     *
     * @return возвращает описание заявки.
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * получение создания заявки.
     *
     * @return возращает создание заявки.
     */
    @SuppressWarnings("unused")
    public final long getCreate() {
        return this.create;
    }

    /**
     * устанавливает создание заявки.
     *
     * @param aCreate создание заявки.
     */
    @SuppressWarnings("unused")
    public final void setCreate(final long aCreate) {
        this.create = aCreate;
    }

    /**
     * устанавливает уникальный ключ для заявки.
     *
     * @param aId уникальный ключ заявки.
     */
    public final void setId(final String aId) {
        this.id = aId;
    }

    /**
     * получение уникального ключа заявки.
     *
     * @return уникальный ключ заявки.
     */
    public final String getId() {
        return this.id;
    }

    @Override
    public final String toString() {
        return '\n'
                + "Заявка: id '"
                + id
                + "', name='"
                + name
                + "', description='"
                + description
                + "'";
    }
}
