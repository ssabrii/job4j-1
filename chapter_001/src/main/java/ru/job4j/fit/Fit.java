package ru.job4j.fit;

/**
 * Программа расчета идеального веса.
 * Fit.
 *
 * @author Maxim Vanny
 * @version 1.0
 * @since 0.1
 */
public class Fit {
    /**
     * Идеальный вес для мужщины.
     *
     * @param height Рост.
     * @return идеальный вес.
     */
    public final double manWeight(final double height) {
        final int first = 100;
        final double percent = 1.15;
        return (height - first) * percent;
    }

    /**
     * Идеальный вес для женщины.
     *
     * @param height Рост.
     * @return идеальный вес.
     */
    public final double womanWeight(final double height) {
        final int first = 110;
        final double percent = 1.15;
        return (height - first) * percent;
    }
}




