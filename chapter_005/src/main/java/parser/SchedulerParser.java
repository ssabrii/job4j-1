package parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * SchedulerParser.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/16/2019
 */
public class SchedulerParser {
    /**
     * field logger.
     */
    private static final Logger LOG = LogManager
            .getLogger(SchedulerParser.class.getName());
    /**
     * field scheduler.
     */
    private Scheduler scheduler;
    /**
     * field argument.
     */
    private static String[] args;

    /**
     * Constructor.
     *
     * @param aArgs a array of arguments
     */
    public SchedulerParser(final String[] aArgs) {
        args = aArgs;
    }

    /**
     * Method to get argument command line.
     *
     * @return argument command line
     */
    public static String getParam() {
        final int index = 0;
        if (!args[index].equals("app.properties")) {
            throw new IllegalArgumentException("Missing app.properties");
        }
        return args[index];
    }

    /**
     * Method to get time for scheduler.
     *
     * @return atime for scheduler.
     */
    public final String getTimeScheduler() {
        final String param = getParam();
        try (final InputStream is = SchedulerParser.class.getClassLoader()
                .getResourceAsStream(param)) {
            final Properties props = new Properties();
            props.load(Objects.requireNonNull(is));
            return props.getProperty("cron.time");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + param);
        }
    }

    /**
     * Method to start the default scheduler.
     *
     * @param time the time to start the scheduler
     */
    public final void getSchedulerStartDefault(final String time) {
        try {
            final JobDetail job = newJob(CronTrigger.class)
                    .withIdentity("CronTrigger")
                    .build();
            final Trigger trigger = newTrigger()
                    .withSchedule(cronSchedule(time))
                    .build();
            this.scheduler = StdSchedulerFactory.
                    getDefaultScheduler();
            this.scheduler.start();
            this.scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Method to shutdown the scheduler.
     */
    public final void getSchedulerShutDown() {
        try {
            this.scheduler.shutdown(true);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
