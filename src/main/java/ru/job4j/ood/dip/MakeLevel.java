package ru.job4j.ood.dip;

/**
 * Интерфейс - модель ноды
 */
public interface MakeLevel {
    Level make(Level current, String name, String prefix);
}
