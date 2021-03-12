package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    @Test(expected = NoSuchElementException.class)
    public void noNextElementThenException() {
        SimpleArray<Integer> data = new SimpleArray<>(1);
        data.add(1);
        Iterator<Integer> it = data.iterator();
        it.next();
        it.next();
    }

    @Test
    public void whenNextElement() {
        SimpleArray<Integer> data = new SimpleArray<>(3);
        data.add(1);
        data.add(2);
        Iterator<Integer> it = data.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenHasNextAndAddElementThenTrue() {
        SimpleArray<Integer> data = new SimpleArray<>(2);
        data.add(1);
        data.add(2);
        Iterator<Integer> it = data.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddNewThenException() {
        SimpleArray<Integer> data = new SimpleArray<>(1);
        data.add(1);
        data.add(2);
    }

    @Test
    public void whenSetNew() {
        SimpleArray<Integer> data = new SimpleArray<>(2);
        data.add(1);
        data.add(2);
        data.set(0, 8);
        data.set(1, 9);
        assertThat(data.get(0), is(8));
        assertThat(data.get(1), is(9));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetNewThenException() {
        SimpleArray<Integer> data = new SimpleArray<>(2);
        data.add(1);
        data.add(2);
        data.set(3, 5);
    }

    @Test
    public void whenRemoveElement() {
        SimpleArray<Integer> data = new SimpleArray<>(5);
        data.add(1);
        data.add(2);
        data.add(3);
        data.remove(1);
        assertThat(data.get(0), is(1));
        assertThat(data.get(1), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveNewThenException() {
        SimpleArray<Integer> data = new SimpleArray<>(2);
        data.add(1);
        data.add(2);
        data.remove(3);
    }

    @Test
    public void whenGetElement() {
        SimpleArray<Integer> data = new SimpleArray<>(2);
        data.add(1);
        data.add(2);
        assertThat(data.get(0), is(1));
        assertThat(data.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetThenException() {
        SimpleArray<Integer> data = new SimpleArray<>(2);
        data.add(1);
        data.add(2);
        data.get(4);
    }
}

