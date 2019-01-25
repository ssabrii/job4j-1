package stream.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream.
 * PhoneDictionary.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class PhoneDictionary {
    /**
     * List Person.
     */
    private final List<Person> persons = new ArrayList<>();

    /**
     * Method add new person in List.
     *
     * @param person person.
     */
    public final void add(final Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     *
     * @param key Ключ поиска.
     * @return Список подошедших пользователей.
     */
    public final List<Person> find(final String key) {
        return persons.stream()
                .filter(person -> person.getName().contains(key)
                        || person.getSurname().contains(key)
                        || person.getAddress().contains(key)
                        || person.getPhone().contains(key))
                .collect(Collectors.toList());
    }
}

