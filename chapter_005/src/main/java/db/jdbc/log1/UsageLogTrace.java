package db.jdbc.log1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * UsageLogOne.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/10/2019
 */
public final class UsageLogTrace {
    /**
     * field logger.
     */
    private static final Logger LOG = LogManager.getLogger(
            UsageLogTrace.class.getName());

    /**
     * Constructor.
     */
    private UsageLogTrace() {
    }

    /**
     * Point to program.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
