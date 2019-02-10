package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Paint.
 * It paints pyramid.
 *
 * @author Maxim Vanny.
 * @version 1.0
 * @since 0.1
 */
public class Pyramid {
    /**
     * Right level.
     *
     * @param height height
     * @return RL
     */
    public final String rightTrl(final int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Left level.
     *
     * @param height height
     * @return LL
     */
    public final String leftTrl(final int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * bild Pyramid.
     *
     * @param height height
     * @return pyramid
     */
    public final String pyramids(final int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) ->
                        row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * loopBy.
     *
     * @param height  height
     * @param weight  weight
     * @param predict predict
     * @return screen
     */
    private String loopBy(final int height, final int weight,
                          final BiPredicate<Integer, Integer> predict) {
        var screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}


