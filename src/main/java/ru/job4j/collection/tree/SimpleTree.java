package ru.job4j.collection.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.BiPredicate;

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

    public boolean isBinary() {
        BiPredicate<Node<E>, E> condition = (node, v) -> node.children.size() > 2;
        return findByPredicate(condition, null).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        BiPredicate<Node<E>, E> condition = (node, v) -> node.value.equals(v);
        return findByPredicate(condition, value);
    }

    private Optional<Node<E>> findByPredicate(BiPredicate<Node<E>, E> condition, E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el, value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}