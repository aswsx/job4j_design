package ru.job4j.gc;

public class MyGCDemo {

    public static void main(String[] args) {
        GCDemo.info();

        /**
         *Создаем 1000 новых объектов
         * если //  System.gc(); закомментирована
         *-Xmx2m -Xms2m ******************* метод не запускается
         *
         * -Xmx4m -Xms2m
         * 2021-09-19 21:51:02,755  INFO GCDemo:info:17 - === Environment state ===
         * 2021-09-19 21:51:02,769  INFO GCDemo:info:18 - Free: 1
         * 2021-09-19 21:51:02,769  INFO GCDemo:info:19 - Total: 4
         * 2021-09-19 21:51:02,769  INFO GCDemo:info:20 - Max: 4
         * 2021-09-19 21:51:02,787  INFO GCDemo:info:17 - === Environment state ===
         * 2021-09-19 21:51:02,787  INFO GCDemo:info:18 - Free: 0
         * 2021-09-19 21:51:02,789  INFO GCDemo:info:19 - Total: 4
         * 2021-09-19 21:51:02,789  INFO GCDemo:info:20 - Max: 4
         ***************************** Не вижу работу GC
         *
         * если 5000 объектов Exception: java.lang.OutOfMemoryError
         *
         * если System.gc(); раскомментирована
         * -Xmx4m -Xms2m
         * ***************************Exception: java.lang.OutOfMemoryError
         *
         * -Xmx4m -Xms4m
         *************************** Exception: java.lang.OutOfMemoryError
         *
         * -Xmx8m -Xms4m
         ***************************** GC работает, вижу удаляемые объекты
         * 2021-09-19 21:57:53,678  INFO GCDemo:info:18 - Free: 3
         * 2021-09-19 21:57:53,678  INFO GCDemo:info:19 - Total: 6
         * 2021-09-19 21:57:53,679  INFO GCDemo:info:20 - Max: 8
         * 2021-09-19 21:57:53,689  INFO User:finalize:36 - Removed 66 null
         * 2021-09-19 21:57:53,692  INFO User:finalize:36 - Removed 99 null
         * 2021-09-19 21:57:53,692  INFO User:finalize:36 - Removed 98 null
         * ...
         * 2021-09-19 21:57:53,703  INFO GCDemo:info:18 - Free: 3
         * 2021-09-19 21:57:53,703  INFO User:finalize:36 - Removed 49 null
         * 2021-09-19 21:57:53,703  INFO GCDemo:info:19 - Total: 7
         * 2021-09-19 21:57:53,703  INFO User:finalize:36 - Removed 48 null
         * 2021-09-19 21:57:53,703  INFO GCDemo:info:20 - Max: 8
         */
        for (int i = 0; i < 5000; i++) {
            new User(i);
        }
        //System.gc();
        GCDemo.info();
    }
}
