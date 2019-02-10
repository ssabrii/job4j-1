package ru.job4j.loop;

/**
 * Paint.
 * It paints pyramid.
 *
 * @author Maxim Vanny.
 * @version 1.0
 * @since 0.1
 */
public class Paint {
    /**
     * Method pains right side pyramid..
     *
     * @param height height pyramid.
     * @return pyramid.
     */
    public final String rightTrl(final int height) {
        // Буфер для результата.
        var screen = new StringBuilder();
        // ширина будет равна высоте.
        // внешний цикл двигается по строкам.
        for (int row = 0; row != height; row++) {
            // внутренний цикл определяет положение ячейки в строке.
            for (int column = 0; column != height; column++) {
                // если строка равна ячейки, то рисуем галку.
                // в данном случае строка определяем,
                // сколько галок будет на строке
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод строки.
            screen.append(System.lineSeparator());
        }
        // Получаем результат.
        return screen.toString();
    }

    /**
     * Method pains left side pyramid.
     *
     * @param height height pyramid.
     * @return pyramid.
     */
    public final String leftTrl(final int height) {
        var screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != height; column++) {
                if (row >= height - column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     * Method paints simple pyramid.
     *
     * @param height height pyramid.
     * @return pyramid.
     */
    public final String pyramid(final int height) {
        var screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
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
