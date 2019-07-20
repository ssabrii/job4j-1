package parser;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import static parser.Postgres.init;

/**
 * CronTrigger.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/19/2019
 */
public class CronTrigger implements Job {
    /**
     * Constructor.
     */
    public CronTrigger() {
    }

    /**
     * field postgres.
     */
    private final Postgres postgres = new Postgres(init());

    @Override
    public final void execute(final JobExecutionContext jobExecutionContext) {
        final ParserJSOUP parser = new ParserJSOUP();
        final var uri = parser.getBaseLink();
        var page = 1;
        final var inPage = 50;
        final var code = 200;
        final var db = this.postgres.getLastDateVacancy();
        boolean check = false;
        while (parser.connPage(uri + page + "&")
                .statusCode() == code && !check) {
            System.out.println("START " + code);
            for (int index = 0; index < inPage; index++) {
                final var date = parser.getDate(index);
                if (db.equals("") || date.equals(db)) {
                    if (!date.contains("19,") || date.equals(db)) {
                        check = true;
                        break;
                    } else {
                        if (getOnlyJava(parser, index)) {
                            addToSet(parser, index, date);
                        }
                    }
                }
            }
            page++;
        }
        this.postgres.close();
    }

    /**
     * Method to add in set vacancies.
     *
     * @param parser parser
     * @param index  index
     * @param date   date
     */
    private void addToSet(final ParserJSOUP parser,
                          final int index, final String date) {
        final var link = parser.getLink(index);
        this.postgres.getSet().add(new Vacancy(
                date,
                parser.getName(index),
                parser.getDescription(link),
                link));
    }

    /**
     * Method check distinct name vacancy.
     *
     * @param parser parser
     * @param z      index vacancy in page
     * @return boolean result
     */
    private boolean getOnlyJava(final ParserJSOUP parser, final int z) {
        return (parser.getName(z).contains("Java")
                && !parser.getName(z).contains("Java script")
                && !parser.getName(z).contains("Javascript")
                && !parser.getName(z).contains("JavaScript"));
    }
}
