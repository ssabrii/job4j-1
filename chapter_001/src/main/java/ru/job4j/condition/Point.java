package ru.job4j.condition;

/**
 * Point.
 * It shows a distance between two points.
 */
public class Point {
    public final int x;
    public final int y;

    /**
     * Constructor.
     *
     * @param x first point.
     * @param y second point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate a distance between two points.
     *
     * @param that second point.
     * @return distance between two points.
     */
    public double distanceTo(Point that) {
        Point a = this;
        return Math.sqrt(Math.pow(a.x - that.x, 2) + Math.pow(a.y - that.y, 2));
    }

    /**
     * Main.
     * The point's enter to program.
     *
     * @param args array string.
     */
    public static void main(String[] args) {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        double result = a.distanceTo(b);
        System.out.println("Расстояние между точками А и В : " + result);
    }
}
