package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * SquareTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        String ls = System.lineSeparator();
        assertThat(square.draw(), is(new StringBuilder()
                        .append("+++++")
                        .append(ls)
                        .append("+   +")
                        .append(ls)
                        .append("+   +")
                        .append(ls)
                        .append("+++++")
                        .toString()
                )
        );
    }

}