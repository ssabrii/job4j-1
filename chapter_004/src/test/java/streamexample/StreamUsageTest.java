package streamexample;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@SuppressWarnings("SpellCheckingInspection")
public class StreamUsageTest {
    @Test
    public void whenCalledthenReturnListOfEvenIndexedStrings() {
        StreamUsage usage = new StreamUsage();
        String[] names
                = {"Afrim", "Bashkim", "Besim", "Lulzim", "Durim", "Shpetim"};
        List<String> expected
                = asList("Afrim", "Besim", "Durim");
        List<String> result
                = usage.getEvenIndexedStrings(names);
        assertEquals(expected, result);
    }

    @Test
    public void givenNestedListthenFlattenImperatively() {
        StreamUsage usage = new StreamUsage();
        List<List<String>> nestedList = List.of(
                List.of("one:one"),
                List.of("two:one", "two:two", "two:three"),
                List.of("three:one", "three:two", "three:three", "three:four"));
        List<String> result = usage.flattenListOfListsImperatively(nestedList);
        assertNotNull(result);
        assertEquals(8, result.size());
        assertThat(result, IsIterableContainingInOrder.contains(
                "one:one",
                "two:one", "two:two", "two:three", "three:one",
                "three:two", "three:three", "three:four"));
    }

    @Test
    public void givenNestedListthenFlattenFunctionally() {
        StreamUsage usage = new StreamUsage();
        List<List<String>> nestedList = List.of(
                List.of("one:one"),
                List.of("two:one", "two:two", "two:three"),
                List.of("three:one", "three:two", "three:three", "three:four"));
        List<String> ls = usage.flattenListOfListsStream(nestedList);
        assertNotNull(ls);
        assertEquals(8, ls.size());
    }

    @Test
    public void givenListwhenSatifyPredicatethenMapValueWithOccurences() {
        List<Integer> numbers = List.of(1, 2, 3, 5, 5);
        Map<Integer, Long> result = numbers.stream()
                .filter(val -> val > 3)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        assertEquals(1, result.size());
        result = numbers.stream()
                .collect(Collectors.groupingBy(i -> i,
                        Collectors.filtering(val -> val > 3, Collectors.counting())));

        assertEquals(4, result.size());
    }

    @Test
    public void whenStream() {
        Stream<String> onceModifiedStream =
                Stream.of("abcd", "bbcd", "cbcd").skip(1);
        onceModifiedStream.forEach(System.out::print);
        System.out.println();
        Stream<String> twiceModifiedStream =
                Stream.of("abcd", "bbcd", "cbcd")
                        .skip(1)
                        .map(element -> element.substring(0, 3));
        twiceModifiedStream.forEach(System.out::println);
    }

    // intermediate operations which reduce the size of the stream
    // should be placed before operations which are applying to each element.
    @Test
    public void whenList() {
        List<String> list = List.of("abc1", "abc2", "2abc");
        long size = list.stream().skip(1)
                .map(element -> element.substring(0, 3)).count();
        System.out.println(size);
        Optional<String> stream = list.stream().filter(element -> {
            System.out.println("filter() was called");
            return element.contains("2");
        }).map(element -> {
            System.out.println("map() was called");
            return element.toUpperCase();
        }).findAny();
        System.out.println(stream);
        final AtomicInteger count = new AtomicInteger();
        long length = list.stream().map(element -> {
            count.getAndIncrement();
            return element.substring(0, 3);
        }).skip(2).count();
        System.out.println(length + " " + count);
        int[] coin = {0};
        long length1 = list.stream().skip(2).map(element -> {
            coin[0]++;
            return element.substring(0, 3);
        }).count();
        System.out.println(length1 + " " + coin[0]);
    }

    @Test
    public void whenReduce() {
        //0+1+2+3=6
        OptionalInt reduced =
                IntStream.range(1, 4).reduce((a, b) -> a + b);
        reduced.ifPresent(System.out::println);

        //10+1+2+3=16
        int reducedTwoParams =
                IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
        System.out.println(reducedTwoParams);
        //
        int reducedParams = Stream.of(1, 2, 3)
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called");
                    return a + b;
                });
        System.out.println(reducedParams);
        //parallel
        //(10 + 1 = 11; 10 + 2 = 12; 10 + 3 = 13
        //11+12+13=36
        int reducedParallel = List.of(1, 2, 3).parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called");
                    return a + b;
                });
        System.out.println(reducedParallel);
        /*REDUCE SUM

        //.reduce(0, ArithmeticUtils::add);
        public static Integer getSumUsingCustomizedAccumulator (List < Integer > integers) {
            return integers.stream()
                    .reduce(0, ArithmeticUtils::add);
        }

        //.reduce(0, Integer::sum);
        public static Integer getSumUsingJavaAccumulator (List < Integer > integers) {
            return integers.stream()
                    .reduce(0, Integer::sum);
        }

        //  .reduce(0, (a, b) -> a + b);
        public static Integer getSumUsingReduce (List < Integer > integers) {
            return integers.stream()
                    .reduce(0, (a, b) -> a + b);
        }

         //.collect(Collectors.summingInt(Integer::intValue));
        public static Integer getSumUsingCollect (List < Integer > integers) {
            return integers.stream()
                    .collect(Collectors.summingInt(Integer::intValue));
        }

       //       .mapToInt(Integer::intValue)
       //                    .sum();
        public static Integer getSumUsingSum (List < Integer > integers) {
            return integers.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        // .mapToInt(Integer::valueOf)
        //                    .sum();
        public static Integer getSumOfMapValues (Map < Object, Integer > map){
            return map.values()
                    .stream()
                    .mapToInt(Integer::valueOf)
                    .sum();
        }

     //.mapToInt(Integer::valueOf)
     //                    .sum();
        public static Integer getSumIntegersFromString (String str){
            Integer sum = Arrays.stream(str.split(" "))
                    .filter((s) -> s.matches("\\d+"))
                    .mapToInt(Integer::valueOf)
                    .sum();
            return sum;
        }

    //  .reduce(0, ArithmeticUtils::add);
     public static Integer getSumUsingCustomizedAccumulator(List<Item> items) {
        return items.stream()
            .map(x -> x.getPrice())
            .reduce(0, ArithmeticUtils::add);
    }

    // .reduce(0, Integer::sum);
    public static Integer getSumUsingJavaAccumulator(List<Item> items) {
        return items.stream()
            .map(x -> x.getPrice())
            .reduce(0, Integer::sum);
    }

    // .reduce(0, (a, b) -> a + b);
    public static Integer getSumUsingReduce(List<Item> items) {
        return items.stream()
            .map(item -> item.getPrice())
            .reduce(0, (a, b) -> a + b);
    }

    //.collect(Collectors.summingInt(Integer::intValue));
    public static Integer getSumUsingCollect(List<Item> items) {
        return items.stream()
            .map(x -> x.getPrice())
            .collect(Collectors.summingInt(Integer::intValue));
    }

    // .sum();
    public static Integer getSumUsingSum(List<Item> items) {
        return items.stream()
            .mapToInt(x -> x.getPrice())
            .sum();
    }
        */
    }

    @Test
    public void whenCollector() {
        class Product {
            private final int counts;
            private final String name;

            public Product(final int aCounts, final String aName) {
                this.counts = aCounts;
                this.name = aName;
            }

            public String getName() {
                return this.name;
            }

            public int getPrice() {
                return this.counts;
            }
        }
        List<Product> productList = List.of(
                new Product(23, "potatoes"),
                new Product(14, "orange"),
                new Product(13, "lemon"),
                new Product(23, "bread"),
                new Product(13, "sugar"));
        //Reducing by Collect to List
        List<String> collectorCollection =
                productList.stream()
                        .map(Product::getName)
                        .collect(Collectors.toList());
        System.out.println(collectorCollection);
        //Reducing by Collect to String
        String listToString = productList.stream().map(Product::getName)
                .collect(Collectors.joining("@ ", "[", "]"));
        System.out.println(listToString);
        //Reducing by Collect ifPresent
        final Optional<Integer> reduce = productList.stream()
                .map(Product::getPrice)
                .reduce((a, b) -> a + b);
        reduce.ifPresent(System.out::println);
        //average
        double averagePrice = productList.stream()
                .collect(Collectors.averagingInt(Product::getPrice));
        System.out.println("average " + averagePrice);
        //summing
        int summingPrice = productList.stream()
                .mapToInt(Product::getPrice)
                .sum();
        //.collect(Collectors.summingInt(Product::getPrice));
        System.out.println("summing " + summingPrice);
        //statistics
        IntSummaryStatistics statistics = productList.stream()
                .collect(Collectors.summarizingInt(Product::getPrice));
        System.out.println(statistics);
    }

    @Test
    public void whenMapToFlat() {
        class Student {
            private String name;
            private Set<String> book;

            public void addBook(String book) {
                if (this.book == null) {
                    this.book = new HashSet<>();
                }
                this.book.add(book);
            }

            public void setName(final String aName) {
                this.name = aName;
            }

            public Set<String> getBook() {
                return book;
            }
        }
        Student obj1 = new Student();
        obj1.setName("mkyong");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");

        Student obj2 = new Student();
        obj2.setName("zilap");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");

        List<Student> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);

        List<String> catalog =
                list.stream()
                        .map(Student::getBook)      //Stream<Set<String>>
                        .flatMap(Collection::stream)   //Stream<String>
                        .distinct()
                        .collect(Collectors.toList());

        catalog.forEach(System.out::println);
    }

    @Test
    public void whenArrayStream() {
        int[] array = {1, 2, 3, 4, 5};
        //1. Stream<int[]>
        Stream<int[]> streamArray = Stream.of(array);
        //2. Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(Arrays::stream);
        intStream.forEach(System.out::println);

    }

    @Test
    public void whenCountAndFilter() {
        class Customer {
            private String name;
            private int points;

            public Customer(final String name, final int points) {
                this.name = name;
                this.points = points;
            }

            public final String getName() {
                return this.name;
            }

            public final int getPoints() {
                return this.points;
            }
        }
        Customer john = new Customer("John P.", 15);
        Customer sarah = new Customer("Sarah M.", 200);
        Customer charles = new Customer("Charles B.", 150);
        Customer mary = new Customer("Mary T.", 1);
        List<Customer> customers = List.of(john, sarah, charles, mary);
        long count = (long) customers.size();
        // long count = customers.stream().count();
        assertThat(count, is(4L));
        long countBigCustomers = customers
                .stream()
                .filter(c -> c.getPoints() > 100)
                .count();
        assertThat(countBigCustomers, is(2L));
        count = customers
                .stream()
                .filter(c -> c.getPoints() > 500)
                .count();
        assertThat(count, is(0L));
        count = customers
                .stream()
                .filter(c -> c.getPoints() > 10 && c.getName().startsWith("Charles"))
                .count();
        assertThat(count, is(1L));
    }

}


