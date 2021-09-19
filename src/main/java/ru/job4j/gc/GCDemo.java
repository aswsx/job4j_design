package ru.job4j.gc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GCDemo {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();
    private static final Logger LOG = LoggerFactory.getLogger(GCDemo.class.getName());

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();

        LOG.info("=== Environment state ===");
        LOG.info(String.format("Free: %d%n", freeMemory / MB));
        LOG.info(String.format("Total: %d%n", totalMemory / MB));
        LOG.info(String.format("Max: %d%n", maxMemory / MB));
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 10; i++) {
            new Person(i, "N" + i);
        }
        System.gc();
        info();
    }

}