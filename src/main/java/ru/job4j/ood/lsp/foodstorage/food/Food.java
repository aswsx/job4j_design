package ru.job4j.ood.lsp.foodstorage.food;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс - модель продукта
 * Содержит конструктор, геттеры, сеттеры, в том числе сеттер, устанавливающий цену со скидкой
 * переопределенные методы equals, hashCode, toString
 */
public class Food {
    private String name;
    private final LocalDate expiryDate;
    private final LocalDate createDate;
    private double price;
    private final double discount;

    /**
     * Конструктор
     *
     * @param name       название продукта
     * @param expiryDate годен до
     * @param createDate дата производства
     * @param price      цена
     * @param discount   скидка
     */
    public Food(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscountPrice(double price) {
        this.price = (price * (1 - discount / 100));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food)) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0 && Double.compare(food.discount, discount)
                == 0 && Objects.equals(name, food.name) && Objects.equals(expiryDate, food.expiryDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }

    @Override
    public String toString() {
        return "Food{"
                + "Name='" + name + '\''
                + ", expiryDate=" + expiryDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
