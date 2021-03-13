package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author Alex Gutorov (lextor78@gmail.com)
 * @version $Id$
 * @since 0.1
 */

public class SimpleArray<T> implements Iterable<T> {
    //TODO Внутри контейнер должен базироваться на массиве Object[] container.
    private Object[] container;
    private int size = 0;
    //TODO Количество изменений - это показатель, который нам говорит, сколько
    // раз коллекция была изменена с момента ее создания
    private int modCount;

    //TODO В методах, где используется индекс нужно делать валидацию.
    // Индекс должен находиться в рамках добавленных элементов
    private void checkIndex(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Objects.checkIndex(index, size);
    }

    public SimpleArray() {
        this.container = new Object[10];
    }

    //TODO Когда элементов становится больше чем ячеек в массиве ArrayList создает
    // новый массив с большим размером.Контейнер должен быть динамическим, т.е. при
    // полном заполнении увеличиваться
    public void checkLength(int newLength) {
        int currentLength = container.length;
        if (newLength == currentLength) {
            int changedLength = (currentLength * 3 / 2) + 1;
            Object[] newDataList = new Object[changedLength];
            System.arraycopy(container, 0, newDataList, 0, size);
            container = newDataList;
        }
    }

    //TODO В методах, где используется индекс нужно делать валидацию.
    // Индекс должен находиться в рамках добавленных элементов
    public T get(int index) {
        checkIndex(index);
        modCount++;
        return (T) container[index];
    }

    //TODO Количество элементов - это показатель, который нам говорит, сколько элементов
    // в коллекции на данный момент. Переменную, которая будет отвечать за это число можно
    // применять для размещения элементов (если говорить про динамический массив).
    public void add(T model) {
        checkLength(size + 1);
        modCount++;
        container[size++] = model;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {
            private final Object[] data = container;
            private final int dataSize = size;
            private int point = 0;
            private final int expectedModCount = modCount;

            //TODO Итератор должен реализовывать fail-fast поведение, т.е. если
            // с момента создания итератора в коллекцию добавили новый элемент,
            // итератор должен кидать ConcurrentModificationException.
            // ConcurrentModificationException. Относится ко второму показателю.
            // Чтобы кинуть это исключение заводят отдельную переменную в итераторе
            // expectedModCount = modCount и проверяют условие if (expectedModCount != modCount)
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < dataSize;
            }

            //TODO  NoSuchElementException. Относиться к первому показателю. Тут просто.
            // Если итератор "уперься", т.е. нет больше элементов, а клиент вызвал этот метод,
            // то этим исключение мы ему подчеркиваем, что элементов больше нет.
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[point++];
            }
        };
    }
}