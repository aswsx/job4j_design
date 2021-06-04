package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class
MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0; //Индекс строки
    private int column = 0; //индекс элемента строки

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == column) {
            // запускаем цикл, проверяем-
            // индекс строки меньше размера массива?
            // И длина текущей строки равна текущему индексу элемента в строке?
            row++; // ЕСЛИ ДА, шагаем в следующую строку
            column = 0; // индекс элемента строки обнуляем
            // и снова проверяем
        }
        return row < data.length; //ЕСЛИ НЕТ возвращаем булево значение-
                                  // индекс строки меньше размера массива
        // если меньше- true, равен - false
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}