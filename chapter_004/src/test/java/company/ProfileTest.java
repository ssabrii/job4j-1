package company;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfileTest {
    private final Profiles profiles = new Profiles();
    private final List<Profile> catalog;
    private final Profile first;
    private final Profile second;
    private final Profile third;
    private final Profile four;
    private final Profile fifth;

    {
        first = new Profile(new Address("Zurich", "Manor",
                1, 10));
        second = new Profile(new Address("Geneva", "Manor",
                2, 20));
        third = new Profile(new Address("Munich", "Manor",
                3, 30));
        four = new Profile(new Address("Zurich", "Manor",
                4, 40));
        fifth = new Profile(new Address("Paris", "Manor",
                5, 50));
        catalog = List.of(this.first, this.second,
                this.third, this.four,
                this.fifth);
    }

    @Test
    public void whenCollectAddressFromProfileToList() {
        List<Address> result = this.profiles.collect(catalog);
        assertThat(result.size(), is(5));
        assertThat(result.toString(), is(new StringBuilder()
                .append("[")
                .append(result.get(0).toString())
                .append(", ")
                .append(result.get(1).toString())
                .append(", ")
                .append(result.get(2).toString())
                .append(", ")
                .append(result.get(3).toString())
                .append(", ")
                .append(result.get(4).toString())
                .append("]")
                .toString()
        ));
    }

    @Test
    public void whenDistinctAddress() {
        List<Address> result = this.profiles.distinct(catalog);
        assertThat(result.size(), is(4));
        assertThat(result.toString(), is(new StringBuilder()
                .append("[")
                .append(result.get(0).toString())
                .append(", ")
                .append(result.get(1).toString())
                .append(", ")
                .append(result.get(2).toString())
                .append(", ")
                .append(result.get(3).toString())
                .append("]")
                .toString())
        );
    }
}