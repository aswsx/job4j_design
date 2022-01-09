package ru.job4j.lsp.stock;

import ru.job4j.lsp.ExpiryChecker;
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
     * В методе выполняется проверка, не превышен ли срок хранения продукта
     *
     * @param food продукт
     * @return true, если срок годности превышен, иначе false
     */
    @Override
    public boolean accept(Food food) {
        return check.expiredTimeInPercents(food) <= 0;
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
