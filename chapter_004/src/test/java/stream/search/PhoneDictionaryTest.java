package stream.search;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Donald", "Tramp", "534872", "NY")
        );
        var persons = phones.find("Donald");
        assertThat(persons.iterator().next().getSurname(), is("Tramp"));
    }

    @Test
    public void whenFindBySurname() {
        var phones = new PhoneDictionary();
        phones.add(new Person("James", "Band", "12345", "Zurich"));
        phones.add(new Person("James", "Bond", "12345", "London"));
        var persons = phones.find("Bond");
        assertThat(persons.iterator().next().getSurname(), is("Bond"));
    }

    @Test
    public void whenFindByAddress() {
        var phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "12345", "Zurich"));
        phones.add(new Person("James", "Bond", "12345", "London"));
        var persons = phones.find("London");
        assertThat(persons.iterator().next().getAddress(), is("London"));
    }

    @Test
    public void whenFindByPhone() {
        var phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "02345", "London"));
        phones.add(new Person("James", "Bond", "12345", "Zurich"));
        var person = phones.find("12345");
        assertThat(person.iterator().next().getPhone(), is("12345"));
    }

    @Test
    public void whenFindByNameFall() {
        var phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "12345", "London"));
        var person = phones.find("Java");
        assertTrue(person.isEmpty());

    }

    @Test
    public void whenFindBySurNameFall() {
        var phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "12345", "London"));
        var person = phones.find("Java");
        assertTrue(person.isEmpty());
    }

    @Test
    public void whenFindByAddressFall() {
        var phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "12345", "London"));
        var person = phones.find("Java");
        assertTrue(person.isEmpty());
    }

    @Test
    public void whenFindByPhoneFall() {
        var phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "12345", "London"));
        var person = phones.find("54321");
        assertTrue(person.isEmpty());
    }
}
