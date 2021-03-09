package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] numbers;
    private int nextPoint;

    public EvenIt(final int[] numbers) {
        this.numbers = numbers;
        nextPoint = nextElement(0);
    }

    private int nextElement(int index) {
        int rsl = -1;
        for (int i = index; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    @Override
    public boolean hasNext() {
        return nextPoint != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer elem = numbers[nextPoint++];
        nextPoint = nextElement(nextPoint);
        return elem;
    }
}
