package parser;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Vacancy.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/12/2019
 */
public class Vacancy {
    /**
     * field id.
     */
    private final String id;
    /**
     * field name.
     */
    private final String name;
    /**
     * field desc.
     */
    private final String desc;
    /**
     * field name link.
     */
    private final String link;
    /**
     * field name date.
     */
    private final String date;

    /**
     * Constructor.
     *
     * @param aId   id vacancy
     * @param aName name vacancy
     * @param aDesc desc vacancy
     * @param aLink link vacancy
     * @param aDate date vacancy
     */
    public Vacancy(final String aId, final String aDate,
                   final String aName, final String aDesc,
                   final String aLink) {
        this.id = aId;
        this.date = aDate;
        this.name = aName;
        this.desc = aDesc;
        this.link = aLink;
    }

    /**
     * Constructor.
     *
     * @param aDate date vacancy
     * @param aName name vacancy
     * @param aDesc desc vacancy
     * @param aLink link vacancy
     */
    public Vacancy(final String aDate, final String aName,
                   final String aDesc, final String aLink) {
        this.id = null;
        this.date = aDate;
        this.name = aName;
        this.desc = aDesc;
        this.link = aLink;
    }

    /**
     * Getter id vacancy.
     *
     * @return id vacancy
     */
    public final String getId() {
        return this.id;
    }

    /**
     * Getter name vacancy.
     *
     * @return name vacancy
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Getter description vacancy.
     *
     * @return description vacancy
     */
    public final String getDescription() {
        return this.desc;
    }

    /**
     * Getter link vacancy.
     *
     * @return link vacancy
     */
    public final String getLink() {
        return this.link;
    }

    /**
     * Getter date vacancy.
     *
     * @return date vacancy
     */
    public final String getDate() {
        return this.date;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Vacancy vacancy = (Vacancy) o;
        return this.name.equals(vacancy.name);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.name);
    }

    @Override
    public final String toString() {
        return new StringJoiner(System.lineSeparator())
                .add("id='" + id + "'")
                .add("date='" + date + "'")
                .add("name='" + name + "'")
                .add("desc='" + desc + "'")
                .add("link='" + link + "'")
                .toString();
    }
}
