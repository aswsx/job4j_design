package ru.job4j.collection;

public class Analyze {
    private int userQty = 0;
    private int modCount = 0;

    //TODO метод должен вычислять количество изменений в списке пользователей
    public Info diff(List<User> previous, List<User> current) {
        return null;
    }

    /**
     * Класс описывает модель пользователя
     */
    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /**
     * Класс опысывающий модель хранения информации о пользователях
     */
    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }
}