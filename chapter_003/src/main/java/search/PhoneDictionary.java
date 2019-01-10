package search;

import java.util.ArrayList;
import java.util.List;
/**
 * Person.
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class PhoneDictionary {
    /**
     * List Person.
     */
    private List<Person> persons = new ArrayList<Person>();

    /**
     * Method add new person in List.
     * @param person person.
     */
    public final void add(final Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подошедших пользователей.
     */
    public final List<Person> find(final String key) {
        List<Person> result = new ArrayList<>();
        return result;
    }
}
