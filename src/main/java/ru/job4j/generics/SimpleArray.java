package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Alex Gutorov (lextor78@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] dataList;
    private int size;

    public SimpleArray(int size) {
        this.size = size;
        this.dataList = new Object[size];
    }

    private void checkIndex(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Objects.checkIndex(index, size);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final Object[] data = dataList;
            private final int dataSize = size;
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < dataSize;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[point++];
            }
        };
    }

    private void add(T model) {
        if (size == dataList.length) {
            throw new IndexOutOfBoundsException();
        }
        dataList[size++] = model;
    }

    private void set(int index, T model) {
        checkIndex(index);
        dataList[index] = model;
    }

    private void remove(int index) {
        checkIndex(index);
        System.arraycopy(dataList, index++, dataList, index, size - index - 1);
        dataList[size - 1] = null;
        size--;
    }

    private T get(int index) {
        checkIndex(index);
        return (T) dataList[index];
    }
}
