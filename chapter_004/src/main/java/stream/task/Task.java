package stream.task;

/**
 * Task.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class Task {
    /**
     * level text task.
     */
    private final String desc;
    /**
     * level number task.
     */
    private final int priority;

    /**
     * Constructor.
     *
     * @param aDesc     level task.
     * @param aPriority level task.
     */
    @SuppressWarnings("unused")
    public Task(final String aDesc, final int aPriority) {
        this.desc = aDesc;
        this.priority = aPriority;
    }

    /**
     * Method get level task.
     *
     * @return level task.
     */
    public final String getDesc() {
        return desc;
    }

    /**
     * Method get level task.
     *
     * @return level task.
     */
    public final int getPriority() {
        return priority;
    }
}
