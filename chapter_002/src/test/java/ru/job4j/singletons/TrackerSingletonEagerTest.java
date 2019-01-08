package ru.job4j.singletons;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * TrackerSingletonEagerTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class TrackerSingletonEagerTest {
    @Test
    public void whenSingletonEagerOK() {
        TrackerSingletonEager result = TrackerSingletonEager.getInstance();
        assertThat(result, is(TrackerSingletonEager.getInstance()));
    }
}