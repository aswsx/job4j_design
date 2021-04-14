package ru.job4j.collection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;

/**
 * Класс выполняет анализ изменения коллекций
 *
 * @author Alex Gutorov
 * @version 2.1
 */

public class Analize {
    /**
     * Метод принимает два списка пользователей и производит их сравнение.
     * Создается временная мапа, в которую заносятся элементы начального списка
     * Изначально выполняется поиск в новом списке элементов с совпадающими ключами.
     * Если в новом спсике элемент с новым ключом, значит элемент добавлен (added++,
     * элемент удаляется из временной мапы)
     * Если найдены элементы с совпадающим ключом, произвоодится их сравнение по именам
     * Если имена совпадают, изменений нет
     * Если имена не совпадают, элемент изменен (changed++, элемент удаляется из временной мапы)
     * В последнюю очередь размер итоговой временной мапы присваевается переменной deleted, поскольку
     * в ней остались только элементы, удаленные из начального списка.
     *
     * @param previous начальный список
     * @param current  конечный список с возможными изменениями
     * @return возвращает статистику выполненных изменений (количество удаленных, измененных и добавленных элементов)
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, User> prevMap = listToMap(previous);
        for (User user : current) {
            User currUser = prevMap.remove(user.id);
            if (currUser == null) {
                info.added++;
            } else if (!currUser.name.equals(user.name)) {
                info.changed++;
            }
        }
        info.deleted = prevMap.size();
        return info;
    }

    /**
     * Метод преобразует лист в мапу
     *
     * @param list входной лист
     * @return возвращает мапу
     */
    private Map<Integer, User> listToMap(List<User> list) {
        return list.stream()
                .distinct()
                .collect(Collectors
                        .toMap(
                                i -> i.id,
                                u -> u
                        )
                );
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return 31 * Integer.hashCode(id) + name.hashCode();
        }
    }

    /**
     * Класс опысывает модель хранения информации о пользователях
     */
    public static class Info {
        int added;
        int changed;
        int deleted;

        /**
         * Конструктор
         */
        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        /**
         * Конструктор
         */
        public Info() {
            this.added = 0;
            this.changed = 0;
            this.deleted = 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added && changed == info.changed && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return 31 * (31 * Integer.hashCode(added) + Integer.hashCode(changed)) + Integer.hashCode(deleted);
        }
    }
}