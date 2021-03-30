package ru.job4j.collection.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddSeq() {
        Set<String> set = new SimpleSet<>();
        assertTrue(set.add("A"));
        assertTrue(set.add("B"));
        assertTrue(set.add("C"));
        assertTrue(set.add("D"));
    }

    @Test
    public void whenAddSeqAndContains() {
        Set<String> set = new SimpleSet<>();
        assertTrue(set.add("A"));
        assertTrue(set.add("B"));
        assertTrue(set.add("C"));
        assertFalse(set.contains("D"));
        assertTrue(set.contains("C"));
        assertTrue(set.contains("B"));
        assertTrue(set.contains("A"));
    }

}