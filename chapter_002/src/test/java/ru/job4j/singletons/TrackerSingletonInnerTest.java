package ru.job4j.singletons;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * TrackerSingletonInnerTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class TrackerSingletonInnerTest {
    @Test
    public void whenSingletonInnerOK() {
        TrackerSingletonInner result = TrackerSingletonInner.getInstance();
        assertThat(result, is(TrackerSingletonInner.getInstance()));
    }

}