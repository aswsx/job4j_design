package ru.job4j.lsp.foodStorage.food;

import java.time.LocalDate;

public class Butter extends Food {
    public Butter(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
