package streamexample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * StreamUsages.
 *
 * @author Maxim Vanny.
 * @version 4.0
 * @since 0.1
 */
public class StreamUsage {
    /**
     * Constructor.
     */
    protected StreamUsage() {
    }

    /**
     * Inner class.
     */
    public static class Task {
        /**
         * name.
         */
        private final String name;
        /**
         * spent.
         */
        private final long spent;

        /**
         * Constructor.
         *
         * @param aName  name
         * @param aSpent spent
         */
        public Task(final String aName, final long aSpent) {
            this.name = aName;
            this.spent = aSpent;
        }

        @Override
        public final String toString() {
            return "Task{"
                    + "name='"
                    + name
                    + '\''
                    + ", spent="
                    + spent
                    + '}';
        }
    }

    /**
     * Index in array.
     *
     * @param names array
     * @return collect
     */

    public final List<String> getEvenIndexedStrings(final String[] names) {
        return IntStream
                .range(0, names.length)
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> names[i])
                .collect(Collectors.toList());
    }

    /**
     * map stream.
     *
     * @param nestedList source list
     * @return result list
     */
    public final List<String> flattenListOfListsImperatively(
            final List<List<String>> nestedList) {
        List<String> ls = new ArrayList<>();
        nestedList.forEach(ls::addAll);
        return ls;
    }

    /**
     * flatMap stream.
     *
     * @param list list
     * @param <T>  T
     * @return list
     */
    public final <T> List<T> flattenListOfListsStream(
            final List<List<T>> list) {
        return list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * point to program.
     *
     * @param args args
     */
    public static void main(final String[] args) {
        final int spent = 100;
        List<Task> tasks = List.of(
                new Task("Bug #1", spent),
                new Task("Task #2", spent),
                new Task("Bug #3", spent)
        );
        //фильтрация
        List<Task> bugs = tasks
                .stream()
                .filter(task -> task.name.contains("Bug"))
                .collect(Collectors.toList());
        bugs.forEach(System.out::println);
        //преобразование
        List<String> names = tasks
                .stream()
                .map(task -> task.name)
                .collect(Collectors.toList());
        names.forEach(System.out::println);
        //упрощение
        long total = tasks
                .stream()
                .map(task -> task.spent)
                .reduce(0L, Long::sum);
        System.out.println(total);

        final int maxSize = 3;
        final int[] ints = {100, 200, 250, 320, 314, 502};
        Predicate<Integer> predicate = p -> {
            final int i1 = 300;
            return p < i1;
        };
        IntStream.of(ints)
                .boxed()
                .filter(predicate)
                .map(x -> x + 1)
                .limit(maxSize)
                .forEach((i) -> System.out.print(i + " "));

        System.out.println();
        Stream<Task> stream = tasks.stream();
        stream.forEach(System.out::println);
        System.out.println();

        tasks.stream()
                .filter(s -> s.name.contains("Bug #1"))
                .forEach(System.out::println);

    }
}
          /*
        System.out.println("parallelStream-FILTERED-MAP-COLLECT-105.");
        List<Integer> list = List.of(10, 20, 30, 40)
                .parallelStream()
                .filter(x -> x > 10)
                .map(x -> x * 2)
                .collect(Collectors.toList());
        list.forEach((i) -> System.out.print(i + " "));
        System.out.println();

        System.out.println("IntStream.RANGE-MAP-SUM-115");
        int sum = IntStream.range(0, 3)
                .parallel()
                .map(x -> x * 10)
                .sum();
        System.out.println(sum);

        System.out.println("list IntStream.RANGE-122");
        final List<Integer> ints = new ArrayList<>();
        IntStream.range(0, 1000000)
                //опасно. или исключение или неверный результат
                // .parallel()
                .forEach(ints::add);
        System.out.println(ints.size());

        System.out.println("Stream.OF-130");
        Stream.of(1, 2, 3)
                .forEach((i) -> System.out.print(i + " "));
        System.out.println();

        System.out.println("Stream.OFNULLABLE-134");
        String str;
        if (Math.random() > 0.5) {
            str = "I'm feeling lucky";
        } else {
            str = null;
        }
        Stream.ofNullable(str)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Stream.GENERATE-144");
        Stream.generate(() -> 6)
                .limit(6)
                .forEach((i) -> System.out.print(i + " "));
        // 6, 6, 6, 6, 6, 6
        System.out.println();

        System.out.println("Stream.ITERATE-161");
        Stream.iterate(2, x -> x + 6)
                .limit(2)
                .forEach((i) -> System.out.print(i + " "));
        // 2, 8
        System.out.println();

        System.out.println("Stream.ITERATE-for-158");
        Stream.iterate(2, x -> x < 25, x -> x + 6)
                .forEach((i) -> System.out.print(i + " "));
        // 2, 8, 14, 20
        System.out.println();

        System.out.println("Stream.CONCAT-163");
        Stream.concat(
                Stream.of(1, 2, 3),
                Stream.of(4, 5, 6))
                .forEach((i) -> System.out.print(i + " "));
        // 1, 2, 3, 4, 5, 6
        System.out.println();

        System.out.println("Stream.CONCAT-170");
        Stream.concat(
                Stream.of(10),
                Stream.of(4, 16))
                .forEach((i) -> System.out.print(i + " "));
        // 10, 4, 16
        System.out.println();
        System.out.println("Stream.BUILDER-ADD-FOR-180");
        Stream.Builder<Integer> integerBuilder = Stream.<Integer>builder()
                .add(0)
                .add(1);
        for (int i = 2; i <= 8; i += 2) {
            integerBuilder.accept(i);
        }
        integerBuilder
                .add(9)
                .add(10)
                .build()
                .forEach((i) -> System.out.print(i + " "));
        // 0, 1, 2, 4, 6, 8, 9, 10
        System.out.println();
        System.out.println("Stream.RANGE-194");
        IntStream.range(0, 10)
                .forEach((i) -> System.out.print(i + " "));
        // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        System.out.println();

        System.out.println("LongStream.RANGE-200");
        LongStream.range(-10L, -5L)
                .forEach((i) -> System.out.print(i + " "));
        // -10, -9, -8, -7, -6
        System.out.println();
        System.out.println("IntStream.RANGECLOSE-205");
        IntStream.rangeClosed(0, 5)
                .forEach((i) -> System.out.print(i + " "));
        // 0, 1, 2, 3, 4, 5
        System.out.println("\nLongStream.-RANGE -209");
        LongStream.range(-8L, -5L)
                .forEach((i) -> System.out.print(i + " "));
        // -8, -7, -6, -5
        System.out.println();

        System.out.println("Stream.OF-215- without exit\n");
        Stream.of(1, 2, 3)
                //   .filter(x -> x == 10)
                .forEach((i) -> System.out.print(i + " "));
        // Вывода нет, так как после фильтрации стрим станет пустым
        System.out.println();

        System.out.println("Stream.OF");
        Stream.of(120, 410, 85, 32, 314, 12)
                .filter(x -> x > 100)
                .forEach((i) -> System.out.print(i + " "));
        // 120, 410, 314
        System.out.println();

        System.out.println("Stream.of-MAP::-MAP+ -229");
        //Применяет функцию к каждому элементу и затем возвращает стрим,
        // в котором элементами будут результаты
        // функции. map можно применять для изменения типа элементов.
        Stream.of("3", "4", "5")
                .map(Integer::parseInt)
                .map(x -> x + 10)
                .forEach((i) -> System.out.print(i + " "));
        // 13, 14, 15
        System.out.println();

        System.out.println("Stream.of-MAP+-240");
        Stream.of(120, 410, 85, 32, 314, 12)
                .map(x -> x + 11)
                .forEach((i) -> System.out.print(i + " "));
        // 131, 421, 96, 43, 325, 23
        System.out.println();

        System.out.println("Stream.of-flatMapToInt-IntStream.RANGE-247");
        //Работает как map, но с одним отличием
        // — можно преобразовать один элемент в ноль,
        // один или множество других.
        // Как и в случае с map,
        // служат для преобразования в примитивный стрим.
        // Для того, чтобы один элемент преобразовать в ноль элементов,
        // нужно вернуть null, либо пустой стрим.
        // Чтобы преобразовать в один элемент,
        // нужно вернуть стрим из одного элемента,
        // например, через Stream.of(x). Для возвращения нескольких элементов,
        // можно любыми способами создать стрим с этими элементами.
        Stream.of(2, 3, 0, 1, 3)
                .flatMapToInt(x -> IntStream.range(0, x))
                .forEach((i) -> System.out.print(i + " "));
        // 0, 1, 0, 1, 2, 0, 0, 1, 2
        System.out.println();

        System.out.println("Stream.of-LIMIT-265");
        //Ограничивает стрим maxSize элементами.
        Stream.of(120, 410, 85, 32, 314, 12)
                .limit(4)
                .forEach((i) -> System.out.print(i + " "));
        // 120, 410, 85, 32
        System.out.println();

        System.out.println("Stream.of-SKIP-272-without exit\n");
        //Пропускает n элементов стрима.
        Stream.of(5, 10)
                .skip(40)
                .forEach((i) -> System.out.print(i + " "));
        // Вывода нет

        System.out.println("Stream.of-SKIP-280");
        //Пропускает n элементов стрима.
        Stream.of(120, 410, 85, 32, 314, 12)
                .skip(2)
                .forEach((i) -> System.out.print(i + " "));
        // 85, 32, 314, 12
        System.out.println();
        System.out.println("Stream.range-SORTED-limit-287");
        IntStream.range(0, 100000000)
                .sorted()
                .limit(3)
                .forEach((i) -> System.out.print(i + " "));
        // 0, 1, 2
        System.out.println();
        System.out.println("Stream.range-SORTED-limit-294");
        IntStream.concat(
                IntStream.range(0, 100000000),
                IntStream.of(-1, -2))
                .sorted()
                .limit(3)
                .forEach((i) -> System.out.print(i + " "));
        // Exception in thread
        // "main" java.lang.OutOfMemoryError: Java heap space
        System.out.println();
        System.out.println("Stream.range-SORTED-limit-303");
        Stream.of(120, 410, 85, 32, 314, 12)
                .sorted()
                .forEach((i) -> System.out.print(i + " "));
        // 12, 32, 85, 120, 314, 410
        System.out.println();

        System.out.println("Stream.DISTINCT -310");
        //Убирает повторяющиеся элементы
        // и возвращаем стрим с уникальными элементами
        Stream.of(2, 1, 8, 1, 3, 2)
                .distinct()
                .forEach((i) -> System.out.print(i + " "));
        // 2, 1, 8, 3
        System.out.println();
        System.out.println("PEEK-318");
        Stream.of(0, 3, 0, 0, 5)
                .peek(x -> System.out.format("before distinct: %d%n", x))
                .distinct()
                .peek(x -> System.out.format("after distinct: %d%n", x))
                .map(x -> x * x)
                .forEach(x -> System.out.format("after map: %d%n", x));
        // before distinct: 0
        // after distinct: 0
        // after map: 0
        // before distinct: 3
        // after distinct: 3
        // after map: 9
        // before distinct: 1
        // after distinct: 1
        // after map: 1
        // before distinct: 5
        // before distinct: 0
        // before distinct: 5
        // after distinct: 5
        // after map: 25
        System.out.println();
        System.out.println("takeWHILE-340");
        Stream.of(1, 2, 3, 4, 2, 5)
                .takeWhile(x -> x < 3)
                .forEach((i) -> System.out.print(i + " "));
        // 1, 2
        System.out.println();
        System.out.println("dropWHILE");
        Stream.of(1, 2, 3, 4, 2, 5)
                .dropWhile(x -> x >= 3)
                .forEach((i) -> System.out.print(i + " "));
        // 1, 2, 3, 4, 2, 5
        System.out.println();
        Stream.of(1, 2, 3, 4, 2, 5)
                .dropWhile(x -> x < 3)
                .forEach((i) -> System.out.print(i + " "));
        // 3, 4, 2, 5
        System.out.println();
        IntStream.range(2, 7)
                .dropWhile(x -> x < 5)
                .forEach((i) -> System.out.print(i + " "));
        // 5, 6
        System.out.println();
        IntStream.of(1, 3, 2, 0, 5, 4)
                .dropWhile(x -> x % 2 == 1)
                .forEach((i) -> System.out.print(i + " "));
        // 2, 0, 5, 6
        System.out.println();
        System.out.println("BOXED-368");
        //Преобразует примитивный стрим в объектный.
        DoubleStream.of(0.1, Math.PI)
                .boxed()
                .map(Object::getClass)
                .forEach((i) -> System.out.print(i + " "));
        // class java.lang.Double
        // class java.lang.Double
        System.out.println();
        System.out.println("forEACH-377");
        Stream.of(120, 410, 85, 32, 314, 12)
                .forEach(x -> System.out.format("%s, ", x));
        // 120, 410, 85, 32, 314, 12
        System.out.println();
        System.out.println("forEACH-map-filtered -382");
        IntStream.range(0, 100000)
                .parallel()
                .filter(x -> x % 10000 == 0)
                .map(x -> x / 10000)
                .forEach((i) -> System.out.print(i + " "));
        // 5, 6, 7, 3, 4, 8, 0, 9, 1, 2
        System.out.println();
        IntStream.range(0, 100000)
                .parallel()
                .filter(x -> x % 10000 == 0)
                .map(x -> x / 10000)
                .forEachOrdered(System.out::print);
        // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        System.out.println();
        System.out.println("COLLECT-toLIST -397");
        List<Integer> myList = Stream.of(1, 2, 3)
                .collect(Collectors.toList());
        myList.forEach(System.out::print);
        // list: [1, 2, 3]
        System.out.println();
        System.out.println("COLLECT-JOINING -403");
        String s = Stream.of(1, 2, 3)
                .map(String::valueOf)
                .collect(Collectors.joining("-", "<", ">"));
        System.out.println(s);
        // s: "<1-2-3>"
        System.out.println("collect(Supplier supplier, " +
                "BiConsumer accumulator, BiConsumer combiner-412");
        System.out.println(
        ".collect(ArrayList::new, ArrayList::add, ArrayList::addAll);");
        List<String> nList = Stream.of("a", "b", "c", "d")
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(nList);
        // list: ["a", "b", "c", "d"]
        System.out.println("toArray-418");
        //Возвращает нетипизированный массив с элементами стрима.
        String[] elements = Stream.of("a", "b", "c", "d")
                .toArray(String[]::new);
        System.out.println(Arrays.toString(elements));
        // elements: ["a", "b", "c", "d"]
        System.out.println("REDUCE-424");
        sum = Stream.of(1, 2, 3, 4, 5)
                .reduce(10, (acc, x) -> acc + x);
        System.out.println(sum);
        // sum: 25
        System.out.println("REDUCE-Optional-429");
        Optional<Integer> result = Stream.<Integer>empty()
                .reduce((acc, x) -> acc + x);
        System.out.println(result.isPresent());
        // false
        System.out.println("REDUCE-Optional-(acc,x)-439");
        Optional<Integer> summer = Stream.of(1, 2, 3, 4, 5)
                .reduce((acc, x) -> acc + x);
        System.out.println(summer.get());
        // 15
        System.out.println("REDUCE-Optional-REDUCE(ident(acc,x))-439");
        sum = IntStream.of(2, 4, 6, 8)
                .reduce(5, (acc, x) -> acc + x);
        // sum: 25
        System.out.println(sum);
        System.out.println("REDUCE-getAsInt-444");
        int product = IntStream.range(0, 10)
                .filter(x -> x++ % 4 == 0)
                .reduce((acc, x) -> acc * x)
                .getAsInt();
        System.out.println(product);
        // product:
        System.out.println("MIN-451");
        int min = Stream.of(20, 11, 45, 78, 13)
                .min(Integer::compare).get();
        System.out.println(min);
        // min: 11

        System.out.println("MAX-457");
        int max = Stream.of(20, 11, 45, 78, 13)
                .max(Integer::compare).get();
        System.out.println(max);
        // max: 78

        int anySeq = IntStream.range(4, 65536)
                .findAny()
                .getAsInt();
        // anySeq: 4
        System.out.println();
        System.out.println("findANY,findFirst-468");
        int firstSeq = IntStream.range(4, 65536)
                .findFirst()
                .getAsInt();
        System.out.println(firstSeq);
        // firstSeq: 4

        int anyParallel = IntStream.range(4, 65536)
                .parallel()
                .findAny()
                .getAsInt();
        System.out.println(anyParallel);
        // anyParallel: 32770

        int firstParallel = IntStream.range(4, 65536)
                .parallel()
                .findFirst()
                .getAsInt();
        System.out.println(firstParallel);
        // firstParallel: 4
        System.out.println();
        System.out.println("AllMATCH,anyMATCH,noneMATCH-489");
        System.out.println("Predicate");
        boolean bolResult = Stream.of(1, 2, 3, 4, 5)
                .allMatch(x -> x < 3);
        System.out.println(bolResult);
        // result: false

        bolResult = Stream.of(1, 2, 3, 4, 5)
                .anyMatch(x -> x == 3);
        System.out.println(bolResult);
        // result: true

        bolResult = Stream.of(1, 2, 3, 4, 5)
                .noneMatch(x -> x == 3);
        System.out.println(bolResult);
        // result: false
        System.out.println("OptionalDouble average()-505");
        double dResult = IntStream.range(2, 16)
                .average()
                .getAsDouble();
        System.out.println(dResult);
        // result: 8.5
        System.out.println("SUM-511");
        long lResult = LongStream.range(2, 16)
                .sum();
        System.out.println(lResult);
        // result: 119
        System.out.println("IntSummaryStatistics summaryStatistics()-516");
        LongSummaryStatistics stats = LongStream.range(2, 16)
                .summaryStatistics();
        System.out.format("  count: %d%n", stats.getCount());
        System.out.format("    sum: %d%n", stats.getSum());
        System.out.format("average: %.1f%n", stats.getAverage());
        System.out.format("    min: %d%n", stats.getMin());
        System.out.format("    max: %d%n", stats.getMax());
        //   count: 14
        //     sum: 119
        // average: 8,5
        //     min: 2
        //     max: 15
    }
}
*/
