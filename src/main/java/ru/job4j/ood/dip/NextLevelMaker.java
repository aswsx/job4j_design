package ru.job4j.ood.dip;

/**
 * Класс нижнего уровня меню
 */
public class NextLevelMaker implements MakeLevel {
    /**
     * Метод создает новый уровень меню
     *
     * @param prev   текущий уровень
     * @param name   имя текущего уровня
     * @param prefix префикс перед именем нового уровня
     * @return возвращаемый созданный уровень
     */
    @Override
    public Level make(Level prev, String name, String prefix) {
        var levelName = new StringBuilder(prefix);
        var number = prev.getChildren().size() + 1;
        var fullNumber = new StringBuilder(prev.getFullNumber())
                .append(number)
                .append(".");
        levelName.append(getPrefix(prev, prefix))
                .append(fullNumber)
                .append(" ")
                .append(name);
        Level current = new Level(prev, number, fullNumber.toString(), name, levelName.toString());
        prev.add(current);
        return current;
    }

    /**
     * Метод создает префикс для нового уровня, беря за основу передаваемый
     *
     * @param level  текущий уровень меню
     * @param prefix префикс текущего уровня уровня
     * @return возвращаемый новый префикс
     */
    private String getPrefix(Level level, String prefix) {
        var rsl = new StringBuilder();
        var current = level;
        while (current.getParent() != null) {
            rsl.append(prefix);
            current = current.getParent();
        }
        return rsl.toString();
    }
}