package parser;

import org.junit.Test;

import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static parser.Postgres.init;


public class PostgresTest {

    private String getCommandLine() {
        return new StringBuilder()
                .append("app.properties")
                .toString();
    }


    @Test
    public void whenAddToSqlAndGeFirstId() throws InterruptedException {
        final String[] args = this.getCommandLine().split(" ");
        final SchedulerParser scheduler = new SchedulerParser(args);
        final Postgres postgres = new Postgres(init());
        postgres.dropTable();
        postgres.createTable();
        final var time = scheduler.getTimeScheduler();
        scheduler.getSchedulerStartDefault(time);
        Thread.sleep(60000);
        final Set<Vacancy> set = postgres.getSet();
        if (set != null) {
            postgres.add(set);
        }
        Vacancy vacancy = postgres.findVacancyById(postgres.getCountRowsInVacancy());
        assertThat(Objects.requireNonNull(vacancy).toString(), is(new StringJoiner(System.lineSeparator())
                .add("id='" + postgres.getCountRowsInVacancy() + "'")
                .add("date='4 янв 19, 10:04'")
                .add("name='Ищем Java-разработчика'")
                .add("desc='Крупная компания ище'")
                .add("link='https://www.sql.ru/forum/1307410/ishhem-java-razrabotchika?hl=java'")
                .toString()));
        scheduler.getSchedulerShutDown();
        postgres.close();
    }

    @Test
    public void whenAddToSqlAndGetCountRowInTable() throws InterruptedException {
        final String[] args = this.getCommandLine().split(" ");
        final SchedulerParser scheduler = new SchedulerParser(args);
        final Postgres postgres = new Postgres(init());
        postgres.dropTable();
        postgres.createTable();
        final var time = scheduler.getTimeScheduler();
        scheduler.getSchedulerStartDefault(time);
        Thread.sleep(60000);
        Set<Vacancy> set = postgres.getSet();
        if (set != null) {
            postgres.add(set);
        }
        Thread.sleep(60000);
        set = postgres.getSet();
        if (set != null) {
            postgres.add(set);
        }
        assertNotNull(set);
        scheduler.getSchedulerShutDown();
        postgres.close();
    }
}
