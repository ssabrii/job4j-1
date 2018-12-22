package ru.job4j.loop;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Maxim Vanny.
 * @version 1.0
 * @since 0.1
 */
public class PyramidTest {

    @Test
    public void whenPyramid4Right() {
        Pyramid pyramid = new Pyramid();
        String rst = pyramid.rightTrl(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("^   ")
                                .add("^^  ")
                                .add("^^^ ")
                                .add("^^^^")
                                .toString()
                )
        );
    }

    @Test
    public void whenPyramid4Left() {
        Pyramid pyramid = new Pyramid();
        String rst = pyramid.leftTrl(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^")
                                .add("  ^^")
                                .add(" ^^^")
                                .add("^^^^")
                                .toString()
                )
        );
    }

    @Test
    public void whenPyramid() {
        Pyramid pyramid = new Pyramid();
        String str = pyramid.pyramids(4);
        System.out.println(str);
        assertThat(str, is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("   ^   ")
                .add("  ^^^  ")
                .add(" ^^^^^ ")
                .add("^^^^^^^")
                .toString()
        ));
    }
}
