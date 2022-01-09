package ru.job4j.lsp.stock;

import ru.job4j.lsp.ExpiryChecker;
import ru.job4j.lsp.food.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс - временный склад, в нем хранятся свежие продукты, со сроком годности более 75%
 */
public class Warehouse implements Distribution {
    /**
     * Временное хранилище продукта
     */
    private final List<Food> store = new ArrayList<>();
    /**
     * Объект класса ExpiryChecker, позволяющий получить доступ к методу expiredTimeInPercents для
     * вычисления срока хранения
     */
    final ExpiryChecker check = new ExpiryChecker();

    @Override
    public void add(Food food) {
        store.add(food);
    }

    /**
     * В методе выполняется проверка, соответствует ли оставшийся срок годности продукта
     * заданным пределам от 75% до 100%
     *
     * @param food продукт
     * @return true, если срок годности достаточен, иначе false
     */
    @Override
    public boolean accept(Food food) {
        return check.expiredTimeInPercents(food) > 75;
    }

    /**
     * Метод возвращает содержимое хранилища и после этого выполняет очистку хранилища от содержимого
     *
     * @return содержимое листа
     */
    @Override
    public List<Food> returnFoodList() {
        List<Food> list = new ArrayList<>(store);
        store.clear();
        return list;
    }
}
