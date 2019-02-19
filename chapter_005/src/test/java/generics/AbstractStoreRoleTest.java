package generics;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AbstractStoreRoleTest {
    private AbstractStore<Role> abstractStore = new RoleStore(4);
    private Role one;
    private Role two;
    private Role three;
    private Role four;

    @Before
    public void beforeAction() {
        one = new Role("1");
        two = new Role("2");
        three = new Role("3");
        four = new Role("4");

        this.abstractStore.add(one);
        this.abstractStore.add(two);
        this.abstractStore.add(three);
    }

    @Test
    public void addOK() {
        this.abstractStore.add(four);
        var result = this.abstractStore.findById("4");
        assertThat(result, is(four));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void addIfSizeOutNEE() {
        this.abstractStore.add(this.four);
        this.abstractStore.add(this.four);
    }

    @Test
    public void replaceOK() {
        this.abstractStore.replace("1", this.four);
        var result = this.abstractStore.findById("4");
        assertThat(result, is(this.four));
    }

    @Test(expected = NoSuchElementException.class)
    public void replaceByNotExistIdNEE() {
        this.abstractStore.replace("5", null);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeOK() {
        this.abstractStore.delete("1");
        var result = this.abstractStore.findById("1");
        assertThat(result, is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteIfNullOutOfSizeNEE() {
        this.abstractStore.delete("4");
    }

    @Test(expected = NoSuchElementException.class)
    public void deleteIfNoIdNEE() {
        this.abstractStore.delete("10");
    }

    @Test
    public void findByIdOK() {
        var result = this.abstractStore.findById("1");
        assertThat(result, is(one));
    }

    @Test(expected = NoSuchElementException.class)
    public void findIfIdOutSizeNEE() {
        this.abstractStore.findById("5");
    }

    @Test(expected = NoSuchElementException.class)
    public void findIfIdNullNEE() {
        this.abstractStore.findById("4");
    }

    @Test(expected = NoSuchElementException.class)
    public void findIfIdEmptyNEE() {
        this.abstractStore = new RoleStore(0);
        this.abstractStore.findById("0");
    }
}


