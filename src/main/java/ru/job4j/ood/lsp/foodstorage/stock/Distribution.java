package ru.job4j.ood.lsp.foodstorage.stock;

import ru.job4j.ood.lsp.foodstorage.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Интерфейс для классов, работающих с продуктами (Warehouse, Shop, Trash)
 */
public interface Distribution {
    /**
     * Метод добавляет продукт в соответствующее хранилище
     *
     * @param food - продукт
     */
    boolean add(Food food);

    boolean accept(Food food);

    /**
     * Метод возвращает коллекцию продуктов
     *
     * @return возвращаемая коллекция продуктов
     */
    List<Food> returnFoodList();

    /**
     * Метод вычисляет оставшийся срок годности продукта
     * lifeTime- срок хранения продукта с момента его производства
     * remain - оставшийся срок годности с текущей даты
     *
     * @param food продукт, для которого производится проверка
     * @return оставшийся срок годности, выраженный в % от полного срока хранения
     */
    default double expiredTimeInPercents(Food food) {
        double lifeTime = ChronoUnit.DAYS.between(food
                .getCreateDate(), food.getExpiryDate());
        double remain = ChronoUnit.DAYS.between(LocalDate
                .now(), food.getExpiryDate());
        return remain / lifeTime * 100;
    }
}
