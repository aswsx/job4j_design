package ru.job4j.collection;

public class SimpleStack<T> {

    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public boolean isEmpty() {
        return !linked.iterator().hasNext();
    }
}