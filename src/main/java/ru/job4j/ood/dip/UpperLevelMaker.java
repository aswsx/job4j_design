package ru.job4j.ood.dip;

/**
 * Класс родительского уровня меню
 */
public class UpperLevelMaker implements MakeLevel {
    @Override
    public Level make(Level prev, String name, String prefix) {
        return new Level(null, 1, "1.", name, "1. " + name);
    }
}
