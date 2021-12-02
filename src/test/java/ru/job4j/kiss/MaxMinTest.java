package ru.job4j.kiss;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class MaxMinTest {
    private MaxMin maxMin;
    private Comparator<Integer> comparator;
    private List<Integer> list;

    @Before
    public void init() {
        maxMin = new MaxMin();
        comparator = Integer::compareTo;
        list = Arrays.asList(1, 5, 2, 6, 8, 4, 3, 9, 7, 10);
    }

    @Test
    public void maxVal() {
        assertThat(maxMin.max(list, comparator), Is.is(10));
    }

    @Test
    public void minVal() {
        assertThat(maxMin.min(list, comparator), Is.is(1));
    }
}