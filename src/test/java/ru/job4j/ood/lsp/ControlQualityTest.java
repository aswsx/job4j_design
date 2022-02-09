package ru.job4j.ood.lsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.lsp.foodstorage.food.Bread;
import ru.job4j.ood.lsp.foodstorage.food.Butter;
import ru.job4j.ood.lsp.foodstorage.food.Food;
import ru.job4j.ood.lsp.foodstorage.food.Milk;
import ru.job4j.ood.lsp.foodstorage.stock.*;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ControlQualityTest {
    List<Distribution> stockList;
    ControlQuality cont;

    @Before
    public void init() {
        this.stockList = List.of(new Warehouse(), new Shop(), new Trash());
        this.cont = new ControlQuality(stockList);
    }

    @Test
    public void whenProductAddedToWarehouse() {
        Food butter = new Butter("Масло Топленое", LocalDate.now().plusMonths(8),
                LocalDate.now().minusMonths(2),
                130, 10);
        cont.distribute(butter);
        Distribution warehouse = stockList.get(0);
        assertThat(warehouse.returnFoodList(), is(List.of(butter)));
    }

    @Test
    public void whenProductAddedToShop() {
        Food bread = new Bread("Хлеб Столичный", LocalDate.now().plusMonths(1),
                LocalDate.now().minusMonths(1),
                120, 20);
        cont.distribute(bread);
        Distribution shop = stockList.get(1);
        assertThat(shop.returnFoodList(), is(List.of(bread)));
    }

    @Test
    public void whenProductAddedToTrash() {
        Food bread = new Bread("Хлеб Бородинский", LocalDate.now(),
                LocalDate.now().minusMonths(1),
                130, 20);
        cont.distribute(bread);
        Distribution trash = stockList.get(2);
        assertThat(trash.returnFoodList(), is(List.of(bread)));
    }

    @Test
    public void whenProductAddedToShopAndDiscount() {
        Food milk = new Milk("Молоко Домик в деревне", LocalDate.now().plusDays(10),
                LocalDate.now().minusMonths(1),
                130, 20);
        cont.distribute(milk);
        Distribution shop = stockList.get(1);
        assertThat(shop.returnFoodList(), is(List.of(milk)));
    }

    @Test
    public void whenProductAddedToShopAndDiscountAndThenResort() {
        Food milk = new Milk("Молоко Домик в деревне", LocalDate.now().plusDays(10),
                LocalDate.now().minusMonths(1),
                130, 20);
        cont.distribute(milk);
        Distribution shop = stockList.get(1);
        assertThat(shop.returnFoodList(), is(List.of(milk)));
        milk.setExpiryDate(LocalDate.of(2022, 1, 14));
        cont.distribute(milk);
        cont.resort();
        shop = stockList.get(2);
        assertThat(shop.returnFoodList(), is(List.of(milk)));
    }
}
