package ru.job4j.design.srp.srpex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class SRPBreakingExamples {

    private static final Logger LOG = LoggerFactory.getLogger(SRPBreakingExamples.class.getName());

    /**
     * Метод производит арифметические операции над числами.
     * <p>
     * В этом методе явное нарушение принципа SRP. Оно заключается в том, что метод
     * выполняет четыре арифметические операции сразу. Если возникнет необходимость выполнить одну
     * конкретную операцию, придется писать отдельный метод для этого. Для решения этой проблемы необходимо
     * выполнение каждой арифметической операции вынести в отдельные методы.
     *
     * @param a число, передаваемое в метод для вычисления
     * @param b число, передаваемое в метод для вычисления
     * @return результат вычисления
     */
    public String calculator(int a, int b) {
        int sum = a + b;
        int subtract = a - b;
        int multiply = a * b;
        int divide = a / b;
        return String.format("сумма чисел %d, разность чисел %d, " +
                "произведение чисел %d, частное чисел %d", sum, subtract, multiply, divide);
    }

    /**
     * Класс, описывающий модель пользователя.
     * В данном классе наблюдается нарушение принципа SRP. В классе создается и инициализируется
     * объект User и одновременно с этим создается подключение к базе данных и добавление объектов
     * в базу данных. Методы, ответственные за базу данных должны быть вынесены в отдельный класс
     */
    public static class User {

        private Connection connection;
        String name;
        int age;
        int id;

        public User() throws SQLException {
            initConnection();
        }

        public void initConnection() throws SQLException {
            var url = "jdbc:postgresql://localhost:5432/postgres";
            var login = "postgres";
            var password = "password";
            connection = DriverManager.getConnection(url, login, password);
        }

        public User insert(User user) {
            try (PreparedStatement statement =
                         connection.prepareStatement("insert into users(name, age) values (?, ?)",
                                 Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getName());
                statement.setInt(2, user.getAge());
                statement.execute();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            User user = (User) o;
            return age == user.age && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    /**
     * Класс преобразует число
     * В данном классе выполняется считывание числа, преобразование числа, вывод результата.
     * Налицо нарушение принципа SRP.
     */
    public static class Transformer {
        Scanner scan = new Scanner(System.in);
        int digit = scan.nextInt();
        public void transform() {
            String result = String.valueOf((int) Math.exp(digit));
            LOG.info(result);
        }
    }

    public static void main(String[] args) {
        SRPBreakingExamples example = new SRPBreakingExamples();
        String rsl = example.calculator(4, 6);
        LOG.info(rsl);
    }
}
