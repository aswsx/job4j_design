package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        return elemAction(id, model);
    }

    @Override
    public boolean delete(String id) {
        return elemAction(id, null);
    }

    private boolean elemAction(String id, T model) {
        T found = findById(id);
        if (found == null) {
            return false;
        }
        mem.set(mem.indexOf(found), model);
        return true;
    }

    @Override
    public T findById(String id) {
        T rsl = null;
        for (T elem : mem) {
            if (elem.getId().equals(id)) {
                rsl = elem;
                break;
            }
        }
        return rsl;
    }
}