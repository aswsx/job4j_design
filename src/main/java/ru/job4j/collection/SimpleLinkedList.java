package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size = 0;
    private int modCount = 0;

    private void checkIndex(int index) {
        Objects.checkIndex(index, size);
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        Node<E> tail = lastNode;
        if (tail == null || size == 0) {
            firstNode = newNode;
        } else {
            lastNode.nextElement = newNode;
        }
        lastNode = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node<E> rsl = firstNode;
        for (var i = 0; i < index; i++) {
            rsl = rsl.nextElement;
        }
        return rsl.currentElement;

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> point = firstNode;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = point.currentElement;
                point = point.nextElement;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        private final E currentElement;
        private Node<E> nextElement;

        public Node(E currentElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
        }
    }
}