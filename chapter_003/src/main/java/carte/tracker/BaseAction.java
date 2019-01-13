package carte.tracker;

/**
 * * Carte.
 * BaseAction
 *
 * @author Maxim Vannny.
 * @version 2.0
 * @since 0.1
 */
public abstract class BaseAction implements UserAction {
    /**
     * The key of menu.
     */
    private final int key;
    /**
     * The name of menu.
     */
    private final String name;

    /**
     * Constructor.
     *
     * @param aKey  the of menu.
     * @param aName the name of menu.
     */
    protected BaseAction(final int aKey, final String aName) {
        this.key = aKey;
        this.name = aName;
    }

    /**
     * Method get the key menu.
     *
     * @return the key of menu.
     */
    @Override
    public final int key() {
        return this.key;
    }

    /**
     * Method get string the key and name of menu.
     *
     * @return string the key and name of menu.
     */
    @Override
    public final String info() {
        return String.format("%s.%s", this.key, this.name);
    }
}
