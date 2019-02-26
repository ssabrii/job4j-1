package list.linkedlist;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

    }

    @Test
    public void deleteOK() {
        System.out.print("Before delete: ");
        for (int i = 0; i < this.list.getSize(); i++) {
            System.out.print(this.list.get(i) + " ");
        }
        System.out.println();
        Integer result = list.delete();
        System.out.print("After: ");
        for (int i = 0; i < this.list.getSize(); i++) {
            System.out.print(this.list.get(i) + " ");
        }
        assertThat(result, is(2));
        assertThat(list.get(0), is(2));
        assertThat(list.getSize(), is(2));
        //проверка удаления. возвращает 1. а не 2
        assertThat(list.delete(), is(1));
        //визуально.
        System.out.println();
        System.out.print("После удаления 2: ");
        for (int i = 0; i < this.list.getSize(); i++) {
            System.out.print(this.list.get(i) + " ");
        }

    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }
}
