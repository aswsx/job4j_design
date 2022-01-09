package ru.job4j.lsp.stock;

import ru.job4j.lsp.food.Food;

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
    void add(Food food);

    /**
     * Метод проверяет соответствует ли срок годности продукта текущему месторасположению
     *
     * @param food продукт
     * @return результат проверки срока годности
     */
    boolean accept(Food food);

    /**
     * Метод возвращает коллекцию продуктов
     *
     * @return возвращаемая коллекция продуктов
     */
    List<Food> returnFoodList();
}
