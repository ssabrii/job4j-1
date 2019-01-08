package ru.job4j.singletons;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * TrackerSingletonEnumTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class TrackerSingletonEnumTest {
    @Test
    public void whenSingletonEnumOK() {
        TrackerSingletonEnum result = TrackerSingletonEnum.INSTANCE;
        assertThat(result, is(TrackerSingletonEnum.INSTANCE));
    }

}