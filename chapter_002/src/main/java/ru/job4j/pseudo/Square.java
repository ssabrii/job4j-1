package ru.job4j.pseudo;

/**
 * Square.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class Square implements Shape {
    /**
     * Method prints square.
     *
     * @return a picture of square.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("+++++");
        pic.append(System.lineSeparator());
        pic.append("+   +");
        pic.append(System.lineSeparator());
        pic.append("+   +");
        pic.append(System.lineSeparator());
        pic.append("+++++");
        return pic.toString();
    }
}
