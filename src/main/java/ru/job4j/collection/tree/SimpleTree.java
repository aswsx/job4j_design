package ru.job4j.collection.tree;

import java.util.*;

class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> pNode = findBy(parent);
        if (findBy(child).isPresent() || pNode.isEmpty()) {
            return false;
        }
        pNode.get().children.add(new Node<>(child));
        return true;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}