package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;

/**
 * UserConvert.
 *
 * @author Maxim Vanny
 * @version 4.0
 * @since 0.1
 */
public class UserConvert {
    /**
     * Inner static class User.
     */
    public static class User {
        /**
         * name.
         */
        private final String name;

        /**
         * Constructor.
         *
         * @param aName name
         */
        public User(final String aName) {
            this.name = aName;
        }

        @Override
        public final String toString() {
            return new StringJoiner(", ",
                    User.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .toString();
        }
    }

    /**
     * Method convert.
     * @param names names
     * @param op interface Function
     * @return users
     */
    public final List<User> convert(final List<String> names,
                                    final Function<String, User> op) {
        List<User> users = new ArrayList<>();
        names.forEach(
                n -> users.add(op.apply(n))
        );
        return users;
    }

    /**
     * Point enter to program.
     * @param args strings array args
     */
    public static void main(final String[] args) {
        List<String> names = Arrays.asList("Petr", "Nick", "Ban");
        UserConvert users = new UserConvert();
        List<User> data = users.convert(names, User::new);
        data.forEach(System.out::println);

    }
}
