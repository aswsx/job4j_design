package ru.job4j.collection.hash;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <K> ключ элемента массива *
 * @param <V> значение элемента массива
 *            Класс - реализация простой коллекции HashMap
 * @author Alex Gutorov
 * @version 1.0
 * <p>
 * tableLength; длина массива (максимально возможное число элементов)
 * elementCounter; счетчик элементов в массиве
 * tableCapacity; емкость массива (количество элементов, по достижению которого массив расширяется)
 * modCount = 0; счетчик модификаций для итератора
 * @see SimpleHashMap#iterator()
 * table; новый массив
 */
public class SimpleHashMap<K, V> implements Iterable<K> {
    private int tableLength;
    private int elementCounter;
    private int tableCapacity;
    private int modCount = 0;
    private Node<K, V>[] table;

    /**
     * Constructor
     * создание объекта с определенными значениями
     * tableLength размер массива. Исходное значение 16
     * loadFactor коэффициент предельной емкости(количества элементов) до расширения
     * table инициализация массива исходного размера
     */
    public SimpleHashMap() {
        tableLength = 16;
        float loadFactor = 0.75F;
        tableCapacity = (int) (loadFactor * tableLength);
        table = new Node[tableLength];
    }

    /**
     * @param key ключ элемента
     * @return возвращает вычисленный индекс элемента
     * Метод расчитывает индекс элемента массива по его ключу
     * int hashCode получение хэшкода ключа
     * int hash вычисление хэша путем битового сдвига и исключающего ИЛИ
     * hash & (tableLength - 1) вычисление индекса используя побитовое И
     */
    private int indexCalc(K key) {
        int hashCode = key.hashCode();
        int hash = hashCode ^ (hashCode >>> 16);
        return hash & (tableLength - 1);
    }

    /**
     * @param key   ключ элемента
     * @param value значение элемента
     * @return возвращает true если элемент добавлен в массив при условии что бакет массива пуст
     * или не содержит элемент с таким же ключом
     * Метод вставляет элемент в бакет массива.
     * При этом осуществляется проверка, что не превышена емкость. Если превышена, запускается метод,
     * расширяющий массив
     * @see SimpleHashMap#tableResize()
     * Если бакет пуста, в нее записывается элемент, инкрементируются счетчики элементов и изменения массива
     * Если бакет не пуст, проверяется соответствие ключей. Если ключи не равны, элемент не заменяется,
     * метод возвращает false
     * Если бакет не пуст и ключи элементов равны, заменяется значение существующего элемента новым,
     * инкрементируется счетчик изменений массива, счетчик элементов не изменяется
     */
    boolean insert(K key, V value) {
        if (elementCounter > tableCapacity) {
            tableResize();
        }
        int index = indexCalc(key);
        if (table[index] == null) {
            table[index] = new Node<>(key, value);
            modCount++;
            elementCounter++;
            return true;
        }
        Node<K, V> elem = table[index];
        if (!elem.getKey().equals(key)) {
            return false;
        }
        elem.setVal(value);
        modCount++;
        return true;
    }

    /**
     * @param key ключ элемента
     * @return возвращает элемент, полученный из бакета по ключу.
     * <p>
     * Метод проверяет не пустой ли бакет и не разные ли ключи у элемента в бакете и не разные ли ключи,
     * и возвращает либо null либо найденный элемент
     */
    V get(K key) {
        int index = indexCalc(key);
        if (table[index] == null || !table[index].getKey().equals(key)) {
            return null;
        }
        return table[index].getVal();
    }

    /**
     * @param key ключ элемента
     * @return возвращает true если элемент удален или false если бакет пуст или ключи не совпадают
     */
    boolean delete(K key) {
        int index = indexCalc(key);
        if (table[index] == null || !table[index].getKey().equals(key)) {
            return false;
        }
        table[index] = null;
        elementCounter--;
        modCount++;
        return true;
    }

    /**
     * Метод расширяет массив при достижении tableCapacity
     * размер увеличивается в два раза. Для этого применяется битовый сдвиг на один бит влево
     * Создается новый массив увеличенного размера, после чего все элементы из старого
     * переносятся в новый
     */
    private void tableResize() {
        Node<K, V>[] resizedTable = table;
        Node<K, V> el;
        modCount = 0;
        tableLength = tableLength << 1;
        tableCapacity = (int) (tableLength * 0.75F);
        table = new Node[tableLength];
        for (Node<K, V> node : resizedTable) {
            if (node != null) {
                el = node;
                this.insert(el.key, el.val);
            }
        }
    }

    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final Node<K, V>[] tempTable = table;
            private final int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = point; i < tempTable.length; i++) {
                    if (tempTable[i] != null) {
                        point = i;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (K) tempTable[point++];
            }
        };
    }

    /** класс NODE описывает ячейки массива
     *
     * @param <K> Ключ элемента массива
     * @param <V> Значение элемента массива
     */
    static class Node<K, V> {
        private final K key;
        private V val;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
        }
    }
}
