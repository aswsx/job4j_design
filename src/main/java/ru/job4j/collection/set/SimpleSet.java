package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {
    private final SimpleArray<T> set = new SimpleArray<>();

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            return true;
        } else if (value == null && !contains(null)) {
            set.add(null);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        for (T elem : this) {
            if (elem == null || elem.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}