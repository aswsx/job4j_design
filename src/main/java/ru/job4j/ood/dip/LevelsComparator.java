package ru.job4j.ood.dip;

import java.util.Comparator;

/**
 * Класс компаратор для сортировки пунктов при формировании меню
 */
public class LevelsComparator implements Comparator<Level> {

    @Override
    public int compare(Level o1, Level o2) {
        return o1.getFullNumber().compareTo(o2.getFullNumber());
    }
}