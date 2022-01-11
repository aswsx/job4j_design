package ru.job4j.lsp.foodstorage.stock;

import ru.job4j.lsp.foodstorage.food.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс - витрина магазина. В него попадают продукты со сроком годности от 75% до 25% по обычной цене
 * и от 25% до 0% по цене со скидкой
 */
public class Shop implements Distribution {
    /**
     * Временное хранилище продукта
     */
    private final List<Food> store = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        if (accept(food)) {
            store.add(food);
            return true;
        }
        return false;
    }

    @Override
    public boolean accept(Food food) {
        if (expiredTimeInPercents(food) >= 25 && expiredTimeInPercents(food) <= 75) {
            return true;
        } else if (expiredTimeInPercents(food) > 0 && expiredTimeInPercents(food) < 25) {
            food.setDiscountPrice(food.getPrice());
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

