package ru.job4j.pseudo;

/**
 * Paint.
 *
 * @author Maxim Vannny.
 * @version 2.0
 * @since 0.1
 */
public class Paint {
    /**
     * Method prints a picture of shape.
     *
     * @param shape reference to interface Shape.
     */
    public final void draw(final Shape shape) {
        System.out.println(shape.draw());
    }

    /**
     * main.
     *
     * @param args strings
     */
    public static void main(final String[] args) {
        var paint = new Paint();
        paint.draw(new Square());
        System.out.println();
        paint.draw(new Triangle());
    }
}
