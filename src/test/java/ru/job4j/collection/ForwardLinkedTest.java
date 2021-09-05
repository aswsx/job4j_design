package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class ForwardLinkedTest {
    private static ForwardLinked<Integer> linked;

    @Before
    public void createForwardLinked() {
        linked = new ForwardLinked<>();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddThenIter() {
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }

    @Test
    public void whenSize0ShouldReturnFalse() {
        assertFalse(linked.revert());
    }

    @Test
    public void whenSize1ShouldReturnFalse() {
        linked.add(1);
        assertFalse(linked.revert());
    }
}