package ru.job4j.ood.dip.dipex;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Класс содержит примеры нарушения принципа DIP
 */
class Dipex {
    /**
     * Класс - модель детали
     */
    static class Detail {
        private String name;
        private String article;

        public Detail(String name, String article) {
            this.name = name;
            this.article = article;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArticle() {
            return article;
        }

        public void setArticle(String article) {
            this.article = article;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Detail)) {
                return false;
            }
            Detail detail = (Detail) o;
            return Objects.equals(name, detail.name) && Objects.equals(article, detail.article);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, article);
        }

        @Override
        public String toString() {
            return "Detail{"
                    + "name='" + name + '\''
                    + ", article='" + article + '\''
                    + '}';
        }
    }

    /**
     * Класс - склад деталей
     * <p>
     * 1. Данный класс нарушает принцип DIP поскольку его реализация напрямую зависит от
     * поля stock, типа Map.
     * Для устарнения нарушения необходимо создать интерфейс для выделения абстракции для хранения.
     * <p>
     * <p>
     * 2. Данный класс нарушает принцип DIP поскольку его реализация напрямую зависит от
     * консольного вывода.
     * Для устранения нарушения необходимо эту зависимость сделать абстракцией, например
     * использовать логгер
     * private static final Logger LOG = LoggerFactory.getLogger(Store.class.getName());
     * LOG.ingo ("Деталь добавлена на склад");
     * <p>
     * 3. Данный класс нарушает принцип DIP, поскольку его реализация напрямую зависит от
     * входного параметра, от Детали (Detail).
     */
    class Store {
        private Map<String, String> stock = new HashMap<>();

        public boolean addToStock() {
            stock.put("Деталь1", "12345");
            System.out.println("Деталь добавлена на склад");
            return true;
        }
    }
}
