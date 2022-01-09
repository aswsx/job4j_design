package ru.job4j.lsp.stock;

import ru.job4j.lsp.ExpiryChecker;
import ru.job4j.lsp.food.Food;

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
     * заданным пределам от 25% до 75% (метод не изменяет цену) или от 0% до 25% (на продукт
     * устанавливается цена со скидкой)
     *
     * @param food продукт
     * @return true, если срок годности достаточен, иначе false
     */
    @Override
    public boolean accept(Food food) {
        if (check.expiredTimeInPercents(food) >= 25 && check.expiredTimeInPercents(food) <= 75) {
            return true;
        } else if (check.expiredTimeInPercents(food) > 0 && check.expiredTimeInPercents(food) < 25) {
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

