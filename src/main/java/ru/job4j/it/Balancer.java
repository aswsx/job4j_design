package ru.job4j.it;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        CyclicIterator<ArrayList<Integer>> iterator = new CyclicIterator<>(nodes);
        while (iterator.hasNext() && source.hasNext()) {
            List<Integer> node = iterator.next();
            node.add(source.next());
        }
    }
}