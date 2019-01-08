package ru.job4j.singletons;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * TrackerSingletonLazyTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class TrackerSingletonLazyTest {
    @Test
    public void whenSingletonLazyOK() {
        TrackerSingletonLazy result = TrackerSingletonLazy.getInstance();
        assertThat(result, is(TrackerSingletonLazy.getInstance()));
    }

}