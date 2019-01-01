package ru.job4j.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * PaintTest.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class PaintTest {

    @Test
    public void whenDrawSquare() {
        //сылка на стандартный вывод в консоль.
        PrintStream stdout = System.out;
        // This class implements an output stream in which the data is
        // written into a byte array
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //Reassigns the "standard" output stream.
        //изменение потока вывода.
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                        .append("+++++")
                        .append(System.lineSeparator())
                        .append("+   +")
                        .append(System.lineSeparator())
                        .append("+   +")
                        .append(System.lineSeparator())
                        .append("+++++")
                        .append(System.lineSeparator())
                        .toString()
                )
        );
        System.setOut(stdout);
    }

    @Test
    public void whenDrawTriangle() {
        //reference to standard output stream
        PrintStream stdout = System.out;
        //reference to byte array in stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //change output stream
        System.setOut(new PrintStream(out));
        //create Paint c method draw() with object Triangle
        new Paint().draw(new Triangle());
        assertThat(new String(out.toByteArray()), is(
                new StringBuilder()
                        .append("   +")
                        .append(System.lineSeparator())
                        .append("  + +")
                        .append(System.lineSeparator())
                        .append(" +   +")
                        .append(System.lineSeparator())
                        .append("+++++++")
                        .append(System.lineSeparator())
                        .toString()
                )
        );
        System.setOut(stdout);
    }
}
