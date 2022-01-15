package ru.job4j.ood.ocp;

import java.util.ArrayList;

/**
 * @author Alex Gutorov
 * @version 1.0
 *
 * Данный класс содержит примеры нарушения принципа OCP
 */
public class OCPBreakingExamples {
    /**
     * Класс нарушает принцип OCP посколько не имеет возможности расширения.
     * Для изменения функционала класса придется создавать новые методы или
     * изменять существующий.
     */
    static class OCPBreak1 {

        private int a;
        private int b;

        public OCPBreak1(int a, int b) {
            this.a = a;
            this.b = b;
        }

        private int sum(int a, int b) {
            return a + b;
        }
    }

    /**
     * Класс нарушает принцип OCP, поскольку метод возвращает конкретную реализацию ArrayList
     * интерфейса List. Это лишает возможности гибкого изменения программы
     * Взаимодействие должно осуществляться через абстракции, в данном случае через интерфейс List
     */
    static class OCPBreak2 {

        private ArrayList<Integer> arrayReturn() {
            ArrayList<Integer> array = new ArrayList<Integer>();
            array.add(1);
            array.add(2);
            array.add(3);
            return array;
        }
    }

    /**
     * В классе нарушен принцип OCP потому что при наследовании класса OCPBreak3 в классе OCPBreak4
     * наследуется состояние объекта предка.
     * Если бы изначально метод doSomething() был вынесен в интерфейс
     * public interface DoSomething {
     *     int doSomething();
     * }
     * проблем с расширением программы не возникло.
     */
    static class OCPBreak3 {
        private final int a;

        public OCPBreak3(int a) {
            this.a = a;
        }

        public int doSomething(int a) {
            return a * a;
        }
    }
    static class OCPBreak4 extends OCPBreak3 {

        public OCPBreak4(int a) {
            super(a);
        }

        @Override
        public int doSomething(int a) {
            return a + a;
        }
    }
}
