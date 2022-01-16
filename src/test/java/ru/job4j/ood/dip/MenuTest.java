package ru.job4j.ood.dip;

import org.junit.Assert;
import org.junit.Test;

public class MenuTest {

    @Test
    public void whenMakeUpperLevel() {
        Menu menu = new Menu();
        menu.add("Задача 1");
        String expected = "1. Задача 1"
                + System.lineSeparator();
        Assert.assertEquals(menu.output(), expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongAddLevels() {
        Menu menu = new Menu();
        menu.add("Задача 1");
        menu.add("Задача 2", "Задача 1");
    }


    @Test
    public void whenMakeNewLevelOnCurrentLevel() {
        Menu menu = new Menu();
        menu.add("Задача 1");
        menu.add("Задача 1", "Задача 1.1");
        String expected = "1. Задача 1"
                + System.lineSeparator()
                + "***1.1. Задача 1.1"
                + System.lineSeparator();
        Assert.assertEquals(menu.output(), expected);
    }

    @Test
    public void whenMakeNextMenuLevel() {
        Menu menu = new Menu();
        menu.add("Задача 1");
        menu.add("Задача 2");
        String expected = "1. Задача 1"
                + System.lineSeparator()
                + "2. Задача 2"
                + System.lineSeparator();
        Assert.assertEquals(menu.output(), expected);
    }

    @Test
    public void whenMakeManyLevels() {
        Menu menu = new Menu();
        menu.add("Задача 1");
        menu.add("Задача 2");
        menu.add("Задача 1", "Задача 1.1");
        menu.add("Задача 1.1", "Задача 1.1.1", new StartAction());
        menu.add("Задача 1", "Задача 1.2");
        String expected = "1. Задача 1"
                + System.lineSeparator()
                + "***1.1. Задача 1.1"
                + System.lineSeparator()
                + "******1.1.1. Задача 1.1.1"
                + System.lineSeparator()
                + "***1.2. Задача 1.2"
                + System.lineSeparator()
                + "2. Задача 2"
                + System.lineSeparator();
        Assert.assertEquals(menu.output(), expected);
    }

    @Test
    public void whenChooseWithoutAction() {
        Menu menu = new Menu();
        menu.add("Задача 1");
        Assert.assertFalse(menu.isSelect("Задача 1"));
    }

    @Test
    public void whenChooseWithAction() {
        Menu menu = new Menu();
        menu.add("Задача 1", new StartAction());
        Assert.assertTrue(menu.isSelect("Задача 1"));
    }
}