package ru.job4j.isp.espex;

public class ISPBreakingExamples {
    /**
     * 1. Интерфейс описывает каркас здания
     * В этом интерфейсе нарушается принцип ISP, поскольку предусматривает и наличие жилых помещений
     * и офисных помещений и лифтов
     */
    public interface Building {
        void apartments();

        void offices();

        void elevators();
    }

    /**
     * В данном классе создается административное здание
     * Поскольку в таком здании не предусмотрены жилые помещения, соответсвующий метод,
     * который приходится имплементировать из интерфейса тут лишний. Его надо вынести
     * в отдельный интерфейс.
     */
    private static class AdministrativeBuilding implements Building {
        @Override
        public void apartments() {
            throw new IllegalStateException("No apartments");
        }

        @Override
        public void offices() {
            System.out.println("There Are many offices");

        }

        @Override
        public void elevators() {
            System.out.println("There are many elevators");
        }
    }

    /**
     * 2. Интерфейс описывает каркас пекарни
     * Интерфейс нарушает принцип ISP, поскольку сочетает в себе слишком много
     * возможных компонентов для выпечки
     */
    public interface Bakery {
        void flour();

        void raisin();

        void poppy();

        /**
         * Пекарня, выпекающая обычный хлеб
         * Методы, добавляющие изюм и мак лишние, но их приходится имплементировать
         * из интерфейса
         */
        class Bread implements Bakery {

            @Override
            public void flour() {
                System.out.println("Для хлеба нужна мука");
            }

            @Override
            public void raisin() {
                throw new IllegalStateException("Изюм не нужен");
            }

            @Override
            public void poppy() {
                throw new IllegalStateException("мак не нужен");
            }
        }

        /**
         * Кулинария, выпекающая булочки с маком
         * Тут пришлось добавить лишний метод, добавляющий изюм
         */
        class PoppyBun implements Bakery {

            @Override
            public void flour() {
                System.out.println("Мука нужна");
            }

            @Override
            public void raisin() {
                throw new IllegalArgumentException("Изюм не нужен");
            }

            @Override
            public void poppy() {
                System.out.println("Мак нужен");
            }
        }

    }

    /**
     * Интерфейс- каркас электронных гаджетов
     */
    public interface Gadget {
        void call();

        void photo();

        void music();
    }

    /**
     * Класс смартфон
     * Тут все хорошо, смартфон может и звонить и снимать фото и проигрывать музыку
     */
    class Smartphone implements Gadget {

        @Override
        public void call() {
            System.out.println("Can call");
        }

        @Override
        public void photo() {
            System.out.println("Can take photo");
        }

        @Override
        public void music() {
            System.out.println("Can play music");
        }
    }

    /**
     * Класс- обычный телефон
     * Тут добавлено два лишних метода, поскольку обычный телефон не может снимать фото
     * и проигрывать музыку
     */
    class Phone implements Gadget {

        @Override
        public void call() {
            System.out.println("Can call");
        }

        @Override
        public void photo() {
            throw new IllegalArgumentException("Can't take photo");
        }

        @Override
        public void music() {
            throw new IllegalArgumentException("Can't play music");
        }
    }
}
