package ru.job4j.lsp.stock;

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

    @Override
    public boolean add(Food food) {
        if (expiredTimeInPercents(food) > 75) {
            store.add(food);
            return true;
        }
        return false;
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
