package ru.job4j.loop;

/**
 * Board.
 * Simple board.
 *
 * @author Maxim Vanny.
 * @version 1.0
 * @since 0.1
 */
public class Board {
    /**
     * Method pains board.
     *
     * @param width  width boar.
     * @param height height board.
     * @return board.
     */
    public final String paint(final int width, final int height) {
        var screen = new StringBuilder();
        var ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // условие проверки, что писать пробел или X
                // Выше в задании мы определили закономерность,
                // когда нужно проставлять X
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод на новую строку.
            screen.append(ln);
        }
        return screen.toString();
    }
}
