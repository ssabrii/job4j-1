package parser;

import static parser.Postgres.init;

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
     * Point enter to program.
     *
     * @param args array command args[0] app.properties.
     * @throws InterruptedException InterruptedException
     */
    public static void main(final String[] args) throws InterruptedException {
       // final String[] s = "java -jar cron.jar app.properties".split(" ");
        final SchedulerParser scheduler = new SchedulerParser(args);
        final Postgres postgres = new Postgres(init());
        //postgres.dropTable();
        postgres.createTable();
        final String time = scheduler.getTimeScheduler();
        scheduler.getSchedulerStartDefault(time);
        final int millis = 80000;
        Thread.sleep(millis);
        if (postgres.getSet() != null) {
            postgres.add(postgres.getSet());
        } else {
            System.out.println("Missing new vacancy");
        }
        final int id = postgres.getCountRowsInVacancy();
        if (id != -1) {
            System.out.println("First vacancy: ");
            System.out.println(postgres.findVacancyById(id));
        } else {
            System.out.println("db is empty");
        }
        scheduler.getSchedulerShutDown();
    }
}
