package ru.job4j.array;

import java.util.stream.IntStream;

/**
 * decorator for string.
 *
 * @author Max Vanny.
 * @version 1.0
 * @since 0.1
 */
public class ArrayChar {
    /**
     * source string.
     */
    private final char[] data;

    /**
     * Constructor.
     * @param line string
     */
    public ArrayChar(final String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет, что слово начинается с префикса.
     *
     * @param prefix префикс.
     * @return boolean
     */
    public final boolean startWith(final String prefix) {
        return IntStream.range(0, prefix.length())
                .filter(z -> prefix.charAt(z) != this.data[z])
                .noneMatch(z -> z != 0);
    }
}
