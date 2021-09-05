package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

public class SimpleArrayTest {

    private static SimpleArray<String> array;

    @Before
    public void createArray() {
        array = new SimpleArray<>();
    }

    @Test
    public void whenAddThenGet() {
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddNull() {
        SimpleArray<Integer> array = new SimpleArray<>();
        array.add(null);
        Integer rsl = array.iterator().next();
        assertNull(rsl);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }
}