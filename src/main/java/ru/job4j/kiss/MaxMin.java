package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

/**
 * Класс, реализующий компараторы для нахождения максимального и минимального элемента в исходном списке
 */
public class MaxMin {
    /**
     *
     * @param value исходный список элементов
     * @param comparator компаратор для сравнения элементов
     * @param <T> обобщенный тип возвращаемого значения
     * @return возврат найденного максимального элемента
     */
    public <T> T max(List<T> value, Comparator<T> comparator) {
      return valuesComparing(value, comparator);
    }

    /**
     *
     * @param value исходный список элементов
     * @param comparator компаратор для сравнения элементов
     * @param <T> обобщенный тип возвращаемого значения
     * @return возврат найденного минимального элемента
     */
    public <T> T min(List<T> value, Comparator<T> comparator) {
        return valuesComparing(value, comparator.reversed());
    }

    /**
     * Метод принимает список элементов, проходит его в цикле и с помощью матода compare сравнивает элементы
     * @param value исходный список элементов
     * @param comparator компаратор для сравнения элементов
     * @param <T> обобщенный тип возвращаемого значения
     * @return возврат найденного элемента
     */
    private static <T> T valuesComparing(List<T> value, Comparator<T> comparator){
        T rsl = value.get(0);
        for(T elem : value) {
            if(comparator.compare(rsl, elem) < 0) {
                rsl = elem;
            }
        }
        return rsl;
    }
}