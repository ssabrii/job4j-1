package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAdd1Until3() {
        Calculator calc = new Calculator();
        List<Double> buffer = new ArrayList<>();
        calc.multiple(
                0, 3, 1,
                MathUtil::add,
                buffer::add
        );
        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D)));
    }

    @Test
    public void whenLinearFunctionThenLinearResults() {
        Calculator calc = new Calculator();
        List<Double> result = calc.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSqrtFunctionThenSqrtResult() {
        Calculator calc = new Calculator();
        List<Double> result = calc.diapason(5, 8, Math::sqrt);
        List<Double> expected = Arrays.asList(
                2.23606797749979,
                2.449489742783178,
                2.6457513110645907
        );
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogFunctionThenLogResult() {
        Calculator calc = new Calculator();
        List<Double> result = calc.diapason(5, 8, MathUtil::logr);
        List<Double> expected = Arrays.asList(
                1.6094379124341003,
                1.791759469228055,
                1.94591014905531324D
        );
        assertThat(result, is(expected));
    }
}