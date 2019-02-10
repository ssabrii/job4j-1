package ru.job4j.condition;

/**
 * Point.
 *
 * @author Maxim Vanny.
 * @version 1.0
 * @since 0.1
 */
public class Point {
    /**
     * field x.
     */
    private final int x;
    /**
     * field y.
     */
    private final int y;

    /**
     * Constructor.
     *
     * @param aX first point.
     * @param aY second point.
     */
    public Point(final int aX, final int aY) {
        this.x = aX;
        this.y = aY;
    }

    /**
     * Getter.
     *
     * @return x
     */
    @SuppressWarnings("unused")
    public final int getX() {
        return this.x;
    }

    /**
     * Getter.
     *
     * @return y
     */
    @SuppressWarnings("unused")
    public final int getY() {
        return this.y;
    }

    /**
     * Calculate a distance between two points.
     *
     * @param that second point.
     * @return distance between two points.
     */
    public final double distanceTo(final Point that) {
        Point a = this;
        return Math.sqrt(Math.pow(a.x - that.x, 2) + Math.pow(a.y - that.y, 2));
    }

    /**
     * Main.
     * The point's enter to program.
     *
     * @param args array string.
     */
    public static void main(final String[] args) {
        final int magic = 5;
        Point a = new Point(0, 1);
        Point b = new Point(2, magic);
        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками А и В : " + result);
    }
}
