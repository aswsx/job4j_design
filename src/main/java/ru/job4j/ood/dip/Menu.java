package ru.job4j.ood.dip;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс - модель меню
 * @author Alex Gutorov
 * @version 1.6
 */
public class Menu {
    private final Map<String, Level> mainMenu;
    private Level lastLevel;

    public Menu() {
        this.mainMenu = new HashMap<>();
        this.lastLevel = null;
    }

    public boolean add(String name) {
        return mainMenu.put(name, createLevel(null, name)) == null;
    }

    public boolean add(String name, Action action) {
        var current = createLevel(null, name);
        current.setAction(action);
        return mainMenu.put(name, current) == null;
    }

    public boolean add(String parent, String name) {
        var parentLevel = mainMenu.get(parent);
        if (parentLevel == null) {
            throw new IllegalArgumentException();
        }
        return mainMenu.put(name, createLevel(parentLevel, name)) == null;
    }

    public boolean add(String parent, String name, Action action) {
        var parentLevel = mainMenu.get(parent);
        if (parentLevel == null) {
            throw new IllegalArgumentException();
        }
        var current = createLevel(parentLevel, name);
        current.setAction(action);
        return mainMenu.put(name, current) == null;
    }

    public boolean isSelect(String key) {
        var level = mainMenu.get(key);
        if (level != null && level.getAction() != null) {
            level.getAction().doAction();
            return true;
        }
        return false;
    }

    public String output() {
        var rsl = new StringBuilder();
        mainMenu.values().stream()
                .sorted(new LevelsComparator())
                .map(Level::getFullName)
                .forEach(str -> rsl.append(str).append(System.lineSeparator()));
        return rsl.toString();
    }

    private Level createLevel(Level parent, String name) {
        if (parent == null) {
            if (lastLevel == null) {
                lastLevel = Level.create(null, new UpperLevelMaker(), name);
            } else {
                lastLevel = Level.create(lastLevel, new InnerLevelMaker(), name);
            }
            return lastLevel;
        }
        return Level.create(parent, new NextLevelMaker(), name);
    }
}
