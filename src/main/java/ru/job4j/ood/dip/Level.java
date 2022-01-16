package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс - модель уровня меню
 */
public class Level {
    private final String name;
    private static final String PREFIX = "***";
    private final Level parent;
    private final int number;
    private final String fullNumber;
    private final String fullName;
    private Action action;
    private final List<Level> children;

    public Level(Level prev, int number, String fullNumber, String name, String fullName) {
        this.parent = prev;
        this.number = number;
        this.fullNumber = fullNumber;
        this.name = name;
        this.fullName = fullName;
        this.children = new ArrayList<>();
    }

    public static Level create(Level prev, MakeLevel creator, String name) {
        return creator
                .make(prev, name, PREFIX);
    }

    public Level getParent() {
        return parent;
    }

    public int getNumber() {
        return number;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFullNumber() {
        return fullNumber;
    }

    public List<Level> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void add(Level level) {
        children.add(level);
    }

    @Override
    public String toString() {
        return "Level{"
                + "name='" + fullName
                + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Level level = (Level) o;
        return Objects.equals(fullName, level.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
}

