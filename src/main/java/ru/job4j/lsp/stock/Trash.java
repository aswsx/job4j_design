package ru.job4j.lsp.stock;

import ru.job4j.lsp.food.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс - мусорная корзина. В него попадают просроченные продукты, со сроком годности <= 0
 */
public class Trash implements Distribution {
    /**
     * Временное хранилище продукта
     */
    private final List<Food> store = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        if (expiredTimeInPercents(food) <= 0) {
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
