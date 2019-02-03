package userconvert;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
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
    public final HashMap<Integer, User> process(final List<User> list) {
        //пробывал разные варианты. гуглил часа 3. но получается каша.
        //не могу выйти на загрузку значений в мапу. функции не подтягиваются.
        list.stream().collect(HashMap<Integer, User>::new,
                (i, u) -> i.put(u.getId(), new User(u.getName(), u.getCity())));
        // getId пишет используется в статическом контексте.
        //getName getCity не функциональные интерфейсы.
        //не понятно как создать новый обьект используя обьект со стрима.
        list.stream().collect(Collectors.toMap(User::getId, new User(User::getName, User::getCity)));
        //здесь m u не подтягивают функции.
        Collector.of(HashMap<Integer, User>::new, (m, u) -> m.put(u.));
        list.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
        @SuppressWarnings("Duplicates")
        HashMap<Integer, User> map = new HashMap<>();
        // здесь работает но это не не Java8 Stream
        list.forEach(e -> map.put(e.getId(), new User(e.getName(), e.getCity())));
        /*for (User user : list) {
            map.put(user.getId(), new User(user.getName(), user.getCity()));
        }*/
        return map;
    }
}
