package io.manager.properties;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PropertySocketTest {
    private final PropertySocket property = new PropertySocket("config/manager.properties");

    @Test
    public void whenPortOK() {
        assertThat(this.property.port(), is("2000"));
    }

    @Test
    public void whenPortFallByIllegalPath() {
        final PropertySocket prop = new PropertySocket("config/manager.prop");
        assertThat(prop.port(), is("Refuse.Missing property file."));
    }

    @Test
    public void whenPortFallAbuseDataByPort() {
        final PropertySocket prop = new PropertySocket("config/manager.falls.properties");
        assertThat(prop.port(), is("Refuse.Missing field port."));
    }
    @Test
    public void whenPortFallAbuseDataByIp() {
        final PropertySocket prop = new PropertySocket("config/manager.falls.properties");
        assertThat(prop.ip(), is("Refuse.Missing field ip."));
    }

    @Test
    public void whenIpOK() {
        assertThat(this.property.ip(), is("localhost"));
    }
}
