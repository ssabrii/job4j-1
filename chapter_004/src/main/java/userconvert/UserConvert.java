package userconvert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * UserConvert.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class UserConvert {
    /**
     * Method convert the List of users to Map of users.
     *
     * @param list the list of users
     * @return the Map this users
     */
    public final Map<Integer, User> process(final List<User> list) {
        final Map<Integer, User> map = list.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        user -> new User(user.getName(), user.getCity())));
        //  list.forEach(e -> map.put(e.getId(),
        //  new User(e.getName(), e.getCity())));

        list.stream()
                .collect(Collectors
                        //@param keyMapper a mapping function to produce keys
                        .toMap(User::getId,
                                // @param valueMapper a mapping
                                // function to produce values
                                user -> user,
                                //  @param mergeFunction a merge function,
                                //  used to resolve collisions
                                (a, b) -> a,
                                // between  values associated with the same key,
                                HashMap::new
                                //  @param mapFactory  a supplier
                                //  providing a new empty {@code Map}
                                //   into which the results will be inserted
                        ));
        return map;
    }
}
