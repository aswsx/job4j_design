package ru.job4j.gc.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Класс WeakDemo
 *
 * @author Alex Gutorov
 * @version 1.0
 * <p>
 * Объекты, на которые ссылаются слабые ссылки удаляются сразу, если на них нет сильных или безопасных ссылок.
 * Данный тип ссылок служит для реализации структур для которых у одного значения типа может быть только
 * один объект, например пул строк и объекты чаще всего используется всего один раз,
 * т.е. сохранили-получили-забыли.
 */
public class WeakDemo {

    public static void main(String[] args) throws InterruptedException {
        example1();
        example2();
        example3();
    }

    /**
     * В первом методе за'null'ение сильной ссылки приводит к удалению объекта
     *  и мы его также уже не можем получить по слабой ссылке.
     * @throws InterruptedException
     */
    private static void example1() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed! example1");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get() + " example1");
        System.out.println();
    }

    /**
     * Во втором методе мы создаем объект вообще без сильной ссылки.
     * При вызове сборщика мусора они все удаляются.
     * @throws InterruptedException
     */
    private static void example2() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new WeakReference<Object>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Removed! example2");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     *Все типы ссылок, за исключением сильных, в Java являются наследниками класса Reference.
     * Все его наследники всегда попадают в ReferenceQueue, это может происходить как явно
     * (мы можем задать свою очередь) или неявно (когда мы не задаем). В нее попадают ссылки
     * тех объектов, которые уже помечены на удаление.     *
     * Особенности ссылок WeakReference и PhantomReference связаны с применением очереди ссылок.
     * Что касается особенности WeakReference, так это то, что когда на объект уже нет сильных
     * или безопасных ссылок происходит за'null'ение слабой ссылки, как в примере выше.
     * Далее WearReference будет помещена в очередь ReferenceQueue и мы можем пока объект
     * не удален физически получить его из этой очереди.
     * -- В таком после удаления объект временно можно получить из ReferenceQueue
     * -- Но если закомментируем явный вызов сборщика мусора, то мы увидим, что в очереди
     * еще нет ничего, т.к. само удаление еще не произошло
     * @throws InterruptedException
     */
    private static void example3() throws InterruptedException {
        System.out.println();
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed! example3");
            }
        };
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weak = new WeakReference<>(object, queue);
        object = null;

        System.gc();

        TimeUnit.SECONDS.sleep(3);
        System.out.println("from link example3 " + weak.get());
        System.out.println("from queue example3 " + queue.poll());
    }
}