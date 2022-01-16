package ru.job4j.ood.dip;

/**
 * Класс создающий новый подпункт меню на текущем уровне
 */
public class InnerLevelMaker implements MakeLevel {
    @Override
    public Level make(Level level, String name, String prefix) {
        var levelName = new StringBuilder();
        var number = level.getNumber() + 1;
        var fullNumber = number + ".";
        levelName.append(fullNumber).append(" ").append(name);
        return new Level(level, number, fullNumber, name, levelName.toString());
    }
}