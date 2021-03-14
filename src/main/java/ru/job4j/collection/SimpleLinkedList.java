package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements Iterable<E> {
    private final Node<E> firstNode = null;
    private Node<E> lastNode;
    private int size = 0;
    private int modCount = 0;

    private void checkIndex(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Objects.checkIndex(index, size);
    }

    private void add(E value) {
        Node<E> newNode = new Node<E>(lastNode, value, null);
        lastNode = newNode;
        size++;
        modCount++;
    }

    private E get(int index) {
        checkIndex(index);
        Node<E> rsl = null;
        if (index == 0) {
            return firstNode.currentElement;
        }
        if (index + 1 == size) {
            return lastNode.currentElement;
        }
        for (int i = 0; i < index; i++) {
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
        private E currentElement;
        private Node<E> nextElement;

        public Node(Node<E> prevElement, E currentElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
        }
    }
}
