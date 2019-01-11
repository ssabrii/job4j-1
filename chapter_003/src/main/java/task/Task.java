package task;

/**
 * Task.
 *
 * @author Maxim Vanny.
 * @version 2.0
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
     * @param pdesc     level task.
     * @param ppriority level task.
     */
    public Task(final String pdesc, final int ppriority) {
        this.desc = pdesc;
        this.priority = ppriority;
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
     * Method get lavel task.
     *
     * @return level task.
     */
    public final int getPriority() {
        return priority;
    }
}
