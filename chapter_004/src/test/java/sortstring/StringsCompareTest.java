package sortstring;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringsCompareTest {
    @Test
    public void whenStringsAreEqualThenZero() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(rst, is(0));
    }

    @Test
    public void whenLeftLessThanRightResultBeNegative() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst, lessThan(0));
    }
    @Test
    public void whenLeftGreaterThanRightResultBePositive() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Ivanova",
                "Ivanov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void firstCharOfLeftGreaterThanFirstCharRightResultBePositive() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightBePositive() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftLessThanRightBeNegative() {
        StringsCompare compare = new StringsCompare();
        int rst = compare.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }
}