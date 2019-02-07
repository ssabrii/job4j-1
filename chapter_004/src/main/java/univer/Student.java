package univer;

import java.util.Comparator;
import java.util.StringJoiner;

/**
 * Student.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class Student implements Comparator<Student> {
    /**
     * FIO student.
     */
    private final String name;
    /**
     * Scope attested.
     */
    private final int scope;

    @Override
    public String toString() {
        return new StringJoiner(", ",
                Student.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("scope=" + scope)
                .toString();
    }

    /**
     * getter name.
     *
     * @return name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * getter scope.
     *
     * @return scope
     */
    public final int getScope() {
        return this.scope;
    }

    /**
     * Constructor.
     *
     * @param aName  name
     * @param aScope scope
     */
    public Student(final String aName, final int aScope) {
        this.name = aName;
        this.scope = aScope;
    }

    @Override
    public int compare(final Student o1, final Student o2) {
        return o1.scope - o2.scope;
    }
}
