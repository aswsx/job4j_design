package ru.job4j.generics;

public class User extends Base {

    @Override
    public String getId() {
        return super.getId();
    }

    public User(String id) {
        super(id);
    }
}
