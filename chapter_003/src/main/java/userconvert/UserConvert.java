package userconvert;

import java.util.HashMap;
import java.util.List;

/**
 * UserConvert.
 *
 * @author Maxim Vanny.
 * @version 2.0
 * @since 0.1
 */
public class UserConvert {
    /**
     * Method convert the List of users to Map of users.
     *
     * @param list the list of users
     * @return the Map this users
     */
    public final HashMap<Integer, User> process(final List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }
}
