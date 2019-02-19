package generics;

/**
 * Base.
 *
 * @author Max Vanny.
 * @version 5.0
 * @since 0.1
 */
public abstract class Base {
    /**
     * id.
     */
    private final String id;

    /**
     * Constructor.
     *
     * @param aId id
     */
    protected Base(final String aId) {
        this.id = aId;
    }

    /**
     * getId.
     *
     * @return id.
     */
    public final String getId() {
        return this.id;
    }
}
