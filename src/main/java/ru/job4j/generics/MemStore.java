package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        BiConsumer<Integer, T> action = (index, T) -> mem.set(index, model);
        return elemAction(id, model, action);
    }

    @Override
    public boolean delete(String id) {
        BiConsumer<Integer, T> action = (index, model) -> mem.remove(index.intValue());
        return elemAction(id, null, action);
    }

    private boolean elemAction(String id, T model, BiConsumer<Integer, T> action) {
        int index = findIndex(id);
        if (index == -1) {
            return false;
        }
        action.accept(index, model);
        return true;
    }

    private int findIndex(String id) {
        for (var i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
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