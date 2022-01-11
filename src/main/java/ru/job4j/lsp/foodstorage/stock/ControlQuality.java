package ru.job4j.lsp.foodstorage.stock;

import ru.job4j.lsp.foodstorage.food.Food;

import java.util.List;

/**
 * Класс, распределяющий продукты по хранилищам в соответствии со сроком годности
 */
public class ControlQuality {
    /**
     * Лист- временное хранилище продукта
     */
    private final List<Distribution> stock;

    /**
     * Конструктор
     *
     * @param stock временное хранилище продукта
     */
    public ControlQuality(List<Distribution> stock) {
        this.stock = stock;
    }

    /**
     * Метод, выполняющий размещение продукта в соответствии с результатом, полученным от метода accept
     * Если он вернул true, выполняется запись продукта в постоянное хранилище (Shop, Warehouse или Trash)
     *
     * @param food продукт
     */
    public void distribute(Food food) {
        for (Distribution dist : stock) {
            dist.add(food);
        }
    }
}
