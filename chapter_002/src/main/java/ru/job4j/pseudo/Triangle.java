package ru.job4j.pseudo;

/**
 * Triangle.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class Triangle implements Shape {
    /**
     * Method prints a pictures of triangle.
     *
     * @return a picture of triangle.
     */
    @Override
    public String draw() {
        StringBuilder pic = new StringBuilder();
        pic.append("   +");
        pic.append(System.lineSeparator());
        pic.append("  + +");
        pic.append(System.lineSeparator());
        pic.append(" +   +");
        pic.append(System.lineSeparator());
        pic.append("+++++++");
        return pic.toString();

    }
}
