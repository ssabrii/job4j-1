package parser;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import static parser.Sqlite.init;

/**
 * Main.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/23/2019
 */
public final class Main {
    /**
     * Constructor.
     */
    private Main() {
    }

    /**
     * Method to set filter for logger java util.
     */
    static class FilterLevelOff implements Filter {
        /**
         * Method to set level logging.
         *
         * @param record record level
         * @return boolean to set level
         */
        public final boolean isLoggable(@NotNull final LogRecord record) {
            return record.getLevel() == Level.FINEST;
        }
    }

    /**
     * Point enter to program.
     *
     * @param args array command args[0] app.properties.
     */
    public static void main(final String[] args) {
        //final Logger logger = Logger.getLogger(Main.class.getName());
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setErr(new PrintStream(bos));
        //PrintStream outPS = new PrintStream(new BufferedOutputStream(
        //new FileOutputStream("out.log", true)));  // append is true
        //System.setErr(outPS);
        // logger.setLevel(Level.OFF);
        // final Filter filter = new FilterLevelOff();
        // logger.setFilter(filter);
        // final Handler fileHandler = new FileHandler("default.log");
        //fileHandler.setFormatter(new XMLFormatter());
        //logger.addHandler(fileHandler);
        //logger.info("Info log message");
        final String[] s = "app.properties".split(" ");
        final SchedulerParser scheduler = new SchedulerParser(s);
        final Sqlite sqlite = new Sqlite(init());
        // postgres.dropTable();
        sqlite.createTable();
        sqlite.getSet().clear();
        System.out.println("START:");
        final String time = scheduler.getTimeScheduler();
        scheduler.getSchedulerStartDefault(time);
        final int millis = 80000;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!sqlite.getSet().isEmpty()) {
            sqlite.add(sqlite.getSet());
        } else {
            System.out.println("Missing new vacancy");
        }
        final int id = sqlite.getCountRowsInVacancy();
        if (id != -1) {
            System.out.println("Last vacancy: ");
            System.out.println(sqlite.findVacancyById(1));
            System.out.println("First vacancy: ");
            System.out.println(sqlite.findVacancyById(id));
        } else {
            System.out.println("db is empty");
        }
        scheduler.getSchedulerShutDown();
    }
}
