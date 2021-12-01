package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактный класс-шаблон на основе которого будет создаваться класс
 * кеширования файлов из папки, базы данных и т.п.
 *
 * @param <K> ключ по которому заполняется кеш
 * @param <V> возвращаемое значение
 */
public abstract class AbstractCache<K, V> {
    public AbstractCache() {
    }

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * @param key ключ
     * @param value значение
     */
    public void put(K key, V value) {
        SoftReference<V> reference = new SoftReference<>(value);
        cache.put(key, reference);
    }

    /**
     * метод возвращает содержимое файла
     * происходит загрузка в кеш, если он пустой с помощью метода load() и возврат содержимого файла
     *
     * @param key ключ
     * @return возвращаемое содержимое файла
     */
    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    /**
     * Метод выполняет загрузку файла в кеш
     *
     * @param key ключ
     * @return возвращаемое значение
     */
    protected abstract V load(K key);
}