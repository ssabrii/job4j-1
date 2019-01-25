package stream.search;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Donald", "Tramp", "534872", "NY")
        );
        List<Person> persons = phones.find("Donald");
        assertThat(persons.iterator().next().getSurname(), is("Tramp"));
    }

    @Test
    public void whenFindBySurname() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("James", "Band", "12345", "Zurich"));
        phones.add(new Person("James", "Bond", "12345", "London"));
        List<Person> persons = phones.find("Bond");
        assertThat(persons.iterator().next().getSurname(), is("Bond"));
    }

    @Test
    public void whenFindByAddress() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "12345", "Zurich"));
        phones.add(new Person("James", "Bond", "12345", "London"));
        List<Person> persons = phones.find("London");
        assertThat(persons.iterator().next().getAddress(), is("London"));
    }

    @Test
    public void whenFindByPhone() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "02345", "London"));
        phones.add(new Person("James", "Bond", "12345", "Zurich"));
        List<Person> person = phones.find("12345");
        assertThat(person.iterator().next().getPhone(), is("12345"));
    }

    @Test
    public void whenFindByNameFall() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "12345", "London"));
        List<Person> person = phones.find("Java");
        assertTrue(person.isEmpty());

    }

    @Test
    public void whenFindBySurNameFall() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "12345", "London"));
        List<Person> person = phones.find("Java");
        assertTrue(person.isEmpty());
    }

    @Test
    public void whenFindByAddressFall() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "12345", "London"));
        List<Person> person = phones.find("Java");
        assertTrue(person.isEmpty());
    }

    @Test
    public void whenFindByPhoneFall() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("James", "Bond", "12345", "London"));
        List<Person> person = phones.find("54321");
        assertTrue(person.isEmpty());
    }
}
