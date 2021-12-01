package ru.job4j.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс, в котором выполняется чтение файла из папки и запись его содержимого в кеш
 */
public class DirFileCache extends AbstractCache<String, String> {
    private static final Logger LOG = LoggerFactory.getLogger(DirFileCache.class.getName());
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Метод, читающий из указанного файла и записывающий в кеш     *
     *
     * @param key ключ (имя файла)
     * @return возвращаемое содержимое файла
     */
    @Override
    protected String load(String key) {
        String value = null;
        try {
            value = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            LOG.error("Wrong path... ", e);
        }
        return value;
    }
}