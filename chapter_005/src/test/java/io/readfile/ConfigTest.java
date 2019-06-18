package io.readfile;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    private final File resources = new File("config/app.properties");
    private final Config config = new Config(this.resources.getAbsolutePath());

    @Test
    public void whenLoadOk() {
        this.config.load();
        assertThat(this.config.toString(), is(new StringBuilder()
                .append("## PostgresSQL")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("hibernate.dialect=org.hibernate.dialect.PostgresSQLDialect")
                .append(System.lineSeparator())
                .append("hibernate.connection.url=jdbc:postgres://127.0.0.1:5432/tracksuit")
                .append(System.lineSeparator())
                .append("hibernate.connection.driver_class=org.postgres.Driver")
                .append(System.lineSeparator())
                .append("hibernate.connection.username=postgres")
                .append(System.lineSeparator())
                .append("hibernate.connection.password=password")
                .toString()));
    }

    @Test
    public void whenValueOk() {
        this.config.load();
        String result = this.config.value("hibernate.connection.username");
        String expected = "postgres";
        assertThat(result, is(expected));

    }

    @SuppressWarnings("unused")
    @Test(expected = Exception.class)
    public void whenLoadFall() {
        String result = this.config.value("hibernate.connection.driver_class");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenValueNotFoundFall() {
        this.config.value("hibernate");
    }
}
