package ru.job4j.lsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.lsp.food.Bread;
import ru.job4j.lsp.food.Butter;
import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.food.Milk;
import ru.job4j.lsp.stock.Distribution;
import ru.job4j.lsp.stock.Shop;
import ru.job4j.lsp.stock.Trash;
import ru.job4j.lsp.stock.Warehouse;

import java.time.LocalDate;
import java.time.Month;
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
        Food butter = new Butter("Масло Топленое", LocalDate.of(2022, Month.NOVEMBER,
                10), LocalDate.of(2021, Month.NOVEMBER, 5),
                130, 10);
        cont.distribute(butter);
        Distribution warehouse = stockList.get(0);
        assertThat(warehouse.returnFoodList(), is(List.of(butter)));
    }

    @Test
    public void whenProductAddedToShop() {
        Food bread = new Bread("Хлеб Столичный", LocalDate.of(2022, Month.JANUARY,
                15), LocalDate.of(2022, Month.JANUARY, 5),
                120, 20);
        cont.distribute(bread);
        Distribution shop = stockList.get(1);
        assertThat(shop.returnFoodList(), is(List.of(bread)));
    }

    @Test
    public void whenProductAddedToTrash() {
        Food bread = new Bread("Хлеб Бородинский", LocalDate.of(2022, Month.JANUARY,
                9), LocalDate.of(2022, Month.JANUARY, 4),
                130, 20);
        cont.distribute(bread);
        Distribution trash = stockList.get(2);
        assertThat(trash.returnFoodList(), is(List.of(bread)));
    }

    @Test
    public void whenProductAddedToShopAnddiscount() {
        Food milk = new Milk("Молоко Домик в деревне", LocalDate.of(2022, Month.JANUARY,
                10), LocalDate.of(2021, Month.NOVEMBER, 5),
                130, 20);
        cont.distribute(milk);
        Distribution shop = stockList.get(1);
        assertThat(shop.returnFoodList(), is(List.of(milk)));
    }
}
