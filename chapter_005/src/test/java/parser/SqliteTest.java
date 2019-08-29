package parser;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static parser.Sqlite.init;

@Ignore
public class SqliteTest {
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private String getCommandLine() {
        return "app.properties";
    }

    @Before
    public void addBeforeBos() {
        System.setErr(new PrintStream(bos));
    }

    @Test
    public void whenAddToSqlAndGeFirstId() throws InterruptedException {
        final String[] args = this.getCommandLine().split(" ");
        final SchedulerParser scheduler = new SchedulerParser(args);
        final Sqlite sqlite = new Sqlite(init());
        sqlite.dropTable();
        sqlite.createTable();
        final var time = scheduler.getTimeScheduler();
        scheduler.getSchedulerStartDefault(time);
        Thread.sleep(60000);
        final Set<Vacancy> set = sqlite.getSet();
        if (set != null) {
            sqlite.add(set);
        }
        Vacancy vacancy = sqlite.findVacancyById(sqlite.getCountRowsInVacancy());
        assertThat(Objects.requireNonNull(vacancy).toString(), is(new StringJoiner(System.lineSeparator())
                .add("id='" + sqlite.getCountRowsInVacancy() + "'")
                .add("date='04 янв 19, 10:04'")
                .add("name='Ищем Java-разработчика'")
                .add("desc='Крупная компания ище'")
                .add("link='https://www.sql.ru/forum/1307410/ishhem-java-razrabotchika?hl=java'")
                .toString()));
        scheduler.getSchedulerShutDown();
        sqlite.close();
    }

    @Test
    public void whenAddToSqlAndGetCountRowInTable() throws InterruptedException {
        final String[] args = this.getCommandLine().split(" ");
        final SchedulerParser scheduler = new SchedulerParser(args);
        final Sqlite sqlite = new Sqlite(init());
        sqlite.dropTable();
        sqlite.createTable();
        final var time = scheduler.getTimeScheduler();
        scheduler.getSchedulerStartDefault(time);
        Thread.sleep(60000);
        Set<Vacancy> set = sqlite.getSet();
        if (set != null) {
            sqlite.add(set);
            sqlite.getSet().clear();
        }
        Thread.sleep(60000);
        set = sqlite.getSet();
        if (set != null) {
            sqlite.add(set);
        }
        assertNotNull(set);
        scheduler.getSchedulerShutDown();
        sqlite.close();
    }
}
