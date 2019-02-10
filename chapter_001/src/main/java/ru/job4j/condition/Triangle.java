package ru.job4j.condition;

/**
 * Point.
 *
 * @author Maxim Vanny.
 * @version 1.0
 * @since 0.1
 */
public class Triangle {
    /**
     * field a.
     */
    private final Point a;
    /**
     * field b.
     */
    private final Point b;
    /**
     * field c.
     */
    private final Point c;

    /**
     * Constructor.
     *
     * @param aA first point.
     * @param aB second point.
     * @param ac second point.
     */
    public Triangle(final Point aA, final Point aB, final Point ac) {
        this.a = aA;
        this.b = aB;
        this.c = ac;
    }

    /**
     * Метод вычисления полупериметра по длинам сторон.
     * <p>
     * Формула.
     * <p>
     * (ab + ac + bc) / 2
     *
     * @param ab расстояние между точками a b.
     * @param ac расстояние между точками a c.
     * @param bc расстояние между точками b c.
     * @return Периметр.
     */
    public final double period(final double ab,
                               final double ac,
                               final double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Метод должен вычислить площадь треугольника.
     *
     * @return прощадь, если треугольник существует, если треугольника нет -1.
     */
    public final double area() {
        double rsl = -1;
        // мы устанавливаем значение -1, так как может быть,
        // что треугольника нет.
        final double ab = this.a.distanceTo(this.b);
        final double ac = this.a.distanceTo(this.c);
        final double bc = this.b.distanceTo(this.c);
        final double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            // написать формулу для расчета площади треугольника.
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Метод проверяет можно ли построить треугольник с такими длинами сторон.
     *
     * @param ab Длина от точки a b.
     * @param ac Длина от точки a c.
     * @param bc Длина от точки b c.
     * @return false.
     */
    private boolean exist(final double ab,
                          final double ac,
                          final double bc) {
        return (ab + ac > bc) && (ab + bc > ac) && (ac + bc > ab);
    }
}
