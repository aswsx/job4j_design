package ru.job4j.lsp;

import ru.job4j.lsp.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * В классе выполняется вычисление срока годности продуктов
 */
public class ExpiryChecker {
    /**
     * Метод вычисляет оставшийся срок годности продукта
     * lifeTime- срок хранения продукта с момента его производства
     * remain - оставшийся срок годности с текущей даты
     *
     * @param food продукт, для которого производится проверка
     * @return оставшийся срок годности, выраженный в % от полного срока хранения
     */
    public double expiredTimeInPercents(Food food) {
        double lifeTime = ChronoUnit.DAYS.between(food
                .getCreateDate(), food.getExpiryDate());
        double remain = ChronoUnit.DAYS.between(LocalDate
                .now(), food.getExpiryDate());
        return remain / lifeTime * 100;
    }
}
