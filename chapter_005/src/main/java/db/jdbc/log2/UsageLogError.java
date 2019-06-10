package db.jdbc.log2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * UsageLogError.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 6/10/2019
 */
public final class UsageLogError {
    /**
     * field logger.
     */
    private static final Logger LOG = LogManager.getLogger(
            UsageLogError.class.getName());

    /**
     * Constructor.
     */
    private UsageLogError() {
    }

    /**
     * Point to program.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        int version = 1;
        LOG.trace("trace message {}", version);
        LOG.debug("trace message {}", version);
        LOG.info("trace message {}", version);
        LOG.warn("trace message {}", version);
        LOG.error("trace message {}", version);
    }
}
