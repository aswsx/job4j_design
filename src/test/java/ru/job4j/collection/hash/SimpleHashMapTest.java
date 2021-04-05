package ru.job4j.collection.hash;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {
    @Test
    public void whenInsertThenGet() {
        SimpleHashMap<Integer, Integer> table = new SimpleHashMap<>();
        table.insert(1, 1);
        table.insert(2, 2);
        table.insert(3, 3);
        table.insert(4, 4);
        assertThat(table.get(1), is(1));
        assertThat(table.get(2), is(2));
        assertThat(table.get(3), is(3));
        assertThat(table.get(4), is(4));
    }

    @Test
    public void whenInsertThenGetEmpty() {
        SimpleHashMap<Integer, Integer> table = new SimpleHashMap<>();
        table.insert(1, 1);
        table.insert(3, 3);
        assertThat(table.get(1), is(1));
        assertNull(table.get(2));
    }

    @Test
    public void whenInsertThenDelete() {
        SimpleHashMap<Integer, Integer> table = new SimpleHashMap<>();
        table.insert(1, 1);
        table.insert(3, 3);
        assertThat(table.get(1), is(1));
        assertTrue(table.delete(1));
        assertNull(table.get(1));
    }

    @Test
    public void whenDelete() {
        SimpleHashMap<Integer, Integer> table = new SimpleHashMap<>();
        assertFalse(table.delete(0));
    }

    @Test
    public void whenInsertThenReplace() {
        SimpleHashMap<Integer, Integer> table = new SimpleHashMap<>();
        table.insert(1, 1);
        assertTrue(table.insert(1, 3));
        assertThat(table.get(1), is(3));
    }

    @Test
    public void whenInsertThenReplaceSame() {
        SimpleHashMap<Integer, Integer> table = new SimpleHashMap<>();
        assertTrue(table.insert(2, 1));
        assertTrue(table.insert(2, 1));
    }

    @Test
    public void whenTableResize() {
        SimpleHashMap<Integer, Integer> table = new SimpleHashMap<>();
        for (int i = 0; i < 30; i++) {
            table.insert(i, i);
        }
        for (int i = 0; i < 30; i++) {
            assertThat(table.get(i), is(i));
        }
    }

    @Test
    public void whenHasNotNext() {
        SimpleHashMap<Integer, Integer> table = new SimpleHashMap<>();
        Iterator<Integer> it = table.iterator();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorExceptionWhenEmpty() {
        SimpleHashMap<Integer, Integer> table = new SimpleHashMap<>();
        Iterator<Integer> it = table.iterator();
        it.next();
    }
}