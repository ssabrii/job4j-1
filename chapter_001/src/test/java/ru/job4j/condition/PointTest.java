package ru.job4j.condition;

import org.junit.Test;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

public class PointTest {


    @Test
    public void calculateDistanceBetweenTwoPoints() {
        Point a = new Point(0, 1);
        Point b = new Point(2, 5);
        double result = sqrt(pow(a.getX() - b.getX(), 2)
                + pow(a.getY() - b.getY(), 2));
        assertThat(result, closeTo(5.0, 5.0));
    }
}

