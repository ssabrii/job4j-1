package io.manager.properties;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class RootTest {
    private final Root root = new Root();

    @Test
    public void whenGetRootCatalogOK() {
        final var result = this.root.getRootCatalog();
        final var expected = this.root.getUserDir() + "src/main/java/io/manager";
        assertThat(result, is(expected));
    }
}
